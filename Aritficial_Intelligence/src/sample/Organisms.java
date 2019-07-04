package sample;

public abstract class Organisms {
    public static int ROW= 16;
    public static int COL = 16;

    private int age = 0;
 //   private boolean alive = true;

    public Organisms() {
        this(0);
    }

    public Organisms(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge() {
        this.age++;
    }

    public boolean isAlive() {
        return true;
    }

    public void setEnergy(int energy) {
    }

    public int getEnergy() {
        return 0;
    }

    public int[][] getVisited(){
        return null;
    }
    public int lastVisited(int row, int col){
        return 0;
    }
    public void setVisited(int row, int col) {

    }


    //public void setAlive(boolean alive){ this.alive = alive; }

    //public void updateLife(){ }

    // method to get neighbor
    public Organisms[] neighbor(Organisms life[][], int row, int col, int X[], int Y[]) {
        Organisms[] currentNeighbor = new Organisms[8];
        return currentNeighbor;
    }
}
