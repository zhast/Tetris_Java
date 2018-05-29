package TetrisPractice;

import java.awt.*;

public class ProgramWindow extends Frame {
    ProgramPanel panel = new ProgramPanel();
    public ProgramWindow(){
        add(panel);
        setTitle("Tetris");
        setSize(1000, 1000);
        setLocation(100, 100);
        setResizable(true);
        setVisible(true);
    }
}
