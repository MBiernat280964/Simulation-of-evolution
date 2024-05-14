package simulation;

public class Cockroach extends Creature {
    static int numOfCockroach = 0;
    Cockroach()
    {
        numOfCockroach++;
        numOfCreatures++;
        this.numOfChildren = 5;
    }
}
