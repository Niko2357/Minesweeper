import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Visual extends JFrame {

    protected static final int SIZE = 10;
    protected static final int MINES = 10;

    protected JButton[][] buttons;
    protected boolean[][] mines;

    public Visual() {
        setTitle("TNT Sweeper");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));
        setLocationRelativeTo(null);

        buttons = new JButton[SIZE][SIZE];
        mines = new boolean[SIZE][SIZE];

        makeButtons();
        placeMine();
        setVisible(true);
    }

    /**
     * Builds the board of this game with buttons.
     */
    public void makeButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                button.setMargin(new Insets(0, 0, 0, 0));
                button.addActionListener(new ButtonWorks(i, j));
                buttons[i][j] = button;
                add(button);
            }
        }
    }

    /**
     * Places mines on playing board.
     */
    public void placeMine() {
        Random rnd = new Random();
        int placed = 0;
        while (placed < MINES) {
            int row = rnd.nextInt(SIZE);
            int column = rnd.nextInt(SIZE);
            if (!mines[row][column]) {
                mines[row][column] = true;
                placed++;
            }
        }
    }

    /**
     * Reveals all mines on playing field.
     */
    public void revealM() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mines[i][j]) {
                    buttons[i][j].setText("X");
                }
            }
        }
    }

    /**
     * This method counts number of mines around one box so that the bow can have number of mines displayed on it.
     * @param row    height, location of the box
     * @param column  width, location of the box
     * @return  number of mines around one box
     */
    public int count(int row, int column) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int theRow = row + i;
                int theColumn = column + j;
                if (theRow >= 0 && theRow < SIZE && theColumn >= 0 && theColumn < SIZE && mines[theRow][theColumn]) {
                    count++;
                }
            }
        }
        return count;
    }

    private class ButtonWorks implements ActionListener {
        private int row;
        private int column;


        public ButtonWorks(int row, int column) {
            this.row = row;
            this.column = column;
        }

        /**
         * Gets information from the cell. If the cell hides mine, all mines are revealed and game ends.
         * Otherwise, the game continues.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (mines[row][column]) {
                button.setText("X");
                revealM();
                JOptionPane.showMessageDialog(Visual.this, "Game Over!");
                System.exit(0);
            } else {
                revealEmpty(row, column);
                int count = count(row, column);
                if (count > 0) {
                    button.setText(String.valueOf(count));
                } else {
                    button.setText("");
                }
            }

            button.setEnabled(false);
        }

        /**
         * Reveals all empty box. By empty meaning with no number on them. Having no mines around them.
         * @param row height of cell
         * @param column width of cell
         */
        public void revealEmpty(int row, int column){
            if (row < 0 || row >= SIZE || column < 0 || column >= SIZE || !buttons[row][column].isEnabled())
                return;

            int count = count(row, column);
            if (count > 0) {
                buttons[row][column].setText(String.valueOf(count));
                buttons[row][column].setEnabled(false);
                return;
            }

            buttons[row][column].setEnabled(false);
            for (int theRow = -1; theRow <= 1; theRow++) {
                for (int theColumn = -1; theColumn <= 1; theColumn++) {
                    revealEmpty(row + theRow, column + theColumn);
                }
            }
        }
    }
}
