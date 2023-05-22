package com.savely;

import com.savely.exception.IncorrectIndexException;
import com.savely.exception.StringNotFoundException;

import java.util.Arrays;


public class StringListImpl implements StringList {

    private String[] arr;
    private int size;
    private static final int INITIAL_CAPACITY = 1;

    public StringListImpl() {
        arr = new String[INITIAL_CAPACITY];
    }

    public String add(String item) {
        if (size == arr.length) {
            String[] newArr = new String[arr.length * 2];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        arr[size++] = item;
        return item;
    }

    public String add(int index, String item) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException();
        }

        String[] newArr = size == arr.length ? new String[arr.length * 2] : new String[arr.length];

        System.arraycopy(arr, 0, newArr, 0, index);
        newArr[index] = item;
        System.arraycopy(arr, index, newArr, index + 1, arr.length - index);
        arr = newArr;
        size++;

        return item;
    }

    public String set(int index, String item) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException();
        }
        arr[index] = item;
        return item;
    }

    public String remove(String item) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                String res = arr[i];
                System.arraycopy(arr, i + 1, arr, i, arr.length - i - 1);
                arr[arr.length - 1] = null;
                size--;
                return res;
            }
        }
        throw new StringNotFoundException();
    }

    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException();
        }

        String res = arr[index];
        System.arraycopy(arr, index + 1, arr, index, arr.length - index - 1);
        arr[arr.length - 1] = null;
        size--;

        return res;
    }

    public boolean contains(String item) {
        for (String s : arr) {
            if (s.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i].equals(item)) {
                return size - 1 - i;
            }
        }
        return -1;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException();
        }
        return arr[index];
    }

    public boolean equals(StringList otherList) {
        if (size != otherList.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!get(i).equals(otherList.get(i))) {
                return false;
            }
        }

        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        arr = new String[INITIAL_CAPACITY];
        size = 0;
    }

    public String[] toArray() {
        return Arrays.copyOf(arr, arr.length);
    }

}
