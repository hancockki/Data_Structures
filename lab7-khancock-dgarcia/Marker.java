/**
 * A marker (white or black) in a game of Hexapawn.
 * 
 * @author Sean Barker
 */
public enum Marker {

  // the two (and only two) Marker instances
  WHITE('W'), BLACK('B');

  // the symbol used for a particular Marker
  private final char symbol;

  /**
   * Construct a new Marker with the given symbol.
   * 
   * @param symbol
   *          The symbol representing the marker.
   */
  private Marker(char symbol) {
    this.symbol = symbol;
  }

  /**
   * Get the character used to draw this marker.
   * 
   * @return The character representing this marker.
   */
  public char symbol() {
    return symbol;
  }

  /**
   * Get the opposing player's marker.
   * 
   * @return The other marker.
   */
  public Marker opponent() {
    // the ternary operator - a compact way to write a conditional expression
    return (this == WHITE) ? BLACK : WHITE;
  }

}
