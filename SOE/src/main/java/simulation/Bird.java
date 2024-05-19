package simulation;

/**
 * Object <code>Bird</code> represents a bird, it is a subclass of Creature
 */
public class Bird extends Creature {
    static int numOfBirds;
    static final int numOfChildren = 1;
    static final int speed = 10;

    /**
     * default constructor creating Object <code>Bird</code>
     */
        Bird()
        {
            numOfCreatures++;
            numOfBirds++;
        }
}
