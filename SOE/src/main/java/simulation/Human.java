package simulation;

/**
 * Object <code>Human</code> represents a human, it is a subclass of Creature
 */

public class Human extends Creature {
    static int numOfHuman = 0;
    static final int speed = 3;
    static final int numOfChildren = 2;
    /**
     * default constructor creating Object <code>Human</code>
     */
    Human()
    {
        numOfHuman++;
        numOfCreatures++;
    }
}
