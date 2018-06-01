
import java.awt.*;

public class ProgramWindow extends Frame {
    ProgramPanel panel = new ProgramPanel();
    public ProgramWindow(){
        add(panel);
        setTitle("Tetris - Will Lu & Steven Zhang");
        setSize(800, 1000);
        setLocation(100, 100);
        setResizable(false);
        setVisible(true);
    }
}
