package eng.algo.sort;
import java.util.*;
import java.math.*;

public class insertion {

    public static void insertionSort(int [] arr)
    {
        int key = -1;
        for(int i = 0; i<arr.length; i++)
        {
            key = arr[i];
            int j = i-1;
            while(j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int[] arr = new int[10];
        for (int i = 0; i<10; i++)
        {
            arr[i] = rand.nextInt(20);
        }
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}