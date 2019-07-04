import java.util.Scanner;

public class Welcome1 {

    private int age;
    public Welcome1(int age) {
        this.age = age;
    }

    public void printProcess() {
        int quotient = age;
        int count = 1;

        while(quotient >= 1) {
            System.out.print(" " +quotient);
            quotient /= 2;
        }
        System.out.println("\n");

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
        Scanner scan = new Scanner(System.in);
        char decision ;
        do {
            System.out.print("Please enter your age: ");
            int age = scan.nextInt();
            Welcome1 w = new Welcome1(age);
            w.printProcess();
            System.out.println("Do you want to continue? (Yes/No) ");
            decision = scan.next().charAt(0);
        } while(decision == 'Y' || decision == 'y');
        System.out.println("Goodbye!");
    }
}
