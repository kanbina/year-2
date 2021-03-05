package eng.prog.meanvariance;
import java.util.*;

public class meanvariance {
    float[] data; // the float array instance variable

    public float mean () {
        // iterates through float array data
        float sum = 0;
        for(int i = 0;i<data.length;i++)
        {
            sum += data[i]; //add next floating point number to sum
        }
        return sum/data.length; //return mean taken as total sum divided by length of array
    }

    public float variance () {
        //iterates through float array data
        float sum = 0;
        for(int i = 0;i<data.length;i++)
        {
            float diff = (data[i]-this.mean()); //take difference from floating point number and the mean
            sum += diff*diff; //add next squared difference
        }
        return sum/data.length; //return variance taken as squared difference divided by length of array
    }

    @Override // overrides Object toString - all Java classes inherit (ultimately) from Object
    public String toString () {
        return "The mean and variance of the following numbers are:\n" +
                    "Numbers: " + Arrays.toString(this.data) +
                    "\nMean: " + this.mean() +
                    "\nVariance: " + this.variance();
    }

    private static int getDataNoFromKeyboard (Scanner input) {
        System.out.printf("Enter the number of data set elements:\n");
        int ndata;
        while (true) { // loop until we get it correctly
            try
            {
                System.out.println("    Enter a number: ");
                ndata = input.nextInt();
                if(ndata >1)
                {
                    break; // we got it correctly, so break out of the loop
                }
                else
                {
                    System.out.printf("The number of data should be >=2 !\n");
                    continue; // continue looping
                }
            }
            catch (InputMismatchException ime) // make sure input is an integer, catch error if not
            {
                System.out.printf("%s: argument must be integer number, try again\n",ime);
                input.nextLine();
            }
        }
        return ndata;
    }

    public void getDataSetFromKeyboard (Scanner input) {
        int ndata = getDataNoFromKeyboard(input);
        data = new float[ndata]; // we create the array instance variable
        System.out.println("Please enter the numbers of the dataset: ");
        int i = 0;
        while(i < ndata) // loop until we get enough data points
        {
            try
            {
                System.out.println("    Enter a number: ");
                data[i] = input.nextFloat();
                i++; // update number of data points
            }
            catch (InputMismatchException ime)
            {
                System.out.printf("%s: arguments must be individual floating point numbers, try again\n",ime);
                input.nextLine(); // do not update i since no new data was collected
            }
        }
    }

    public void getRandomDataSet (int ndata, int upperBound) {
        Random rand = new Random();
        data = new float[ndata];
        for(int i = 0; i<ndata; i++) //iterate until dataset is filled
        {
            data[i] = upperBound*rand.nextFloat(); //nextfloat gives value between 0 and 1, multiply by upper bound
        }
    }

    public meanvariance (float[] myData) { // constructor for preset input
        data = myData;
    }

    public meanvariance () { // constructor with no data
        data = null;
    }

    public static void main(String[] args)
    {
        // run different modes based on number of cmd line arguments
        if(args.length > 2 || args.length == 1) //both lengths don't associate to a constructor
        {
            System.out.printf("Incorrect number of arguments, try again\n");
            System.exit(0); // terminate program
        }
        try { //to catch error
            meanvariance mv = new meanvariance();
            if (args.length == 0) { // no arguments, so we get user data from the keyboard
                Scanner input = new Scanner(System.in); // create scanner that reads stdin
                mv.getDataSetFromKeyboard(input); // get user data from keyboard
            } else if (args.length == 2) { // two arguments, produce random data set
                int ndata = Integer.parseInt(args[0]); // this is where error may occur - take int from string
                int upperBound = Integer.parseInt(args[1]);
                if (ndata <= 0 || upperBound == 0) { // incorrect values of ndata or upperBound
                    System.out.printf("ndata and/or upperBound has wrong value, try again\n");
                    System.exit(0); // terminate program
                }
                mv.getRandomDataSet(ndata, upperBound); // produce random data set
            }
            System.out.printf(mv.toString());
        }
        catch(NumberFormatException e)
        {
            System.out.printf("%s: arguments must be integers, try again\n",e);
        }
    }
}