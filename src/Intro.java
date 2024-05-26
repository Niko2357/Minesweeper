import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Intro {

    public void menu(){
        int choice;
        do {
            System.out.println("Welcome to MineSweeper game");
            System.out.println("1)  Play console version");
            System.out.println("2)  Play window version");
            System.out.println("3)  How to play");
            System.out.println("4)  Sign in");
            System.out.println("5)  Quit");
            System.out.println("  ");
            System.out.println("Choose:");

            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Game game = new Game();
                    game.playGame();
                    break;
                case 2:
                    Visual vis = new Visual();
                    break;
                case 3:
                    try (BufferedReader reader = new BufferedReader(new FileReader("src//Intro.eng"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong.");
                    }
                    break;
                case 4:
                    // History history = new History();
                    // history.account();
                    break;
                case 5:
                    System.out.println("Bye bye!");
                    break;
                default:
                    System.out.println("Wrong choice. Choose again.");
            }
        }while(choice != 5);
    }



}
