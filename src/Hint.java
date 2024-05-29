import javax.swing.*;
import java.awt.*;

public class Hint {
    private Visual visual;

    public Hint(Visual visual){
        this.visual = visual;
        giveAHint();
    }

    public void giveAHint(){
        boolean[][] mines = visual.mines;
        JButton[][] buttons = visual.buttons;
        for(int i = 0; i < mines.length; i++){
            for(int j = 0; j < mines[i].length; j++){
                if(!mines[i][j] && buttons[i][j].getText().equals("")){
                    buttons[i][j].setBackground(Color.CYAN);
                    return;
                }
            }
        }
    }
}
