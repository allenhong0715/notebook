package org.example.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Q_215Test {

    @Test
    void findKthLargest() {

        int[] nums = new int[]{
                3,2,1,5,6,4
        };
        int k = 2;

        Q_215 q_215 = new Q_215();
        int largest = q_215.findKthLargest(nums, k);
        assert largest == 5;

    }
}