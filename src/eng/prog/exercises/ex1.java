package eng.prog.exercises;
import java.util.*;

class ex1{

    public static void main(String args[]){
        print("Pick any integer! ");
        int a = Reader.getIntFromKeyboard();
        print("Pick an integer between 3 and 10");
        int b = Reader.getIntFromKeyboard(3, 10);

        print("You have chosen:");
        print(String.format("%d and %d", a, b));
    }
    public static void print(String str){
        System.out.println(str);
    }
}

class Reader {
    public static int getIntFromKeyboard() {
        Scanner scan = new Scanner(System.in);
        int num = -1;
        boolean invalid = true;
        do {
            try {
                num = scan.nextInt();
                invalid = false;
            } catch (InputMismatchException ime) {
                System.out.printf("%s: arguments must be integers, try again\n", ime);
                scan.nextLine();
            } catch (StackOverflowError soe) {
                System.out.printf("%s: input is too large, try again\n", soe);
                scan.nextLine();
            } catch (NumberFormatException nfe) {
                System.out.printf("%s: arguments must be integers, try again\n", nfe);
                scan.nextLine();
            }
        } while (invalid);
        return num;
    }
    public static int getIntFromKeyboard(int lower, int upper) {
        Scanner scan = new Scanner(System.in);
        int num = -1;
        boolean invalid = true;
        do {
            try {
                num = scan.nextInt();
                if( lower <= num && num <= upper)
                {
                    invalid = false;
                }
                else
                {
                    System.out.printf("\nInteger is not between %d and %d, try again.\n",lower,upper);
                    continue;
                }
            } catch (InputMismatchException ime) {
                System.out.printf("%s: arguments must be integers, try again\n", ime);
                scan.nextLine();
            } catch (StackOverflowError soe) {
                System.out.printf("%s: input is too large, try again\n", soe);
                scan.nextLine();
            } catch (NumberFormatException nfe) {
                System.out.printf("%s: arguments must be integers, try again\n", nfe);
                scan.nextLine();
            }
        } while (invalid);
        return num;
    }
}