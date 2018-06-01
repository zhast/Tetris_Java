# Tetris_Java
ICS4U Project 

Description of Program
Tetris is a tile-matching puzzle game released in 1984. It is composed of a 10 x 20 block board where 4-block shapes fall and stack onto each other. 

Description Annotations 
1. Finish main structure

Main.java
Imports packages needed to run Tetris in Java and sets up the class Main 

ProgramPanel.java
Create class ProgramPanel that extends Panel and implements MouseListener and KeyListener. These files are necessary to parse keyboard and mouse inputs into commands in the game. 

ProgramWindow.java
Sets the dimensions for the window that Tetris runs in.

GamePiece.java
Blocks in java were hard coded into a 3D array. Blocks that are filled are assigned colours

	TetrisGame.java
	Contains the logic for Tetris, such as number of pieces, game functions, and score. 

2. Display empty board
	ProgramPanel.java
	Add the paint and newBoard function, which allows the Tetris board to be created. 
	All game logic in TetrisGame was moved to ProgramPanel for simplicity. 
	

