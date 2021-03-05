package eng.prog.inheritance;

import java.util.*;


public class ShapeTest {
    // get a valid double input from user
    private static double getDouble(Scanner scan)
    {
        double num = 0;
        boolean invalid = true;
        while (invalid) // loop until valid input
        {
            try
            {
                num = scan.nextDouble();
                invalid = false;
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

    // get a valid double from user greater than double lower; overloaded method
    private static double getDouble(Scanner scan, double lower)
    {
        double num = 0;
        boolean invalid = true;
        while(invalid) // loop until valid
        {
            num = getDouble(scan);
            if(num>lower) // implements base getDouble with condition that num is greater than lower
            {
                invalid = false;
            }
            else
            {
                System.out.printf("\nPlease print a number greater than %f\n",lower);
            }
        }
        return num;
    }

    // get a valid int input from user
    private static int getInt(Scanner scan)
    {
        int num = -1;
        boolean invalid = true;
        while (invalid) // loop until valid
        {
            try
            {
                num = scan.nextInt();
                invalid = false;
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

    // returns x y coordinates
    public static int[] getCoords(Scanner scan)
    {
        int[] coords = new int[2];
        System.out.println("Please print the integer x-coordinate:");
        coords[0] = getInt(scan);
        System.out.println("Please print the integer y-coordinate:");
        coords[1] = getInt(scan);
        return coords;
    }

    // ensures that storage array has enough slots for next input and makes bigger if not
    public static Shape[] checkStorage(Shape[] storage, int nextIndex)
    {
        if(nextIndex > storage.length-1) // if storage array is too small, copy and extend
        {
            System.out.println("[Storage has been extended by 10 to a length of " + (storage.length+10) + "]");
            Shape[] newStorage = new Shape[storage.length+10];
            System.arraycopy(storage, 0, newStorage, 0, storage.length);
            return newStorage; // returns a new array with 10 extra spots
        }
        else
        {
            return storage;
        }
    }

    // adds element to back of effective array (not counting null indices)
    public static void push(Shape[] storage, Shape newShape, int nextIndex)
    {
        storage[nextIndex] = newShape;
    }

    // removes element at index and returns popped shape
    public static Shape pop(Shape[] storage, int ind)
    {
        Shape removed = storage[ind];
        try
        {
            storage[ind] = null; // null element
            for(int i = ind; i<storage.length-1;i++) // shifts elements into popped position
            {
                storage[i] = storage[i+1];
            }
        }
        catch(NullPointerException npe)
        {
            System.out.printf("%s: There's nothing to pop there. Process was not completed.");
        }
        catch(IndexOutOfBoundsException ibe)
        {
            System.out.printf("%s: That index is not valid. Process was not completed.");
        }
        return removed;
    }

    // prints array elements
    public static void printArr(Shape[] storage)
    {
        System.out.println("Your current array:\n");
        try {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    break;
                } //Don't print extra spaces
                else {
                    System.out.printf("Index %d - %s: %s\n", i, storage[i].getName(), storage[i]);
                }
            }
        }
        catch(NullPointerException npe)
        {
            System.out.printf("%s: There's nothing to pop there. Process was not completed.",npe);
        }
        catch(IndexOutOfBoundsException ibe)
        {
            System.out.printf("%s: That index is not valid. Process was not completed.",ibe);
        }
    }

    // makes new shape and adds to back of array
    public static void makeNew(Scanner scan, Shape[] storage, int nextIndex)
    {
        System.out.println("Would you like to make a\n1: Point\n2: Circle\n3: Cylinder\nPlease type a corresponding number: ");
        int choice = getInt(scan); // get a choice for the switch statement
        double rad = 0,height = 0;
        switch(choice) { // make the specified object, add to array, and print it
            case 1: // if 1, make a Point and get coords
                int[] coords = getCoords(scan);
                Point point = new Point(coords[0],coords[1]);
                System.out.printf("%s:\n%s",point.getName(),point);
                push(storage,point,nextIndex);
                break;
            case 2: // if 2, make a Circle and get coords and radius
                int[] coordsC = getCoords(scan);
                System.out.println("Please print a floating point radius:");
                rad = getDouble(scan,0);
                Circle circle = new Circle(coordsC[0],coordsC[1],rad);
                System.out.printf("%s:\n%s\nArea = %f",circle.getName(),circle,circle.getArea());
                push(storage,circle,nextIndex);
                break;
            case 3: // if 3, make a Cylinder and get coords, radius, and height
                int[] coordsCy = getCoords(scan);
                System.out.println("Please print a floating point radius:");
                rad = getDouble(scan,0);
                System.out.println("Please print a floating point height:");
                height = getDouble(scan,0);
                Cylinder cyl = new Cylinder(coordsCy[0],coordsCy[1],rad,height);
                System.out.printf("%s:\n%s\nSurface area = %f; Volume = %f",cyl.getName(),cyl,cyl.getArea(),cyl.getVolume());
                push(storage,cyl,nextIndex);
                break;
            default: // all other non-valid ints are invalid
                System.out.println("Option not found.");
                break;
        }
    }

    public static void main(String[] args)
    {
        boolean end = false;
        Scanner scan = new Scanner(System.in);
        Shape storage[] = new Shape[10]; // initiate storage to 10
        int nextIndex = 0;
        do
        {
            System.out.println("\nWhat would you like to do?" +
                    "\n1. Make a new shape" +
                    "\n2. View all existing shapes" +
                    "\n3. Delete a shape" +
                    "\n4. End program");
            int choice = getInt(scan);
            switch (choice) {
                case 1 -> { // make new shape
                    storage = checkStorage(storage, nextIndex);
                    makeNew(scan, storage, nextIndex);
                    nextIndex++;
                }
                case 2 -> printArr(storage); // view all shapes
                case 3 -> { //delete a shape
                    System.out.println("Identify the index of the object you'd like to remove: ");
                    int removeIndex = getInt(scan);
                    pop(storage, removeIndex);
                    nextIndex--;
                }
                case 4 -> end = true; // end program
                default -> System.out.println("Option not found."); // invalid menu option
            }
        } while(!end);
        scan.close();
    }
}
