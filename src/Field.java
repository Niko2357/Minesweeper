public class Field {

    protected int column;
    protected int row;
    protected Box[][] box;

    public Field(int row, int column) {
        this.column = column;
        this.row = row;
        box = new Box[row] [column];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                box[i] [j] = new Box();
            }
        }
    }


    /**
     * Prints out board in console.
     */
    public void printOut(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                System.out.print(box[i] [j] + " ");
            }
            System.out.println(" ");
        }
    }

    /**
     * Show the number on board at chosen location.
     * @param loc location row*column
     */
    public void showBox(Location loc){
        box[loc.row][loc.column].see();
    }

    /**
     * Check if box is already uncover in this location.
     *
     * @param loc position of the box
     * @return if box is already revealed
     */
    public boolean canSee(Location loc){
        return box[loc.row][loc.column].isSeen();
    }

    /**
     * Makes sure the input from user is not out of bounds from the playing field.
     *
     * @param loc position of the box
     * @return if this location is on the board
     */
    public boolean checkExistenceofBox(Location loc){
        return loc.row >= 0 && loc.row < row && loc.column >= 0 &&  loc.column < column;
    }
}
