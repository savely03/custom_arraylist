package com.savely;

import com.savely.bean.BinarySearch;
import com.savely.exception.IncorrectIndexException;
import com.savely.exception.ItemIsNullException;
import com.savely.exception.ItemNotFoundException;

import java.util.Arrays;
import java.util.Objects;

public class CustomListImpl<T> implements CustomList<T> {

    private Object[] arr;
    private int size;
    private static final int INITIAL_CAPACITY = 1;

    public CustomListImpl() {
        arr = new Object[INITIAL_CAPACITY];
    }

    private void checkItem(T item) {
        if (Objects.isNull(item)) {
            throw new ItemIsNullException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IncorrectIndexException();
        }
    }

    private Object[] grow() {
        return Arrays.copyOf(arr, size * 2);
    }


    @Override
    public T add(T item) {
        checkItem(item);

        if (size == arr.length) {
            arr = grow();
        }

        arr[size++] = item;
        return item;
    }

    @Override
    public T add(int index, T item) {
        checkItem(item);
        checkIndex(index);

        if (size == arr.length) {
            Object[] newArr = grow();
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
    public T set(int index, T item) {
        checkItem(item);
        checkIndex(index);
        arr[index] = item;
        return item;
    }

    @Override
    public T remove(T item) {
        checkItem(item);
        int index = indexOf(item);

        if (index == -1) {
            throw new ItemNotFoundException();
        }

        return remove(index);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T res = (T) arr[index];

        if (index + 1 != size) {
            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        }

        arr[--size] = null;

        return res;
    }

    @Override
    public boolean contains(T item) {
        checkItem(item);
        BinarySearch<T> binarySearch = new BinarySearch<>();
        T[] arrCopy = (T[]) toArray();
        binarySearch.sort(arrCopy, 0, arrCopy.length - 1);
        return binarySearch.binarySearch(arrCopy, item) != -1;
    }

    @Override
    public int indexOf(T item) {
        checkItem(item);
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T item) {
        checkItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) arr[index];
    }

    @Override
    public boolean equals(CustomList<T> otherList) {
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
        arr = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }
}
