import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SelectDiff extends JFrame {
    public SelectDiff() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        setTitle("Difficulty Selection");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        ImageIcon icon = new ImageIcon("Menus/MainMenu5.png");
        setIconImage(icon.getImage());
        ImageIcon backgroundImage = new ImageIcon("Menus/MainMenu10.png");

        Image back = backgroundImage.getImage();
        BackgroundFlow background = new BackgroundFlow(back);

        JButton easy = new JButton("Easy");
        JButton medium = new JButton("Medium");
        JButton hard = new JButton("Hard");
        JButton genius = new JButton("Genius");
        JButton impossible = new JButton("Impossible");

        File wiiTheme = new File("Audio/Wii Theme.wav");
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(wiiTheme);
        Clip clip1 = AudioSystem.getClip();
        clip1.open(inputStream);



        easy.addActionListener(e -> {
            try {
                new Visual(Difficulty.EASY);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });
        medium.addActionListener(e ->{
            try {
                new Visual(Difficulty.MEDIUM);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });
        hard.addActionListener(e ->{
            try {
                new Visual(Difficulty.HARD);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });
        genius.addActionListener(e -> {
            try {
                new Visual(Difficulty.GENIUS);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });
        impossible.addActionListener(e -> {
            try {
                new Visual(Difficulty.IMPOSSIBLE);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        background.add(easy);
        background.add(medium);
        background.add(hard);
        background.add(genius);
        background.add(impossible);
        add(background);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
