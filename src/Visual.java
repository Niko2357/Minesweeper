import javax.swing.*;
import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class Visual extends JFrame {

    protected int SIZE;
    protected int MINES;

    protected JButton[][] buttons;
    protected boolean[][] mines;
    protected boolean flagMode = false;
    protected int foundMines = 0;
    protected boolean lost;
    protected boolean won;
    protected Difficulty difficulty;
    protected Hint hint;
    ImageIcon TNT;

    public Visual(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.SIZE = difficulty.getSize();
        this.MINES = difficulty.getMines();

        setTitle("TNT Sweeper");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(SIZE, SIZE));
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("Menus/MainMenu5.png");
        setIconImage(icon.getImage());
        TNT = new ImageIcon("Floor/TNT.png");

        buttons = new JButton[SIZE][SIZE];
        mines = new boolean[SIZE][SIZE];

        JPanel mainPanel = new JPanel();
        JButton flagButton = new JButton("Mode: Dig");
        flagButton.addActionListener(ac -> {
            flagMode = !flagMode;
            flagButton.setText(flagMode ? "Mode: Mark" : "Mode: Dig");
        });
        JButton hintButton = new JButton("Hint");
        hintButton.addActionListener(ac ->{
            hint = new Hint(this);
        });

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem item1 = new JMenuItem("Back to Main menu");
        item1.addActionListener(ac -> {
            new Menu();
            dispose();
        });
        JMenuItem item2 = new JMenuItem("Flag choice");
        item2.addActionListener(ac -> {
            new SelectFlag();
        });
        JMenuItem item3 = new JMenuItem("Semi-Solve");
        item3.addActionListener(ac -> {
            new Solver(this);
            winCheck();
        });

        menu.add(item1);
        menu.add(item2);
        menu.add(item3);

        menubar.add(menu);
        setJMenuBar(menubar);

        mainPanel.add(flagButton);
        mainPanel.add(hintButton);
        add(mainPanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(SIZE, SIZE));
        add(gridPanel, BorderLayout.CENTER);

        ImageIcon grass = new ImageIcon("Floor/grass.png");

        makeButtons(gridPanel, grass);
        placeMine();
        setVisible(true);
    }


    /**
     * Builds the board of this game with buttons.
     */
    public void makeButtons(JPanel gridPanel, ImageIcon icon) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                button.setIcon(icon);
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setOpaque(false);
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
                    buttons[i][j].setIcon(TNT);
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
            return;
        }
        buttons[row][column].setEnabled(false);
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
        int allCells = SIZE * SIZE;
        int notMineCells = allCells - MINES;
        if(allCells - MINES == notMineCells && foundMines == MINES){
            JOptionPane.showMessageDialog(this, "Good job");
            win();
        }
    }

    /**
     * Carries on the message of winning a game and leads player to the menu.
     */
    public void win(){
        won = true;
        int option = JOptionPane.showOptionDialog(this, "You have won!!!", "Winner",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Play again", "Menu"}, null);
        if(option == 0){
            this.dispose();
            new SelectDiff();
        }else if(option == 1){
            this.dispose();
            new Menu();
        }
    }

    /**
     * Support variable.
     * @return
     */
    public boolean lostGame(){
        return lost;
    }

    /**
     * Support variable.
     * @return
     */
    public boolean wonGame(){
        return won;
    }


}
