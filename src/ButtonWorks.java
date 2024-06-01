import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ButtonWorks implements ActionListener {
    private final int row;
    private final int column;
    private final Visual visual;

    ImageIcon dirt = new ImageIcon("Floor/dirt.jpg");
    ImageIcon TNT = new ImageIcon("Floor/TNT.png");

    ImageIcon flag = new ImageIcon("Flags/Poppy.png");
    ImageIcon obsidian = new ImageIcon("Floor/Obsidian.jpg");
    ImageIcon grass = new ImageIcon("Floor/grass.png");

    public ButtonWorks(int row, int column, Visual visual) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.row = row;
        this.column = column;
        this.visual = visual;
    }
    int clickCount = 10;

    File dirtClip = new File("Audio/dirt.wav");
    AudioInputStream inputStream = null;
    Clip clip2 = null;
    File explosion = new File("Audio/TNT_explosion.wav");
    AudioInputStream inputStream1 = null;
    Clip clip3 = null;
    /**
     * Gets signal from button of the board and starts other methods under some conditions.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        try {
            inputStream = AudioSystem.getAudioInputStream(dirtClip);
        } catch (UnsupportedAudioFileException | IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            clip2 = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }
        try {
            clip2.open(inputStream);
        } catch (LineUnavailableException | IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            inputStream1 = AudioSystem.getAudioInputStream(explosion);
        } catch (UnsupportedAudioFileException | IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            clip3 = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }
        try {
            clip3.open(inputStream1);
        } catch (LineUnavailableException | IOException ex) {
            throw new RuntimeException(ex);
        }
        JButton button = (JButton) e.getSource();
        if(button.getIcon().equals(obsidian)){
            try {
                obsidianBlock(button);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            try {
                clip2.start();
                normalBlock(button);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Handles hint block.
     * @param button
     */
    public void obsidianBlock(JButton button) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(visual.flagMode){
            flagMode(button);
        }else{
            if(clickCount == 0){
                revealCell(button);
            }else {
                clickCount--;
            }
        }
        visual.winCheck();
    }

    /**
     * Handles normal grass block.
     * @param button
     */
    public void normalBlock(JButton button) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(visual.flagMode){
            flagMode(button);
        }else{
            revealCell(button);
        }
        visual.winCheck();
    }

    /**
     * Handles marking mode and is setting a flag.
     * @param button
     */
    public void flagMode(JButton button){
        if(flag.equals(button.getIcon())){
            button.setIcon(null);
            button.setIcon(grass);
        }else{
            button.setIcon(null);
            button.setIcon(flag);
        }
        button.setText("");
        if(visual.mines[row][column]){
            visual.foundMines ++;
        }
    }

    /**
     * reveals all mine cells if mine is found or calls method from class visual that reveals all empty cells.
     * @param button
     */
    public void revealCell(JButton button) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(flag.equals(button.getIcon())){
            return;
        }
        if(visual.mines[row][column]){
            button.setIcon(null);
            button.setIcon(TNT);
            clip3.start();
            visual.revealM();
            visual.lost = true;
            GameOver();
        }else{
            visual.revealEmpty(row, column);
            button.setIcon(null);
            clip2.start();
            button.setIcon(dirt);
            button.isEnabled();
            int count = visual.count(row, column);
            button.setText(count > 0 ? String.valueOf(count) : "");
            button.setEnabled(false);
        }
    }

    /**
     * Handles lost game. Shows message and options, what to do next.
     */
    public void GameOver() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int option = JOptionPane.showOptionDialog(visual, "Game over", "You lost", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Play again", "Menu"}, null);
        if(option == 0){
            visual.dispose();
            new SelectDiff();
        }else if(option == 1){
            visual.dispose();
            new Menu();
        }
    }

}

