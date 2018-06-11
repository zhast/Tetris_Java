# Tetris_Java
ICS4U SUMMATIVE PROJECT
Programming TETRIS in Java
by Will and Steven 


1. Description of Program


TETRIS is a tile-matching puzzle game released in 1984. It gets its name from the greek prefix “tetra” because TETRIS is composed of 4-block shapes fall and stack onto each other on a 10 x 20 block rectangular grid. The goal of the game is to drop these 4-block shapes to complete a row, which gains the player points. The game ends when the next tetromino exceeds the top layer of the grid. 

After the user compiles and runs the code, a ProgramWindow object is created so the user can see the window on the monitor and the game starts immediately. The ProgramWindow creates a new ProgramPanel, which shows the game board. When the grid is created, any necessary timers are started and the game begins. A 4-block tetromino will be spawned at the top of the grid and proceed downwards at a constant pace until it stops because it lands on either the bottom of the board or on a pre-existing piece. A new piece will be spawned as soon as the last piece stops moving. The game ends when another block can not be placed without overlapping existing blocks. 

The tetrominoes come in shapes known as I, T, O, L, J, S, and Z. These tetrominoes fall from the top of the grid to the bottom of the grid. When a full row is filled by blocks, the clearRow function is called and the row disappears gains points. Tetrominoes can be manipulated by rotation 90 degrees by pressing the “up-arrow” key to rotates the tetromino right and the “C” key to rotate the tetromino left. The checkRight and checkLeft functions are called to ensure that tetrominoes stay within the grid and a rotation is possible. Tetrominoes can be dropped to the bottom of the grid when the “spacebar” key is pressed, which calls the dropPiece function. TETRIS pieces drop down at a constant speed unless the user commands the tetrominoes to drop faster by pressing the “down-arrow” key, which calls the movePieceDown function. Together, these functions in the class ProgramPanel translate the user’s inputs into the game to manipulate the blocks. 




2. Analysis of the Programs



There are 4 classes required to make TETRIS run properly in Java. 

Main.java is where the program starts. It imports packages needed to run TETRIS in Java and uses any other classes required to make TETRIS run. Main.java also initiates the program by creating a new frame using the ProgramWindow object, which makes the game visible to the player. A window listener is added to the window to allow the user to close the window.

GamePiece.java contains the information for all the blocks in TETRIS. GamePiece uses a 3-dimensional array to represent one of the seven different possible tetrominoes. The 3-dimensional array can be visualized as an array of 2-dimensional arrays. Each 2-dimensional arrays  Different colours are assigned to each block. Rotations functions are contained in this class. 

Class GamePiece has a constructor that initializes a new tetromino randomly from the seven possible pieces. The seven pieces are known as I, T, O, L, J, S, and Z, which are all stored in the three dimensional array. The pieceArray contains the rotation data for which blocks need to be painted when the rotation functions are called. Unfilled blocks are painted the background colour. 

The public functions getWidth, getHeight, rotateRight, rotateLeft, and getRotation are also part of the GamePiece class. The getWidth and getHeight functions returns the width or height of the current rotation pieces, the rotateRight and rotateLeft functions rotates the piece left or right, and finally the getRotaion functions returns the current rotation of the piece for other classes to call on. 

ProgramWindow.java describes the game window for TETRIS. ProgramWindow sets the dimensions for the window that TETRIS runs in and the title of the window. Once the a ProgramWindow object is created, the code in ProgramWindow.java creates a new ProgramPanel, which displays the game. The ProgramWindow has a default name, size and location on the monitor. 

ProgramPanel.java create class ProgramPanel that extends Panel and implements KeyListener. ProgramPanel is responsible for displaying the game board, handling user keyboard inputs, and all the movements and behaviours of blocks on the board. This class initializes a game board to blank state and is called by the constructor when on game start. 

The most important role of ProgramPanel is to use KeyListener to parse keyboard inputs into commands in the game. The “down-arrow” key, which triggers the function movePieceDown moves the current tetromino down and activates checkBelow to ensure moving the piece down is possible, the “left-arrow” and “right-arrow” key triggers the checkRight and checkLeft functions respectively and ensures that the space to the left or right of the board is empty so the piece can be rotated, and the “spacebar” key triggers the dropPiece function, which also triggers the checkBelow function to place the current piece on the bottom of the board. 

ProgramPanel also contains the logic in the game, such as the loadPiece function, which generates a new GamePiece, the clearRow function which removes a row given by the parameter when it is full, function paint, which allocates memory for off-screen images, function boardUpdate, which checks if any row is full and clears it, and finally the function update, which performs all drawing on ProgramPanel.

ProgramPanel contains all the variables required to run Tetris, such as the boardWidth and boardHeight, representing the 10 x 20 grid. The currentPiece and gamePiece variables are constantly updated when being controlled by the user. 

ProgramPanel is also responsible for rendering the game to display to the user. An off-screen BufferedImage using off-screen graphics is created to send a complete frame for the user so no flashing and tearing is present when TETRIS is being played. 