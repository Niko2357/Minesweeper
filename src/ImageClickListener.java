import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ImageClickListener extends MouseAdapter {
    protected String label;
    String flagAddress;
    protected SelectFlag flag;
    public static ArrayList<String> flagsChosen = new ArrayList<>();

    public ImageClickListener(String label, SelectFlag flag){
        this.label = label;
        this.flag = flag;
        this.flagAddress = "";
    }
    @Override
    public void mouseClicked(MouseEvent e){
        JOptionPane.showMessageDialog(null,"You chose " +  label, "Chosen flag", JOptionPane.INFORMATION_MESSAGE);
        SelectFlag.chosenFlag = flagAddress;
        flagsChosen.add(flagAddress);
        System.out.println(flagsChosen);
        flag.dispose();
    }
}
