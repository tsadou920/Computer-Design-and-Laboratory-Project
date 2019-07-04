package sample;

public class Herbivore extends Animal {
    private final int MAX_LIFE = 5;

    public Herbivore() {
        super();
    }

    public Herbivore(int age, int energy) {
        super(age, energy);
    }

    public Herbivore( int age, int energy, int visited[][]){
        super(age, energy, visited);
    }
    @Override
    public boolean isAlive() {
        if ( super.getAge() > MAX_LIFE || super.getEnergy() < 0)
            return false;
        else return true;
    }

    @Override
    public String toString() {
        return " h ";
    }
}
