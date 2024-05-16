package simulation;

public abstract class Creature {
    private boolean isAlive;
    int numOfChildren;
    private int x,y;

    static int numOfCreatures = 0;

    void setAliveToDead()
    {
        this.isAlive = false;
    }

    boolean getIsAlive() {
        return isAlive;
    }
}
