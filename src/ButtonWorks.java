import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonWorks implements ActionListener {
    private int row;
    private int column;
    private Visual visual;


    public ButtonWorks(int row, int column, Visual visual) {
        this.row = row;
        this.column = column;
        this.visual = visual;
    }
    int clickCount = 10;
    ImageIcon dirt = new ImageIcon("Floor/dirt.jpg");
    ImageIcon TNT = new ImageIcon("Floor/TNT.png");
    String flagAddress = ImageClickListener.flagsChosen.get(ImageClickListener.flagsChosen.size() - 1);
    ImageIcon flag = new ImageIcon(flagAddress);;
    ImageIcon obsidian = new ImageIcon("Floor/Obsidian.jpg");
    ImageIcon grass = new ImageIcon("Floor/grass.png");


    /**
     * Gets information from the cell. If the cell hides mine, all mines are revealed and game ends.
     * Otherwise, the game continues.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        JButton button = (JButton) e.getSource();
        if (button.getIcon().equals(obsidian)) {
            if (clickCount == 0) {
                if (visual.flagMode) {
                    if (flag.equals(button.getIcon())) {
                        button.setIcon(null);
                        button.setIcon(grass);
                        button.setText("");
                        if (visual.mines[row][column]) {
                            visual.foundMines--;
                        }
                    } else {
                        button.setIcon(null);
                        button.setIcon(flag);
                        button.setText("");
                        if (visual.mines[row][column]) {
                            visual.foundMines++;
                        }
                    }
                } else {
                    if (flag.equals(button.getIcon())) {
                        return;
                    }
                    if (visual.mines[row][column]) {
                        button.setIcon(null);
                        button.setIcon(TNT);
                        button.setText("");
                        visual.revealM();
                        visual.lost = true;
                        int option = JOptionPane.showOptionDialog(visual, "Game Over!", "You lost", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Play again", "Menu"}, null);
                        if (option == 0) {
                            visual.dispose();
                            new SelectDiff();
                        } else if (option == 1) {
                            visual.dispose();
                            new Menu();
                        }
                    } else {
                        visual.revealEmpty(row, column);
                        int count = visual.count(row, column);
                        button.setIcon(null);
                        button.setIcon(dirt);
                        button.setOpaque(true);
                        if(count > 0) {
                            button.setText(String.valueOf(count));
                        }
                    }
                    button.setEnabled(false);
                }
                visual.winCheck();
            } else {
                clickCount--;
            }
        } else {
            if (visual.flagMode) {
                if (flag.equals(button.getIcon())) {
                    button.setIcon(null);
                    button.setIcon(grass);
                    button.setText("");
                    if (visual.mines[row][column]) {
                        visual.foundMines--;
                    }
                } else {
                    button.setIcon(null);
                    button.setIcon(flag);
                    button.setText("");
                    if (visual.mines[row][column]) {
                        visual.foundMines++;
                    }
                }
            } else {
                if (flag.equals(button.getIcon())) {
                    return;
                }else if (visual.mines[row][column]) {
                    button.setIcon(null);
                    button.setIcon(TNT);
                    visual.revealM();
                    visual.lost = true;
                    int option = JOptionPane.showOptionDialog(visual, "Game Over!", "You lost", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Play again", "Menu"}, null);
                    if (option == 0) {
                        visual.dispose();
                        new SelectDiff();
                    } else if (option == 1) {
                        visual.dispose();
                        new Menu();
                    }
                } else {
                    button.setIcon(null);
                    button.setIcon(dirt);
                    visual.revealEmpty(row, column);
                    int count = visual.count(row, column);
                    if (count > 0) {
                        button.setText(String.valueOf(count));
                    } else {
                        button.setText("");
                    }
                }
                button.setEnabled(false);
                button.setIcon(null);
            }
            visual.winCheck();
        }
    }
}

