package sample;

import javafx.application.Application;

import java.security.SecureRandom;

public class GenerateLife extends Main{
    static Organisms[][] life = new Organisms[Organisms.ROW][Organisms.COL];
    public static SecureRandom rand = new SecureRandom();

    /*public static void main(String[] args) {
        // initialize life
        initializeLife(life);
        Application.launch(args);
        try {
            for (int generation = 1; generation <= 30; generation++) {
                System.out.print("\033[H\033[2J");
                System.out.printf("Generation: %d%n", generation);
                printLife(life);
                // update life in a loop
                updateGeneration(life, Organisms.ROW, Organisms.COL);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }*/

    public static void updateGeneration(Organisms life[][], int ROW, int COL) {
        for ( int i = 0; i < ROW; i++)
            for ( int j = 0; j < COL; j++ ) {
                move(life, i, j);
                reproduce(life, i, j);
                if (life[i][j] != null) {
                    life[i][j].setAge();
                    life[i][j].setEnergy(1);
                }
                if ( life[i][j] != null && !life[i][j].isAlive())
                    life[i][j] = null;
            }
            growPlant(life);

    }

    public static void printLife(Organisms life[][]) {

        for (int i = 0; i < Organisms.ROW; i++) {
            for (int j = 0; j < Organisms.COL; j++) {
                if (life[i][j] == null)
                    System.out.print(" ~ ");
                else
                    System.out.print(life[i][j]);
            }
            System.out.println();
        }
    }
    public static void initializeLife(Organisms life[][]) {

        for( int i = 0; i < 50; i++) {
            int row = rand.nextInt(Organisms.ROW);
            int col = rand.nextInt(Organisms.COL);
            int age = rand.nextInt(6);
            if( life[row][col] instanceof Plant || life[row][row] instanceof Animal)
                i--;
            else
                life[row][col] = new Plant(age);
        }
            for( int i = 0; i < 40; i++) {
                int row = rand.nextInt(Organisms.ROW);
                int col = rand.nextInt(Organisms.COL);
                int age = rand.nextInt(6);
                int energy = rand.nextInt(3) + 2;
                if( life[row][col] instanceof Plant || life[row][row] instanceof Animal)
                    i--;
                else
                    life[row][col] = new Herbivore(age, energy);
            }

        for( int i = 0; i < 20; i++) {
            int row = rand.nextInt(Organisms.ROW);
            int col = rand.nextInt(Organisms.COL);
            int age = rand.nextInt(6);
            int energy = rand.nextInt(3)+2;
            if( life[row][col] instanceof Plant || life[row][row] instanceof Animal)
                i--;
            else
                life[row][col] = new Carnivore(age, energy);
        }
    }



