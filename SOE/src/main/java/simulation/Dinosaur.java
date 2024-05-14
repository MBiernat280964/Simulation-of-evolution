package simulation;

public class Dinosaur extends Creature {
    static int numOfDinosaur = 0;
    Dinosaur()
    {
        numOfDinosaur++;
        numOfCreatures++;
        this.numOfChildren = 1;
    }
}
