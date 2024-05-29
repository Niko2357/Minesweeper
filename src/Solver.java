import javax.swing.*;
import java.awt.*;

public class Solver {
    protected Visual visual;
    protected JButton[][] buttons;
    protected boolean[][] mines;
    protected int rows;
    protected int columns;
    protected boolean[][] did;

    public Solver(Visual visual){
        this.visual = visual;
        this.buttons = visual.buttons;
        this.mines = visual.mines;
        this.rows = buttons.length;
        this.columns = buttons[0].length;
        this.did = new boolean[rows][columns];
        solve();
    }

    public void solve(){
        while(!visual.lostGame() && !visual.wonGame()){
            boolean progress = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if(buttons[i][j].getText().equals("") && !did[i][j]){
                        int closeMines = countCloseM(i, j);
                        int closeFlags = countCloseF(i, j);
                        int closeHidden = countHidden(i, j);
                        if(closeMines == closeFlags){
                            revealMines(i, j);
                            progress = true;
                        }else if(closeMines == closeHidden + closeFlags){
                            revealF(i, j);
                            progress = true;
                        }
                    }
                }
            }
            if(!progress){
                System.out.println("It's not solving.");
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
                if(r >= 0 && c >= 0 && r < rows && c < columns && "F".equals(buttons[r][c].getText())) {
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
                if (r >= 0 && c >= 0 && r < rows && c < columns && buttons[r][c].isEnabled() && !"F".equals(buttons[r][c].getText())) {
                    count++;
                }
            }
        }
        return count;
    }

    public void revealMines(int row, int column) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = column + j;
                    if (r >= 0 && c >= 0 && r < rows && c < columns && buttons[r][c].getText().equals("") && !did[r][c]) {
                        did[r][c] = true;
                        buttons[r][c].doClick();
                        if(buttons[r][c].getText().equals("0")){
                            revealMines(r, c);
                        }
                }
            }
        }
    }

    public void revealF(int row, int column) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = column + j;
                if (r >= 0 && c >= 0 && r < rows && c < columns && buttons[r][c].isEnabled() && buttons[r][c].getText().equals("")) {
                    buttons[r][c].setText("F");
                    buttons[r][c].setBackground(Color.RED);
                    visual.foundMines++;
                }
            }
        }
    }

}
