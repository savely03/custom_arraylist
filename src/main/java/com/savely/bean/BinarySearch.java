package com.savely.bean;

public class BinarySearch<T> {
    private void swapElements(T[] arr, int left, int right) {
        T temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private int partition(T[] arr, int begin, int end) {
        T pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (pivot.getClass() == Integer.class) {
                if ((Integer) arr[j] <= (Integer) pivot) {
                    i++;
                    swapElements(arr, i, j);
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    public void sort(T[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            sort(arr, begin, partitionIndex - 1);
            sort(arr, partitionIndex + 1, end);
        }
    }

    public int binarySearch(T[] arr, T item) {
        if (item.getClass() != Integer.class) {
            throw new IllegalArgumentException();
        }

        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if ((Integer) item > (Integer) arr[mid]) {
                l = mid + 1;
            } else if ((Integer) item < (Integer) arr[mid]) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
