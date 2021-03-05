package eng.prog.studentDatabase;

import java.util.*;

public class IntStrOrderedListTest {
    // get a valid int input from user
    public static int getInt(Scanner scan) {
        int num = -1;
        boolean invalid = true;
        while (invalid) // loop until valid
        {
            try {
                num = scan.nextInt();
                invalid = false;
            } catch (InputMismatchException ime) {
                System.out.printf("%s: arguments must be integers, try again\n", ime);
                scan.nextLine();
            } catch (StackOverflowError soe) {
                System.out.printf("%s: input is too large, try again\n", soe);
                scan.nextLine();
            }
        }
        return num;
    }

    public static String[] parseInputs(Scanner scan) {
        String in = scan.nextLine();
        String key = " ";
        String[] parsable;
        if (in.contains(",")) {
            in = in.replaceAll("\\s+", "");
            key = ",";
        }
        parsable = in.split(key);
        return parsable;
    }

    public static boolean allInt(String[] parsable) {
        try {
            for (String s : parsable) {
                Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /*
    This should be done by getting a series of numbers or strings from the standard input, inserting
them and printing any of the two lists when required. It should also support simply finding an
element and also removing an element the user specifies. You should also implement a small
menu to guide the user through activities to enter numbers/strings, print the appropriate list,
remove a number/string, etc.
     */
    public static void main(String[] args) {
        boolean end = false;
        Scanner scan = new Scanner(System.in);
        IntegerOrderedList intList = new IntegerOrderedList();
        StringOrderedList strList = new StringOrderedList();
        do {
            System.out.println("\nWhat would you like to do?" +
                    "\n1. Insert new object(s) into ordered list." +
                    "\n2. Remove an existing object." +
                    "\n3. Print your lists so far." +
                    "\n4. Exit program.");
            int choice = getInt(scan);
            scan.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println("Please print your input(s) on a single line.");
                    String[] parsable = parseInputs(scan);
                    if (allInt(parsable)) {
                        for (String s : parsable) {
                            intList.insert(Integer.parseInt(s));
                            System.out.println("Inserted " + s);
                        }
                    } else {
                        for (String s : parsable)
                            strList.insert(s);
                    }
                }
                case 2 -> {
                    System.out.println("Please print your input(s) on a single line.");
                    String[] parsable = parseInputs(scan);
                    if (allInt(parsable)) {
                        boolean successful = true;
                        for (String s : parsable) {
                            if (intList.remove(Integer.parseInt(s))==null)
                                successful = false;
                        }
                        if(!successful) // edge case where ints exist in string input
                        {
                            System.out.println("Testing string ordered list...");
                            for (String s : parsable)
                                strList.remove(s);
                        }
                    } else {
                        for (String s : parsable)
                            strList.remove(s);
                    }
                }
                case 3 -> {
                    System.out.println("Integer Ordered List:\n" + intList);
                    System.out.println("String Ordered List:\n" + strList);
                }
                case 4 -> end = true; // end program
                default -> System.out.println("Option not found."); // invalid menu option
            }
        } while (!end);
        scan.close();
    }
}