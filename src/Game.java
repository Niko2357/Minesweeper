import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    protected ArrayList<User> users = new ArrayList<>();
    protected Field field;

    public Game() {
        this.users = users;
        this.field = new Field(10, 10);
    }

    Scanner sc = new Scanner(System.in);

    public void playGame(){
        Location loca;
        do{
            field.printOut();
            loca = inLocate();
            field.showBox(loca);
        }while(true);
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

    /**
     * Handles user error, when they try to play out of board.
     *
     * @param locat position of box
     * @return whatever box is in the playing field or not
     */
    public boolean locExists(Location locat){
        if(!field.checkExistenceofBox(locat)){
            System.out.println("You cannot use these numbers. They are too far.");
            return false;
        }else if(field.canSee(locat)){
            System.out.println("You can see this box, so why??");
            return false;
        }
        return true;
    }

    /**
     * Asks for position user wants to reveal.
     *
     * @return the position set by user
     */
    public Location inLocate(){
        Location in = new Location(0, 0);
        do{
            System.out.println("Enter coordinates(example: 1 1 s):");
            in.row = sc.nextInt() -1;
            in.column = sc.nextInt() -1;
        }while(!locExists(in));
        return in;
    }
}
