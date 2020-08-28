/**
 * A class which simulates a textual game board for the Silver Dollar Game.
 * A CoinStrip object is initialized from the PlayGame class to begin a game.
 * The board is updated after the user types in each move until there is a winner.
 * 
 * @Kim Hancock
 * @date 9/17/18
 */

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class CoinStrip {
    
    private static int COINS;
    private static int BOARD_LENGTH;
    private int currentPlayer;
    private int[] coinPositions;
    
    /**
     * The constructor prints a visual representation of the starting game board, 
     * with a random number of slots and coins, along with game instructions.
     * The position of the coins on the board is also random.
     * 
     */
    
    public CoinStrip(int coins, int boardLength, int currentPlayer) {
       this.COINS = coins;
       this.BOARD_LENGTH = boardLength;
       this.currentPlayer = currentPlayer;
       
       coinPositions = startingPositions(coins);
       String myGameBoard = boardString(coinPositions);
       
       String gameInstructions = "Welcome to the Silver Dollar Game!\nTo make a move, enter the number of the coin you want to move," + 
               " then a space, and then the number of slots to the left you want to move it. \nThe game ends when all the coins are to the" + 
               " leftmost possible slot, and the player who made the last move is the winner!";
       
       System.out.println(gameInstructions + "\n" + myGameBoard + "\nPlayer " + currentPlayer + ", it is your turn.\nEnter your move:");

    }
    
    /**
     * Creates an array of possible positions on the board each coin can hold, then
     * randomly picks integers from it and adds the same number of integers as coins to a new array.
     * Each integer in the array is the position on the board each coin will hold,
     * starting from position zero, the slot furthest to the left.
     * 
     * @param coins The number of coins on the game board.
     * @return An array which holds the position of each coin on the board.
     */
    
    public int [] startingPositions(int coins) {

        Random slot = new Random();
        int coinIndex = 0;
        int [] possibleCoinIndeces = new int [BOARD_LENGTH - 2]; //array for coin indeces
        coinPositions = new int [coins]; 
        
        for (int i = 2; i < BOARD_LENGTH; i++) { 
            possibleCoinIndeces[coinIndex] = i; //indeces start from 2 to avoid immediate wins
            coinIndex++;
        }
        
        for (int i = 1; i < possibleCoinIndeces.length; i++) {
            int randomIndex = slot.nextInt(i);
            int shuffle = possibleCoinIndeces[randomIndex];
            possibleCoinIndeces[randomIndex] = possibleCoinIndeces[i];
            possibleCoinIndeces[i] = shuffle; //shuffles possible indeces
        }
     
        for (int i = 0; i < coins; i++) {
            coinPositions[i] = possibleCoinIndeces[i];} //makes an array with same number of elements as coins
            
        Arrays.sort(coinPositions); //want coin positions in ascending order
        return coinPositions;
        
    }
    
    /**
     * Creates a string representation of the game board, called after each move
     * the user makes to print updated board.
     * 
     * @param startBoard The array of indeces on the game board the coins will be on.
     * @return The string representation of the board.
     */
    
    public String boardString(int [] startBoard) {
        int coinNumber = 0;
        String myBoardPrinted = "";

        for (int i = 0; i < BOARD_LENGTH; i++){
            try {
                if (startBoard[coinNumber] == i ) {
                    myBoardPrinted += "|" + (coinNumber) + "|"; 
                    coinNumber++;
                }
                else {
                    myBoardPrinted += "|__|";
                }
                

            }
        
            catch (Exception exception) { //positions that are after last coin index
                myBoardPrinted += "|__|";
            }
        
        }
        
        return myBoardPrinted;

    }
    
    /**
     * Updates the startBoard array based on user's input. 
     * Then, tests if user gave a winning move.
     * If not, updates currentPlayer and prompts user for next move.
     * 
     * @param coinNum The given coin based on user input
     * @param index The number of spaces the coin will move, based on user input.
     */
    
    public void nextMove(int coinNum, int index){
        int userPos = coinPositions[coinNum];
        coinPositions[coinNum] = userPos - index; //new position
        String myBoardPrinted = boardString(coinPositions);

        if (winnerOrNot() == true) {
            System.out.println("Congrats Player " + currentPlayer + ", you are the big winner! " +
                "All the silver coins are for you :)\n" + myBoardPrinted);
            System.exit(0);
        }        
        else {
            if (currentPlayer == 1) {
                currentPlayer = 2;
            }
            else {
                currentPlayer = 1;
            }
            System.out.println("Player " + currentPlayer + " it's your turn!\n" + myBoardPrinted + "\nEnter move here: ");
        }
        boardString(coinPositions);
       
    }
    
    /**
     * Wrapper method, creates a new Scanner object for user's input for the next turn.
     * Checks if input is valid, and if so, calls method to update the board.
     * If input is invalid, calls the method again.
     */
    
    public void nextInput() {
        Scanner input = new Scanner(System.in);
        int playerCoinNumber = input.nextInt();
        int mySlots = input.nextInt();
        
        if (isInputValid(playerCoinNumber, mySlots)) {
            nextMove(playerCoinNumber, mySlots);
        } else {
            System.out.println("Invalid move, try again!" + "\nEnter move here: ");
            nextInput();
        }
            
    }
    
    /**
     * Tests if the user gave a winning move.
     * 
     * @return Boolean, true if it's a winning move, false if not
     */
    
    public boolean winnerOrNot () {
        int lastIndex = coinPositions.length - 1;
        
        if (coinPositions[0] == 0 && coinPositions[lastIndex] == lastIndex) {
            return true; }
            else {
                return false;
            }
            
    }
    
    /**
     * Tests if the user gave valid input for a move.
     * 
     * @param coinNum The number of the coin the user entered.
     * @param numSlots The number of slots the user entered to move the coin.
     * @return Boolean which returns true for valid input, false for invalid.
     */
    public boolean isInputValid(int coinNum, int numSlots) {
        if (coinNum > coinPositions.length - 1 || coinPositions[coinNum] - numSlots < 0 ){
            return false;
        }
        
        if (coinNum == 0) {
            return true;
        }
        
        if (coinPositions[coinNum] - numSlots > coinPositions[coinNum - 1]) {
            return true;
        }       
            else {
                return false;
            }
    
    }
}