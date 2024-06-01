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

    ImageIcon flag;
    ImageIcon obsidian = new ImageIcon("Floor/Obsidian.jpg");
    ImageIcon grass = new ImageIcon("Floor/grass.png");

    public ButtonWorks(int row, int column, Visual visual) {
        this.row = row;
        this.column = column;
        this.visual = visual;
        this.flag = new ImageIcon("Flags/Poppy.png");
    }
    int clickCount = 10;
    public void setFlag(String address){
        this.flag = new ImageIcon(address);
    }

    /**
     * Gets information from the cell. If the cell hides mine, all mines are revealed and game ends.
     * Otherwise, the game continues.
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

    public void obsidianBlock(JButton button){
        if(visual.flagMode){
            flagMode(button);
        }else{
            revealCell(button);
        }
        visual.winCheck();
    }

    public void normalBlock(JButton button){
        if(visual.flagMode){
            flagMode(button);
        }else{
            revealCell(button);
        }
        visual.winCheck();
    }

    public void flagMode(JButton button){
        if(flag.equals(button.getIcon())){
            button.setIcon(grass);
        }else{
            button.setIcon(flag);
        }
        button.setText("");
        if(visual.mines[row][column]){
            visual.foundMines += (flag.equals(button.getIcon()) ? 1: -1);
        }
    }

    public void revealCell(JButton button){
        if(flag.equals(button.getIcon())){
            return;
        }
        if(visual.mines[row][column]){
            button.setIcon(TNT);
            visual.revealM();
            visual.lost = true;
            GameOver();
        }else{
            visual.revealEmpty(row, column);
            button.setIcon(dirt);
            int count = visual.count(row, column);
            button.setText(count > 0 ? String.valueOf(count) : "");
            button.setEnabled(false);
        }
    }

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

