import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
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

    ImageIcon flag = new ImageIcon("Flags/Poppy.png");
    ImageIcon dirt = new ImageIcon("Floor/dirt.jpg");
    ImageIcon grass = new ImageIcon("Floor/grass.png");
    ImageIcon obsidian = new ImageIcon("Floor/Obsidian.jpg");

    public Visual(Difficulty difficulty) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
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

        File wiiTheme = new File("Audio/Wii Theme.wav");
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(wiiTheme);
        Clip clip1 = AudioSystem.getClip();
        clip1.open(inputStream);

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
            try {
                clip1.start();
                new Menu();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            }
            dispose();
        });
        JMenuItem item2 = new JMenuItem("Flag choice");
        item2.addActionListener(ac -> {
            new SelectFlag();
        });
        JMenuItem item3 = new JMenuItem("Semi-Solve");
        item3.addActionListener(ac -> {
            new Solver(this);
            try {
                winCheck();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

        makeButtons(gridPanel, grass);
        placeMine();
        setVisible(true);
    }

    /**
     * Builds the board of this game with buttons.
     */
    public void makeButtons(JPanel gridPanel, ImageIcon icon) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                button.setIcon(icon);
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setOpaque(false);
                button.setBackground(Color.DARK_GRAY);
                button.setMargin(new Insets(0, 0, 0, 0));
                ButtonWorks buttonWorks = new ButtonWorks(i, j, this);
                button.addActionListener(buttonWorks);
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
        if (row < 0 || row >= SIZE || column < 0 || column >= SIZE || !buttons[row][column].isEnabled()  || flag.equals(buttons[row][column].getIcon())){
            return;
        }
        buttons[row][column].setEnabled(false);
        buttons[row][column].setIcon(null);
        int count = count(row, column);
        if (count > 0) {
            buttons[row][column].setText(String.valueOf(count));
            return;
        }else{
            buttons[row][column].setIcon(dirt);
            buttons[row][column].setOpaque(true);
        }
        for (int theRow = -1; theRow <= 1; theRow++) {
            for (int theColumn = -1; theColumn <= 1; theColumn++) {
                if(theRow != 0 || theColumn != 0) {
                    revealEmpty(row + theRow, column + theColumn);
                }
            }
        }
    }

    /**
     * This method checks whether player won. Player can achieve that by he marks all mines and reveals all cells.
     */
    public void winCheck() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
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
    public void win() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
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
