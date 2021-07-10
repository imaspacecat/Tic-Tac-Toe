import java.util.Random;

class Board{
  final private int width;
  final private int height;
  private char[][] board;

  //Created a contructer
  public Board(int width, int height)
  {
     this.width = width;
     this.height = height;
     board = new char[width][height];

     setUpBoard();
  }

  //turns the input (a number between 0-8 (including)) into a set of coordinates that can be put 
  //in the 2d array. Before it inputs the char in the array it checks if the space is open.
  //It returns a boolean which is used in Main to loop until there is no error.
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
  //This is one of the simpler methods which just replaces each space in the array with a '-'
  private void setUpBoard(){
    
    for(int x = 0; x < width; x++){
      for(int y = 0; y < height; y++){
        board[x][y] = '-';
      }
    }
    
    }
    //prints the board and adds some lines to make the board fancier
    public void printBoard(){
      
      String fmt1 = "     |     |     ";
      //when formatted '%c' will be replaced with a char 
      String fmt2 = "  %c  |  %c  |  %c  ";
      String fmt3 = "_____|_____|_____";

      System.out.println(fmt1);
      System.out.println(String.format(fmt2, board[0][0], board[0][1], board[0][2]));
      System.out.println(fmt3);

      System.out.println(fmt1);
      System.out.println(String.format(fmt2, board[1][0], board[1][1], board[1][2]));
      System.out.println(fmt3);

      System.out.println(fmt1);
      System.out.println(String.format(fmt2, board[2][0], board[2][1], board[2][2]));
      System.out.println(fmt1);
      
      System.out.println();


    }


  private int turnCount = 0;
  //This function calculates the computers' move.
  public void calculateMove(){
    turnCount++;

    //this code only works for the first move of the computer. If the player doesn't put their symbol in the center, the computer does. If the player does put their symbol in the center the computer puts their symbol in the corner. 
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
    //This code goes over all the open squares and checks if the compute can win this turn. 
    //If it can it will put their symbol where that can win if not nothing happens and it moves on to the next stage in the algorithm. 
    outer1: for(int x = 0; x < 3; x++){
      for(int y = 0; y < 3; y++){
        if(board[x][y] == '-'){
          board[x][y] = 'O';
         
          WINSTATE winState = checkGameOver();
          if(winState == WINSTATE.PLAYER_TWO){
            return;
          } else{
            board[x][y] = '-';
            }
         
          }
        }
      }
      //check block
      //This code checks to see if the player can win and if they can the computer blocks them by putting their symbol there.
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
    
    
    /**In order to stop situations where the player can win no matter what (when they have two options to win. For example if there is an 'X' in square 0, 2, and 8). What it does is it checks all the possibilities two moves forward for the player ('X') and if it sees that there are two possible places to win, it replaces the original 'X' with an 'O' to block a double attack from happening.
    
    Here is an example game where 'X' is the player and 'O' the computer using this stage:

    X--     X--     X--     X--
    ---  -> -O-  -> -O-  -> -O-
    ---     ---     -X-     -XO

    This is a game not using this stage:
    X--     X--     X--     X-O
    ---  -> -O-  -> -O-  -> -O-  <- Because this situation leads to a loss for the  
    ---     ---     -X-     -X-     computer.
    **/ 
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
                WINSTATE winState = checkGameOver(board2);
                if(winState == WINSTATE.PLAYER_ONE){
                  lossCount++;
                  if(lossCount >= 2){
                    board[a2][b2] = 'O';
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
          } else{
            board2[a][b] = '-';
          } 

          

        }
      }
    }

    //Random
    /**This does the move in the first open square. I did some tests and at the point where none of the previous stages do a move this is the only thing left to do. Usually its when there is going to be a tie.**/ 
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

  //this function simply checks to see if the board is full. I use this function to //check if the game is a tie or not.
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
  
  //create an enum that will hold the 4 possible states of the game: player one wins, player two wins, its a tie, or the game is still in progress.
  public enum WINSTATE{
    PLAYER_ONE,
    PLAYER_TWO,
    TIE,
    IN_PROGRESS
  };

  //In this function I check if the game is over.
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