package TetrisPractice;

import java.awt.*;
import java.awt.event.*;

public class ProgramPanel extends Panel implements MouseListener, KeyListener{
    Dimension dim = null;
    TetrisGame game;
    public ProgramPanel(){
        setBackground(Color.lightGray);
        game = new TetrisGame();
        addMouseListener(this);
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
