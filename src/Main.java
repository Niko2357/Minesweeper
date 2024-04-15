public class Main {
    public static void main(String[] args) {

        Field f = new Field(9,9);

        Intro intro = new Intro();
        intro.menu();

        Game game = new Game();
        game.playGame();
    }
}