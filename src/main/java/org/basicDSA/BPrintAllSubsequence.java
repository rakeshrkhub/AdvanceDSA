package org.basicDSA;

import java.util.ArrayList;
import java.util.List;

public class BPrintAllSubsequence {
    public static void getAllSubsequence(int index, int[] arr, List<Integer> result){
        if(index>=arr.length){
            System.out.println(result);
            return;
        }
        result.add(arr[index]);
        getAllSubsequence(index+1,arr,result);
        result.remove(result.size()-1);
        getAllSubsequence(index+1,arr,result);
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        getAllSubsequence(0,arr,new ArrayList<>());
    }
}
