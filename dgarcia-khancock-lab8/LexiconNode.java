import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a single node in a LexiconTrie.
 *
 * @author Devon Garcia, Kim Hancock
 * @version November 19, 2018
 */
public class LexiconNode {
    
    private List<LexiconNode> children = new ArrayList<LexiconNode>(); // list of LexiconNode objects
    
    private char currentLetter; // the letter at the current LexiconNode object
    
    private boolean isEndLetter; // whether a letter is the last letter of a word
    
    /**
     * Constructor for LexiconNode objects.
     * 
     * @param currentLetter
     *          The letter at the current LexiconNode object.
     */
    public LexiconNode(char currentLetter) {
        this.currentLetter = currentLetter;
        this.children = children;
    }
    
    /**
     * Gets a LexiconNode object based on a given letter from a list of LexiconNode objects.
     * 
     * @param letter
     *          A character for which a corresponding LexiconNode object is searched for.
     * 
     * @return A LexiconNode object holding a letter from a list of LexiconNode objects,
     *         or null if no such object exists in the list.
     */
    public LexiconNode getChild(char letter) {
        for(LexiconNode nextChild : children) {
            if (nextChild.getCurrentLetter() == letter) {
                return nextChild;
            }
        }
        return null;
    }
    
    /**
     * Gets the letter of the current LexiconNode object.
     * 
     * @return currentLetter
     *          The letter at the current LexiconNode object.
     */
    public char getCurrentLetter() {
        return currentLetter;
    }     
    
    /**
     * Gets the list of LexiconNode children.
     * 
     * @return children
     *          A list (ArrayList) of LexiconNode objects.
     */
    public List<LexiconNode> getChildren() {
        return children;
    }
    
    /**
     * Adds a child to its proper place in alphabetical order in the list of LexiconNode children.
     * 
     * @param letter
     *          The letter to be added to the list of LexiconNodes.
     * 
     * @return A LexiconNode object containing a letter.
     */
    public LexiconNode addChild(char letter) {
        int i = 0;
        LexiconNode nextLetter = new LexiconNode(letter);
        if (children.size() == 0) {
            this.children.add(i, nextLetter);              
            return nextLetter;
        } else {
            char nextChar;
            for (LexiconNode child : children) {
                nextChar = child.getCurrentLetter();
                if (Character.valueOf(letter).compareTo(Character.valueOf(nextChar)) == 0) {
                    return null;
                } else if (Character.valueOf(letter).compareTo(Character.valueOf(nextChar)) < 0) {
                    this.children.add(i, nextLetter);              
                    return nextLetter;
                }                        
                i++;
            }
            this.children.add(nextLetter);
            return nextLetter;
        }
    }
    
    /**
     * Sets the isEndLetter instance variable equal to true if the path leading to a node represents
     * a word in the lexicon, false if not.
     * 
     * @param bool
     *          A boolean describing whether isEndLetter is true or false.
     */
    public void setIsEndLetter(boolean bool) {
        this.isEndLetter = bool;
    }
    
    /**
     * Returns whether the letter of a node is the last letter of a word in the trie.
     * 
     * @return True if the letter is the last, false if not.
     */
    public boolean getIsEndLetter() {
        return isEndLetter;
    }
    
    /**
     * Removes a child LexiconNode (ie. a character) from the list of LexiconNode children.
     * 
     * @param letter
     *          The character for which its corresponding LexiconNode object is to be removed.
     */
    public void removeChild(char letter) {
        for (LexiconNode child : children) {
            if (child.getCurrentLetter() == letter) {
                children.remove(child);
                isEndLetter = false;
            }
        }
    }
}