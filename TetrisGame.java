package TetrisPractice;

import java.awt.*;

public class TetrisGame {
    private Panel panel;
    private Color[][] board;
    private int pieceX, pieceY;
    public TetrisGame(){
        board = new Color[10][10];
        pieceX = 3;
        pieceY = 0;
        System.out.println(panel.getHeight());
    }
    public void moveDown(){
        pieceY++;
    }
    public void display(){

    }
}
