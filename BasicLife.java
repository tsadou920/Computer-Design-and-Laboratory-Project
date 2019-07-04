import java.util.Random;

public abstract class BasicLife {   //a template for all the subclasses
    private int energy, age;
    public BasicLife() {    //default constructor
        energy = 0;
        age = 0;
    }
    public BasicLife(int energy) {  //constructor with an initial value for energy
        this.energy = energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public int getEnergy() { return energy; }
    public void setAge(int age) {
        if(age<0)
            throw new IllegalArgumentException("Age must be greater than or equal to 0!");
        this.age = age;
    }
    public int getAge() { return age; }
    public abstract void eat(BasicLife[][] grid, int i, int j);
    public abstract void move(BasicLife[][] grid, int i, int j);
    public abstract String getClassName();
    @Override
    public String toString() {
        return "-";
    }
}

class Plant extends BasicLife {
   public Plant() { super(); }

   @Override
   public void eat(BasicLife[][] grid, int i, int j) { return; }

   @Override
   public void move(BasicLife[][] grid, int i, int j) { return; }

   @Override
   public String getClassName() {
       return this.getClass().getSimpleName();
   }
   @Override
   public String toString() {
       return "#";
   }
}

class Herbivore extends Plant {
    private int age;
    public Herbivore() {
        super();
        age = 0;
    }

