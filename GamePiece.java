
import java.awt.*;

public class GamePiece {
    private Color[][][] pieceArray;
    public GamePiece(int n){
        if(n==0){ //line piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {new Color(0, 255, 255), null, null, null },
                            {new Color(0, 255, 255), null, null, null },
                            {new Color(0, 255, 255), null, null, null },
                            {new Color(0, 255, 255), null, null, null }
                    },
                    {//second rotation
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null},
                            {new Color(0, 255, 255), new Color(0, 255, 255), new Color(0, 255, 255), new Color(0, 255, 255),}
                    }

            };
        }else if(n==1){//L piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {Color.orange, null, null},
                            {Color.orange, null, null},
                            {Color.orange, Color.orange, null},
                    },
                    {//second rotation
                            {null, null, null},
                            {null, null, Color.orange},
                            {Color.orange, Color.orange, Color.orange}
                    },
                    {//third rotation
                            {Color.orange, Color.orange, null},
                            {null, Color.orange, null},
                            {null, Color.orange, null}
                    },
                    {//fourth rotation
                            {null, null, null},
                            {Color.orange, Color.orange, Color.orange},
                            {Color.orange, null, null}
                    }
            };
        }else if(n==2){//J piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {null, null, Color.blue},
                            {null, null, Color.blue},
                            {null, Color.blue, Color.blue},
                    },
                    {//second rotation
                            {null, null, null},
                            {Color.blue, Color.blue, Color.blue},
                            {null, null, Color.blue}
                    },
                    {//third rotation
                            {null, Color.blue, Color.blue},
                            {null, Color.blue, null},
                            {null, Color.blue, null}
                    },
                    {//fourth rotation
                            {null, null, null},
                            {Color.blue, Color.blue, Color.blue},
                            {Color.blue, null, null}
                    }
            };
        }else if(n==3){//T piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {null, null, null},
                            {null, new Color(153, 0, 255), null},
                            {new Color(153, 0, 255), new Color(153, 0, 255), null},
                    },
                    {//second rotation
                            {null, null, new Color(153, 0, 255)},
                            {null, new Color(153, 0, 255), new Color(153, 0, 255)},
                            {null, null, new Color(153, 0, 255)}
                    },
                    {//third rotation
                            {new Color(153, 0, 255), new Color(153, 0, 255), new Color(153, 0, 255)},
                            {null, new Color(153, 0, 255), null},
                            {null, null, null}
                    },
                    {//fourth rotation
                            {new Color(153, 0, 255), null, null},
                            {new Color(153, 0, 255), new Color(153, 0, 255), null},
                            {new Color(153, 0, 255), null, null}
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
                            {null, null, null},
                            {null, Color.green, Color.green},
                            {Color.green, Color.green, null},
                    },
                    {//second rotation
                            {Color.green, null, null},
                            {Color.green, Color.green, null},
                            {null, Color.green, null}
                    }
            };
        }else if(n==6){//Z piece
            pieceArray = new Color[][][]{
                    {//first rotation
                            {null, null, null},
                            {Color.red, Color.red, null},
                            {null, Color.red, Color.red},
                    },
                    {//second rotation
                            {null, Color.red, null},
                            {Color.red, Color.red, null},
                            {Color.red, null, null}
                    }
            };
        }
    }
}
