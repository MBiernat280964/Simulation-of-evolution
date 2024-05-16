package simulation;

/**
 * Object <code>Dinosaur</code> represents a dino, it is a subclass of Creature
 */
public class Dinosaur extends Creature {
    static int numOfDinosaur = 0;
    static final int numOfChildren = 5;
    /**
    * default constructor creating Object <code>Dinosaur</code>
    */
    Dinosaur()
    {
        numOfDinosaur++;
        numOfCreatures++;
    }
}
