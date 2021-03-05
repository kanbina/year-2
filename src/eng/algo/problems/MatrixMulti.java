package eng.algo.problems;

import java.time.Duration;
import java.time.Instant;
import java.math.*;
import java.util.Random;

public class MatrixMulti {
    public static int[][] simpleSquare(int[][] A, int[][] B, int len)
    {
        int[][] temp = new int[len][len];
        for(int i=0; i<len; i++)
        {
            for(int j=0; j<len; j++)
            {
                for(int k=0; k<len; k++)
                    temp[i][j] += A[i][k] * B[k][j];
            }
        }
        return temp;
    }



    public static void print2D(int[][] arr)
    {
        int len = arr.length;
        for (int i = 0; i<len; i++)
        {
            System.out.print("[");
            for (int j = 0; j < len; j++) {
                System.out.printf(" %d ",arr[i][j]);
            }
            System.out.print("]\n");
        }
    }

    public static void main(String[] args) {
        Random rand = new Random(15);
        int len = 3;
        int[][] arr = new int[len][len];
        int[][] arr2 = new int[len][len];
        int[][] result = new int[len][len];
        for (int i = 0; i<len; i++) {
            for (int j = 0; j < len; j++)
                arr[i][j] = rand.nextInt(20) - 10;
        }
        for (int i = 0; i<len; i++) {
            for (int j = 0; j < len; j++)
                arr2[i][j] = rand.nextInt(20) - 10;
        }
        Instant start = Instant.now();
        result = simpleSquare(arr,arr2,len);
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toNanos();
        print2D(arr);
        System.out.println("\n");
        print2D(arr2);
        System.out.println("\n");
        print2D(result);
        System.out.println(timeElapsed);

    }
}
