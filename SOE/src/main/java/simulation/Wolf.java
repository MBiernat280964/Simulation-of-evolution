package simulation;

public class Wolf extends Creature {
    static int numOfWolf = 0;
    Wolf()
    {
        numOfWolf++;
        numOfCreatures++;
        this.numOfChildren = 2;
    }
}
