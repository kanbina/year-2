package eng.algo.problems;
import java.time.*;
import java.util.*;

public class MaxSubArray {
    public static int[] maxCrossing(int[] arr, int low, int mid, int high)
    {
        int[] result = new int[3];
        int left = (int)Integer.MIN_VALUE;
        int right = (int)Integer.MIN_VALUE;
        int sum = 0, maxL = 0, maxR = 0;
        for(int i = mid; i>=low; i--)
        {
            sum += arr[i];
            if(sum > left)
            {
                left = sum;
                maxL = i;
            }
        }
        sum = 0;
        for(int j = mid+1; j<=high; j++)
        {
            sum += arr[j];
            if(sum > right)
            {
                right = sum;
                maxR = j;
            }
        }
        result[0] = maxL;
        result[1] = maxR;
        result[2] = left+right;
        return result;
    }

    public static int[] maxSub(int arr[], int low, int high)
    {
        int[] result = new int[3];
        if(low == high)
        {
            result[0] = low;
            result[1] = high;
            result[2] = arr[low];
            return result;
        }
        else
        {
           int mid = (low+high)/2;
           int[] tempL = maxSub(arr,low,mid);
           int[] tempR = maxSub(arr,mid+1,high);
           int[] tempCross = maxCrossing(arr,low,mid,high);
           if(tempL[2] >= tempR[2] && tempL[2] >= tempCross[2]) {
               System.out.println("returning tempL\n");
               return tempL;
           }
           else if(tempR[2] >= tempL[2] && tempR[2] >= tempCross[2]) {
               System.out.println("returning tempR\n");
               return tempR;
           }
           else return tempCross;
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int[] arr = new int[rand.nextInt(50)];
        for (int i = 0; i<arr.length; i++)
        {
            arr[i] = rand.nextInt(20)-10;
        }
        System.out.println(Arrays.toString(arr));
        int[] max = new int[3];
        Instant start = Instant.now();
        max = maxSub(arr,0, arr.length-1);
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toNanos();
        System.out.println(Arrays.toString(max));
        System.out.println(timeElapsed);
    }
}
