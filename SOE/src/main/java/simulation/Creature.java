package simulation;

public abstract class Creature {
    private boolean isAlive;
    int numOfChildren;
    private int x,y;

    static int numOfCreatures = 0;

    void setAlivetoDead()
    {
        this.isAlive = false;
    }

    public boolean getIsAlive() {
        return isAlive;
    }
}
