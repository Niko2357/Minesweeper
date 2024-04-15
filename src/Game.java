import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    protected ArrayList<User> users = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void playGame(){
        int row;
        int column;
        System.out.println("Enter coordinates(example: 1 1 s):");
        row = sc.nextInt();
        column = sc.nextInt();
        System.out.println("You wrote: " + row + " row, " + column + " column");
        /*try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("src//Wii Theme.mp3"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            while(true){
                Thread.sleep(1000);
            }
        }catch(Exception e){
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }*/
    }
}
