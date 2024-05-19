package simulation;
import java.util.Random;

public abstract class Creature {
    Random rand = new Random(System.currentTimeMillis());
    private boolean isAlive;
    int x,y;
    static int numOfCreatures = 0;

    void setAliveToDead()
    {
        this.isAlive = false;
        numOfCreatures--;
    }

    boolean getIsAlive() {
        return isAlive;
    }
    void randCoordinates()
    {
        int rand_int1=0;
        int rand_int2 =0;
        do
        {
            rand_int1 = rand.nextInt(100);
            rand_int2 = rand.nextInt(100);
            this.x = rand_int1;
            this.y = rand_int2;
        }
        while (Map.checkIfFree(rand_int1, rand_int2) == true);
    }
}

