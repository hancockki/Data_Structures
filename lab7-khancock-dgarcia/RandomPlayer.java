import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class RandomPlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomPlayer extends Player {
  
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
  public RandomPlayer(String playerName, Marker playerMarker) {
     super(playerName, playerMarker);
     name = playerName;
     marker = playerMarker;
  }

        public boolean equals(Player player2) {
        if (name.equals(player2.getName())) {
            return true; 
        } else {
            return false;
        }
         
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
      Random rand = new Random();
      System.out.println(position.getValue()); 
      Marker currentPlayer = getMarker();
      //base case
      if (position.size() == 0) {
          System.out.println("Congrats, " + opponent.getName() + ", you have won!");
          return opponent;
      } else {
         System.out.println(getName() + "'s turn: ");
         List<HexMove> moves = position.getValue().moves(currentPlayer);
         int chosenMove;
         chosenMove = rand.nextInt(moves.size());
         System.out.println("The computer picks a move randomly");
         HexMove move = moves.get(chosenMove);
         System.out.println(currentPlayer + " chosen move: " + move);
         GameTree nextMove = position.getChildren().get(chosenMove);
         return opponent.play(nextMove, this);
      }
    }
  
  public static void main(String[] args) {
      Marker activePlayer = Marker.BLACK;
      RandomPlayer opponent = new RandomPlayer("Kim", Marker.WHITE);
      RandomPlayer currentPlayer = new RandomPlayer("Devon", Marker.BLACK);
      HexBoard board = new HexBoard(3, 3);
      GameTree playGame = new GameTree(board, activePlayer);
      currentPlayer.play(playGame, opponent);
    }

}
