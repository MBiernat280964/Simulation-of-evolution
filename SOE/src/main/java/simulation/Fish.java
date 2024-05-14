package simulation;

public class Fish extends Creature {
    static int numOfFish = 0;
    Fish()
    {
        numOfFish++;
        numOfCreatures++;
        this.numOfChildren = 4;
    }
}
