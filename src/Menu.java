import javax.swing.*;
import java.awt.*;
public class Menu extends JFrame {

    public Menu() {
        setTitle("TNT Sweeper Main Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton playButton = new JButton("Play");
        JButton signInButton = new JButton("Sign In");
        JButton howToPlayButton = new JButton("How to Play");
        JButton quitButton = new JButton("Quit");

        playButton.addActionListener(e -> {
            new Visual();
            dispose();
        });

        signInButton.addActionListener(e -> JOptionPane.showMessageDialog(Menu.this, "Sign In Feature Not Implemented Yet"));

        howToPlayButton.addActionListener(e -> new Instructions());
        quitButton.addActionListener(e -> System.exit(0));

        mainPanel.add(playButton);
        mainPanel.add(signInButton);
        mainPanel.add(howToPlayButton);
        mainPanel.add(quitButton);

        add(mainPanel);
        setVisible(true);
    }


}
