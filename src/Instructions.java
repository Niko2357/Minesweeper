import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Instructions extends JFrame {
    public Instructions(){
        setTitle("How to play");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem item = new JMenuItem("Back to Main menu");
        item.addActionListener(e -> {
            new Menu();
            this.dispose();
        });

        menu.add(item);
        menubar.add(menu);
        setJMenuBar(menubar);
        String text = readFile("src/Intro.eng");
        JTextArea textA = new JTextArea(text);
        textA.setEditable(false);
        textA.setLineWrap(true);
        textA.setWrapStyleWord(true);
        textA.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 24));
        JScrollPane scrollPane = new JScrollPane(textA);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

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
}
