import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageClickListener extends MouseAdapter {
    protected String label;
    protected SelectFlag flag;

    public ImageClickListener(String label, SelectFlag flag){
        this.label = label;
        this.flag = flag;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        JOptionPane.showMessageDialog(null,"You chose " +  label, "Chosen flag", JOptionPane.INFORMATION_MESSAGE);
        flag.dispose();
    }
}
