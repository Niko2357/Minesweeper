import javax.swing.*;
import java.awt.*;

public class SelectDiff extends JFrame {
    public SelectDiff(){
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

        easy.addActionListener(e -> {
            new Visual(Difficulty.EASY);
            dispose();
        });
        medium.addActionListener(e ->{
            new Visual(Difficulty.MEDIUM);
            dispose();
        });
        hard.addActionListener(e ->{
            new Visual(Difficulty.HARD);
            dispose();
        });
        genius.addActionListener(e -> {
            new Visual(Difficulty.GENIUS);
            dispose();
        });
        impossible.addActionListener(e -> {
            new Visual(Difficulty.IMPOSSIBLE);
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
