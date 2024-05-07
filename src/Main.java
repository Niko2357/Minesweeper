public class Main {
    public static void main(String[] args) {
        Intro intro = new Intro();
        intro.menu();

        Game game = new Game();
        game.playGame();

        Window win = new Window();
    }
}