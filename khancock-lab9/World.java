import java.util.Random;

/**
 * This class represents the two-dimensional world during a Darwin simulation.
 * Each position of the world may be populated either by nothing or by a single
 * creature.
 */
public class World {

    private int width;
    private int height;
    private Creature[][] darwinWorld;
    
    /**
     * Create a new world consisting of width columns and height rows. Initially,
     * the world contains no creatures.
     * 
     * @param width The width of the world.
     * @param height The height of the world.
     */
    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.darwinWorld = new Creature[width][height];       
    }

    /**
     * Get the height of the world.
     * 
     * @return The world height.
     */
    public int height() {
        return this.height;
    }

    /**
     * Get the width of the world.
     * 
     * @return The world width.
     */
    public int width() {
        return this.width;
    }

    /**
     * Check whether the given position is within the bounds of the world (i.e.,
     * its x and y coordinates specify a valid world position).
     * 
     * @param pos The position to check.
     * @return Whether the given position is within the world bounds.
     */
    public boolean inBounds(Position pos) {
        if (pos.getX() <= width && pos.getY() <= height) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a random position within the bounds of the world. All valid world
     * positions (occupied or not) are returned with equal probability.
     * 
     * @return A random position within the world.
     */
    public Position randomPosition() {
        Random rand = new Random();
        int randX = rand.nextInt(width);
        int randY = rand.nextInt(height);
        return new Position(randX, randY);
    }

    /**
     * Update the given world position to contain the given creature (which may be
     * null, in which case the world position is cleared).
     * 
     * @param pos The position to update.
     * @param creature The creature to place at the given position, or null.
     */
    public void set(Position pos, Creature creature) {
        if (creature == null) {
            darwinWorld[pos.getX()][pos.getY()] = null;
        } else {
            creature.pos = pos; 
            darwinWorld[pos.getX()][pos.getY()] = creature;
        }
    }

    /**
     * Get the creature at the given position of the board, or null if no creature
     * occupies that position.
     * 
     * @param pos The position to get.
     * @return The creature at the specified position, or null.
     */
    public Creature get(Position pos) {
        Creature thisCreature = darwinWorld[pos.getX()][pos.getY()];        
        return thisCreature;
    }
    
    /**
     * Tests the functionality of the World class.
     */
    public static void main(String[] args) {
        World testWorld = new World(10, 5);
        
        //test inBounds method
        Position testPos = new Position(11, 5);        
        System.out.println("Test for inBounds:" + testWorld.inBounds(testPos));
        
        //test width method
        System.out.println("Test for width:" + testWorld.width());
        
        //test height method
        System.out.println("Test for height:" + testWorld.height());
        
        //test set method
        Creature testCreature1 = new Creature();
        Creature testCreature2 = new Creature();
        testWorld.set(new Position(3, 4), testCreature1);
        testWorld.set(new Position(2, 4), testCreature2);
        System.out.println(testWorld.darwinWorld[3][4]);
        System.out.println(testWorld.darwinWorld[2][4]);       
        System.out.println("The following test should be null!\n" + testWorld.darwinWorld[1][4]); //should be null
        
        //test randomPosition method
        System.out.println(testWorld.randomPosition());
        
        //test get method
        System.out.println(testWorld.get(new Position(3,4)));
        
    }

}
