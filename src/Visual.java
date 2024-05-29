import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Visual extends JFrame {

    protected static final int SIZE = 10;
    protected static final int MINES = 10;

    protected JButton[][] buttons;
    protected boolean[][] mines;
    protected boolean flagMode = false;
    protected int foundMines = 0;
    protected int revealedCells = 0;
    protected boolean lost;
    protected Difficulty difficulty;

    public Visual() {
        setTitle("TNT Sweeper");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(SIZE, SIZE));
        setLocationRelativeTo(null);

        buttons = new JButton[SIZE][SIZE];
        mines = new boolean[SIZE][SIZE];

        JPanel mainPanel = new JPanel();
        JButton tButton = new JButton("Mode: Dig");
        tButton.addActionListener(ac -> {
            flagMode = !flagMode;
            tButton.setText(flagMode ? "Mode: Mark" : "Mode: Dig");
        });
        mainPanel.add(tButton);
        add(mainPanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(SIZE, SIZE));
        add(gridPanel, BorderLayout.CENTER);

        makeButtons(gridPanel);
        placeMine();
        setVisible(true);
    }


    /**
     * Builds the board of this game with buttons.
     */
    public void makeButtons(JPanel gridPanel) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                button.setMargin(new Insets(0, 0, 0, 0));
                button.addActionListener(new ButtonWorks(i, j, this));
                buttons[i][j] = button;
                gridPanel.add(button);
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

    /**
     * Reveals all empty box. By empty meaning with no number on them. Having no mines around them.
     * @param row height of cell
     * @param column width of cell
     */
    public void revealEmpty(int row, int column) {
        if (row < 0 || row >= SIZE || column < 0 || column >= SIZE || !buttons[row][column].isEnabled()  || "F".equals(buttons[row][column].getText())){
            return;
    }
        buttons[row][column].setEnabled(false);
        int count = count(row, column);
        if (count > 0) {
            buttons[row][column].setText(String.valueOf(count));
            buttons[row][column].setEnabled(false);
            revealedCells++;
            return;
        }

        buttons[row][column].setEnabled(false);
        revealedCells++;
        for (int theRow = -1; theRow <= 1; theRow++) {
            for (int theColumn = -1; theColumn <= 1; theColumn++) {
                revealEmpty(row + theRow, column + theColumn);
            }
        }
    }

    /**
     * This method checks whether player won. Player can achieve that by he marks all mines and reveals all cells.
     */
    public void winCheck(){
        int diff = 0;
        switch(diff){
            case 1:
                if(MINES == 10 && (SIZE*SIZE-MINES) == 90){
                    JOptionPane.showMessageDialog(this, "Winner!!!");
                    win();
                }
            case 2:
                if(MINES == 30 && (SIZE*SIZE-MINES) == 139){
                    JOptionPane.showMessageDialog(this, "Winner!!!");
                    win();
                }
            case 3:
                if(MINES == 40 && (SIZE*SIZE-MINES) == 216){
                    JOptionPane.showMessageDialog(this, "Winner!!!");
                    win();
                }
            case 4:
                if(MINES == 99 && (SIZE*SIZE-MINES) == 342){
                    JOptionPane.showMessageDialog(this, "Winner!!!");
                    win();
                }
            case 5:
                if(MINES == 100 && (SIZE*SIZE-MINES) == 0){
                    JOptionPane.showMessageDialog(this, "Winner!!!");
                    win();
                }
            default:
             diff = 1;
        }
    }

    /**
     * Carries on the message of winning a game and leads player to the menu.
     */
    public void win(){
        int option = JOptionPane.showOptionDialog(this, "You have won!!!", "Winner",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Play again", "Menu"}, null);
        if(option == 0){
            this.dispose();
            new Visual();
        }else if(option == 1){
            new Menu();
        }
    }

    public boolean lostGame(){
        return lost;
    }


}
