import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Instructions extends JFrame {
    public Instructions(Menu mainMenu){
        setTitle("How to play");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("Menus/MainMenu5.png");
        setIconImage(icon.getImage());

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem item = new JMenuItem("Back to Main menu");
        item.addActionListener(e -> {
            try {
                mainMenu.dispose();
                mainMenu.clip1.stop();
                new Menu();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        });
        JMenuItem item2 = new JMenuItem("I still don't understand");
        item2.addActionListener(e ->{
            openWeb("https://minesweepergame.com/strategy/how-to-play-minesweeper.php");
        });

        menu.add(item);
        menu.add(item2);
        menubar.add(menu);
        setJMenuBar(menubar);
        String text = readFile("src/Intro.eng");
        JTextArea textA = new JTextArea(text);
        textA.setEditable(false);
        textA.setLineWrap(true);
        textA.setWrapStyleWord(true);
        textA.setFont(new Font("Dialog", Font.BOLD, 24));
        JScrollPane scrollPane = new JScrollPane(textA);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Reads a file and puts all lines together in one String.
     * @param file Intro.eng
     * @return whole content of a file
     */
    public String readFile(String file){
        StringBuilder build  = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                build.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
        return build.toString();
    }

    /**
     * Opens web page in supported web browser with further instructions of game Minesweeper.
     * @param URL address of web page
     */
    public void openWeb(String URL){
        try{
            URI uri = new URI(URL);
            if(Desktop.isDesktopSupported()){
                Desktop desktop = Desktop.getDesktop();
                try{
                    desktop.browse(uri);
                }catch(IOException e){
                    System.out.println("Something went wrong");
                }
            }else{
                System.out.println("You cannot open this URL.");
            }
        }catch(URISyntaxException e){
            System.out.println("Something went wrong");
        }
    }
}
