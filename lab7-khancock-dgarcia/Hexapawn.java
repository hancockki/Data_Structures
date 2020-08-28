import java.util.Scanner;

/**
 * Write a description of class Hexapawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hexapawn {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of rows: ");
        int numRows = scan.nextInt();
        System.out.println("Enter number of columns: ");
        int numCols = scan.nextInt();
        System.out.println("Enter player 1 type [crh]: ");
        String player1 = scan.next();
        System.out.println("Enter plater 2 type [crh]: ");
        String player2 = scan.next();
        System.out.println("Enter number of games: ");
        int numGames = scan.nextInt();
        int numWins = 0;
        HexBoard startingBoard = new HexBoard(numRows, numCols);
        
        Player firstPlayer = null;
        Player secondPlayer = null;
        Marker activePlayer = Marker.WHITE;
        
        if (player1.equals("c")) {
            firstPlayer = new ComputerPlayer ("Kim", Marker.WHITE);
        } else if (player1.equals("r")) {
            firstPlayer = new RandomPlayer("Kim", Marker.WHITE);
        } else if (player1.equals("h")) {
            firstPlayer = new HumanPlayer("Kim", Marker.WHITE);
        }
        
        if (player2.equals("c")) {
            secondPlayer = new ComputerPlayer ("Dan", Marker.BLACK);
        } else if (player2.equals("r")) {
            secondPlayer = new RandomPlayer("Dan", Marker.BLACK);
        } else if (player2.equals("h")) {
            secondPlayer = new HumanPlayer("Dan", Marker.BLACK);
        }
        GameTree playGame = new GameTree(startingBoard, activePlayer);  
        
        Player winner;
        for(int i = 0; i < numGames; i++) {
            winner = firstPlayer.play(playGame, secondPlayer);
            //System.out.println(winner);
            if ((winner.equals(firstPlayer))) {
                System.out.println(winner.equals(firstPlayer));
                numWins++;
            }
        }        
        System.out.println(numWins);
    }    
}
