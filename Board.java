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


//  private int[] openSquares(){
//     /** calculates what squares are open. Outputs an array of integers between 0-8. Checks each 'square'. If it is a '-' then it records the corresponding number and puts it into the array.**/

//     int[] available = new int[9];
    
//     int index = 0;

//     for(int x = 0; x < 3; x++){
//       for(int y = 0; y < 3; y++){
//         if(board[x][y] == '-'){
//           int i = 3 * x + y;
//           available[index] = i;
//           System.out.println("Square " + available[index] + "is available");
//           index += 1;
//           // System.out.println("Available square: " + i);
//         }
//       }
//     }
//   return available;
//   }

 public void calculateMove(){
//https://docs.google.com/document/d/1iEzQWZdhEB93Y5AqpAF7Mj-S50KIOZBD4iTPx7RLCNU/edit

    outer1: for(int x = 0; x < 3; x++){
      for(int y = 0; y < 3; y++){
        if(board[x][y] == '-'){
          board[x][y] = 'O';
         
          WINSTATE winState = checkGameOver();
          if(winState == WINSTATE.PLAYER_TWO){
            break outer1;
          } else{
            board[x][y] = '-';
          }
         
        }
      }

      outer2: for(int a = 0; a < 3; a++){
      for(int b = 0; b < 3; b++){
        if(board[a][b] == '-'){
          board[a][b] = 'X';
         
          WINSTATE winState = checkGameOver();
          if(winState == WINSTATE.PLAYER_ONE){
            board[a][b] = 'O';
            break outer2;
          } else{
            board[a][b] = '-';
          } 
          
        }
      }
    }

    outer3: for(int x1 = 0; x1 < 3; x1++){ //1st
      for(int y1 = 0; y1 < 3; y1++){
        if(board[x1][y1] == '-'){
          board[x1][y1] = 'O';
          
          for(int x2 = 0; x2 < 3; x2++){ //2nd
            for(int y2 = 0; y2 < 3; y2++){
              if(board[x2][y2] == '-'){
                board[x2][y2] = 'X';

                for(int x3 = 0; x3 < 3; x3++){ //3rd
                  for(int y3 = 0; y3 < 3; y3++){
                    if(board[x3][y3] == '-'){
                      board[x3][y3] = 'O';

                      for(int x4 = 0; x4 < 3; x4++){ //4th
                        for(int y4 = 0; y4 < 3; y4++){
                          if(board[x4][y4] == '-'){
                            board[x4][y4] = 'X';

                            for(int x5 = 0; x5 < 3; x5++){ //5th
                              for(int y5 = 0; y5 < 3; y5++){
                                if(board[x5][y5] == '-'){
                                  board[x5][y5] = 'O';

                                  for(int x6 = 0; x6 < 3; x6++){ //6th
                                    for(int y6 = 0; y6 < 3; y6++){
                                      if(board[x6][y6] == '-'){
                                        board[x6][y6] = 'X';

                                        for(int x7 = 0; x7 < 3; x7++){ //7th
                                          for(int y7 = 0; y7 < 3; y7++){
                                            if(board[x7][y7] == '-'){
                                              board[x7][y7] = 'O';

                                              for(int x8 = 0; x8 < 3; x8++){ //8th
                                                for(int y8 = 0; y8 < 3; y8++){
                                                  if(board[x8][y8] == '-'){
                                                    board[x8][y8] = 'X';

                                                    for(int x9 = 0; x9 < 3; x9++){ //9th
                                                      for(int y9 = 0; y9 < 3; y9++){
                                                        if(board[x9][y9] == '-'){
                                                          board[x9][y9] = 'O';

                                                  


                                      
                                                          WINSTATE winState = checkGameOver();
                                                          if(winState == WINSTATE.PLAYER_TWO){
                                                            break outer3;
                                                          } else{
                                                            board[x9][y9] = '-';
                                                          }
                                                    
                                                        }
                                                      }
                                                    }

                                                    WINSTATE winState = checkGameOver();
                                                    if(winState == WINSTATE.PLAYER_ONE){
                                                      board[x8][y8] = 'O';
                                                      break outer3;
                                                    } else{
                                                      board[x8][y8] = '-';
                                                    }
                                              
                                                  }
                                                }
                                              }
                                                  
                                              WINSTATE winState = checkGameOver();
                                              if(winState == WINSTATE.PLAYER_TWO){
                                                break outer3;
                                              } else{
                                                board[x7][y7] = '-';
                                              }
                                        
                                            }
                                          }
                                        }

                                        WINSTATE winState = checkGameOver();
                                        if(winState == WINSTATE.PLAYER_ONE){
                                          board[x6][y6] = 'O';
                                          break outer3;
                                        } else{
                                          board[x6][y6] = '-';
                                        }
                                  
                                      }
                                    }
                                  }

                                  WINSTATE winState = checkGameOver();
                                  if(winState == WINSTATE.PLAYER_TWO){
                                    break outer3;
                                  } else{
                                    board[x5][y5] = '-';
                                  }
                            
                                }
                              }
                            }

                            WINSTATE winState = checkGameOver();
                            if(winState == WINSTATE.PLAYER_ONE){
                              board[x4][y4] = 'O';
                              break outer3;
                            } else{
                              board[x4][y4] = '-';
                            }
                      
                          }
                        }
                      }
                          
                      WINSTATE winState = checkGameOver();
                      if(winState == WINSTATE.PLAYER_TWO){
                        break outer3;
                      } else{
                        board[x3][y3] = '-';
                      }
                
                    }
                  }
                }

                WINSTATE winState = checkGameOver();
                if(winState == WINSTATE.PLAYER_ONE){
                  board[x2][y2] = 'O';
                  break outer3;
                } else{
                  board[x2][y2] = '-';
                }
          
              }
            }
          }
         
          WINSTATE winState = checkGameOver();
          if(winState == WINSTATE.PLAYER_TWO){
            break outer3;
          } else{
            board[x1][y1] = '-';
          }
          
        }
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