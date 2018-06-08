
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
