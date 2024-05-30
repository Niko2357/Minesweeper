import javax.swing.*;
import java.awt.*;
public class Menu extends JFrame {

    public Menu() {
        setTitle("TNT Sweeper Main Menu");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("MainMenu5.png");
        setIconImage(icon.getImage());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton playButton = new JButton("Play");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        playButton.addActionListener(e -> {
            new SelectDiff();
            dispose();
        });

        howToPlayButton.addActionListener(e -> new Instructions());
        quitButton.addActionListener(e -> System.exit(0));

        mainPanel.add(playButton);
        mainPanel.add(howToPlayButton);
        mainPanel.add(quitButton);

        add(mainPanel);
        setVisible(true);
    }


}
