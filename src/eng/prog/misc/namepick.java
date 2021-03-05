package eng.prog.misc;
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class namepick {
    ArrayList<String> names = new ArrayList<String>();

    public String pick ()
    {
        Random rand = new Random();
        int i = rand.nextInt(names.size()-1);
        return names.get(i);
    }

    public static boolean isInt(String s)
    {
        try
        {
            int i = Integer.parseInt(s);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    public void userinput (Scanner scan)
    {
        boolean cont = true;
        while(cont)
        {
            System.out.println("Enter a name: ");
            names.add(scan.nextLine());
            System.out.println("Enter another? (Y or N) : ");
            if(scan.nextLine().equalsIgnoreCase("N")) {
                cont = false;
            }
            else
            {
                continue;
            }
        }
    }

    public void fileinput (String file)
    {
        try
        {
            FileInputStream fis = new FileInputStream(file);
            Scanner scan = new Scanner(fis);
            while(scan.hasNextLine())
            {
                names.add(scan.nextLine());
            }
            scan.close();
        }
        catch(IOException e)
        {
            System.out.printf("%s: file could not be read; try again\n",e);
        }
    }

    public namepick ()
    {

    }

    public static void main(String[] args)
    {
        namepick picker = new namepick();
        if(args.length == 1 && !isInt(args[0]))
        {
            picker.fileinput(args[0]);
        }
        else if (args.length == 0)
        {
            Scanner scan = new Scanner(System.in);
            picker.userinput(scan);
        }
        else
        {
            System.out.printf("incorrect number of arguments, try again\n");
            System.exit(0); // terminate program
        }
        System.out.printf("Random name: %s\n", picker.pick());
    }
}
