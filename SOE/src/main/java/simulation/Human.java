package simulation;

public class Human extends Creature {
    static int numOfHuman = 0;
    Human()
    {
        numOfHuman++;
        numOfCreatures++;
        this.numOfChildren = 2;
    }
}
