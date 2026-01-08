package org.basicDSA;

import java.util.ArrayList;
import java.util.List;

public class GAllSubsequence {
    private static void getAllSubsequence(int[] arr, int index, List<Integer> ds){
        if(index>=arr.length){
            System.out.println(ds);
            return;
        }
        ds.add(arr[index]);
        getAllSubsequence(arr,index+1,ds);
        ds.remove(ds.size()-1);
        getAllSubsequence(arr,index+1,ds);
    }
    public static void main(String[] args) {
        int[] arr={1,2,3};
        getAllSubsequence(arr,0,new ArrayList<>());
    }
}
