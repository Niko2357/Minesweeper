import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Visual();
                dispose();
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Menu.this, "Sign In Feature Not Implemented Yet");
            }
        });

        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Instructions();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainPanel.add(playButton);
        mainPanel.add(signInButton);
        mainPanel.add(howToPlayButton);
        mainPanel.add(quitButton);

        add(mainPanel);
        setVisible(true);
    }


}
