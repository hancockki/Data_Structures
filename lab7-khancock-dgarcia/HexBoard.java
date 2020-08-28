import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * A class representing a particular configuration of a Hexapawn board.
 * 
 * @author Duane Bailey, Steve Freund, Sean Barker
 */
public class HexBoard {

  // default number of board rows
  private static final int DEFAULT_ROWS = 3;

  // default number of board columns
  private static final int DEFAULT_COLS = 3;

  // array of markers in each board space (null for blanks)
  private Marker[] board;

  // number of rows in the board
  private int rows;

  // number of columns in the board
  private int cols;

  // stores the possible moves for white, or null if not yet constructed
  private List<HexMove> whiteMoves = null;

  // stores the possible moves for black , or null if not yet constructed
  private List<HexMove> blackMoves = null;

  /**
   * Construct a new hexapawn board with the default 3x3 board size.
   */
  public HexBoard() {
    this(DEFAULT_ROWS, DEFAULT_COLS);
  }

  /**
   * Construct a new hexapawn board with the specified board size.
   * 
   * @param numRows
   *          Rows of board.
   * @param numCols
   *          Columns of board.
   */
  public HexBoard(int numRows, int numCols) {
    this.rows = numRows;
    this.cols = numCols;
    board = new Marker[numRows * numCols];
    for (int pos = 0; pos < numRows * numCols; pos++) {
      if (pos < numCols) {
        board[pos] = Marker.WHITE;
      } else if (pos >= (numRows - 1) * numCols) {
        board[pos] = Marker.BLACK;
      } else {
        board[pos] = null;
      }
    }
  }

  /**
   * Construct a new hexapawn board that is a copy of the given board with the
   * given hexapawn move applied to it.
   * 
   * @param prevBoard
   *          The previous board.
   * @param move
   *          The game move to be applied.
   */
  public HexBoard(HexBoard prevBoard, HexMove move) {
    this.rows = prevBoard.rows;
    this.cols = prevBoard.cols;
    this.board = new Marker[rows * cols];
    for (int i = 0; i < rows * cols; i++) {
      board[i] = prevBoard.board[i];
    }
    int to = move.getToPos();
    int from = move.getFromPos();
    board[to] = board[from];
    board[from] = null;
  }

  /**
   * Returns whether the given player (white or black) has won.
   * 
   * @param lastPlayer
   *          The last player to move.
   * @return Whether the specified player has won.
   */
  public boolean checkWin(Marker lastPlayer) {
    int startPos;
    if (lastPlayer == Marker.WHITE) {
      startPos = (rows - 1) * cols;
    } else {
      startPos = 0;
    }

    // win if player's pawn has reached other end of board
    for (int pos = startPos; pos < startPos + cols; pos++) {
      if (board[pos] == lastPlayer) {
        return true;
      }
    }

    // win if opponent cannot move
    return moves(lastPlayer.opponent()).isEmpty();
  }

  // construct a list of all valid moves for white to make from this position
  private List<HexMove> findWhiteMoves() {
    List<HexMove> moves = new ArrayList<HexMove>();
    for (int pos = 0; pos < (rows - 1) * cols; pos++) {
      if (board[pos] == Marker.WHITE) {
        if (board[pos + cols] == null) {
          moves.add(new HexMove(pos, pos + cols, cols));
        }
        if ((pos % cols) != 0 && board[pos + (cols - 1)] == Marker.BLACK) {
          moves.add(new HexMove(pos, pos + cols - 1, cols));
        }
        if ((pos % cols) != (cols - 1) && board[pos + cols + 1] == Marker.BLACK) {
          moves.add(new HexMove(pos, pos + cols + 1, cols));
        }
      }
    }
    return moves;
  }

  // construct a list of all valid moves for black to make from this position
  private List<HexMove> findBlackMoves() {
    List<HexMove> moves = new ArrayList<HexMove>();
    for (int pos = cols; pos < rows * cols; pos++) {
      if (board[pos] == Marker.BLACK) {
        if (board[pos - cols] == null) {
          moves.add(new HexMove(pos, pos - cols, cols));
        }
        if ((pos % cols) != 0 && board[pos - cols - 1] == Marker.WHITE) {
          moves.add(new HexMove(pos, pos - cols - 1, cols));
        }
        if ((pos % cols) != cols - 1 && board[pos - cols + 1] == Marker.WHITE) {
          moves.add(new HexMove(pos, pos - cols + 1, cols));
        }
      }
    }
    return moves;
  }

  /**
   * Get a list of all possible moves for the given player to make from this
   * position.
   * 
   * @param player
   *          The player to move.
   * @return A list of valid moves for the given player.
   */
  public List<HexMove> moves(Marker player) {
    if (player == Marker.WHITE) {
      if (whiteMoves == null) {
        // only calculate possible moves if we didn't do it previously
        whiteMoves = findWhiteMoves();
      }
      return whiteMoves;
    } else {
      if (blackMoves == null) {
        blackMoves = findBlackMoves();
      }
      return blackMoves;
    }
  }

  @Override
  public String toString() {
    String result = "-";
    for (int pos = 0; pos < cols; pos++) {
      result += "--";
    }
    result += "\n";
    for (int pos = 0; pos < rows * cols; pos++) {
      if (board[pos] == null) {
        result += "  ";
      } else {
        result += " " + board[pos].symbol();
      }
      if ((cols - 1) == (pos % cols)) {
        result += '\n';
      }
    }
    for (int pos = 0; pos < cols; pos++) {
      result += "--";
    }
    result += "-";
    return result;
  }

  // an example of how to use this class to play a game of Hexapawn
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Random rand = new Random();

    HexBoard board = new HexBoard();
    System.out.println(board);
    Marker activePlayer = Marker.WHITE;
    Marker winner = null;
    do {

      List<HexMove> moves = board.moves(activePlayer);
      int chosenMove;
      System.out.println(activePlayer + " turn:");

      if (activePlayer == Marker.WHITE) {
        // white (human) plays manually
        int moveNum = 0;
        for (HexMove move : moves) {
          System.out.println(moveNum + ". Move from " + move);
          moveNum++;
        }
        do {
          System.out.print("Enter your move choice: ");
          chosenMove = in.nextInt();
        } while (chosenMove < 0 || chosenMove >= moves.size());
      } else {
        // black (computer) plays randomly
        chosenMove = rand.nextInt(moves.size());
        System.out.println("The computer picks a move randomly.");
      }

      HexMove move = moves.get(chosenMove);
      System.out.println(activePlayer + " chosen move: " + move);

      board = new HexBoard(board, move);
      System.out.println(board);

      if (board.checkWin(activePlayer)) {
        winner = activePlayer;
      }
      activePlayer = activePlayer.opponent();
    } while (winner == null);

    System.out.println(winner + " wins!");
    in.close();

  }

}

