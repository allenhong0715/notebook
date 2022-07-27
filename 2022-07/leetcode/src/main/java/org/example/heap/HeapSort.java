package org.example.heap;

public class HeapSort {

    public int  run(int[] arr, int k) {

        buildHeap(arr, arr.length);

        for (int i = arr.length - 1; i > (arr.length - 1 - k); i--) {
            swap(arr, 0, i);
            heapify(arr, i,0);
        }
        return arr[arr.length - k];
    }

    void buildHeap(int[] arr, int n) {
        int lastNode = n - 1;
        int lastParent = (lastNode - 1) / 2;
        for (int i = lastParent; i >= 0; i--) {
            heapify(arr,n,i);
        }
    }


     void heapify(int[] arr, int n, int i) {

        if (i >= n) {
            return;
        }
            
        int c1 = 2 * i + 1;
        int c2 = 2 * i + 2;
        int max = i;

        if (c1 < n && arr[c1] > arr[max]) {
            max = c1;
        }

        if (c2 < n && arr[c2] > arr[max]) {
            max = c2;
        }

        if (max != i) {
            swap(arr,max,i);
            heapify(arr,n,max);
        }
    }

     void swap(int[] arr, int max, int i) {
        
        int tmp = arr[max];
        arr[max] = arr[i];
        arr[i] = tmp;
    }
}
