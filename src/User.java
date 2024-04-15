public class User {
    protected String name;
    protected int score;
    protected int wins;
    protected int gamesPlayed;

    public User(String name, int score, int wins, int gamesPlayed) {
        this.name = name;
        this.score = score;
        this.wins = wins;
        this.gamesPlayed = gamesPlayed;
    }

    @Override
    public String toString() {
        return "User{" +
                "name:  " + name + '\'' +
                ", score:  " + score +
                ", wins:  " + wins +
                ", games played:  " + gamesPlayed +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}
