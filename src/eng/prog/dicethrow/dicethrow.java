package eng.prog.dicethrow;
import java.util.*;

public class dicethrow {
    int sides;
    int numrolls;
    int[] rolls; // initiate instance variables and holding array

    // returns string holding a table of side and number of times it was rolled
    public String getSides()
    {
        int[] sorted = Arrays.copyOf(rolls,rolls.length); //make a copy of rolls and sort it
        Arrays.sort(sorted); //time complexity O(n*log(n))
        System.out.println(Arrays.toString(sorted));
        String s = "Result  ||  Frequency\n";
        for(int i = 1; i<sides+1;i++)
        {
            int temp = frequency(sorted,i);
//            int temp = frequency(sorted,i); // call method which counts how many times number occurs
                s += i + "           " + temp + "\n"; // string formatting into a table
        }
        s += "___________________________";
        return s;
    }

    // implement binary search for first occurrence, time complexity O(log(n))
    public int first(int arr[], int left, int right, int x)
    {
        if(right >= left) // if elements exist in sorted array (if right is higher than left)
        {
            int mid = (right+left)/2;
            if( (mid == 0 || x > arr[mid-1]) && arr[mid] == x)
                // return mid if x = arr[mid] and is greater than the previous array element
                return mid;
            else if(x > arr[mid])
                return first(arr, (mid + 1), right, x); // recurse with right subarray
            else
                return first(arr, left, (mid -1), x); // recurse with left subarray
        }
        return -1;
    }

    // takes a sorted array and returns frequency of the match integer
    public int frequency(int[] arr, int match)
    {
        int count = 0; // initialize count to 0
        if(match == arr[0]) // case 1: the element is the smallest of the array
        {
            count = first(arr,0,numrolls,match+1);
            // find the index of the second smallest
            if(arr[0] == arr[arr.length-1])
                // in case that array only holds one value so element is both smallest and largest, return array length
            {
                count = arr.length;
            }
        }
        else if(match == arr[arr.length-1]) // case 2: the element is the largest of the array
        {
            count = arr.length-first(arr,0,numrolls,match);
            // find the index of the first occurrence and subtract from length
        }
        else // case 3: the element is within the array and not smallest/greatest number
        {
            int low = first(arr,0,numrolls, match);
            int high = first(arr,0,numrolls,match+1);
            count = high-low;
        }
        return(Math.max(0,count));
        // binarysearch returns a negative if element not found, so return either 0 or a positive number*/
    }

    // populates rolls array with randomised 'rolls'
    public void toss()
    {
        rolls = new int[numrolls]; // initialize array to the predefined number of rolls
        Random rand = new Random(); // create instance of Random object
        for(int i = 0;i<numrolls;i++)
        {
            rolls[i] = rand.nextInt(sides) + 1; // get next int from 1 to number of sides
        }
    }

    // get int which is a multiple of int factor; overloaded method
    private int getInt(String s,Scanner scan, int factor)
    {
        int num = -1; // initialize as an invalid return
        boolean invalid = true;
        while(invalid) // loop until output is valid according to conditions within
        {
            num = getInt(s,scan);
            if(num%factor == 0) // implements base getInt with condition that num is multiple of factor
            {
                invalid = false; // break loop
            }
        }
        return num;
    }

    // get a valid int input from user
    private int getInt(String s,Scanner scan)
    {
        int num = -1; // initialize as an invalid return
        boolean invalid = true;
        while (invalid) // loop until valid input
        {
            System.out.println(s); // prints a custom message defining what value is for
            try
            {
                num = scan.nextInt();
                if(num > 1) // base case is number of sides, which have to be at least 2
                {
                    invalid = false;
                }
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

    // get a valid yes or no answer in boolean form
    private boolean menu (Scanner scan)
    {
        String s = ""; // loop until answer  is either yes or no
        while(!(s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("no"))) {
            System.out.println("Enter yes/no: ");
            s = scan.nextLine(); // take user input
        }
        return s.equalsIgnoreCase("yes");
    }

    @Override // overrides Object toString - all Java classes inherit (ultimately) from Object
    public String toString () {
        return Arrays.toString(rolls) + "\n" + getSides();
    }

    // initiate instance variables with inputs
    public void assign(int numSides, int tosses)
    {
        sides = numSides;
        numrolls = tosses;
        rolls = new int[numrolls];
    }

    // null constructor
    public dicethrow()
    {
        sides = 0;
        rolls = null;
    }

    // general start of program
    public void start(dicethrow dt)
    {
        boolean end = false;
        Scanner scan = new Scanner(System.in); // create the 'master' instance to feed to other methods
        do
        {
            int numSides = getInt("Please enter an integer number of sides greater than 1:",scan);
            scan.nextLine();
            int tosses = getInt("Please enter an integer number of tosses greater than 1 and a multiple of "
                    + numSides + ": ",scan,numSides);
            scan.nextLine();
            dt.assign(numSides, tosses);
            dt.toss(); // populate array with random tosses
            System.out.println(dt); // call custom toString to show results
            System.out.println("Would you like to throw again?");
            end = !menu(scan); // update end condition
        } while(!end);
        scan.close();
    }

    public static void main(String[] args)
    {
        dicethrow dt = new dicethrow();
        dt.start(dt);
    }
}
