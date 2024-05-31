import javax.swing.*;
import java.awt.*;

public class SelectFlag extends JFrame {
    protected String[] flags = {"Flags/Allium.png", "Flags/Blue_Orchid.png", "Flags/Cyan_Flower.png", "Flags/Dandelion.png","Flags/Lilac.png", "Flags/Orange_Tulip.png",
            "Flags/Peony_aka_Paeonia.png", "Flags/Poppy.png", "Flags/Rose_Bush.png"};
    protected String[] labels = {"Allium", "Blue Orchid", "Cyan Flower", "Dandelion", "Lilac", "Orange Tulip", "Peony", "Poppy", "Rose Bush"};

    public SelectFlag(){
        setTitle("Select Flag");
        setSize(800, 990);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Menus/MainMenu5.png");
        setIconImage(icon.getImage());

        JPanel panel = new JPanel(new GridLayout(0, 3));
        panel.setBackground(Color.WHITE);
        JLabel[] imLabel = new JLabel[flags.length];
        for(int i = 0; i < flags.length; i++){
            ImageIcon image = new ImageIcon(flags[i]);
            JLabel picture = new JLabel(image);
            JLabel text = new JLabel(labels[i]);
            picture.setHorizontalAlignment(SwingConstants.CENTER);
            text.setHorizontalAlignment(SwingConstants.CENTER);
            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.add(picture, BorderLayout.CENTER);
            imagePanel.add(text, BorderLayout.SOUTH);

            picture.addMouseListener(new ImageClickListener(labels[i], this));
            panel.add(imagePanel);
        }
        add(new JScrollPane(panel));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
