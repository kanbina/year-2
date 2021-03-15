package eng.prog.studentDatabase;

import java.util.*;
import java.io.*;
import java.util.regex.PatternSyntaxException;

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
        String last = getAlph(scan);
        scan.nextLine();
        System.out.println("Please print the first name: ");
        String first = getAlph(scan);
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

    public static String getAlph(Scanner scan) {
        boolean invalid = true;
        String s = "";
        while (invalid) // loop until valid input
        {
            try {
                s = scan.next().toLowerCase();
                if (s.matches("^[a-z]*$"))
                    invalid = false;
                else
                    System.out.println("Input must be in alphabetical format.");
            } catch (PatternSyntaxException e) {
                System.out.printf("%s: invalid regex.\n", e);
                scan.nextLine();
            }
        }
        return s;
    }
    
    public static void readFile(String filePath,StudentRecordOrderedList records) {
        try {
            int count = 0;
            File dataFile = new File(filePath);
            Scanner input = new Scanner(dataFile);
            while (input.hasNextLine()) {
                String[] line = input.nextLine().split(" ");
                System.out.println(line.length);
                switch (line.length) {
                    case 3 -> {
                        if (line[2].matches("-?\\d+")) {
                            records.insert(new StudentRecord(line[0], line[1], Integer.parseInt(line[2])));
                            count++;
                        }
                    }
                    case 4 -> {
                        if (line[2].matches("-?\\d+") && line[3].matches("-?\\d+(\\.\\d+)?")) {
                            records.insert(new StudentRecord(line[0], line[1], Integer.parseInt(line[2]), Float.parseFloat(line[3])));
                            count++;
                        }
                    }
                    default -> System.out.println("Line did not contain valid information.");
                }
            }
            input.close();
            System.out.println(count + " records were added.");
        } catch (FileNotFoundException e) {
            System.out.printf("%s: Data file \"" + filePath + "\" was not found.\n",e);
        }
    }

    public static void writeFile(String filePath,StudentRecordOrderedList records) {
        try {
            File f = new File(filePath);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            }
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter output = new PrintWriter(fileWriter);
            ListNode thisNode = records.firstNode;
            while (thisNode != null) {
                output.printf("%s %s %d %f\n",((StudentRecord)thisNode.data).surname,
                        ((StudentRecord)thisNode.data).name,((StudentRecord)thisNode.data).studentNo,
                        ((StudentRecord)thisNode.data).averageMark);
                thisNode = thisNode.next;
            }
            System.out.println("Records saved to system.");
            output.close();
        } catch (FileNotFoundException e) {
            System.out.printf("%s: Data file \"" + filePath + "\" was not found.\n",e);
            System.exit(1);
        } catch (IOException e) {
            System.out.printf("%s: Could not write to file.\n",e);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        boolean end = false;
        Scanner scan = new Scanner(System.in);
        StudentRecordOrderedList records = new StudentRecordOrderedList();
        String filePath;
        if(args.length == 1) {
            filePath = args[0];
            readFile(filePath, records);
        }
        else {
            filePath = "C:\\Users\\yolan\\IdeaProjects\\UCL\\studentRecords.txt";
            readFile(filePath, records);
        }
        do {
            System.out.println("""

                    What would you like to do?
                    1. Create a new student record.
                    2. Remove a record.
                    3. Adjust/add average mark.
                    4. Print all records.
                    5. Exit program.""");
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
                case 4 -> System.out.println(records.getSize() + " Student Records:\n" + records);
                case 5 -> {
                    writeFile(filePath,records);
                    end = true;} // end program
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