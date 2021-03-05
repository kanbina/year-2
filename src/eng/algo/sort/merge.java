package eng.algo.sort;

import java.util.Arrays;
import java.util.Random;

public class merge {
    public static void merge(int[] arr, int[] left, int[] right, int l, int r)
    {
        int i=0, j=0, k=0;
        while(i<l && j<r)
        {
            if(left[i] <= right[j])
            {
                arr[k++] = left[i++];
            }
            else
            {
                arr[k++] = right[j++];
            }
        }
        while(i<l)
        {
            arr[k++] = left[i++];
        }
        while(j<r)
        {
            arr[k++] = right[j++];
        }
    }

    public static void mergeSort(int[] arr, int len)
    {
        if(len<2)
        {
            return;
        }
        int mid = len/2;
        int[] left = new int[mid];
        int[] right = new int[len-mid];
        for(int i = 0; i<mid; i++)
        {
            left[i] = arr[i];
        }
        for(int i = mid; i<len; i++)
        {
            right[i-mid] = arr[i];
        }
        mergeSort(left, mid);
        mergeSort(right,len-mid);
        merge(arr,left,right,mid,len-mid);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int[] arr = new int[10];
        for (int i = 0; i<10; i++)
        {
            arr[i] = rand.nextInt(20);
        }
        mergeSort(arr,arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
