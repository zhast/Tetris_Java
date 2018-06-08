/*
Main Class, contains the main method that is called by java first.
Creates a new frame using programWindow()
 */
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        //creates a new window using ProgramWindow
        ProgramWindow task = new ProgramWindow();

        //window and program exits when 'x' button is clicked
        task.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
