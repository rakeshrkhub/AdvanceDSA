package org.recursion;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationOfPhoneNumber {
    private static List<String> getAllCombination(String number, String result){
        if(number.isEmpty()){
            List<String> list= new ArrayList<>();
            list.add(result);
            return list;
        }
        int digit=number.charAt(0)-'0';
        int i=(digit-2)*3;
        if(digit>7) i +=1;
        int length=i+3;
        if(digit==7 || digit==9) length+=1;
        List<String> list= new ArrayList<>();
        for(;i<length;i++){
            char ch=(char) ('a'+i);
            list.addAll(getAllCombination(number.substring(1),result+ch));
        }
        return list;
    }
    public static void main(String[] args) {
        String number="23";
        System.out.println(getAllCombination(number,""));
    }
}
