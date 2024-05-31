import javax.swing.*;
import java.awt.*;

public class SelectFlag extends JFrame {
    protected String[] flags = {"Flags/Allium.png", "Flags/Blue_Orchid.png", "Flags/Cyan_Flower.png", "Flags/Dandelion.png","Flags/Lilac.png", "Flags/Orange_Tulip.png",
            "Flags/Peony_aka_Paeonia.png", "Flags/Poppy.png", "Flags/Rose_Bush.png"};
    protected String[] labels = {"Allium", "Blue Orchid", "Cyan Flower", "Dandelion", "Lilac", "Orange Tulip", "Peony", "Poppy", "Rose Bush"};

    static String chosenFlag;
    public SelectFlag(){
        setTitle("Select Flag");
        setSize(800, 990);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Menus/MainMenu5.png");
        setIconImage(icon.getImage());

        JPanel panel = new JPanel(new GridLayout(0, 3));
        panel.setBackground(Color.WHITE);

        ImageIcon fl1 = new ImageIcon(flags[0]);
        String name1 = labels[0];
        JLabel picture = new JLabel(fl1);
        JLabel text = new JLabel(name1);
        picture.setHorizontalAlignment(SwingConstants.CENTER);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel imagePanel1 = new JPanel(new BorderLayout());
        imagePanel1.add(picture, BorderLayout.CENTER);
        imagePanel1.add(text, BorderLayout.SOUTH);
        picture.addMouseListener(new ImageClickListener(name1, this));
        panel.add(imagePanel1);

        ImageIcon fl2 = new ImageIcon(flags[1]);
        String name2 = labels[1];
        JLabel picture2 = new JLabel(fl2);
        JLabel text2 = new JLabel(name2);
        picture2.setHorizontalAlignment(SwingConstants.CENTER);
        text2.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel imagePanel2 = new JPanel(new BorderLayout());
        imagePanel2.add(picture2, BorderLayout.CENTER);
        imagePanel2.add(text2, BorderLayout.SOUTH);
        picture2.addMouseListener(new ImageClickListener(name2, this));
        panel.add(imagePanel2);

        ImageIcon fl3 = new ImageIcon(flags[2]);
        String name3 = labels[2];
        JLabel picture3 = new JLabel(fl3);
        JLabel text3 = new JLabel(name3);
        picture3.setHorizontalAlignment(SwingConstants.CENTER);
        text3.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel imagePanel3 = new JPanel(new BorderLayout());
        imagePanel3.add(picture3, BorderLayout.CENTER);
        imagePanel3.add(text3, BorderLayout.SOUTH);
        picture3.addMouseListener(new ImageClickListener(name3, this));
        panel.add(imagePanel3);

        ImageIcon fl4 = new ImageIcon(flags[3]);
        String name4 = labels[3];
        JLabel picture4 = new JLabel(fl4);
        JLabel text4 = new JLabel(name4);
        picture4.setHorizontalAlignment(SwingConstants.CENTER);
        text4.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel imagePanel4 = new JPanel(new BorderLayout());
        imagePanel4.add(picture4, BorderLayout.CENTER);
        imagePanel4.add(text4, BorderLayout.SOUTH);
        picture4.addMouseListener(new ImageClickListener(name4, this));
        panel.add(imagePanel4);

        ImageIcon fl5 = new ImageIcon(flags[4]);
        String name5 = labels[4];
        JLabel picture5 = new JLabel(fl5);
        JLabel text5 = new JLabel(name5);
        picture5.setHorizontalAlignment(SwingConstants.CENTER);
        text5.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel imagePanel5 = new JPanel(new BorderLayout());
        imagePanel5.add(picture5, BorderLayout.CENTER);
        imagePanel5.add(text2, BorderLayout.SOUTH);
        picture5.addMouseListener(new ImageClickListener(name5, this));
        panel.add(imagePanel5);

        ImageIcon fl6 = new ImageIcon(flags[5]);
        String name6 = labels[5];
        JLabel picture6 = new JLabel(fl6);
        JLabel text6 = new JLabel(name6);
        picture6.setHorizontalAlignment(SwingConstants.CENTER);
        text6.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel imagePanel6 = new JPanel(new BorderLayout());
        imagePanel6.add(picture6, BorderLayout.CENTER);
        imagePanel6.add(text6, BorderLayout.SOUTH);
        picture6.addMouseListener(new ImageClickListener(name6, this));
        panel.add(imagePanel6);

        ImageIcon fl7 = new ImageIcon(flags[6]);
        String name7 = labels[6];
        JLabel picture7 = new JLabel(fl7);
        JLabel text7 = new JLabel(name7);
        picture7.setHorizontalAlignment(SwingConstants.CENTER);
        text7.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel imagePanel7 = new JPanel(new BorderLayout());
        imagePanel7.add(picture7, BorderLayout.CENTER);
        imagePanel7.add(text7, BorderLayout.SOUTH);
        picture7.addMouseListener(new ImageClickListener(name7, this));
        panel.add(imagePanel7);

        ImageIcon fl8 = new ImageIcon(flags[7]);
        String name8 = labels[7];
        JLabel picture8 = new JLabel(fl8);
        JLabel text8 = new JLabel(name8);
        picture8.setHorizontalAlignment(SwingConstants.CENTER);
        text8.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel imagePanel8 = new JPanel(new BorderLayout());
        imagePanel8.add(picture8, BorderLayout.CENTER);
        imagePanel8.add(text8, BorderLayout.SOUTH);
        picture8.addMouseListener(new ImageClickListener(name8, this));
        panel.add(imagePanel8);

        ImageIcon fl9 = new ImageIcon(flags[8]);
        String name9 = labels[8];
        JLabel picture9 = new JLabel(fl9);
        JLabel text9 = new JLabel(name9);
        picture9.setHorizontalAlignment(SwingConstants.CENTER);
        text9.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel imagePanel9 = new JPanel(new BorderLayout());
        imagePanel9.add(picture9, BorderLayout.CENTER);
        imagePanel9.add(text9, BorderLayout.SOUTH);
        picture9.addMouseListener(new ImageClickListener(name9, this));
        panel.add(imagePanel9);

        add(new JScrollPane(panel));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
