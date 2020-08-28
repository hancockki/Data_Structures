import java.util.List;
import java.util.ArrayList;

public class GameTree {
        
    private GameTree parent;
    
    private List<GameTree> children = new ArrayList<GameTree>();
    
    private HexBoard value;
    
    private static int counter = 1;
    
    public GameTree() {
        this.parent = parent;
        this.value = null;
        this.children = null;
    }
    
    public GameTree(HexBoard value, Marker activePlayer) {

        this.value = value; //current board representation
        this.children = children; //list of next possible boards
        this.parent = parent; //
                
        List<HexMove> moves = value.moves(activePlayer); //gets list of next moves
        
        if (value.checkWin(activePlayer.opponent()) == true) {
            GameTree nullTree = new GameTree();
        } else if (value.moves(activePlayer) == null) {
            GameTree nullTree = new GameTree();
        } else {
            for (HexMove move : moves) {
                HexBoard nextBoard = new HexBoard(value, move);
                Marker newPlayer = activePlayer.opponent();
                parent = new GameTree(nextBoard, newPlayer);
                counter++;
                children.add(parent);                
            }      
        } 
        // System.out.println(counter);
    }
    
    public 
    public static void main(String[] args) {        
       HexBoard board = new HexBoard();
       System.out.println(board);
       Marker activePlayer = Marker.WHITE;
       GameTree testTree = new GameTree(board, activePlayer);
    }

    public HexBoard getValue() {
        return this.value;
    }
    
    public GameTree getParent() {
        return parent;
    }
    
    public List<GameTree> getChildren() {
        return children;
    }
    
    public void setChildren(GameTree children) {
        children = children;
    }
    
    public int size() {
        if (isEmpty()) {
            return 0;
        } else if (children.size() == 0) {
            return 0;
        } else {
            for (GameTree child : children) {
            return child.size() + 1;
           }
        }
        return 9; //return children.size() + 1;
    }
    
    public boolean isEmpty() {
        return value == null;
    }
    
    public void setParent(GameTree parent) {
        parent.addChild(this);
        this.parent = parent;
    }
    
    public void addChild(HexBoard data) {
        Marker firstPlayer = Marker.WHITE;
        GameTree child = new GameTree(data, firstPlayer);
        child.setParent(this);
        this.children.add(child);
    }
    
    public void addChild(GameTree child) {
        child.setParent(this);
        this.children.add(child);
    }            
}
