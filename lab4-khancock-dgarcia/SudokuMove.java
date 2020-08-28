
/**
 * an object representing a single digit placement while solving a sudoku puzzle, to be
 * used by the solver
 */
public class SudokuMove {
    private int i;
    private int j;
    private int newNum;
    
    public SudokuMove(int newNum, int i, int j) {
        this.i = i;
        this.j = j;
        this.newNum = newNum;
    }
    
    public int getI() {
        return i;
    }
    
    public int getJ() {
        return j;
    }
    
    public int getNewNum() {
        return newNum;
    }
    
    public String toString() {
        return i + " " + j + " " + newNum;
    }
}