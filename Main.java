
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        ProgramWindow task = new ProgramWindow();
        task.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
