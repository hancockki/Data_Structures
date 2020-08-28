
/**
 * Write a description of class SudokuTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SudokuTest {
    
    public static void main(String[] args) {
        SudokuPuzzle puzzleTest = new SudokuPuzzle("s2.txt");
        //SudokuPuzzle puzzleTestAnswer = new SudokuPuzzle("s3-solution.txt");
        SudokuSolver myPuzzle = new SudokuSolver(puzzleTest);
        myPuzzle.solve();
        //System.out.println(puzzleTest.equals(puzzleTestAnswer));
    }
}