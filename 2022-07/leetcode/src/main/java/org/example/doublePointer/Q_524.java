package org.example.doublePointer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Q_524 {
    public String findLongestWord(String s, List<String> dictionary) {


        if (s == null || s.isEmpty() || dictionary.isEmpty()) {
            return null;
        }
        List<String> results = new ArrayList<>();
        int len = 0;
        for (String d : dictionary) {

            int i = d.length() - 1;
            int j = s.length() - 1;

            while (i >= 0 && j >= 0) {
                if (d.charAt(i) == s.charAt(j)) {
                    i--;
                    j--;
                } else {
                    j--;
                }

            }

            if (i < 0) {
                if (d.length() > len) {
                    len = d.length();
                    results = new ArrayList<>();
                    results.add(d);
                } else if (d.length() == len) {
                    results.add(d);
                }
            }
        }
        if (results.size() == 0) {
            return "";
        }
        return results.stream().sorted().collect(Collectors.toList()).get(0);
    }
}
