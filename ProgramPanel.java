
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class ProgramPanel extends Panel implements MouseListener, KeyListener{
    Dimension dim = null;
    int boardHeight = 20;
    int boardWidth = 10;
    Color[][] board = new Color[boardHeight][boardWidth]; //[height][width]
    GamePiece currentPiece;
    int pieceX, pieceY;
    private long delay = 500;
    private long period = 1000;
    private Color backgroundColor = Color.black;
    BufferedImage osi;
    Graphics osg;

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
                boardUpdate();
            }
        };
        tm.scheduleAtFixedRate(task, delay, period);
    }

    public void boardUpdate(){
        for(int i=0; i<boardHeight; i++){
            if(isFull(board[i])){
                clearRow(i);
            }
        }
        repaint();
    }

    //clear's selected row and increments everything above on the board down by one row
    public void clearRow(int x){
        for(int i=x; i>0; i--){
            for(int j=0; j<boardWidth; j++){
                board[i][j] = board[i-1][j];
            }
        }
        for(int j=0; j<boardWidth; j++){
            board[0][j] = Color.black;
        }
    }

    //checks if board row is filled with pieces
    public boolean isFull(Color[] row){
        for(Color c:row){
            if(c==backgroundColor) return false;
        }
        return true;
    }

    public void movePieceDown(){
        if(checkBelow()){
            pieceY++;
        }else{
            int pi=0;
            for(int i=pieceY; i<pieceY+currentPiece.getHeight(); i++){
                int pj=0;
                for(int j=pieceX; j<pieceX+currentPiece.getWidth(); j++){
                    if(board[i][j] == Color.black)board[i][j] = currentPiece.pieceArray[currentPiece.getRotation()][pi][pj];
                    pj++;
                }
                pi++;
            }
            boardUpdate();
            loadPiece();
        }
        repaint();
    }
    //checks if currentpiece can move right
    public boolean checkRight(){
        if(pieceX+currentPiece.getWidth()>=boardWidth){
            return false;
        }
        for(int i=0; i<currentPiece.pieceArray[currentPiece.getRotation()].length; i++){
            for(int j=0; j<currentPiece.pieceArray[currentPiece.getRotation()][i].length; j++){
                if(board[pieceY+i][pieceX+j+1] != backgroundColor){
                    return false;
                }
            }
        }
        return true;
    }

    //checks if currentpiece can move left
    public boolean checkLeft(){
        if(pieceX<=0){
            return false;
        }
        for(int i=0; i<currentPiece.pieceArray[currentPiece.getRotation()].length; i++){
            for(int j=0; j<currentPiece.pieceArray[currentPiece.getRotation()][i].length; j++){
                if(board[pieceY+i][pieceX+j-1] != backgroundColor){
                    return false;
                }
            }
        }
        return true;
    }

    //checks if currentpiece can move down
    public boolean checkBelow(){
        if(pieceY+currentPiece.getHeight()>=boardHeight){
            return false;
        }
        for(int i=0; i<currentPiece.pieceArray[currentPiece.getRotation()].length; i++){
            for(int j=0; j<currentPiece.pieceArray[currentPiece.getRotation()][i].length; j++){
                if(currentPiece.pieceArray[currentPiece.getRotation()][i][j] != backgroundColor) {
                    if (board[pieceY + i + 1][pieceX + j] != backgroundColor) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void movePieceRight(){
        if(checkRight()){
            pieceX++;
            repaint();
        }
    }

    public void movePieceLeft(){
        if(checkLeft()){
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

    //places piece at lowest possible position on board
    public void dropPiece(){
        while(checkBelow()){
            pieceY++;
        }
        repaint();
    }

    public void paint(Graphics g){
        dim = getSize();
        osi = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
        osg = osi.getGraphics();
        update(g);

    }

    public void update(Graphics g){
        System.out.println("updating");
        int x = 0;
        int y = 0;
        int width = 40;
        int height = 40;
        int pieceI = 0;
        int pieceJ = 0;
        for(int i=0; i<board.length; i++){ //i=updown
            for(int j=0; j<board[0].length; j++){ //j=leftright
                if(j>=pieceX && j<pieceX+currentPiece.getWidth() && i>=pieceY && i<pieceY+currentPiece.getHeight()){
                    osg.setColor(currentPiece.pieceArray[currentPiece.getRotation()][pieceI][pieceJ]);
                    pieceJ++;
                    if(pieceJ>=currentPiece.getWidth()){
                        pieceJ = 0;
                        pieceI++;
                    }
                }else{
                    osg.setColor(board[i][j]);
                }
                if(board[i][j]!=Color.black) osg.setColor(board[i][j]);
                osg.fillRect(x, y, width, height);
                osg.setColor(Color.lightGray);
                osg.drawRect(x, y, width, height);
                x += width;
            }
            y += height;
            x = 0;
        }
        g.drawImage(osi, 0,0,this);

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
            case KeyEvent.VK_UP:
                currentPiece.rotateRight();
                repaint();
                break;
            case KeyEvent.VK_C:
                currentPiece.rotateLeft();
                repaint();
                break;
            case KeyEvent.VK_SPACE:
                dropPiece();
                break;
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
