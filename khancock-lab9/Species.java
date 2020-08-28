import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class represents a type of creature in a Darwin simulation. All
 * creatures of a given species execute the instructions of that species's
 * Darwin program, which specify how the creatures behave in the world. Each
 * Darwin program consists of a set of ordered instructions, each with an
 * instruction address (starting from address 0, like an index). At any given
 * time, each creature is at some instruction address, and continues executing
 * from that point the next time the creature acts. All creatures of a given
 * species are represented by a particular color.
 */
public class Species {

    /**
     * An exception indicating that the given Species program file was malformed.
     */
    public static class BadSpeciesException extends RuntimeException {

        /**
         * Construct a new exception indicating that the species program was
         * malformed.
         * 
         * @param msg A message describing the problem.
         */
        public BadSpeciesException(String msg) {
            super(msg); // pass msg to parent constructor
        }

    }

    /**
     * Create a new species using the given Darwin program and the specified
     * color. May throw a BadSpeciesException if the given file does not exist or
     * does not contain a well-formed Darwin program.
     * 
     * @param filename The filename of a Darwin program.
     * @param color The color to use for this species.
     */
    public Species(String filename, Color color) {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Get the name of the species.
     * 
     * @return The species name.
     */
    public String getName() {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Get the color of the species.
     * 
     * @return The species color.
     */
    public Color getColor() {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Get the number of instructions in the species program.
     * 
     * @return The number of species program instructions.
     */
    public int programSize() {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Get a particular instruction from the species program.
     * 
     * @param address The address of the desired instruction.
     * @return The specified instruction.
     */
    public Instruction programStep(int address) {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Get the address of the instruction within the species program corresponding
     * to the given label. Assumes that a label instruction with the given name
     * exists within the species program.
     * 
     * @param label The name of the label to lookup.
     * @return The instruction address of the given label.
     */
    public int getLabelAddress(String label) {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Construct a string representation of the species program in some reasonable
     * format. Useful for debugging.
     * 
     * @return A string representing the species program.
     */
    public String programToString() {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Tests the functionality of the Species class.
     */
    public static void main(String[] s) {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

}
