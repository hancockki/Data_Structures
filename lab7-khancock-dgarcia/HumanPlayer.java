import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


/**
 * Write a description of class HumanPlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HumanPlayer extends Player {
        
  // human-readable player name
  protected final String name;

  // marker associated with this player
  protected final Marker marker;

  /**
   * Construct a new player with the given name (e.g., "Alice") and represented
   * by the given marker.
   * 
   * @param playerName A human-readable name for the player (e.g., "Alice").
   * @param playerMarker The marker (black or white) representing this player.
   */
  public HumanPlayer(String playerName, Marker playerMarker) {    
    super(playerName, playerMarker);
    this.name = playerName;
    this.marker = playerMarker;
  }

  /**
   * Get the player's name.
   * 
   * @return The player name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the player's marker.
   * 
   * @return The player marker.
   */
  public Marker getMarker() {
    return marker;
  }

  @Override
  public String toString() {
    return name + " (" + marker + ")";
  }
  
  @Override
  /**
   * Play a complete game against the given opponent, starting from the given
   * game tree. Returns the winning player (either this or opponent).
   * 
   * @param position
   *          A non-null game tree node.
   * @param opponent
   *          The player that moves after this player.
   * @return The winning player.
   */
  
  public Player play(GameTree position, Player opponent) {
      Scanner in = new Scanner(System.in);
      System.out.println(position.getValue()); 
      Marker currentPlayer = getMarker();
      //base case
      if (position.size() == 0) {
          System.out.println("Congrats, " + opponent.getName() + ", you've won!");
          return opponent;
      } else {
         System.out.println(getName() + "'s turn: ");
         List<HexMove> moves = position.getValue().moves(currentPlayer);
         int moveNum = 0;
         for (HexMove move : moves) {
             System.out.println(moveNum + ". Move from " + move);
             moveNum++;
         }
         int chosenMove;
         do {
             //int chosenMove;
             System.out.print("Enter your move choice: ");
             chosenMove = in.nextInt();
         } while (chosenMove < 0 || chosenMove >= moves.size());
         HexMove move = moves.get(chosenMove);
         System.out.println(currentPlayer + " chosen move: " + move);
         GameTree nextMove = position.getChildren().get(chosenMove);
         return opponent.play(nextMove, this);
      }
    }

    
    public boolean equals(Player player2) {
        if (marker == player2.getMarker()) {
            return true; 
        } else {
            return false;
        }
         
    }
    
  public static void main(String[] args) {
      Marker activePlayer = Marker.BLACK;
      HumanPlayer opponent = new HumanPlayer("Kim", Marker.WHITE);
      HumanPlayer currentPlayer = new HumanPlayer("Devon", Marker.BLACK);
      HexBoard board = new HexBoard(3, 3);
      GameTree playGame = new GameTree(board, activePlayer);
      currentPlayer.play(playGame, opponent);
      
    }
}
