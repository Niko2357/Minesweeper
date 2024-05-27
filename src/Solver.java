import javax.swing.*;
import java.awt.*;

public class Solver {
    protected Visual visual;
    protected JButton[][] buttons;
    protected boolean[][] mines;
    protected int rows;
    protected int columns;

    public Solver(Visual visual){
        this.visual = visual;
        this.buttons = visual.buttons;
        this.mines = visual.mines;
        this.rows = buttons.length;
        this.columns = buttons[0].length;
    }

    public void solve(){
        while(!visual.lostGame()){
            boolean progress = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if(buttons[i][j].getText().equals("")){
                        int closeMines = countCloseM(i, j);
                        int closeFlags = countCloseF(i, j);
                        int closeHidden = countHidden(i, j);
                        if(closeMines == closeFlags){
                            revealH(i, j);
                            progress = true;
                        }else if(closeMines == closeHidden + closeFlags){
                            revealF(i, j);
                            progress = true;
                        }
                    }
                }
            }
            if(!progress){
                System.out.println("Its not solving.");
                break;
            }
        }
    }

   public int countCloseM(int row, int column){
        int count = 0;
       for (int i = -1; i <= 1; i++) {
           for (int j = -1; j <= 1; j++) {
               int r = row + i;
               int c = column + j;
               if (r >= 0 && c >= 0 && r < rows && c < columns && mines[r][c]) {
                   count++;
               }
           }
       }
       return count;
   }

   public int countCloseF(int row, int column){
        int count = 0;
        for(int i = -1; i <=1; i ++){
            for(int j = -1; j <= 1; j ++){
                int r = row + i;
                int c = column + j;
                if(r >= 0 && c >= 0 && r < rows && c < columns && buttons[r][c].getText().equals("F")) {
                    count++;
                }
            }
       }
        return count;
   }

    public int countHidden(int row, int column) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = column + j;
                if (r >= 0 && c >= 0 && r < rows && c < columns && buttons[r][c].getText().equals("")) {
                    count++;
                }
            }
        }
        return count;
    }

    public void revealH(int row, int column) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = column + j;
                if (r >= 0 && c >= 0 && r < rows && c < columns && buttons[r][c].getText().equals("")) {
                    buttons[r][c].doClick();
                }
            }
        }
    }

    public void revealF(int row, int column) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = column + j;
                if (r >= 0 && c >= 0 && r < rows && c < columns && buttons[r][c].getText().equals("")) {
                    buttons[r][c].setText("F");
                    buttons[r][c].setBackground(Color.RED);
                }
            }
        }
    }

}
