/**
 * An abstract class for a player of Hexapawn.
 * 
 * @author Sean Barker
 */
public abstract class Player {
    
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
  public Player(String playerName, Marker playerMarker) {
    name = playerName;
    marker = playerMarker;
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
  public abstract Player play(GameTree position, Player opponent);

}

