package simulation;
import java.util.Random;

public class Creature {
    private Random rand = new Random(System.currentTimeMillis());
    private Species species;
    private int x,y;
    private int hp;

    public Creature(Species species) {
        this.species = species;
    }

    void randCoordinates()
    {
        //do
        //{
            x = rand.nextInt(100);
            y = rand.nextInt(100);
        //}
        //while (map.checkIfFree(rand_int1, rand_int2) == true);
    }

    public Species getSpecies() {
        return species;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}

