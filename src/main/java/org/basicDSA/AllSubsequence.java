package org.basicDSA;

public class AllSubsequence {
    private static void getAllSubsequence(String s,int i,StringBuilder result){
        if(i>=s.length()){
            System.out.print(result+" ");
            return;
        }
        getAllSubsequence(s,i+1,result);
        getAllSubsequence(s,i+1,result.append(s.charAt(i)));
        result.deleteCharAt(result.length()-1);
    }
    public static void main(String[] args) {
        String s="abc";
        getAllSubsequence(s,0,new StringBuilder());

    }
}
