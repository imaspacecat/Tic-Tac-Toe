import java.util.Scanner;

class Main {
  
  private static int input = 0;
  private static Scanner scanner = new Scanner(System.in);
  private static int inputGameMode = 0;

  public static void main(String[] args) {
    while(true){
      runGame();
    }
  }


  public static void runGame() {
    Board board  = new Board(3, 3);

    
    System.out.println("How many players? (1 or 2) type: '3' to quit");
    
    while(true){
      try{
        inputGameMode = scanner.nextInt();
        break;
      } catch(Exception e){
        System.out.println("Please input either '1', '2', or '3'!");
        scanner.next();
      }
    
    }
    
    if(inputGameMode == 3){
      System.exit(0);
    }


    if(inputGameMode == 2){

      while(true){
        board.printBoard();      
        

        boolean error;
        do{
          System.out.println("Player 1 turn!");
          checkInput(scanner);
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
          System.out.println("Player 2 turn!");
          checkInput(scanner);
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
          System.out.println("Your turn!");
          checkInput(scanner);
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
      
      if(inputGameMode == 1){
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
      }
      if(inputGameMode == 2){
        Board.WINSTATE winState = board.checkGameOver();
        if(winState == Board.WINSTATE.PLAYER_ONE){
          System.out.println("Player 1 Won!");
          return true;
        } 
        if(winState == Board.WINSTATE.PLAYER_TWO){
          System.out.println("Player 2 Won!");
          return true;
        } 
        if(winState == Board.WINSTATE.TIE){
          System.out.println("It's a tie!");
          return true;
        }
      }
      
    return false;

      
  }
  
  
  static void checkInput(Scanner scanner){
    while(true){
      try{
        input = scanner.nextInt();

        if(input >= 0 && input < 9){
          break;
        } else{
          System.out.println("Please input a number between 0-8!");
          
        }
    
    } catch(Exception e){
      System.out.println("Please input an integer between 0-8 (including)!");
      scanner.next();
      }
    }

  }
}
