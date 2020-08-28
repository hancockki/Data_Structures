import java.util.Iterator;
import java.util.Set;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Implementation of the Lexicon interface. Implements methods for constructing a lexicon, or word 
 * list, including adding, removing, and iterating over words. Uses a trie structure of recursively 
 * defined LexiconNode objects representing letters.
 *
 * @authors Devon Garcia, Kim Hancock
 * @version November 19, 2018
 */
public class LexiconTrie implements Lexicon {    

    private LexiconNode root;

    private int numWords = 0;
    
    /**
     * Constructor for LexiconTrie objects.
     */
    public LexiconTrie() {
        this.root = new LexiconNode(' '); //root is empty
    }

    /**
     * Adds the specified word to the lexicon. Should run in time proportional to
     * the length of the word being added. Returns whether the lexicon was
     * modified by adding the word.
     * 
     * @param word
     *          The lowercase word to add to the lexicon.
     * @return True if the word was added and false if the word was already part
     *         of the lexicon.
     */
    public boolean addWord(String word) {
        LexiconNode currentNode = root;
        String currentWord = word;
        if (containsWord(word)) {
            return false;
        } else {
            while (currentWord.length() > 0) {
                if (currentNode.getChild(currentWord.charAt(0)) == null) {                
                    LexiconNode nextNode = currentNode.addChild(currentWord.charAt(0));
                    currentNode = nextNode;
                    currentWord = currentWord.substring(1);
                } else {
                    currentNode = currentNode.getChild(currentWord.charAt(0));
                    currentWord = currentWord.substring(1);
                }
            }
            currentNode.setIsEndLetter(true);
            numWords++;
            return true;
        }
    }

