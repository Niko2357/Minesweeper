import java.awt.*;
import javax.swing.*;

public class BackgroundFlow extends JPanel{
    protected Image background;
    public BackgroundFlow(Image background){
        this.background = background;
        setPreferredSize(new Dimension(background.getWidth(this), background.getHeight(this)));
        setLayout(new FlowLayout());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(background != null){
            g.drawImage(background, 0, 0, this);
        }
    }
}
