import javax.swing.*;
import javax.swing.Box;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Window extends JFrame implements ActionListener {
    JButton button;

    Window(){
        JLabel label = new JLabel("TNT Sweeper");
        JButton button = new JButton();
        //button.setBounds(200, 50, 100, 50);
        button.setText("Play");

        JButton button1 = new JButton();
        //button1.setBounds(200, -50, 100, 50);
        button1.setText("How to play");

        JButton button2 = new JButton();
        //button2.setBounds(200, -150, 100, 50);
        button2.setText("Sign in");
        JPanel centerPanel = createPanel(5, label, button, button1, button2);

        JFrame frame = new JFrame();
        frame.add(centerPanel);
        frame.setVisible(true);
        frame.pack();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(null);
        //this.setSize(1000, 1000);
        this.setVisible(true);
        //this.add(button);
        //this.add(button1);
        //this.add(button2);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            System.out.println("yes");
        }
    }

    public JPanel createPanel(int space, JComponent...components){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Arrays.stream(components).forEach(component -> {
            component.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            panel.add(component);
            panel.add(Box.createRigidArea(new Dimension(0, space)));
        });
        return panel;
    }
}
