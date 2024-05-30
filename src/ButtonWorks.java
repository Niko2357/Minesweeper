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

    /**
     * Gets information from the cell. If the cell hides mine, all mines are revealed and game ends.
     * Otherwise, the game continues.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getBackground().equals(Color.CYAN)) {
            if (clickCount == 0) {
                if (visual.flagMode) {
                    if ("F".equals(button.getText())) {
                        button.setText("");
                        if (visual.mines[row][column]) {
                            visual.foundMines--;
                        }
                    } else {
                        button.setText("F");
                        if (visual.mines[row][column]) {
                            visual.foundMines++;
                        }
                    }
                } else {
                    if ("F".equals(button.getText())) {
                        return;
                    }
                    if (visual.mines[row][column]) {
                        button.setText("X");
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
                        new Menu();
                        visual.dispose();
                    } else {
                        visual.revealEmpty(row, column);
                        int count = visual.count(row, column);
                        if (count > 0) {
                            button.setText(String.valueOf(count));
                        } else {
                            button.setText("");
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
                if ("F".equals(button.getText())) {
                    button.setText("");
                    if (visual.mines[row][column]) {
                        visual.foundMines--;
                    }
                } else {
                    button.setText("F");
                    if (visual.mines[row][column]) {
                        visual.foundMines++;
                    }
                }
            } else {
                if ("F".equals(button.getText())) {
                    return;
                }
                if (visual.mines[row][column]) {
                    button.setText("X");
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
                    if (count > 0) {
                        button.setText(String.valueOf(count));
                    } else {
                        button.setText("");
                    }
                }
                button.setEnabled(false);
            }
            visual.winCheck();
        }
    }
}

