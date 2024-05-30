import javax.swing.*;
import java.awt.*;

public class BackgroundBorder extends JPanel {
    protected Image background;
    public BackgroundBorder(Image background){
        this.background = background;
        setLayout(new BorderLayout());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(background != null){
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void setBackgroundImage(Image background){
        this.background = background;
        repaint();
    }

}
