package sample;

public class Animal extends Organisms {
    private int energy = 5;
    private int[][] visited = new int[ROW][COL];

    public Animal() {
        super(0);
        this.energy = 5;
    }

    public Animal(int age, int energy) {
        super(age);
        this.energy = energy;
    }

    public Animal(int age, int energy, int[][] visited) {
        super(age);
        this.energy = energy;
        this.visited = visited;
    }

    @Override
    public void setEnergy(int energy) {
        this.energy = this.energy + energy;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public boolean isAlive() {
        if ( super.getAge() > 5 || this.getEnergy() <= 0)
            return false;
        else
            return true;
    }

    @Override
    public int[][] getVisited(){
        return this.visited;
    }
    @Override
    public int lastVisited(int row, int col){
        return this.visited[row][col];
    }
    @Override
    public void setVisited(int row, int col) {
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++) {
                if (i == row && j == col)
                    this.visited[row][col] = 1;
                else if ( this.lastVisited(row,col) > 0)
                    this.visited[i][j]++;
            }
    }


    // method to get neighbor
    public Organisms[] neighbor(Organisms life[][], int row, int col, int X[], int Y[]) {
        Organisms[] currentNeighbor = new Organisms[8];
        int[] currentX = new int[8];
        int[] currentY = new int[8];
        int location = 0;
        for (int i = row-1; i <= row+1; i++) {
            for ( int j = col-1; j < col+1; j++ ) {
                if ( i != row || j != col) {
                    if( ( i >= 0 && i < life.length) && ( j >= 0 && j < life[i].length)) {
                        currentNeighbor[location] = life[i][j];
                        currentX[location] = i;
                        currentY[location] = j;
                    }
                    location++;
                }
            }
        }
        X = currentX;
        Y = currentY;
        return currentNeighbor;
    }
}
