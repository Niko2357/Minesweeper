import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageClickListener extends MouseAdapter {
    protected String label;

    public ImageClickListener(String label){
        this.label = label;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        JOptionPane.showMessageDialog(null, label, "flower", JOptionPane.INFORMATION_MESSAGE);
    }
}
