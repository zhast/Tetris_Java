
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
                            {background, background, Color.orange},
                            {Color.orange, Color.orange, Color.orange}
                    },
                    {//third rotation
                            {Color.orange, Color.orange},
                            {background, Color.orange},
                            {background, Color.orange}
                    },
                    {//fourth rotation
                            {Color.orange, Color.orange, Color.orange},
                            {Color.orange, background, background}
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
                            {Color.blue, Color.blue, Color.blue},
                            {background, background, Color.blue}
                    },
                    {//third rotation
                            {Color.blue, Color.blue},
                            {Color.blue, background},
                            {Color.blue, background}
                    },
                    {//fourth rotation
                            {Color.blue, Color.blue, Color.blue},
                            {Color.blue, background, background}
                    }
            };
        }else if(n==3){//T piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {background, new Color(153, 0, 255), background},
                            {new Color(153, 0, 255), new Color(153, 0, 255), new Color(153, 0, 255)},
                    },
                    {//second rotation
                            {background, new Color(153, 0, 255)},
                            {new Color(153, 0, 255), new Color(153, 0, 255)},
                            {background, new Color(153, 0, 255)}
                    },
                    {//third rotation
                            {new Color(153, 0, 255), new Color(153, 0, 255), new Color(153, 0, 255)},
                            {background, new Color(153, 0, 255), background}
                    },
                    {//fourth rotation
                            {new Color(153, 0, 255), background},
                            {new Color(153, 0, 255), new Color(153, 0, 255)},
                            {new Color(153, 0, 255), background}
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
    public int getWidth(){
        return pieceArray[rotation][0].length;
    }
    public int getHeight(){
        return pieceArray[rotation].length;
    }
    public void rotateRight(){
        rotation = (rotation+1)%(pieceArray.length);
    }
    public void rotateLeft(){
        int x = rotation-1;
        if(x<0){
            rotation = pieceArray.length + x;
        }else rotation = x%pieceArray.length;
    }
    public int getRotation(){
        return rotation;
    }
    public boolean canMoveRight(){
        for(int i=0; i<pieceArray[rotation].length; i++){
            if(pieceArray[rotation][i][pieceArray[rotation][i].length-1]!=background){
                return false;
            }
        }
        return true;
    }
}