    public static void move(Organisms[][] grid, int i, int j) {
        if (grid[i][j] instanceof Animal) {
            if ((i - 1) >= 0 && (j - 1) >= 0) {
                if (grid[i - 1][j - 1] instanceof Plant && grid[i][j] instanceof Herbivore ) {
                    grid[i][j].setVisited(i-1, j-1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(2);
                    grid[i - 1][j - 1] =  grid[i][j];
                    grid[i][j] = null;
                    return;
                }
                else if (grid[i - 1][j - 1] instanceof Herbivore && grid[i][j] instanceof Carnivore ) {
                    grid[i][j].setVisited(i-1, j-1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(3);
                    grid[i - 1][j - 1] = grid[i][j];
                    grid[i][j] = null;
                    return;
                }
                else if (grid[i - 1][j - 1] == null && ( grid[i][j].lastVisited(i-1, j-1) > 3  || grid[i][j].lastVisited(i-1, j-1) ==  0) ) {
                    grid[i][j].setVisited(i-1, j-1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(-2);
                    grid[i - 1][j - 1] = grid[i][j];
                    grid[i][j] = null;
                    return;
                }
            }

            else if ((i - 1) >= 0) {
                if (grid[i - 1][j]  instanceof Plant && grid[i][j] instanceof Herbivore ) {
                    grid[i][j].setVisited(i-1, j);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(2);
                    grid[i - 1][j] =  grid[i][j];
                    grid[i][j] = null;
                    return;
                }
                else if (grid[i - 1][j] instanceof Herbivore && grid[i][j] instanceof Carnivore ) {
                    grid[i][j].setVisited(i-1, j);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(3);
                    grid[i - 1][j] = grid[i][j];
                    grid[i][j] = null;
                    return;
                }

                else if (grid[i - 1][j] == null && (grid[i][j].lastVisited(i-1, j ) > 3 || grid[i][j].lastVisited(i-1, j ) == 0)) {
                    grid[i][j].setVisited(i-1, j);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(-1);
                    grid[i - 1][j] = grid[i][j];
                    grid[i][j] = null;
                    return;
                }
            }

            else if ((i - 1) >= 0 && (j + 1) < 16) {
                if (grid[i - 1][j + 1]  instanceof Plant && grid[i][j] instanceof Herbivore ) {
                    grid[i][j].setVisited(i-1, j+1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(2);
                    grid[i - 1][j + 1] =  grid[i][j];
                    grid[i][j] = null;
                    return;
                }

                else if (grid[i - 1][j + 1]  instanceof Herbivore && grid[i][j] instanceof Carnivore ) {
                    grid[i][j].setVisited(i-1, j+1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(3);
                    grid[i - 1][j + 1] = grid[i][j];
                    grid[i][j] = null;
                    return;
                }
                else if (grid[i - 1][j + 1] == null && (grid[i][j].lastVisited(i-1, j+1) > 3 || grid[i][j].lastVisited(i-1, j+1) == 0 ) ) {
                    grid[i][j].setVisited(i-1, j+1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(-1);
                    grid[i - 1][j + 1] =  grid[i][j];
                    grid[i][j] = null;
                    return;
                }
            }

            else if ((j - 1) >= 0) {
               if (grid[i][j - 1]  instanceof Plant && grid[i][j] instanceof Herbivore ) {
                    grid[i][j].setVisited(i, j-1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(2);
                    grid[i][j - 1] =  grid[i][j];
                    grid[i][j] = null;
                    return;
                }
               else if (grid[i][j - 1]  instanceof Herbivore && grid[i][j] instanceof Carnivore ) {
                    grid[i][j].setVisited(i, j-1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(3);
                    grid[i][j - 1] =  grid[i][j];
                    grid[i][j] = null;
                    return;
               }
               else if (grid[i][j - 1] == null  && (grid[i][j].lastVisited(i, j-1 ) > 4 || grid[i][j].lastVisited(i, j-1 ) == 0)) {
                    grid[i][j].setVisited(i, j-1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(-1);
                    grid[i][j - 1] =  grid[i][j];
                    grid[i][j] = null;
                    return;
               }
            }

            else if ((j + 1) < 16) {
               if (grid[i][j + 1] instanceof Plant && grid[i][j] instanceof Herbivore ) {
                    grid[i][j].setVisited(i, j+1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(2);
                    grid[i][j + 1] =  grid[i][j];
                    grid[i][j] = null;
                    return;
               }
               else if (grid[i][j + 1] instanceof Herbivore && grid[i][j] instanceof Carnivore ) {
                    grid[i][j].setVisited(i, j+1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(3);
                    grid[i][j + 1] =  grid[i][j];
                    grid[i][j] = null;
                    return;
               }
               else if (grid[i][j + 1] == null  &&  ( grid[i][j].lastVisited(i, j+1 ) > 4 || grid[i][j].lastVisited(i, j+1 ) == 4)) {
                    grid[i][j].setVisited(i, j+1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(-1);
                    grid[i][j + 1] =  grid[i][j];
                    grid[i][j] = null;
                    return;
                }
            }

            else if ((i + 1) < 16 ) {
                if (grid[i + 1][j - 1] instanceof Plant && grid[i][j] instanceof Herbivore ) {
                    grid[i][j].setVisited(i+1, j-1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(2);
                    grid[i + 1][j - 1] =  grid[i][j];
                    grid[i][j] = null;
                    return;
                }

                else if (grid[i + 1][j - 1]  instanceof Herbivore && grid[i][j] instanceof Carnivore ) {
                    grid[i][j].setVisited(i+1, j-1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(3);
                    grid[i + 1][j - 1] = grid[i][j];
                    grid[i][j] = null;
                    return;
                }
                else if (grid[i + 1][j - 1] == null&& (j - 1) >= 0 && ( grid[i][j].lastVisited(i+1, j-1 ) > 4 || grid[i][j].lastVisited(i+1, j-1 ) == 0 ) ) {
                    grid[i][j].setVisited(i+1, j);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(-1);
                    grid[i + 1][j - 1] =  grid[i][j];
                    grid[i][j] = null;
                    return;
                }
            }
            else if ((i + 1) < 16) {
                if (grid[i + 1][j] instanceof Plant && grid[i][j] instanceof Herbivore )  {
                    grid[i][j].setVisited(i+1, j);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(2);
                    grid[i + 1][j] = grid[i][j];
                    grid[i][j] = null;
                    return;
                }

               else if (grid[i + 1][j] instanceof Herbivore && grid[i][j] instanceof Carnivore ) {
                    grid[i][j].setVisited(i+1, j);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(3);
                    grid[i + 1][j] = grid[i][j];
                    grid[i][j] = null;
                    return;
                }

                else if (grid[i + 1][j] == null && grid[i][j].lastVisited(i+1, j ) > 4 && grid[i][j].lastVisited(i+1, j ) == 0) {
                    grid[i][j].setVisited(i+1, j);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(-1);
                    grid[i + 1][j] = grid[i][j];
                    grid[i][j] = null;
                    return;
                }
            }

            else if ((i + 1) < 16 && (j + 1) < 16 )  {
                if (grid[i + 1][j + 1] instanceof Plant && grid[i][j] instanceof Herbivore ) {
                    grid[i][j].setVisited(i+1, j+1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(2);
                    grid[i + 1][j + 1] = grid[i][j];
                    grid[i][j] = null;
                }

               else if (grid[i + 1][j + 1] instanceof Herbivore && grid[i][j] instanceof Carnivore ) {
                    grid[i][j].setVisited(i+1, j+1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(3);
                    grid[i + 1][j + 1] =  grid[i][j];
                    grid[i][j] = null;
                }

               else if (grid[i + 1][j + 1] == null&& ( grid[i][j].lastVisited(i+1, j+1 ) > 4 || grid[i][j].lastVisited(i+1, j+1 ) == 0) ) {
                    grid[i][j].setVisited(i+1, j+1);
                    grid[i][j].setVisited(i,j);
                    grid[i][j].setEnergy(-1);
                    grid[i + 1][j + 1] = grid[i][j];
                    grid[i][j] = null;
                }
            }
        }
    }

    public static void reproduce(Organisms[][] grid, int i, int j) {
        if (grid[i][j] instanceof Animal) {
            if ((i + 1) < 16 && (j + 1) < 16 && grid[i + 1][j + 1] == null) {
                if (grid[i][j] instanceof Herbivore && grid[i][j].getAge() > 2 && grid[i][j].getEnergy() > 3)
                    grid[i + 1][j + 1] = new Herbivore();
                else if (grid[i][j] instanceof Carnivore && grid[i][j].getAge() > 3 && grid[i][j].getEnergy() > 3)
                    grid[i + 1][j + 1] = new Carnivore();
                grid[i][j].setEnergy(-1);
            }
            else if ((i + 1) < 16 && grid[i + 1][j] == null) {
                if (grid[i][j] instanceof Herbivore && grid[i][j].getAge() > 2 && grid[i][j].getEnergy() > 5)
                    grid[i + 1][j] = new Herbivore();
                else if (grid[i][j] instanceof Carnivore && grid[i][j].getAge() > 3 && grid[i][j].getEnergy() > 3)
                    grid[i + 1][j] = new Carnivore();
                grid[i][j].setEnergy(-1);
            }
            else if ((i + 1) < 16 && (j - 1) >= 0 && grid[i + 1][j - 1] == null) {
                if (grid[i][j] instanceof Herbivore && grid[i][j].getAge() > 2 && grid[i][j].getEnergy() > 3)
                    grid[i + 1][j - 1] = new Herbivore();
                else if (grid[i][j] instanceof Carnivore && grid[i][j].getAge() > 3 && grid[i][j].getEnergy() > 3)
                    grid[i + 1][j - 1] = new Carnivore();
                grid[i][j].setEnergy(-1);
            }
            else if ((j - 1) >= 0 && grid[i][j - 1] == null) {
                if (grid[i][j] instanceof Herbivore && grid[i][j].getAge() > 2 && grid[i][j].getEnergy() > 3)
                    grid[i][j - 1] = new Herbivore();
                else if (grid[i][j] instanceof Carnivore && grid[i][j].getAge() > 3 && grid[i][j].getEnergy() > 3)
                    grid[i][j - 1] = new Carnivore();
                grid[i][j].setEnergy(-1);
            }
            else if ((j + 1) < 16 && grid[i][j + 1] == null) {
                if (grid[i][j] instanceof Herbivore && grid[i][j].getAge() > 2 && grid[i][j].getEnergy() > 3)
                    grid[i][j + 1] = new Herbivore();
                else if (grid[i][j] instanceof Carnivore && grid[i][j].getAge() > 3 && grid[i][j].getEnergy() > 3)
                    grid[i][j + 1] = new Carnivore();
                grid[i][j].setEnergy(-1);
            }
            else if ((i - 1) >= 0 && (j + 1) < 16 && grid[i - 1][j + 1] == null) {
                if (grid[i][j] instanceof Herbivore && grid[i][j].getAge() > 2 && grid[i][j].getEnergy() > 3)
                    grid[i - 1][j + 1] = new Herbivore();
                else if (grid[i][j] instanceof Carnivore && grid[i][j].getAge() > 3 && grid[i][j].getEnergy() > 3)
                    grid[i - 1][j + 1] = new Carnivore();
                grid[i][j].setEnergy(-1);
            }
            else if ((i - 1) >= 0 && grid[i - 1][j] == null) {
                if (grid[i][j] instanceof Herbivore && grid[i][j].getAge() > 2 && grid[i][j].getEnergy() > 3)
                    grid[i - 1][j] = new Herbivore();
                else if (grid[i][j] instanceof Carnivore && grid[i][j].getAge() > 3 && grid[i][j].getEnergy() > 3)
                    grid[i - 1][j] = new Carnivore();
                grid[i][j].setEnergy(-1);
            }
            else if ((i - 1) >= 0 && (j - 1) >= 0 && grid[i - 1][j - 1] == null) {
                if (grid[i][j] instanceof Herbivore && grid[i][j].getAge() > 2 && grid[i][j].getEnergy() > 3)
                    grid[i - 1][j - 1] = new Herbivore();
                else if (grid[i][j] instanceof Carnivore && grid[i][j].getAge() > 3 && grid[i][j].getEnergy() > 3)
                    grid[i - 1][j - 1] = new Carnivore();
                grid[i][j].setEnergy(-1);
            }

        }
    }

    public static void growPlant(Organisms life[][]) {
        for( int i = 0; i < 5; i++) {
            int row = rand.nextInt(Organisms.ROW);
            int col = rand.nextInt(Organisms.COL);
            if( life[row][col] != null)
                i--;

            else
                life[row][col] = new Plant();
        }
    }

}
