package sample;

public class Carnivore extends Animal {
    private final int MaxLife = 7;

    public Carnivore() {
        super();
    }

    public Carnivore(int age, int energy)
    {
        super(age, energy);
    }
    public Carnivore ( int age, int energy, int visited[][]){
        super(age, energy, visited);
    }
    @Override
    public boolean isAlive() {
        if ( super.getAge() > MaxLife|| super.getEnergy() < 0)
            return false;
        else
            return true;
    }
    @Override
    public String toString() {
        return " c ";
    }
}
