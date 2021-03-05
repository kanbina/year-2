package eng.algo.sort;

import java.util.Arrays;
import java.util.Random;

public class bubble {
    public static void bubbleSort(int[] arr)
    {
        for(int i=0; i<arr.length; i++)
        {
            for(int j=arr.length-1; j>i; j--)
            {
                if(arr[j]<arr[j-1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int[] arr = new int[10];
        for (int i = 0; i<10; i++)
        {
            arr[i] = rand.nextInt(20);
        }
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
