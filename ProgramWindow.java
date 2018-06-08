/*
Called by Main.java
Creates a frame and adds panel to it using ProgramPanel.
Sets default variables of frame like title, size, etc.
 */
import java.awt.*;

public class ProgramWindow extends Frame {
    ProgramPanel panel = new ProgramPanel();
    public ProgramWindow(){
        //add ProgramPanel to the frame
        add(panel);
        setTitle("Tetris - Will Lu & Steven Zhang");
        setSize(405, 830);
        setLocation(200, 100);
        setResizable(false);
        setVisible(true);
    }
}
