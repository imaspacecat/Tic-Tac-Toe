import java.util.Random;

class Board{
  final private int width;
  final private int height;
  private char[][] board;

  public Board(int width, int height)
  {
     this.width = width;
     this.height = height;
     board = new char[width][height];

     setUpBoard();
  }


  public boolean processInput(int i, char a){
       
    int squareY = i % 3;
    // i = 3 * squareY + squareX;
    int squareX = (i - squareY)/3;

    //the equations are i = 3y + x and i % 3 = x
    if(board[squareX][squareY] == '-'){
      board[squareX][squareY] = a;
      return false;

    } else {
      return true;
    }

  }

  private void setUpBoard(){
    
    for(int x = 0; x < width; x++){
      for(int y = 0; y < height; y++){
        board[x][y] = '-';
      }
    }
    
    }
    
    public void printBoard(){
      
      for(int x = 0; x < height; x++){
        //for(int x = 0; x < width; x++){
        String ln = new String(board[x]);
        System.out.println(ln);
        //}
        //System.out.println(); //new line
      }
    }


  private int turnCount = 0;
  public void calculateMove(){
//https://docs.google.com/document/d/1iEzQWZdhEB93Y5AqpAF7Mj-S50KIOZBD4iTPx7RLCNU/edit
    turnCount++;

    //turn 1
    if(turnCount == 1){
      if(board[1][1] == '-'){
        board[1][1] = 'O';
      } else{
        board[0][0] = 'O';
      }
      return;
    }
    

    //turn 2-9
    //check win
    outer1: for(int x = 0; x < 3; x++){
      for(int y = 0; y < 3; y++){
        if(board[x][y] == '-'){
          board[x][y] = 'O';
         
          WINSTATE winState = checkGameOver();
          if(winState == WINSTATE.PLAYER_TWO){
            // break outer1;
            return;
          } else{
            board[x][y] = '-';
            }
         
          }
        }
      }
      //check block
      outer2: for(int a = 0; a < 3; a++){
      for(int b = 0; b < 3; b++){
        if(board[a][b] == '-'){
          board[a][b] = 'X';
         
          WINSTATE winState = checkGameOver();
          if(winState == WINSTATE.PLAYER_ONE){
            board[a][b] = 'O';
            // break outer2;
            return;
          } else{
            board[a][b] = '-';
          } 
          
        }
      }
    }
    
    
    
    char board2[][] = new char[3][3];
    board2[0] = board[0].clone();    
    board2[1] = board[1].clone();    
    board2[2] = board[2].clone();    

    outer3: for(int a = 0; a < 3; a++){
      for(int b = 0; b < 3; b++){
        System.out.println("a: " + a + " b: " + b);
        if(board2[a][b] == '-'){
          
          int lossCount = 0; 
          board2[a][b] = 'X';

          inner: for(int a2 = 0; a2 < 3; a2++){
            for(int b2 = 0; b2 < 3; b2++){
              System.out.println("a2: " + a2 + " b2: " + b2);
              if(board2[a2][b2] == '-'){
                board2[a2][b2] = 'X';
                System.out.println("inside inner loop");
                WINSTATE winState = checkGameOver(board2);
                if(winState == WINSTATE.PLAYER_ONE){
                  lossCount++;
                  System.out.println("lossCount: " + lossCount);
                  if(lossCount >= 2){
                    // board2[a2][b2] = 'O';
                    System.out.println("lossCount is bigger than 2");
                    board[a2][b2] = 'O';
                    // break outer3;
                    return;
                  }
                  board2[a2][b2] = '-';
                  continue inner;
                } else{
                  board2[a2][b2] = '-';
                  // System.out.println("replace with '-' inner loop check");
                } 
              }
              

            }
          }


          WINSTATE winState = checkGameOver(board2);
          if(winState == WINSTATE.PLAYER_ONE){
            board2[a][b] = 'O';
            System.out.println("replace with 'O' outer loop check");
          } else{
            board2[a][b] = '-';
            System.out.println("replace with '-' outer loop check");
          } 

          

        }
      }
    }

    //Random
    outer4: for(int a3 = 0; a3 < 3; a3++){
      for(int b3 = 0; b3 < 3; b3++){
        if(board[a3][b3] == '-'){
          board[a3][b3] = 'O';
          // break outer4;
          return;
        }
      }
    }


        
    
  } 

  private boolean isBoardFull(){
    for(int x = 0; x < 3; x++){
      for(int y = 0; y < 3; y++){
        if(board[x][y] == '-'){
        return false;
        }
      }
    }
  return true;
  }
  
  public enum WINSTATE{
    PLAYER_ONE,
    PLAYER_TWO,
    TIE,
    IN_PROGRESS
  };
  private WINSTATE checkGameOver(char[][] board){

    for(int y = 0; y < 3; y++){
      if(board[y][0] == board[y][1] && board[y][1] == board[y][2] && board[y][0] != '-'){
        
        if(board[y][0] == 'X'){
          return WINSTATE.PLAYER_ONE;
        } else{
          return WINSTATE.PLAYER_TWO;
        }
        // return (board[y][0] == 'X')? PLAYER_ONE: PLAYER_TWO;
      } 
    }


    for(int x = 0; x < 3; x++){
      if(board[0][x] == board[1][x] && board[1][x] == board[2][x] && board[0][x] != '-'){
        
        if(board[0][x] == 'X'){
          return WINSTATE.PLAYER_ONE;
        } else{
          return WINSTATE.PLAYER_TWO;
        }
      } 
    }

    if(board[0][0] == board[1][1] && board[1][1] == board[2][2]&& board[0][0] != '-'){
      
      if(board[0][0] == 'X'){
          return WINSTATE.PLAYER_ONE;
        } else{
          return WINSTATE.PLAYER_TWO;
        }
    }


    if(board[0][2] == board[1][1] && board[1][1] == board[2][0]&& board[0][2] != '-'){
      
      if(board[0][2] == 'X'){
          return WINSTATE.PLAYER_ONE;
        } else{
          return WINSTATE.PLAYER_TWO;
        }
    }

    boolean boardFull = isBoardFull();
    
    if(boardFull){
      return WINSTATE.TIE;
    }

    return WINSTATE.IN_PROGRESS;
  }


  public WINSTATE checkGameOver(){
    
    return checkGameOver(board);

  }

}