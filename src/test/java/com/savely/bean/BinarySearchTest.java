package com.savely.bean;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class BinarySearchTest {

    private final Integer[] sortedArr = {1, 2, 3, 4, 5};
    private final BinarySearch binarySearch = new BinarySearch();

    @Test
    void sortTest() {
        Integer[] arr = {5, 4, 1, 3, 2};
        binarySearch.sort(arr);
        assertThat(arr).isEqualTo(sortedArr);
    }

    @Test
    void binarySearchTest() {
        assertThat(binarySearch.binarySearch(sortedArr, 5)).isEqualTo(4);
        assertThat(binarySearch.binarySearch(sortedArr, 1)).isEqualTo(0);
        assertThat(binarySearch.binarySearch(sortedArr, 3)).isEqualTo(2);
    }

    @Test
    void binarySearchTestWhenItemDoesNotExist() {
        assertThat(binarySearch.binarySearch(sortedArr, 10)).isEqualTo(-1);
    }
}