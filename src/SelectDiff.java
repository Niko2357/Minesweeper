import javax.swing.*;
import java.awt.*;

public class SelectDiff extends JFrame {
    public SelectDiff(){
        setTitle("Difficulty Selection");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        ImageIcon icon = new ImageIcon("MainMenu5.png");
        setIconImage(icon.getImage());

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

        add(easy);
        add(medium);
        add(hard);
        add(genius);
        add(impossible);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
