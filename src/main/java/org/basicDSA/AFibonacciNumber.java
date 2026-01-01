package org.basicDSA;

import java.util.Arrays;

public class AFibonacciNumber {
    private static int[] fiboNumber(int number){
        int[] result = new int[number];
        result[0]=0;
        result[1]=1;
        for(int i=2;i<number;i++){
            result[i]=result[i-1]+result[i-2];
        }
        return result;
    }

    private static void fiboNumber(int number,int[] result, int counter){
        if(counter==0 || counter==1){
            result[counter]=counter;
            counter++;
        }
        if(counter<number){
            result[counter]=result[counter-1]+result[counter-2];
            counter++;
        }

    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(fiboNumber(10)));
    }
}
