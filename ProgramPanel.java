import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class ProgramPanel extends Panel implements KeyListener{
    Dimension dim = null;

    //dimensions of the board (number of cells wide/high)
    int boardHeight = 20;
    int boardWidth = 10;

    Color[][] board = new Color[boardHeight][boardWidth]; //2d Color array that describes the board state

    GamePiece currentPiece; //the current piece being controlled by the user
    GamePiece nextPiece; //the next piece

    int pieceX, pieceY; //location of the top left corner of the current piece on the board

    //delay and period for the regularly scheduled tasks
    private long delay = 500;
    private long period = 1000;

    private Color backgroundColor = Color.black; //background color of the board

    //off screen graphics and image for double buffered painting
    BufferedImage osi;
    Graphics osg;

    //Constructor
    public ProgramPanel(){
        newBoard(board); //initialize the board
        setBackground(Color.lightGray);
        addKeyListener(this); //add key listener to the panel
        loadPiece(); //load new piece

        //create timer to regularly drop piece
        Timer tm = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                //moves piece down at a scheduled rate
                movePieceDown();
            }
        };
        tm.scheduleAtFixedRate(task, delay, period);
    }

    //exits the game if next piece cannot be placed (if game is over)
    public void checkOver(){
        System.out.println(nextPiece.getHeight());
        for(int i=0; i<nextPiece.getHeight(); i++){
            for(int j = 4; j<4+nextPiece.getWidth(); j++){
                if(board[i][j] != backgroundColor){
                    System.exit(0);
                }
            }
        }
    }


    public void boardUpdate(){
        for(int i=0; i<boardHeight; i++){
            if(isFull(board[i])){
                clearRow(i);
            }
        }
        repaint();
    }

    //takes current piece and copies it into the board
    public void placePiece(){
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
    }

    //clears selected row and increments everything above on the board down by one row
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

    //returns true if board row is filled with pieces
    public boolean isFull(Color[] row){
        for(Color c:row){
            if(c==backgroundColor) return false;
        }
        return true;
    }

    //moves piece down if possible. otherwise calls placePiece(), boardUpdate(), and loadPiece()
    public void movePieceDown(){
        if(checkBelow()){
            pieceY++;
        }else{
            placePiece();
            loadPiece();
            boardUpdate();
        }
        repaint();
    }

    //returns true if currentpiece can move right
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

    //returns true if currentpiece can move left
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

    //returns true if currentpiece can move down
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

    //moves piece to the right
    public void movePieceRight(){
        if(checkRight()){
            pieceX++;
            repaint();
        }
    }

    //moves piece to the left
    public void movePieceLeft(){
        if(checkLeft()){
            pieceX--;
            repaint();
        }
    }

    //resets current piece location and loads in a new GamePiece
    public void loadPiece(){
        if(nextPiece==null) {
            currentPiece = new GamePiece((int)(Math.random()*7), backgroundColor) ;
            nextPiece = new GamePiece((int)(Math.random()*7), backgroundColor);
        }
        else {
            checkOver();
            currentPiece = nextPiece;
            nextPiece = new GamePiece((int)(Math.random()*7), backgroundColor);
        }
        pieceY = 0;
        pieceX = 4;

    }

    //initializes game board to blank state
    public void newBoard(Color[][] board){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                board[i][j] = backgroundColor;
            }
        }
    }

    /*
    places piece at lowest possible position on board
    Calls boardUpdate(), loadPiece()
     */
    public void dropPiece(){
        while(checkBelow()){
            pieceY++;
        }
        placePiece();
        boardUpdate();
        loadPiece();
        repaint();
    }

    public void paint(Graphics g){
        dim = getSize();
        osi = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
        osg = osi.getGraphics();
        update(g);

    }

    //implements double buffering by using off screen graphics
    public void update(Graphics g){
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

    //handles key inputs
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
                while(pieceX + currentPiece.getWidth()>boardWidth){
                    pieceX--;
                }
                repaint();
                break;
            case KeyEvent.VK_C:
                currentPiece.rotateLeft();
                while(pieceX + currentPiece.getWidth()>=boardWidth){
                    pieceX--;
                }
                repaint();
                break;
            case KeyEvent.VK_SPACE:
                dropPiece();
                break;
        }
    }



    //methods below are not used
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
