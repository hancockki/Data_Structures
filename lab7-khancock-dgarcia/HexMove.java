/**
 * A class representing a specific move in a Hexapawn game.
 * 
 * @author Duane Bailey, Steve Freund, Sean Barker
 */
public class HexMove {

  // origin position
  private int fromPos;

  // destination position
  private int toPos;

  // number of columns in game board (used for printing)
  private int cols;

  /**
   * Construct a new move from fromPos to toPos on a board with cols columns.
   * 
   * @param fromPos
   *          The origin position.
   * @param toPos
   *          The destination position.
   * @param cols
   *          The number of columns on the board.
   */
  public HexMove(int fromPos, int toPos, int cols) {
    this.fromPos = fromPos;
    this.toPos = toPos;
    this.cols = cols;
  }

  public int getFromPos() {
    return fromPos;
  }

  public int getToPos() {
    return toPos;
  }

  @Override
  public String toString() {
    return "[" + (fromPos / cols) + "," + (fromPos % cols) + "] " + "to ["
        + (toPos / cols) + "," + (toPos % cols) + "]";
  }

}
