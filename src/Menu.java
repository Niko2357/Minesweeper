import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    JButton button;

    Window(){
        JButton button = new JButton();
        button.setBounds(200, 100, 100, 50);
        button.setText("Menu");


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.add(button);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            System.out.println("yes");
        }
    }
}