    /**
     * Reads the words contained in the specified file and adds them to the
     * lexicon. The format of the given file is expected to be one word per line
     * of the file. All words should be converted to lowercase before adding.
     * Returns the number of new words added, or -1 if the file could not be read.
     * 
     * @param filename
     *          The name of the file to read.
     * @return The number of new words added, or -1 if the file could not be read.
     */
    public int addWordsFromFile(String filename) {
        int numNewWords = 0;
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine()) {                
                String[] newWords = scan.nextLine().toLowerCase().split(" ");
                for (String word : newWords) {
                    addWord(word);
                    numNewWords++;
                }
            }
            return numNewWords;
        } catch (FileNotFoundException e) {
            return -1;
        }        
    }

    /**
     * Attempts to remove the specified word from the lexicon. If the word appears
     * in the lexicon, it is removed and true is returned. If the word does not
     * appear in the lexicon, the lexicon is unchanged and false is returned.
     * Should run in time proportional to the length of the word being removed. It
     * is implementation-dependent whether unneeded prefixes as a result of
     * removing the word are also removed from the lexicon.
     * 
     * @param word
     *          The lowercase word to remove from the lexicon.
     * @return Whether the word was removed.
     */
    public boolean removeWord(String word) {
        LexiconNode lastLetter = checkForWord(word);
        lastLetter.setIsEndLetter(false);
        return true;
    }

    /**
     * Returns the number of words contained in the lexicon. Should run in
     * constant time.
     * 
     * @return The number of words in the lexicon.
     */
    public int numWords() {
        return numWords;
    }

    /**
     * Checks whether the given word exists in the lexicon. Should run in time
     * proportional to the length of the word being looked up.
     * 
     * @param word
     *          The lowercase word to lookup in the lexicon.
     * @return Whether the given word exists in the lexicon.
     */
    public boolean containsWord(String word) {
        LexiconNode lastNode = checkForWord(word);
        if (lastNode == null) {
            return false;
        } else if (lastNode.getIsEndLetter()) {
            return true;
        }        
        return false;
    }

    /**
     * Helper method that iterates through the trie over the path of the 
     * input string. Checks to see that each letter is a LexiconNode in the trie.
     * 
     * @param word
     *          The lowercase string to lookup in the lexicon.
     * @return The LexiconNode object representing the last letter in the
     *       input string, or null if the string is not in the trie.
     */
    public LexiconNode checkForWord(String word) {
        LexiconNode currentNode = root;
        String currentWord = word;
        while (currentWord.length() > 0) {
            char nextChar = currentWord.charAt(0);
            if (currentNode.getChild(nextChar) != null) {
                currentNode = currentNode.getChild(nextChar);
                currentWord = currentWord.substring(1);
            } else {
                return null;
            }       
        }        
        return currentNode;
    }    

    /**
     * Checks whether any words in the lexicon begin with the specified prefix. A
     * word is defined to be a prefix of itself, and the empty string is defined
     * to be a prefix of everything. Should run in time proportional to the length
     * of the prefix.
     * 
     * @param prefix
     *          The lowercase prefix to lookup in the lexicon.
     * @return Whether the given prefix exists in the lexicon.
     */
    public boolean containsPrefix(String prefix) {        
        LexiconNode lastNode = checkForWord(prefix);
        if (lastNode == null) {
            return false;
        } else if(lastNode.getIsEndLetter()) {
            return true;
        } else if (lastNode.getChildren() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns an iterator over all words contained in the lexicon. The iterator
     * should return words in the lexicon in alphabetical order.
     */
    public Iterator<String> iterator() {
        LexiconNode currentNode = root;
        List<String> wordsToPrint= new ArrayList<String>();
        addToList(wordsToPrint, "", currentNode);            
        Iterator<String> stringIter = wordsToPrint.iterator();        
        return stringIter;        
    }
    
    /**
     * Helper method for iterator(). Recursively adds words to a list to iterate over by adding
     * characters from a particular branch until a LexiconNode that is a word end is reached.
     * 
     * @param wordsToPrint 
     *          The list of words to iterate over.
     * @param nextWord
     *          The next word to add to the list in the trie.
     * @param currentNode
     *          The next node to iterate over.
     */
    public void addToList(List<String> wordsToPrint, String nextWord, LexiconNode currentNode) {
        if (currentNode.getIsEndLetter()) {
            wordsToPrint.add(nextWord);
        }                
        for (LexiconNode child : currentNode.getChildren()) {
            addToList(wordsToPrint, nextWord + child.getCurrentLetter(), child);        
        }
    }
    
    /**
     * Returns a set of words in the lexicon that are suggested corrections for a
     * given (possibly misspelled) word. Suggestions will include all words in the
     * lexicon that are at most maxDistance distance from the target word, where
     * the distance between two words is defined as the number of character
     * positions in which the words differ. Should run in time proportional to the
     * length of the target word.
     * 
     * @param target
     *          The target word to be corrected.
     * @param maxDistance
     *          The maximum word distance of suggested corrections.
     * @return A set of all suggested corrections within maxDistance of the target
     *         word.
     */
    public Set<String> suggestCorrections(String target, int maxDistance) {
        Set<String> corrections = new HashSet<String>();
        int curDistance = 0;
        LexiconNode currentNode = root;
        addCorrections(corrections, "", target, currentNode, curDistance, maxDistance);          
        return corrections;   
    }
    
    /**
     * Helper method for suggestCorrections. Recursively checks whether the letter
     * at the current node matches the letter in the string suggested.
     * 
     * @param corrections
     *          The set of words whose distance from the given string is less
     *          than the distance given.
     * @param nextWord
     *          The next word to be added to the set if it fits the given criteria.
     * @param target
     *          The word to find suggestions for.
     * @param currentNode
     *          The next node to iterate over.
     * @param curDistance
     *          The current word distance of suggested corrections.
     * @param maxDistance
     *          The maximum word distance of suggested corrections.
     */
    private void addCorrections(Set<String> corrections, String nextWord, String target, LexiconNode currentNode, int curDistance, int maxDistance) {
        if (maxDistance < curDistance) { //reached max distance
            return;
        } else if (target.length() < 1 && currentNode.getIsEndLetter()) { //word is in trie
            corrections.add(nextWord);
        } else if (target.length() < 1) { //word is not in trie
            return;        
        } else {
            for (LexiconNode child : currentNode.getChildren()) {
                if (target.charAt(0) == (child.getCurrentLetter())) { //letter matches
                    addCorrections(corrections, nextWord + child.getCurrentLetter(), target.substring(1), child, curDistance, maxDistance);
                } else if (target.charAt(0) != child.getCurrentLetter()) { //letter does not match
                    addCorrections(corrections, nextWord + child.getCurrentLetter(), target.substring(1), child, curDistance + 1, maxDistance);
                }
            }
        }        
    }    
    
    /**
     * Returns a set of all words in the lexicon that match the given regular
     * expression pattern. The regular expression pattern may contain only letters
     * and wildcard characters '*', '?', and '_'.
     * 
     * @param pattern
     *          The regular expression pattern to match.
     * @return A set of all words in the lexicon matching the pattern.
     */
    public Set<String> matchRegex(String pattern) {
        Set<String> regexWords = new HashSet<String>();
        LexiconNode currentNode = root;
        addRegexMatches(regexWords, currentNode, pattern, "");        
        return regexWords;
    }     
    
    /**
     * Helper method for matchRegex. Recursively adds words that match
     * the given regular expressions pattern to a set.
     * 
     * @param regexWords
     *          The set of words in the lexicon matching the pattern.
     * @param currentNode
     *          The current node to iterate over.
     * @param pattern
     *          The regular expression pattern to match.
     * @param match
     *          The word in the lexicon that matches the pattern and is added to the set.
     */
    private void addRegexMatches(Set<String> regexWords, LexiconNode currentNode, String pattern, String match) {
        if (pattern.length() == 0 && currentNode.getIsEndLetter()) {
            regexWords.add(match);
        } else if(pattern.length() == 0) {
            return;
        } else {
            for (LexiconNode child : currentNode.getChildren()) {
                if (pattern.charAt(0) == '*') {
                    addRegexMatches(regexWords, child, pattern, match + child.getCurrentLetter());
                    addRegexMatches(regexWords, currentNode, pattern.substring(1), match);
                } else if (pattern.charAt(0) == '?') {
                    addRegexMatches(regexWords, child, pattern.substring(1), match + child.getCurrentLetter());
                    addRegexMatches(regexWords, currentNode, pattern.substring(1), match);
                } else if (pattern.charAt(0) == '_') {
                    addRegexMatches(regexWords, child, pattern.substring(1), match + child.getCurrentLetter());
                } else if (child.getCurrentLetter() == pattern.charAt(0)) {
                    addRegexMatches(regexWords, child, pattern.substring(1), match + child.getCurrentLetter());
                }                    
            }
        }
    }
}
