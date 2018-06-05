
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class ProgramPanel extends Panel implements MouseListener, KeyListener{
    Dimension dim = null;
    int boardHeight = 20;
    int boardWidth = 10;
    Color[][] board = new Color[boardHeight][boardWidth];
    GamePiece currentPiece;
    int pieceX, pieceY;
    private long delay = 500;
    private long period = 250;
    private Color backgroundColor = Color.black;
    public ProgramPanel(){
        newBoard(board);
        setBackground(Color.lightGray);
        addMouseListener(this);
        addKeyListener(this);
        loadPiece();
        Timer tm = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                movePieceDown();
            }
        };
        tm.scheduleAtFixedRate(task, delay, period);
    }

    public void movePieceDown(){
        if(pieceY+currentPiece.getHeight()<boardHeight && board[pieceY+currentPiece.getHeight()][pieceX]==backgroundColor){
            pieceY++;
        }else{
            int pi=0;
            System.out.println(pieceY + " " + currentPiece.getHeight());
            for(int i=pieceY; i<pieceY+currentPiece.getHeight(); i++){
                System.out.println("here");
                int pj=0;
                for(int j=pieceX; j<pieceX+currentPiece.getWidth(); j++){
                    System.out.println("changing");
                    board[i][j] = currentPiece.pieceArray[currentPiece.getRotation()][pi][pj];
                    pj++;
                }
                pi++;
            }
            loadPiece();
        }
        repaint();
    }

    public void movePieceRight(){
        if(pieceX+currentPiece.getWidth()<=boardWidth && board[pieceY][pieceX+currentPiece.getWidth()]==backgroundColor){
            pieceX++;
            repaint();
        }
    }

    public void movePieceLeft(){
        if(pieceX-1>=0 && board[pieceY][pieceX-1]==backgroundColor){
            pieceX--;
            repaint();
        }
    }

    public void loadPiece(){
        pieceY = 0; //updown
        int x = (int)(Math.random()*6);
        System.out.println(x);
        currentPiece = new GamePiece(x, backgroundColor);
        pieceX = 4; //leftright
    }

    public void newBoard(Color[][] board){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                board[i][j] = backgroundColor;
            }
        }
    }

    public void paint(Graphics g){
        System.out.println("painting");
        int x = 100;
        int y = 100;
        int width = 40;
        int height = 40;
        int pieceI = 0;
        int pieceJ = 0;
        for(int i=0; i<board.length; i++){ //i=updown
            for(int j=0; j<board[0].length; j++){ //j=leftright
                if(j>=pieceX && j<pieceX+currentPiece.getWidth() && i>=pieceY && i<pieceY+currentPiece.getHeight()){
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
        int keyCode = e.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_RIGHT:
                movePieceRight();
                break;
            case KeyEvent.VK_LEFT:
                movePieceLeft();
                break;
            case KeyEvent.VK_DOWN:
                movePieceDown();
                break;
            /*case KeyEvent.VK_UP:
                currentPiece.rotateRight();
                repaint();
                break;
*/
        }
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
