import javax.swing.*;
import javax.swing.Box;
import java.awt.*;
import java.util.ArrayList;
public class Menu extends JFrame {
    protected Background backgroundImage;
    protected Timer time;
    protected ArrayList<Image> backgroundImages;
    protected int curImage;

    public Menu() {
        setTitle("TNT Sweeper");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("Menus/MainMenu5.png");
        setIconImage(icon.getImage());

        backgroundImages = new ArrayList<>();
        backgroundImages.add(new ImageIcon("Menus/MainMenu1.jpg").getImage());
        backgroundImages.add(new ImageIcon("Menus/MainMenu2.jpg").getImage());
        backgroundImages.add(new ImageIcon("Menus/MainMenu3.jpg").getImage());
        backgroundImages.add(new ImageIcon("Menus/MainMenu4.png").getImage());
        backgroundImages.add(new ImageIcon("Menus/MainMenu6.jpg").getImage());
        backgroundImages.add(new ImageIcon("Menus/MainMenu7.jpg").getImage());
        backgroundImages.add(new ImageIcon("Menus/MainMenu8.png").getImage());
        backgroundImages.add(new ImageIcon("Menus/MainMenu9.jpg").getImage());
        backgroundImages.add(new ImageIcon("Menus/MainMenu10.png").getImage());
        backgroundImages.add(new ImageIcon("Menus/MainMenu11.jpg").getImage());
        curImage = 0;

        backgroundImage = new Background(backgroundImages.get(curImage));

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        ImageIcon titlePic = new ImageIcon("Title3.PNG");
        JLabel titleLabel = new JLabel(titlePic);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon background = new ImageIcon("Obsidian.jpg");
        JLabel backPic = new JLabel(background);

        Dimension buttonSize = new Dimension(150, 50);
        Font buttonFont = new Font("Calibri", Font.BOLD, 20);

        JButton playButton = new JButton("Play");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setPreferredSize(buttonSize);
        playButton.setFont(buttonFont);

        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        howToPlayButton.setPreferredSize(buttonSize);
        howToPlayButton.setFont(buttonFont);

        JButton quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setFont(buttonFont);
        quitButton.setPreferredSize(buttonSize);

        playButton.addActionListener(e -> {
            new SelectDiff();
            dispose();
        });

        howToPlayButton.addActionListener(e -> new Instructions());
        quitButton.addActionListener(e -> System.exit(0));

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(playButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(howToPlayButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(quitButton);
        mainPanel.add(Box.createVerticalGlue());
        //mainPanel.add(backPic);

        backgroundImage.add(mainPanel, BorderLayout.CENTER);
        setContentPane(backgroundImage);

        time = new Timer(5000, e -> switchImage());
        time.start();
        setVisible(true);
    }

    public void switchImage(){
        curImage = (curImage + 1) % backgroundImages.size();
        backgroundImage.setBackgroundImage(backgroundImages.get(curImage));
    }


}
