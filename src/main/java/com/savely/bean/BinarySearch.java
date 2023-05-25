package com.savely.bean;

public class BinarySearch {

    public void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public int binarySearch(Integer[] arr, int item) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (item > arr[mid]) {
                l = mid + 1;
            } else if (item < arr[mid]) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
