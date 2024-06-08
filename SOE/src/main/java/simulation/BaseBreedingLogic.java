package simulation;

public abstract class BaseBreedingLogic implements BreedingLogic{

    protected boolean isBreedingEnabled() {
        return false;
    }

    /*protected boolean isEnemyNear() {git
        return false;
    }

    protected boolean isPartnerNear() {
        return false;
    }*/

    protected boolean isMaxPopulationReached() {
        return false;
    }

    protected void drawBreedingChance() {

    }


    protected void breed(Creature secondParent) {

    }

    @Override
    public void performBreeding() {

    }

}
