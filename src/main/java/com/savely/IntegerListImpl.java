package com.savely;

import com.savely.bean.BinarySearch;
import com.savely.exception.IncorrectIndexException;
import com.savely.exception.ItemIsNullException;
import com.savely.exception.ItemNotFoundException;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {

    private Integer[] arr;
    private int size;
    private static final int INITIAL_CAPACITY = 1;

    public IntegerListImpl() {
        arr = new Integer[INITIAL_CAPACITY];
    }

    private void checkItem(Integer item) {
        if (Objects.isNull(item)) {
            throw new ItemIsNullException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException();
        }
    }

    @Override
    public Integer add(Integer item) {
        checkItem(item);

        if (size == arr.length) {
            arr = Arrays.copyOf(arr, size * 2);
        }

        arr[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkItem(item);
        checkIndex(index);

        if (size == arr.length) {
            Integer[] newArr = new Integer[size * 2];
            System.arraycopy(arr, 0, newArr, 0, index);
            newArr[index] = item;
            System.arraycopy(arr, index, newArr, index + 1, size - index);
            arr = newArr;
        } else {
            System.arraycopy(arr, index, arr, index + 1, size - index);
            arr[index] = item;
        }
        size++;

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkItem(item);
        checkIndex(index);
        arr[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItem(item);
        int index = indexOf(item);

        if (index == -1) {
            throw new ItemNotFoundException();
        }

        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);


        Integer res = arr[index];

        if (index + 1 != size) {
            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        }

        arr[--size] = null;

        return res;
    }

    @Override
    public boolean contains(Integer item) {
        checkItem(item);
        BinarySearch binarySearch = new BinarySearch();
        Integer[] arrCopy = toArray();
        binarySearch.sort(arrCopy);
        return binarySearch.binarySearch(arrCopy, item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        checkItem(item);
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return arr[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        arr = new Integer[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    @Override
    public String toString() {
        return "StringListImpl{" +
                "arr=" + Arrays.toString(toArray()) +
                '}';
    }
}
