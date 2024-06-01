import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonWorks implements ActionListener {
    private int row;
    private int column;
    private Visual visual;

    ImageIcon dirt = new ImageIcon("Floor/dirt.jpg");
    ImageIcon TNT = new ImageIcon("Floor/TNT.png");

    ImageIcon flag = new ImageIcon("Flags/Peony_aka_Paeonia.png");
    ImageIcon obsidian = new ImageIcon("Floor/Obsidian.jpg");
    ImageIcon grass = new ImageIcon("Floor/grass.png");

    public ButtonWorks(int row, int column, Visual visual) {
        this.row = row;
        this.column = column;
        this.visual = visual;
    }
    int clickCount = 10;

    /**
     * Gets signal from button of the board and starts other methods under some conditions.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        JButton button = (JButton) e.getSource();
        if(button.getIcon().equals(obsidian)){
            obsidianBlock(button);
        }else{
            normalBlock(button);
        }
    }

    /**
     * Handles hint block.
     * @param button
     */
    public void obsidianBlock(JButton button){
        if(visual.flagMode){
            flagMode(button);
        }else{
            if(clickCount == 0){
                revealCell(button);
            }else {
                clickCount--;
            }
        }
        visual.winCheck();
    }

    /**
     * Handles normal grass block.
     * @param button
     */
    public void normalBlock(JButton button){
        if(visual.flagMode){
            flagMode(button);
        }else{
            revealCell(button);
        }
        visual.winCheck();
    }

    /**
     * Handles marking mode and is setting a flag.
     * @param button
     */
    public void flagMode(JButton button){
        if(flag.equals(button.getIcon())){
            button.setIcon(null);
            button.setIcon(grass);
        }else{
            button.setIcon(null);
            button.setIcon(flag);
        }
        button.setText("");
        if(visual.mines[row][column]){
            visual.foundMines ++;
        }
    }

    /**
     * reveals all mine cells if mine is found or calls method from class visual that reveals all empty cells.
     * @param button
     */
    public void revealCell(JButton button){
        if(flag.equals(button.getIcon())){
            return;
        }
        if(visual.mines[row][column]){
            button.setIcon(null);
            button.setIcon(TNT);
            visual.revealM();
            visual.lost = true;
            GameOver();
        }else{
            visual.revealEmpty(row, column);
            button.setIcon(null);
            button.setIcon(dirt);
            button.isEnabled();
            int count = visual.count(row, column);
            button.setText(count > 0 ? String.valueOf(count) : "");
            button.setEnabled(false);
        }
    }

    /**
     * Handles lost game.
     */
    public void GameOver(){
        int option = JOptionPane.showOptionDialog(visual, "Game over", "You lost", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Play again", "Menu"}, null);
        if(option == 0){
            visual.dispose();
            new SelectDiff();
        }else if(option == 1){
            visual.dispose();
            new Menu();
        }
    }

}

