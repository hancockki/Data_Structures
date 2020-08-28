/**
 * A class which creates a CoinStrip object and controls the game simulation.
 * Continues calling methods from CoinStrip until the game is over.
 * 
 * @author Kim Hancock
 * @date 9/17/18
 */
import java.util.Scanner;
import java.util.Random;

public class PlayGame {
   private static Scanner input = new Scanner(System.in);
   private static Random r = new Random();
   
   /**
    * Main method which acts as a wrapper for the CoinStrip class.
    * Initializes game and calls for the next move until a winning
    * move has been made.
    */

   public static void main(String[] args) {
       int coins = r.nextInt(5) + 3;
       int boardLength = r.nextInt(5) + 10;
       int currentPlayer = 1;
       CoinStrip game = new CoinStrip(coins, boardLength, currentPlayer);

       game.nextInput();
       
       while (game.winnerOrNot() == false) {
           game.nextInput();
        }       
       
   }
    
}
