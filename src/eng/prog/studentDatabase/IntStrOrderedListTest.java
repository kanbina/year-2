package eng.prog.studentDatabase;

import java.util.*;
import java.util.regex.PatternSyntaxException;

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
        boolean invalid = true;
        String[] parsable = null;
        String in = scan.nextLine();
        String key = " ";
        if (in.contains(",")) {
            in = in.replaceAll("\\s+", "");
            key = ",";
        }
        parsable = in.split(key);
        int count = 0;
        for (String s : parsable) {
            if (!isAlphNum(s)) {
                parsable[count] = null;
                System.out.printf("%s: input was not alphanumeric and removed.\n",s);
            }
            count++;
        }
        String[] cleaned = Arrays.stream(parsable).filter(Objects::nonNull).toArray(String[]::new);
        System.out.println(Arrays.toString(cleaned));
        return cleaned;
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isAlphNum(String s) {
        try {
            if(s.matches("^[a-zA-Z]*$|^-?(0|[1-9]\\d*)$")) // /^-?(0|[1-9]\d*)$/
                return true;
        }
        catch (PatternSyntaxException e)
        {
            System.out.printf("%s: invalid regex.\n", e);
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean end = false;
        Scanner scan = new Scanner(System.in);
        IntegerOrderedList intList = new IntegerOrderedList();
        StringOrderedList strList = new StringOrderedList();
        do {
            System.out.println("""

                    What would you like to do?
                    1. Insert new object(s) into ordered list.
                    2. Remove an existing object.
                    3. Print your lists so far.
                    4. Exit program.""");
            int choice = getInt(scan);
            scan.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println("Please print your input(s) on a single line.");
                    String[] parsable = parseInputs(scan);
                    System.out.println("finished parseInputs");
                    for (String s : parsable) {
                        if(isInt(s)) {
                            intList.insert(Integer.parseInt(s));
                            System.out.println("Inserted " + s + " to int list.");
                        }
                        else {
                            strList.insert(s);
                            System.out.println("Inserted " + s + " to string list.");
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Please print your input(s) on a single line.");
                    String[] parsable = parseInputs(scan);
                    for (String s : parsable) {
                        if (isInt(s)) {
                            intList.remove(Integer.parseInt(s));
                            System.out.println("Removed " + s + " from int list.");
                        } else {
                            strList.remove(s);
                            System.out.println("Removed " + s + " from string list.");
                        }
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
