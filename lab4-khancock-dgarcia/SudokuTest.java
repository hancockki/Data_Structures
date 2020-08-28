import java.util.Scanner;

/**
 * Write a description of class SudokuTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SudokuTest {        
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter puzzle file name: ");
        String puzzleName = scan.nextLine();
        SudokuPuzzle puzzleTest = new SudokuPuzzle(puzzleName);;
        
        System.out.println("Enter puzzle solution file name (optional): ");
        String puzzleSolution = scan.nextLine();
        
        System.out.println("Starting puzzle is: \n" + puzzleTest.toString() + "\n");
        SudokuSolver solvePuzzle = new SudokuSolver(puzzleTest);        
        solvePuzzle.solve();
        
        if (!puzzleSolution.isEmpty()) {
           SudokuPuzzle puzzleTestAnswer = new SudokuPuzzle(puzzleSolution);
           puzzleTest.equals(puzzleTestAnswer);
        }
    }   
}
