/*
Describes each tetromino piece
Contains methods to manipulate shape and rotation of the tetromino
Created by ProgramPanel
 */
import java.awt.*;

public class GamePiece {
    public Color[][][] pieceArray; //3d Color array holding the shape of each piece: [rotation][row][column]
    private int rotation; //index of current piece's rotation
    private Color background; //color of background

    /*
    Constructor
    Initializes a new GamePieces randomly to one of the 7 possible pieces
    Parameters: id of shape - random int between 0 and 7
                background color
     */
    public GamePiece(int n, Color background){
        this.background = background;
        rotation = 0;
        if(n==0){ //line piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {new Color(0, 255, 255)},
                            {new Color(0, 255, 255)},
                            {new Color(0, 255, 255)},
                            {new Color(0, 255, 255)}
                    },
                    {//second rotation
                            {new Color(0, 255, 255), new Color(0, 255, 255), new Color(0, 255, 255), new Color(0, 255, 255),}
                    }

            };
        }else if(n==1){//L piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {Color.orange, background},
                            {Color.orange, background},
                            {Color.orange, Color.orange},
                    },
                    {//second rotation
                            {Color.orange, Color.orange, Color.orange},
                            {Color.orange, background, background}
                    },
                    {//third rotation
                            {Color.orange, Color.orange},
                            {background, Color.orange},
                            {background, Color.orange}
                    },
                    {//fourth rotation
                            {background, background, Color.orange},
                            {Color.orange, Color.orange, Color.orange}
                    }
            };
        }else if(n==2){//J piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {background, Color.blue},
                            {background, Color.blue},
                            {Color.blue, Color.blue},
                    },
                    {//second rotation
                            {Color.blue, background, background},
                            {Color.blue, Color.blue, Color.blue}
                    },
                    {//third rotation
                            {Color.blue, Color.blue},
                            {Color.blue, background},
                            {Color.blue, background}
                    },
                    {//fourth rotation
                            {Color.blue, Color.blue, Color.blue},
                            {background, background, Color.blue}
                    }
            };
        }else if(n==3){//T piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {background, new Color(153, 0, 255), background},
                            {new Color(153, 0, 255), new Color(153, 0, 255), new Color(153, 0, 255)},
                    },
                    {//second rotation
                            {new Color(153, 0, 255), background},
                            {new Color(153, 0, 255), new Color(153, 0, 255)},
                            {new Color(153, 0, 255), background}
                    },
                    {//third rotation
                            {new Color(153, 0, 255), new Color(153, 0, 255), new Color(153, 0, 255)},
                            {background, new Color(153, 0, 255), background}
                    },
                    {//fourth rotation
                            {background, new Color(153, 0, 255)},
                            {new Color(153, 0, 255), new Color(153, 0, 255)},
                            {background, new Color(153, 0, 255)}
                    }
            };
        }else if(n==4){//O piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {Color.yellow, Color.yellow},
                            {Color.yellow, Color.yellow},
                    }
            };
        }else if(n==5){//S piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {background, Color.green, Color.green},
                            {Color.green, Color.green, background},
                    },
                    {//second rotation
                            {Color.green, background},
                            {Color.green, Color.green},
                            {background, Color.green}
                    }
            };
        }else if(n==6){//Z piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {Color.red, Color.red, background},
                            {background, Color.red, Color.red},
                    },
                    {//second rotation
                            {background, Color.red},
                            {Color.red, Color.red},
                            {Color.red, background}
                    }
            };
        }
    }

    //returns the width of the current rotation of the piece
    public int getWidth(){
        return pieceArray[rotation][0].length;
    }

    //returns the height of the current rotation of the piece
    public int getHeight(){
        return pieceArray[rotation].length;
    }

    //rotates the piece to the right
    public void rotateRight(){
        rotation = (rotation+1)%(pieceArray.length);
    }

    //rotates the piece to the left
    public void rotateLeft(){
        int x = rotation-1; //temporary variable
        if(x<0){
            rotation = pieceArray.length + x;
        }else rotation = x%pieceArray.length;
    }

    //returns the current rotation of the piece
    public int getRotation(){
        return rotation;
    }
}
