import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Intro {

    public void menu(){
        int choice;
        do {
            System.out.println("Welcome to MineSweeper game");
            System.out.println("1)  Play");
            System.out.println("2)  How to play");
            System.out.println("3)  Sign in");
            System.out.println("4)  Quit");
            System.out.println("  ");
            System.out.println("Choose:");

            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Game game = new Game();
                    // game.playGame();
                case 2:
                    try (BufferedReader reader = new BufferedReader(new FileReader("src//Intro.eng"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong.");
                    }
                case 3:
                    // History history = new History();
                    // history.account();
                case 4:
                    System.out.println("Bye bye!");
                    break;
                default:
                    System.out.println("Wrong choice. Choose again.");
            }
        }while(choice != 4);
    }



}
