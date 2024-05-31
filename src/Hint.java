import javax.swing.*;
import java.awt.*;
public class Hint {
    protected Visual visual;
    public Hint(Visual visual){
        this.visual = visual;
        giveAHint();
    }

    ImageIcon obsidian = new ImageIcon("Floor/Obsidian.jpg");

    public void giveAHint(){
        boolean[][] mines = visual.mines;
        JButton[][] buttons = visual.buttons;
        for(int i = 0; i < mines.length; i++){
            for(int j = 0; j < mines[i].length; j++){
                if(!mines[i][j] && buttons[i][j].isEnabled()){
                    buttons[i][j].setIcon(obsidian);
                    return;
                }
            }
        }
    }

}
