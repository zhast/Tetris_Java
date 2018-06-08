/*
Creates a panel and handles all game board activities and user input.
Called by ProgramWindow.
Creates new pieces using GamePiece().
Uses double buffering through offscreen graphics.
 */

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

    /*
    Constructor
    Creates new program panel, initializes board, new piece, and timer
     */
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
        //for each space that the next would occupy, if it has a block in it, the game is over
        for(int i=0; i<nextPiece.getHeight(); i++){
            for(int j = 4; j<4+nextPiece.getWidth(); j++){
                if(board[i][j] != backgroundColor){
                    System.exit(0);
                }
            }
        }
    }

    //checks if any row is full and clears it
    public void boardUpdate(){
        //for each row on the board, check if it is full
        for(int i=0; i<boardHeight; i++){
            if(isFull(board[i])){
                clearRow(i);
            }
        }
        repaint();
    }

    //takes current piece and copies it into the board
    public void placePiece(){
        /*
        i and j describe the indices on the board
        pi and pj describe the corresponding indices of the current piece
        for each space the current piece occupies, paint it onto the board
         */
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

    /*
    Clears selected row and increments everything above on the board down by one row
    Parameter: row to clear
     */
    public void clearRow(int x){
        /*
        starts from the row given by the parameter
        copies all values in the above row to the current row and then moves up to the next row
         */
        for(int i=x; i>0; i--){
            for(int j=0; j<boardWidth; j++){
                board[i][j] = board[i-1][j];
            }
        }
        //reset the top row to background color.
        for(int j=0; j<boardWidth; j++){
            board[0][j] = backgroundColor;
        }
    }

    /*
    Returns true if board row is filled with pieces
    Parameter: row to check
     */
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

    //Returns true if current piece can move right
    public boolean checkRight(){
        //checks if the piece is at the right edge of the board
        if(pieceX+currentPiece.getWidth()>=boardWidth){
            return false;
        }
        //checks if the space ot the right of each block is empty
        for(int i=0; i<currentPiece.pieceArray[currentPiece.getRotation()].length; i++){
            for(int j=0; j<currentPiece.pieceArray[currentPiece.getRotation()][i].length; j++){
                if(currentPiece.pieceArray[currentPiece.getRotation()][i][j] != backgroundColor) {
                    if(board[pieceY+i][pieceX+j+1] != backgroundColor){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Returns true if currentpiece can move left
    public boolean checkLeft(){
        //check if the piece is on the left edge of the board
        if(pieceX<=0){
            return false;
        }
        //checks if the space to the left of each block is empty
        for(int i=0; i<currentPiece.pieceArray[currentPiece.getRotation()].length; i++){
            for(int j=0; j<currentPiece.pieceArray[currentPiece.getRotation()][i].length; j++){
                if(currentPiece.pieceArray[currentPiece.getRotation()][i][j] != backgroundColor) {
                    if (board[pieceY + i][pieceX + j - 1] != backgroundColor) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Returns true if currentpiece can move down
    public boolean checkBelow(){
        //checks if the piece is at the bottom edge of the board
        if(pieceY+currentPiece.getHeight()>=boardHeight){
            return false;
        }
        //checks if space below each block is empty
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

    //moves piece to the right if possible
    public void movePieceRight(){
        if(checkRight()){
            pieceX++;
            repaint();
        }
    }

    //moves piece to the left if possible
    public void movePieceLeft(){
        if(checkLeft()){
            pieceX--;
            repaint();
        }
    }

    //resets current piece location and loads in a new GamePiece
    public void loadPiece(){
        //if nextPiece has not been initialized yet (this only happens once, at the start of the game)
        if(nextPiece==null) {
            currentPiece = new GamePiece((int)(Math.random()*7), backgroundColor) ;
            nextPiece = new GamePiece((int)(Math.random()*7), backgroundColor);
        }
        else { //set the current piece to the next piece, and initialize next piece to a new GamePiece
            checkOver();
            currentPiece = nextPiece;
            nextPiece = new GamePiece((int)(Math.random()*7), backgroundColor);
    }

    //reset position of the current piece
    pieceY = 0;
    pieceX = boardWidth/2 - currentPiece.getWidth()/2;
    }

    /*
    Initializes game board to blank state.
    Called by constructor on game start.
     */
    public void newBoard(Color[][] board){
        //sets each cell to the background color
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                board[i][j] = backgroundColor;
            }
        }
    }

    /*
    Places piece at lowest possible position on board
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

    //allocates memory for off-screen images
    public void paint(Graphics g){
        dim = getSize();
        osi = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
        osg = osi.getGraphics();
        update(g);

    }

    /*
    Performs all drawing on ProgramPanel
    Draws each color on the board and draws the current piece on top.
    Implements double buffering by using off screen graphics
     */
    public void update(Graphics g){
        /*
        x and y describe the coordinates of the boxes to be painted in the program window
        they increment by width and height after painting a box
         */
        int x = 0;
        int y = 0;
        //width and height describe the number of pixels wide and high of each box
        int width = 40;
        int height = 40;
        //pieceI and pieceJ describe the index of the box in the current piece that is being painted
        int pieceI = 0;
        int pieceJ = 0;
        //i and j describe the current index of the board
        for(int i=0; i<board.length; i++){ //i:up/down
            for(int j=0; j<board[0].length; j++){ //j:left/right
                //if i and j describe a location that the current piece occupies, use that piece's color
                if(j>=pieceX && j<pieceX+currentPiece.getWidth() && i>=pieceY && i<pieceY+currentPiece.getHeight()){
                    osg.setColor(currentPiece.pieceArray[currentPiece.getRotation()][pieceI][pieceJ]);
                    pieceJ++;
                    if(pieceJ>=currentPiece.getWidth()){
                        pieceJ = 0;
                        pieceI++;
                    }
                }else{
                    //if i and j describe a location where the current piece does not occupy, use the board's color at that index instead
                    osg.setColor(board[i][j]);
                }
                if(board[i][j]!=backgroundColor) osg.setColor(board[i][j]);
                osg.fillRect(x, y, width, height);

                //draw outline of the gameboard
                osg.setColor(Color.lightGray);
                osg.drawRect(x, y, width, height);
                x += width;
            }
            y += height;
            x = 0;
        }
        //draw the off-screen image to the Program Panel
        g.drawImage(osi, 0,0,this);
    }

    //handles key inputs
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode){
            //when user presses right arrow, move piece right
            case KeyEvent.VK_RIGHT:
                movePieceRight();
                break;
            //when user presses left arrow, move piece left
            case KeyEvent.VK_LEFT:
                movePieceLeft();
                break;
            //when user presses down arrow, move piece down
            case KeyEvent.VK_DOWN:
                movePieceDown();
                break;
            //when user presses up arrow rotate piece to the right
            case KeyEvent.VK_UP:
                currentPiece.rotateRight();
                //make sure the rotated piece does not go out of the board
                while(pieceX + currentPiece.getWidth()>boardWidth){
                    pieceX--;
                }
                repaint();
                break;
            //when user presses c key, rotate piece to the left
            case KeyEvent.VK_C:
                currentPiece.rotateLeft();
                //make sure the rotated piece does not go out of the board
                while(pieceX + currentPiece.getWidth()>=boardWidth){
                    pieceX--;
                }
                repaint();
                break;
            //when user presses spacebar, drop piece
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
