package sample;

public class Plant extends Organisms {
    private final int MAX_LIFE_YEAR = 10;

    public Plant(){
        super(0);
    }

    public Plant( int age ) {
        super(age);
    }

    @Override
    public boolean isAlive() {
        if ( getAge() > MAX_LIFE_YEAR )
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return " p ";
    }
}
