package org.example;

import org.example.heap.HeapSort;
import org.example.sort.Q_215;
import org.example.sort.Solution;

public class Main {
    public static void main(String[] args) {
//        String a = "leetcode";
//        String b = "hello";
//        System.out.println(Q_345.reverseVowers(a));
//        System.out.println(Q_345.reverseVowers(b));


//        Q_680 q_680 = new Q_680();
//        System.out.println(q_680.validPalindrome("cbbcc"));
//        Q_524 q_524 = new Q_524();
//        System.out.println(q_524.findLongestWord("apple", Arrays.asList("zxc", "vbn")));
//        System.out.println(q_524.findLongestWord("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea")));


//        Q_215 solution = new Q_215();
//        int[] nums = new int[]{
//                3,2,1,5,6,4
//        };
//
//        System.out.println(solution.findKthLargest(nums, 2));


        HeapSort heapSort = new HeapSort();
        int[] arr = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(heapSort.run(arr, 4));
        for (int a : arr) {
            System.out.println(a);
        }
    }
}