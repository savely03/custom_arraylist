package com.savely;

import com.savely.exception.IncorrectIndexException;
import com.savely.exception.StringIsNullException;
import com.savely.exception.StringNotFoundException;

import java.util.Arrays;
import java.util.Objects;


public class StringListImpl implements StringList {

    private String[] arr;
    private int size;
    private static final int INITIAL_CAPACITY = 1;

    public StringListImpl() {
        arr = new String[INITIAL_CAPACITY];
    }

    private void checkString(String item) {
        if (Objects.isNull(item)) {
            throw new StringIsNullException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException();
        }
    }


    public String add(String item) {
        checkString(item);

        if (size == arr.length) {
            String[] newArr = new String[size * 2];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }

        arr[size++] = item;
        return item;
    }

    public String add(int index, String item) {
        checkString(item);
        checkIndex(index);

        if (size == arr.length) {
            String[] newArr = new String[size * 2];
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

    public String set(int index, String item) {
        checkString(item);
        checkIndex(index);
        arr[index] = item;
        return item;
    }

    public String remove(String item) {
        checkString(item);
        int index = indexOf(item);

        if (index == -1) {
            throw new StringNotFoundException();
        }

        return remove(index);
    }

    public String remove(int index) {
        checkIndex(index);


        String res = arr[index];

        if (index + 1 != size) {
            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        }

        arr[--size] = null;

        return res;
    }

    public boolean contains(String item) {
        checkString(item);
        return indexOf(item) != -1;
    }

    public int indexOf(String item) {
        checkString(item);
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(String item) {
        checkString(item);
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public String get(int index) {
        checkIndex(index);
        return arr[index];
    }

    public boolean equals(StringList otherList) {
        return Arrays.equals(toArray(), otherList.toArray());
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
        return Arrays.copyOf(arr, size);
    }

//    @Override
//    public String toString() {
//        return "StringListImpl{" +
//                "arr=" + Arrays.toString(arr) +
//                '}';
//    }
//
//    public static void main(String[] args) {
//        StringListImpl list = new StringListImpl();
//        list.add("a");
//        list.add("b");
//        list.remove("a");
//        System.out.println(list);
//        list.add("c");
//        list.add("c");
//        list.add("a");
//
//        StringListImpl list1 = new StringListImpl();
//        list1.add("a");
//        list1.add("b");
//        list1.add("c");
//        list1.add("c");
//        System.out.println(list.equals(list1));
//        System.out.println(list);
//    }

}
