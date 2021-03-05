package eng.prog.studentDatabase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentRecordOrderedListTest {
    // get a valid float input from user
    private static float getFloat(Scanner scan)
    {
        float num = 0;
        boolean invalid = true;
        while (invalid) // loop until valid input
        {
            try
            {
                num = scan.nextFloat();
                if(num>=0 && num<=100)
                    invalid =false;
                else
                    System.out.println("Print a number between 0 and 100.");
            }
            catch (InputMismatchException ime)
            {
                System.out.printf("%s: arguments must be floats, try again\n",ime);
                scan.nextLine();
            }
            catch (StackOverflowError soe)
            {
                System.out.printf("%s: input is too large, try again\n",soe);
                scan.nextLine();
            }
        }
        return num;
    }

    private static int getInt(Scanner scan)
    {
        int num = -1; // initialize as an invalid return
        boolean invalid = true;
        while (invalid) // loop until valid input
        {
            try
            {
                num = scan.nextInt();
                if(num >= 0) // student number shouldn't be negative
                    invalid = false;
                else
                    System.out.println("Print a number greater than 0.");
            }
            catch (InputMismatchException ime)
            {
                System.out.printf("%s: arguments must be integers, try again\n",ime);
                scan.nextLine();
            }
            catch (StackOverflowError soe)
            {
                System.out.printf("%s: input is too large, try again\n",soe);
                scan.nextLine();
            }
        }
        return num;
    }

    public static StudentRecord makeNewSR (Scanner scan) {
        System.out.println("Please print the surname: ");
        String last = scan.next().toLowerCase();
        scan.nextLine();
        System.out.println("Please print the first name: ");
        String first = scan.next().toLowerCase();
        System.out.println("Please print the student number (greater than 0): ");
        int sn = getInt(scan);
        System.out.println("Please print the student's average mark or 0 if unavailable (0% to 100%): ");
        float mark = getFloat(scan);
        return new StudentRecord(last,first,sn,mark);
    }

    public static String[] parseInputs(Scanner scan) {
        boolean invalid = true;
        String[] parsable = new String[0];
        while(invalid) {
            System.out.println("Please print two inputs: ");
            String in = scan.nextLine();
            String key = " ";
            if (in.contains(",")) {
                in = in.replaceAll("\\s+", "");
                key = ",";
            }
            parsable = in.split(key);
            if(parsable.length == 2)
                invalid = false;
        }
        return parsable;
    }

    public static void main(String[] args) {
        boolean end = false;
        Scanner scan = new Scanner(System.in);
        StudentRecordOrderedList records = new StudentRecordOrderedList();
        do {
            System.out.println("\nWhat would you like to do?" +
                    "\n1. Create a new student record." +
                    "\n2. Remove a record." +
                    "\n3. Adjust/add average mark." +
                    "\n4. Print all records." +
                    "\n5. Exit program.");
            int choice = getInt(scan);
            scan.nextLine();
            switch (choice) {
                case 1 -> {
                    StudentRecord newStudent = makeNewSR(scan);
                    records.insert(newStudent);
                }
                case 2 -> {
                    System.out.println("Please print the student's name in the following format: " +
                            "\"lastname,firstname\"\n");
                    String[] parsable = parseInputs(scan);
                    StudentRecord temp = new StudentRecord(parsable[0],parsable[1]);
                    records.remove(temp);
                }
                case 3 -> {
                    System.out.println("Please print the student's name in the following format: " +
                            "\"lastname,firstname\"\n");
                    String[] parsable = parseInputs(scan);
                    StudentRecord temp = new StudentRecord(parsable[0],parsable[1]);
                    if(records.binaryFind(temp)!=null) {
                        System.out.println("Please print the student's average mark (0% to 100%): ");
                        float mark = getFloat(scan);
                        ((StudentRecord) records.binaryFind(temp).data).newAvg(mark);
                        System.out.println("Average was changed to " + mark);
                    }
                    else {
                        System.out.println("Student not found.");
                    }
                }
                case 4 -> {
                    System.out.println(records.getSize() + " Student Records:\n" + records);
                }
                case 5 -> end = true; // end program
                default -> System.out.println("Option not found."); // invalid menu option
            }
        } while (!end);
        scan.close();
    }
}

class StudentRecord {
    public String surname;
    public String name;
    public int studentNo;
    public float averageMark;

    public String toString () {
        if (studentNo  == 0 && averageMark == 0)
            return surname + ", " + name;
        return studentNo + ": " + surname + ", " + name + " || %" + averageMark;
    }

    public void newAvg (float avg) {
        averageMark = avg;
    }

    public StudentRecord () // null constructor
    {
        surname = "";
        name = "";
        studentNo  = 0;
        averageMark = 0;
    }

    public StudentRecord (String last, String first)
    {
        surname = last;
        name = first;
        studentNo  = 0;
        averageMark = 0;
    }

    public StudentRecord (String last, String first, int num)
    {
        surname = last;
        name = first;
        studentNo  = num;
        averageMark = 0;
    }

    public StudentRecord (String last, String first, int num, float mark)
    {
        surname = last;
        name = first;
        studentNo  = num;
        averageMark = mark;
    }
}

class StudentRecordOrderedList extends OrderedList {
    @Override
    protected int compare(Object obj1, Object obj2) {
        StudentRecord sr1 = (StudentRecord) obj1;
        StudentRecord sr2 = (StudentRecord) obj2;
        String str1 = sr1.surname + ", " + sr1.name;
        String str2 = sr2.surname + ", " + sr2.name;
        return (str1.compareTo(str2));
    }

    @Override
    public String toString()
    {
        String temp = super.toString();
        temp = temp.replaceAll("Node \\d+","");
        temp = temp.replace("-->","\n");
        return temp;
    }
}