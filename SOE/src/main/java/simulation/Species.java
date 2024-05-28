package simulation;

public class Species {
    private int speed;
    private int baseHp;
    private String name;

    public Species(int speed, int baseHp, String name) {
        this.speed = speed;
        this.baseHp = baseHp;
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
