import java.util.Scanner;

class Main {
  

  public static void main(String[] args) {
    Board board  = new Board(3, 3);

    
    System.out.println("How many players? (1 or 2)");
    Scanner scanner = new Scanner(System.in);
    int inputGameMode = scanner.nextInt();


    if(inputGameMode == 2){

      while(true){
        board.printBoard();      
        

        boolean error;
        do{
          int input = scanner.nextInt();
          error = board.processInput(input, 'X');
          if(error){
            System.out.println("This square is taken, please try again!");
          }
        } while(error);
        
        board.printBoard();
        
        if(gameState(board) == true){
          break;
        }

        // board.calculateMove();

        
        do{
          int input = scanner.nextInt();
          error = board.processInput(input, 'O');
          if(error){
            System.out.println("This square is taken, please try again!");
          }
        } while(error);
        
        
        
        
        
        if(gameState(board) == true){
          break;
        }
      }

    }

    if(inputGameMode == 1){

      while(true){
        board.printBoard();      
        

        boolean error;
        do{
          int input = scanner.nextInt();
          error = board.processInput(input, 'X');
          if(error){
            System.out.println("This square is taken, please try again!");
          }
        } while(error);
        
        board.printBoard();
        
        if(gameState(board) == true){
          break;
        }

        board.calculateMove();
        
        if(gameState(board) == true){
          break;
        }
      }


    }
    


  }

  public static boolean gameState(Board board){
      
      Board.WINSTATE winState = board.checkGameOver();
      if(winState == Board.WINSTATE.PLAYER_ONE){
        System.out.println("You won! (The computer lost)");
        return true;
      } 
      if(winState == Board.WINSTATE.PLAYER_TWO){
        System.out.println("Game Over! (You lost)");
        return true;
      } 
      if(winState == Board.WINSTATE.TIE){
        System.out.println("It's a tie!");
        return true;
      }
      return false;
  }
  


  

}