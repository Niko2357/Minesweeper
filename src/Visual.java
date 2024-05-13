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


        buttons = new JButton[SIZE][SIZE];
        mines = new boolean[SIZE][SIZE];

        makeButtons();
        placeMine();
        setVisible(true);
    }

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

    public void revealM() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mines[i][j]) {
                    buttons[i][j].setText("X");
                }
            }
        }
    }

    public int count(int row, int column) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int nr = row + dr;
                int nc = column + dc;
                if (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE && mines[nr][nc]) {
                    count++;
                }
            }
        }
        return count;
    }

    private class ButtonWorks implements ActionListener {
        private int row;
        private int col;

        public ButtonWorks(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (mines[row][col]) {
                button.setText("X");
                revealM();
                JOptionPane.showMessageDialog(Visual.this, "Game Over!");
                System.exit(0);
            } else {
                int count = count(row, col);
                if (count > 0) {
                    button.setText(String.valueOf(count));
                } else {
                    button.setText("");
                }
            }

            button.setEnabled(false);
        }

    }
}
