package simulation;

/**
 * Object <code>Wolf</code> represents a wolf, it is a subclass of Creature
 */
public class Wolf extends Creature {
    static int numOfWolf = 0;
    static final int numOfChildren = 2;
    /**
     * default constructor creating Object <code>Wolf</code>
     */
    Wolf()
    {
        numOfWolf++;
        numOfCreatures++;
    }
}
