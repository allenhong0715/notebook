package org.example.sort;

public class Q_215 {
    public int findKthLargest(int[] nums, int k) {

        int index = nums.length - k;
        quickSelect(nums,0,nums.length - 1);
        return nums[index];

    }

    public void quickSelect(int[] nums, int l, int r){

        if(l > r){
            return;
        }

        int x = nums[l];
        int i = l;
        int j = r;

        while (i < j) {
            while (i < j && nums[j] >= x) {
                j--;
            }
            if (i < j) {
                nums[i++] = nums[j];
            }
            while (i < j && nums[i] <= x) {
                i++;
            }
            if (i < j) {
                nums[j--] = nums[i];
            }
        }
        nums[i] = x;
        quickSelect(nums,l,i-1);
        quickSelect(nums,i+1,r);
    }

}
