
import java.awt.*;
import java.awt.event.*;

public class ProgramPanel extends Panel implements MouseListener, KeyListener{
    Dimension dim = null;
    Color[][] board = new Color[20][10];
    public ProgramPanel(){
        newBoard(board);
        setBackground(Color.lightGray);
        addMouseListener(this);
        addKeyListener(this);
    }

    public void newBoard(Color[][] board){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                board[i][j] = Color.black;
            }
        }
    }

    public void paint(Graphics g){
        int x = 100;
        int y = 100;
        int width = getSize().width - 200;
        int height = getSize().height;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                g.setColor(board[i][j]);
                g.fillRect(x, y, width/board[0].length, height/board.length);
                g.setColor(Color.lightGray);
                g.drawRect(x, y, width/board[0].length, height/board.length);
                x += width/board[0].length;
            }
            y += height/board.length;
            x = 100;
        }
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
