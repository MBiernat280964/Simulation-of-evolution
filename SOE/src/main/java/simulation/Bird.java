package simulation;

public class Bird extends Creature {
    static int numOfBirds;

        Bird()
        {
            numOfCreatures++;
            numOfBirds++;
            this.numOfChildren = 1;
        }
}
