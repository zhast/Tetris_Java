
import java.awt.*;

public class GamePiece {
    public Color[][][] pieceArray; //[rotation][row][col]
    private int rotation;
    private Color background;
    public GamePiece(int n, Color background){
        this.background = background;
        rotation = 0;
        if(n==0){ //line piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {new Color(0, 255, 255), background, background, background },
                            {new Color(0, 255, 255), background, background, background },
                            {new Color(0, 255, 255), background, background, background },
                            {new Color(0, 255, 255), background, background, background }
                    },
                    {//second rotation
                            {background, background, background, background},
                            {background, background, background, background},
                            {background, background, background, background},
                            {new Color(0, 255, 255), new Color(0, 255, 255), new Color(0, 255, 255), new Color(0, 255, 255),}
                    }

            };
        }else if(n==1){//L piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {Color.orange, background, background},
                            {Color.orange, background, background},
                            {Color.orange, Color.orange, background},
                    },
                    {//second rotation
                            {background, background, background},
                            {background, background, Color.orange},
                            {Color.orange, Color.orange, Color.orange}
                    },
                    {//third rotation
                            {Color.orange, Color.orange, background},
                            {background, Color.orange, background},
                            {background, Color.orange, background}
                    },
                    {//fourth rotation
                            {background, background, background},
                            {Color.orange, Color.orange, Color.orange},
                            {Color.orange, background, background}
                    }
            };
        }else if(n==2){//J piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {background, background, Color.blue},
                            {background, background, Color.blue},
                            {background, Color.blue, Color.blue},
                    },
                    {//second rotation
                            {background, background, background},
                            {Color.blue, Color.blue, Color.blue},
                            {background, background, Color.blue}
                    },
                    {//third rotation
                            {background, Color.blue, Color.blue},
                            {background, Color.blue, background},
                            {background, Color.blue, background}
                    },
                    {//fourth rotation
                            {background, background, background},
                            {Color.blue, Color.blue, Color.blue},
                            {Color.blue, background, background}
                    }
            };
        }else if(n==3){//T piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {background, background, background},
                            {background, new Color(153, 0, 255), background},
                            {new Color(153, 0, 255), new Color(153, 0, 255), background},
                    },
                    {//second rotation
                            {background, background, new Color(153, 0, 255)},
                            {background, new Color(153, 0, 255), new Color(153, 0, 255)},
                            {background, background, new Color(153, 0, 255)}
                    },
                    {//third rotation
                            {new Color(153, 0, 255), new Color(153, 0, 255), new Color(153, 0, 255)},
                            {background, new Color(153, 0, 255), background},
                            {background, background, background}
                    },
                    {//fourth rotation
                            {new Color(153, 0, 255), background, background},
                            {new Color(153, 0, 255), new Color(153, 0, 255), background},
                            {new Color(153, 0, 255), background, background}
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
                            {background, background, background},
                            {background, Color.green, Color.green},
                            {Color.green, Color.green, background},
                    },
                    {//second rotation
                            {Color.green, background, background},
                            {Color.green, Color.green, background},
                            {background, Color.green, background}
                    }
            };
        }else if(n==6){//Z piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {background, background, background},
                            {Color.red, Color.red, background},
                            {background, Color.red, Color.red},
                    },
                    {//second rotation
                            {background, Color.red, background},
                            {Color.red, Color.red, background},
                            {Color.red, background, background}
                    }
            };
        }
    }
    public int getWidth(){
        return pieceArray[0][0].length;
    }
    public int getHeight(){
        return pieceArray[0].length;
    }
    public void rotateRight(){
        rotation = (rotation+1)%(pieceArray.length);
    }
    public void rotateLeft(){
        int x = rotation-1;
        if(x<0){
            rotation = pieceArray.length + 1 + x;
        }else rotation = x%pieceArray.length;
    }
    public int getRotation(){
        return rotation;
    }
}
