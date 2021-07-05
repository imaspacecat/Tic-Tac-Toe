import java.util.Scanner;

class Main {
  
  
  boolean player1 = false;
  boolean player2 = false;
  static boolean running = false;

  public static void main(String[] args) {
    Board board  = new Board(3, 3);

    
    Scanner scanner = new Scanner(System.in);

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