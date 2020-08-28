import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

/**
 * a particular configuration of a Sudoku puzzle
 */
public class SudokuPuzzle {
    
    private String puzzleFile;
    private int[][] puzzleBoard = new int[9][9];
    
    public SudokuPuzzle(String puzzleFile) {
        this.puzzleFile = puzzleFile; 
        Scanner scanFile;
        try {
            scanFile = new Scanner(new File ("puzzles/" + puzzleFile));
        } catch (Exception e) {
            scanFile = null;
            System.out.println("File not found");
            System.exit(0);
        }
        
        while(scanFile.hasNext()) {
            for(int i = 0; i <= 8; i++) {
                for(int j = 0; j <= 8; j++) {
                    int nextNum = scanFile.nextInt();
                    puzzleBoard[i][j] = nextNum;
                }
            }
        }
        System.out.println(toString());
    }
    
    public String toString() {
        String boardPrinted = "";
        for(int i = 0; i <= 8; i++) {
            boardPrinted += "\n";
            for(int j = 0; j <= 8; j++) {
                boardPrinted += " " + puzzleBoard[i][j];
            }
        }
        return "Current board is: \n" + boardPrinted;
    }
    
    // check whether object is equivalent to this object
    public boolean equals(Object obj) {
        if (obj instanceof SudokuPuzzle) {      // object is of type SudokuPuzzle
            // check parts of SudokuPuzzles' states to see if they are equivalent
            SudokuPuzzle answerKey = (SudokuPuzzle) obj;
            int[][] answerArray = answerKey.puzzleBoard;
            for(int i = 0; i <= 8; i++) {
                for(int j = 0; j <= 8; j++) {
                    if (this.puzzleBoard[i][j] == answerArray[i][j]) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        } else {        // object is not of type SudokuPuzzle
            return false;
        }
        return true;
    }
    
    // method check row
    public boolean checkRow (int row, int currentNum) {
        for (int j = 0; j < 9; j++) {
            if (puzzleBoard[row][j] == currentNum) {
                return true;
            }
        }
        return false;
    }
    
    // method check column
    public boolean checkColumn (int column, int currentNum) {
        for (int i = 0; i < 9; i++) {
            if (puzzleBoard[i][column] == currentNum) {
                return true;
            }
        }
        return false;
    }
    
    // method check box
    public boolean checkBox (int row, int column, int currentNum) {
        int boxRow = row - row%3;
        int boxCol = column - column%3;
        for (int i = boxRow; i < boxRow+3; i++) {
            for (int j = boxCol; j < boxCol+3; j++) {
                if (puzzleBoard[i][j] == currentNum) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // method boolean isMoveAllowed 
        // checks to see if it is safe to assign a number in the cell, true or false
        // row, column, and box methods must all return false for this to be true
    public boolean isMoveValid(int row, int col, int currentNum) {
        return !(checkRow(row, currentNum) || checkColumn(col, currentNum) || checkBox(row, col, currentNum));
    }
    
    // method getZeroIndex to get the number at a specific position in the board
    public int getCurrentIndex(int i, int j) {
        return puzzleBoard[i][j];
    }
    
    // chooses a number out of possible options to place in space; places in space and returns that num
    public int pickNum(int row, int col, int attemptedNum) {
        int num = 0;
        for (int i = attemptedNum; i <= 9; i++) {
            if(isMoveValid(row, col, i)) {
                num = i;
                puzzleBoard[row][col] = num;
                return num;
            }
        }
        return num;
    }
    
    public void assignSpaceValue (int i, int j, int num) {
        puzzleBoard[i][j] = num;
    }
    
    public void resetToZero(int i, int j) {
        puzzleBoard[i][j] = 0;
    }
    
}