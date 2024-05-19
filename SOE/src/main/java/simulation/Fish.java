package simulation;

/**
 * Object <code>Fish</code> represents a fish, it is a subclass of Creature
 */
public class Fish extends Creature {
    static int numOfFish = 0;
    static final int speed = 5;
    static final int numOfChildren = 4;
    /**
     * default constructor creating Object <code>Fish</code>
     */
    Fish()
    {
        numOfFish++;
        numOfCreatures++;
    }
}
