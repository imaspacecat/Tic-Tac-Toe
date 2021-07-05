import java.util.Random;

class Board{
  final private int width;
  final private int height;
  final private char[][] board;

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


 private int[] openSquares(){
    /** calculates what squares are open. Outputs an array of integers between 0-8. Checks each 'square'. If it is a '-' then it records the corresponding number and puts it into the array.**/

    int[] available = new int[9];
    
    int index = 0;

    for(int x = 0; x < 3; x++){
      for(int y = 0; y < 3; y++){
        if(board[x][y] == '-'){
          int i = 3 * x + y;
          available[index] = i;
          System.out.println("Square " + available[index] + "is available");
          index += 1;
          // System.out.println("Available square: " + i);
        }
      }
    }
  return available;
  }

//  public void calculateMove(){
//     /**gets the available moves array from the openSquares() method and chooses a random index (number) using Math.random() (with a min and max depending on the length of the arrray). checks with Main.checkGameOver() if the game ends. If it does then you send that move to printMove() which will print the move.**/

    
//     int minIndex = 0;
//     int maxIndex = index;
//     Random rand = new Random();
//     int randIndex = rand.nextInt(maxIndex - minIndex + 1) + minIndex;

//     randY = randIndex % 3;
//     // i = 3 * squareY + squareX;
//     randX = (randIndex - randY)/3;

//     board[randX][randY] = 'O';
//     checkGameOver();

//   } 

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
  public WINSTATE checkGameOver(){

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




}