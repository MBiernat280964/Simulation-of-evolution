package simulation;

/**
 * Object <code>Cockroach</code> represents a cockroach, it is a subclass of Creature
 */
public class Cockroach extends Creature {
    static int numOfCockroach = 0;
    static final int numOfChildren = 5;

    /**
     * default constructor creating Object <code>Cockroach</code>
     */
    Cockroach()
    {
        numOfCockroach++;
        numOfCreatures++;
    }
}
