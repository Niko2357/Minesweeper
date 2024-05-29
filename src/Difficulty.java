public enum Difficulty {
    EASY(10, 10),
    MEDIUM(13, 30),
    HARD(16, 40),
    GENIUS(21, 99),
    IMPOSSIBLE(10, 100);

    protected final int size;
    protected final int mines;

    Difficulty(int size, int mines){
        this.size = size;
        this.mines = mines;
    }

    public int getSize() {
        return size;
    }

    public int getMines() {
        return mines;
    }
}
