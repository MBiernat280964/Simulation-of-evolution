package simulation;
import java.util.List;
import java.util.Random;
/**
 * Object <code>Creature</code> has information about the creature
 */
public class Creature {
    private Species species;
    private int x;
    private int y;
    private int hp;
    private boolean breedingEnabled;
    private int speed;
    private int[] velocity;
    /**
     *Constructor for creature
     */
    public Creature(Species species) {
        this.species = species;
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

    public boolean getBreedingEnabled() {
        return breedingEnabled;
    }

    public void setBreedingEnabled(boolean isBreedingEnabled) {
        breedingEnabled = isBreedingEnabled;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int[] getVelocity() {
        return velocity;
    }

    public void setVelocity(int[] velocity) {
        this.velocity = velocity;
    }
}

