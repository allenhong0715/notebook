package org.example.doublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q_345 {

    static List<Character> chars = Arrays.asList('a','e','i','o','u','A','E','I','O','U');

    public static String reverseVowers(String s){

        if (s == null || s.isEmpty()) {
            return s;
        }

        char[] tars = s.toCharArray();

        int i = 0;
        int j = s.length() - 1;
        while (i < j){
            if (chars.contains(tars[i]) && chars.contains(tars[j])) {
                char tmp = tars[i];
                tars[i] = tars[j];
                tars[j] = tmp;
                i++;
                j--;
            }else if (!chars.contains(tars[i])){
                i++;
            }else if (!chars.contains(tars[j])){
                j--;
            }
        }

        return new String(tars);
    }

}
