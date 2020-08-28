import java.util.LinkedList;
import java.util.Deque;

/**
 * an object that can solve a given SudokuPuzzle
 */
public class SudokuSolver
{
    // instance variables - replace the example below with your own
    
    private SudokuPuzzle puzzleToSolve;
    
    private Deque<SudokuMove> collectionOfMoves = new LinkedList<SudokuMove>();

    public SudokuSolver(SudokuPuzzle puzzleToSolve) {
        this.puzzleToSolve = puzzleToSolve;
    }
    
    public boolean solve() {
        while (!puzzleToSolve.isComplete()) {
            fillNextZero();
        }
        System.out.println("Solved puzzle: \n" + puzzleToSolve + "\n");
        return true;
    }
    
    public boolean fillNextZero() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzleToSolve.getCurrentIndex(i, j) == 0) {
                    SudokuMove myMove = addToDeque(i, j, 0);
                    if (myMove != null) {
                        return false;
                    }
                    
                    while (myMove == null) {
                        SudokuMove lastMove = collectionOfMoves.pop();
                        puzzleToSolve.resetToZero(lastMove.getI(), lastMove.getJ());
                        myMove = addToDeque(lastMove.getI(), lastMove.getJ(), lastMove.getNewNum());
                    }
                    return false;
                }
            }
        }
        return false;
    }
    
    private SudokuMove addToDeque(int i, int j, int newNum) {
        SudokuMove myMove;
        int nextNum = puzzleToSolve.pickNum(i,j, newNum);
        if (nextNum != 0) {
                myMove = new SudokuMove(nextNum, i, j);                
                collectionOfMoves.push(myMove);
                return myMove;
        
        }
        return null;
    }
}
        
    
    
    