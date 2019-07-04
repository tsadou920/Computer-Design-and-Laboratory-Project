//  Shofiqur Rahman
// shofi384@gmail.com

import java.util.Scanner;

public class Welcome2 {
    private int age;

    public Welcome2(int age) {
        this.age = age;
    }

    private static int getAge() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your age: ");
        return scan.nextInt();
    }

    private static void printQuotients(int age) {
        while (age >= 1) {
            System.out.print(" " + age);
            age /= 2;
        }
        System.out.println("\n");
    }

    private static void printStar(int age) {
        int count = 1;
        while(count<=age) {
            for(int i=0; i<count; i++) {
                System.out.print(" *");
            }
            System.out.println();
            count++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        char decision ;
        int age;
        Scanner scan = new Scanner(System.in);
        do {
            age = Welcome2.getAge();
            Welcome2.printQuotients(age);
            Welcome2.printStar(age);
            System.out.println("Do you want to continue? (Yes/No) ");
            decision = scan.next().charAt(0);
        } while(decision == 'Y' || decision == 'y');
        System.out.println("Goodbye!");
    }
}
