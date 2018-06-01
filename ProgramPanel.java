
import java.awt.*;
import java.awt.event.*;

public class ProgramPanel extends Panel implements MouseListener, KeyListener{
    Dimension dim = null;
    Color[][] board = new Color[20][10];
    GamePiece currentPiece;
    int pieceX, pieceY;
    public ProgramPanel(){
        newBoard(board);
        setBackground(Color.lightGray);
        addMouseListener(this);
        addKeyListener(this);
        loadPiece();
    }

    public void loadPiece(){
        pieceY = 0;
        int x = (int)(Math.random()*6);
        System.out.println(x);
        currentPiece = new GamePiece(x, Color.black);
        pieceX = 4;
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
        int width = 40;
        int height = 40;
        int pieceI = 0;
        int pieceJ = 0;
        for(int i=0; i<board.length; i++){ //i=updown
            for(int j=0; j<board[0].length; j++){ //j=leftright
                if(j>=pieceX && j<pieceX+currentPiece.getWidth() && i>=pieceY && i<currentPiece.getHeight()){
                    g.setColor(currentPiece.pieceArray[currentPiece.getRotation()][pieceI][pieceJ]);
                    pieceJ++;
                    if(pieceJ>=currentPiece.getWidth()){
                        pieceJ = 0;
                        pieceI++;
                    }
                }else{
                    g.setColor(board[i][j]);
                }
                g.fillRect(x, y, width, height);
                g.setColor(Color.lightGray);
                g.drawRect(x, y, width, height);
                x += width;
            }
            y += height;
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
