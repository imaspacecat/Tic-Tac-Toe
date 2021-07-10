import java.util.Scanner;

class Main {
  
  private static int input = 0;
  private static Scanner scanner = new Scanner(System.in);
  private static int inputGameMode = 0;

  public static void main(String[] args) {
    System.out.println("How to play: Input a number between 0-8 (including) to put your symbol ('X' or 'O') there. Don't worry, if you forget what the number is for a corresponding square it is automatically printed after each turn.");

    while(true){
      runGame();
    }
  }


  public static void runGame() {
    //create a board object
    Board board  = new Board(3, 3);

    
    System.out.println("How many players? (1 or 2) type: '3' to quit");
    
    //I use try catch to avoid errors if the input isn't one of the possibilities
    //if there is an error it will not break and go to catch
    while(true){
      try{
        inputGameMode = scanner.nextInt();
        break;
      } catch(Exception e){
        System.out.println("Please input either '1', '2', or '3'!");
        scanner.next();
      }
    
    }
    //stops the program if 3 is inputed
    if(inputGameMode == 3){
      System.exit(0);
    }

    //two player mode
    if(inputGameMode == 2){

      while(true){
        board.printBoard();      
        

        boolean error;
        do{
          System.out.println("Player 1 turn!");
          System.out.println("Numbers for each square:");
          System.out.println("012");
          System.out.println("345");
          System.out.println("678");

          checkInput(scanner);
          error = board.processInput(input, 'X');
          if(error){
            System.out.println("This square is taken, please try again!");
          }
        } while(error);
        
        board.printBoard();
        
        //check if the game is over and if it is I stop the loop
        if(gameState(board) == true){
          break;
        }


        
        do{
          System.out.println("Player 2 turn!");
          System.out.println("Numbers for each square:");
          System.out.println("012");
          System.out.println("345");
          System.out.println("678");

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
          System.out.println("Numbers for each square:");
          System.out.println("012");
          System.out.println("345");
          System.out.println("678");

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
        board.printBoard();      
        break;
        }
      }


    }
    


  }

  //This function checks what state the game is currently in and acts correspondingly. It returns a boolean value which I use in runGame() to know when to break.
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
  
  //This function gets the input and checks it to avoid errors.
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
