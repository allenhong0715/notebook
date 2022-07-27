package org.example.doublePointer;

public class Q_680 {

    public boolean validPalindrome(String s) {
        int j = s.length() - 1;
        for (int i = 0; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return sub(s,i+1,j) || sub(s,i,j-1);
            }
        }
        return true;
    }

    boolean sub(String s, int i, int j) {

        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

}