    @Override
    public void eat(BasicLife[][] grid, int i, int j) {
        if((i-1)>=0 && (j-1)>=0 && grid[i-1][j-1] != null && grid[i-1][j-1].getClassName() == "Plant") {
            grid[i-1][j-1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((i-1)>=0 && grid[i-1][j] != null && grid[i-1][j].getClassName() == "Plant") {
            grid[i-1][j] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((i-1)>=0 && (j+1)<16 && grid[i-1][j+1] != null && grid[i-1][j+1].getClassName() == "Plant") {
            grid[i-1][j+1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((j-1)>=0 && grid[i][j-1] != null && grid[i][j-1].getClassName() == "Plant") {
            grid[i][j-1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((j+1)<16 && grid[i][j+1] != null && grid[i][j+1].getClassName() == "Plant") {
            grid[i][j+1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((i+1)<16 && (j-1)>=0 && grid[i+1][j-1] != null && grid[i+1][j-1].getClassName() == "Plant") {
            grid[i+1][j-1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((i+1)<16 && grid[i+1][j] != null && grid[i+1][j].getClassName() == "Plant") {
            grid[i+1][j] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((i+1)<16 && (j+1)<16 && grid[i+1][j+1] != null && grid[i+1][j+1].getClassName() == "Plant") {
            grid[i+1][j+1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
    }

    @Override
    public void move(BasicLife[][] grid, int i, int j) {
        if((i-1)>=0 && (j-1)>=0) {
            if(grid[i-1][j-1] == null) {
                grid[i-1][j-1] = grid[i][j];
                grid[i][j] = null;
                return;
            }
        }
        else if((i-1)>=0) {
            if(grid[i-1][j] == null) {
                grid[i-1][j] = grid[i][j];
                grid[i][j] = null;
                return;
            }
        }
        else if((i-1)>=0 && (j+1)<16) {
            if(grid[i-1][j+1] == null) {
                grid[i-1][j+1] = grid[i][j];
                grid[i][j] = null;
                return;
            }
        }
        else if((j-1)>=0) {
            if(grid[i][j-1] == null) {
                grid[i][j-1] = grid[i][j];
                grid[i][j] = null;
                return;
            }
        }
        else if((j+1)<16) {
            if(grid[i][j+1] == null) {
                grid[i][j+1] = grid[i][j];
                grid[i][j] = null;
                return;
            }
        }
        else if((i+1)<16 && (j-1)>=0) {
            if(grid[i+1][j-1] == null) {
                grid[i+1][j-1] = grid[i][j];
                grid[i][j] = null;
                return;
            }
        }
        else if((i+1)<16) {
            if(grid[i+1][j] == null) {
                grid[i+1][j] = grid[i][j];
                grid[i][j] = null;
                return;
            }
        }
        else if((i+1)<16 && (j+1)<16) {
            if(grid[i+1][j+1] == null) {
                grid[i+1][j+1] = grid[i][j];
                grid[i][j] = null;
            }
        }
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "&";
    }
}

class Carnivore extends Herbivore {
    public Carnivore() {
        super();
    }

    @Override
    public void eat(BasicLife[][] grid, int i, int j) {
        if((i-1)>=0 && (j-1)>=0 && grid[i-1][j-1] != null && grid[i-1][j-1].getClassName() != "Carnivore") {
            grid[i-1][j-1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((i-1)>=0 && grid[i-1][j] != null && grid[i-1][j].getClassName() != "Carnivore") {
            grid[i-1][j] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((i-1)>=0 && (j+1)<16 && grid[i-1][j+1] != null && grid[i-1][j+1].getClassName() != "Carnivore") {
            grid[i-1][j+1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((j-1)>=0 && grid[i][j-1] != null && grid[i][j-1].getClassName() != "Carnivore") {
            grid[i][j-1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((j+1)<16 && grid[i][j+1] != null && grid[i][j+1].getClassName() != "Carnivore") {
            grid[i][j+1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((i+1)<16 && (j-1)>=0 && grid[i+1][j-1] != null && grid[i+1][j-1].getClassName() != "Carnivore") {
            grid[i+1][j-1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((i+1)<16 && grid[i+1][j] != null && grid[i+1][j].getClassName() != "Carnivore") {
            grid[i+1][j] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
        else if((i+1)<16 && (j+1)<16 && grid[i+1][j+1] != null && grid[i+1][j+1].getClassName() != "Carnivore") {
            grid[i+1][j+1] = null;
            grid[i][j].setEnergy(grid[i][j].getEnergy() + 5);
            return;
        }
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "@";
    }
}

class TestLife {
    public static void printPlanet(BasicLife[][] grid) {
        for(int i =0; i<grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == null)
                    System.out.print("- ");
                else
                    System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*growPlant populates cells of the grid with at most
    * n number of plants*/
    public static void growPlant(BasicLife[][] grid, int n) {
        Random rn = new Random();
        int c1 = rn.nextInt(70) + n;
        for (int k = 0; k < c1; k++) {
            int i = rn.nextInt(16);
            int j = rn.nextInt(16);
            if (grid[i][j] == null) {
                grid[i][j] = new Plant();
            }
        }
    }

    /*createLife populates the cells of the grid with some
    * random numbers of plant, herbivore, and carnivore*/
    public static void createLife(BasicLife[][] grid) {
        Random rn = new Random();
        growPlant(grid, 150);

        int numOfHerb = 0;
        while(numOfHerb<60) {
            int i = rn.nextInt(16);
            int j = rn.nextInt(16);
            if (grid[i][j] == null) {
                grid[i][j] = new Herbivore();
                numOfHerb += 1;
            }
        }

        int numOfCarn = 0;
        while(numOfCarn<10) {
            int i = rn.nextInt(16);
            int j = rn.nextInt(16);
            if (grid[i][j] == null) {
                grid[i][j] = new Carnivore();
                numOfCarn += 1;
            }
        }
    }

    public static void reproduce(BasicLife[][] grid, int i, int j) {
        if((i+1)<16 && (j+1)<16 && grid[i+1][j+1] == null) {
            if (grid[i][j].getClassName() == "Herbivore")
                grid[i+1][j+1] = new Herbivore();
            else
                grid[i+1][j+1] = new Carnivore();
        }
        if((i+1)<16 && grid[i+1][j] == null) {
            if (grid[i][j].getClassName() == "Herbivore")
                grid[i+1][j] = new Herbivore();
            else
                grid[i+1][j] = new Carnivore();
        }
        if((i+1)<16 && (j-1)>=0 && grid[i+1][j-1] == null) {
            if (grid[i][j].getClassName() == "Herbivore")
                grid[i+1][j-1] = new Herbivore();
            else
                grid[i+1][j-1] = new Carnivore();
        }
        if((j-1)>=0 && grid[i][j-1] == null) {
            if (grid[i][j].getClassName() == "Herbivore")
                grid[i][j-1] = new Herbivore();
            else
                grid[i][j-1] = new Carnivore();
        }
        if((j+1)<16 && grid[i][j+1] == null) {
            if (grid[i][j].getClassName() == "Herbivore")
                grid[i][j+1] = new Herbivore();
            else
                grid[i][j+1] = new Carnivore();
        }
        if((i-1)>=0 && (j+1)<16 && grid[i-1][j+1] == null) {
            if (grid[i][j].getClassName() == "Herbivore")
                grid[i-1][j+1] = new Herbivore();
            else
                grid[i-1][j+1] = new Carnivore();
        }
        if((i-1)>=0 && grid[i-1][j] == null) {
            if (grid[i][j].getClassName() == "Herbivore")
                grid[i-1][j] = new Herbivore();
            else
                grid[i-1][j] = new Carnivore();
        }
        if((i-1)>=0 && (j-1)>=0 && grid[i-1][j-1] == null) {
            if (grid[i][j].getClassName() == "Herbivore")
                grid[i-1][j-1] = new Herbivore();
            else
                grid[i-1][j-1] = new Carnivore();
        }
    }

    public static void main(String[] args) {
        BasicLife[][] grid = new BasicLife[16][16];
        System.out.println("Initially, there is no life in the planet!!!");
        printPlanet(grid);

        System.out.println("\nNow, I am creating some lifeforms in the planet: ");
        createLife(grid);
        printPlanet(grid);

        System.out.println("\nNow, in each iteration I am going to implement the food chain \n as well as the movements of life: ");
        for(int k=0; k<32; k++) {
            growPlant(grid, 10);
            for(int i=0; i<16; i++)
                for(int j=0; j<16; j++) {
                    if (grid[i][j] != null && grid[i][j].getClassName() != "Plant") {
                        grid[i][j].setAge(grid[i][j].getAge() + 1);
                        grid[i][j].eat(grid, i, j);
                        grid[i][j].move(grid, i, j);
                    }
                    if(grid[i][j] != null && grid[i][j].getClassName() != "Plant" && grid[i][j].getAge()>=5 && grid[i][j].getEnergy()>=5) {
                        reproduce(grid, i, j);
                    }
                }
            System.out.println("\nIteration no. " + (k+1));
            printPlanet(grid);
        }
    }
}
