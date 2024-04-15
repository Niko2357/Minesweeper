public class Field {

    protected int column;
    protected int row;
    protected char[][] field;

    public Field(int row, int column) {
        this.column = column;
        this.row = row;
        field = new char[row] [column];
        makeField();
        printOut();
    }

    public void makeField(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                field[i][j] = '-' + '+';
            }
        }
    }

    public void printOut(){
        System.out.print("  ");
        for(int j = 0; j < column; j++){
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < row; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < column; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }


}
