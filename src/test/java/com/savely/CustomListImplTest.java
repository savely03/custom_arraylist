package com.savely;

import com.savely.exception.IncorrectIndexException;
import com.savely.exception.ItemIsNullException;
import com.savely.exception.ItemNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.savely.constants.CustomListImplConstants.*;
import static org.assertj.core.api.Assertions.*;

class CustomListImplTest {

    private final CustomList<Integer> out = new CustomListImpl<>();
    private final Random random = new Random();

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 5; i++) {
            out.add(i);
        }
    }

    @Test
    void addTest() {
        int initialSize = out.size();

        assertThat(out.add(1)).isEqualTo(1);
        assertThat(out.size()).isEqualTo(initialSize + 1);

    }

    @Test
    void addWhenItemIsNullTest() {
        assertThatExceptionOfType(ItemIsNullException.class).isThrownBy(
                () -> out.add(NULL)
        );
    }

    @Test
    void addByIndexTest() {
        int initialSize = out.size();
        int ind = random.nextInt(initialSize);
        int initialNumber = out.get(ind);

        assertThat(out.add(ind, NUMBER)).isEqualTo(NUMBER);
        assertThat(out.get(ind)).isEqualTo(NUMBER);
        assertThat(out.get(ind + 1)).isEqualTo(initialNumber);
        assertThat(out.size()).isEqualTo(initialSize + 1);
    }

    @Test
    void addByIndexWhenArrayIsFullTest() {
        CustomList<Integer> outCopy = new CustomListImpl<>();
        outCopy.add(NUMBER);
        int initialSize = outCopy.size();
        int ind = random.nextInt(initialSize);
        int initialNumber = outCopy.get(ind);


        assertThat(outCopy.add(ind, NUMBER)).isEqualTo(NUMBER);
        assertThat(outCopy.get(ind)).isEqualTo(NUMBER);
        assertThat(outCopy.get(ind + 1)).isEqualTo(initialNumber);
        assertThat(outCopy.size()).isEqualTo(initialSize + 1);

    }

    @Test
    void addByIndexWhenIncorrectIndexTest() {
        assertThatExceptionOfType(IncorrectIndexException.class).isThrownBy(
                () -> out.add(out.size(), NUMBER)
        );
    }


    @Test
    void setTest() {
        int initialSize = out.size();
        int ind = random.nextInt(initialSize);

        assertThat(out.set(ind, NUMBER)).isEqualTo(NUMBER);
        assertThat(out.size()).isEqualTo(initialSize);
    }

    @Test
    void removeTest() {
        int initialSize = out.size();
        Integer removeItem = out.get(random.nextInt(initialSize));

        assertThat(out.remove(removeItem)).isEqualTo(removeItem);
        assertThat(out.size()).isEqualTo(initialSize - 1);
    }

    @Test
    void removeWhenItemNotFoundTest() {
        assertThatExceptionOfType(ItemNotFoundException.class).isThrownBy(
                () -> out.remove((Integer) 7)
        );
    }

    @Test
    void removeByIndexTest() {
        int initialSize = out.size();
        int ind = random.nextInt(initialSize);
        int item = out.get(ind);

        assertThat(out.remove(ind)).isEqualTo(item);
        assertThat(out.size()).isEqualTo(initialSize - 1);
    }

    @Test
    void containsTest() {
        int initialSize = out.size();
        Integer item = out.get(random.nextInt(initialSize));

        assertThat(out.contains(item)).isTrue();
        assertThat(out.contains(7)).isFalse();
        assertThat(out.size()).isEqualTo(initialSize);
    }

    @Test
    void indexOfTest() {
        out.add(NUMBER);
        assertThat(out.indexOf(NUMBER)).isEqualTo(0);
    }

    @Test
    void lastIndexOfTest() {
        out.add(NUMBER);

        assertThat(out.lastIndexOf(NUMBER)).isEqualTo(out.size() - 1);

        assertThat(out.lastIndexOf(7)).isEqualTo(-1);

    }

    @Test
    void getTest() {
        assertThat(out.get(0)).isEqualTo(NUMBER);
    }

    @Test
    void equalsTest() {
        CustomList<Integer> copyOut = new CustomListImpl<>();

        for (int i = 1; i <= 5; i++) {
            copyOut.add(i);
        }

        assertThat(out.equals(copyOut)).isTrue();
        assertThat(out.equals(EMPTY_LIST)).isFalse();

    }

    @Test
    void sizeTest() {
        assertThat(out.size()).isEqualTo(5);
        assertThat(EMPTY_LIST.size()).isEqualTo(0);
    }

    @Test
    void isEmpty() {
        assertThat(out.isEmpty()).isFalse();
        assertThat(EMPTY_LIST.isEmpty()).isTrue();
    }

    @Test
    void clearTest() {
        out.clear();
        EMPTY_LIST.clear();

        assertThat(out.size()).isEqualTo(0);
        assertThat(EMPTY_LIST.size()).isEqualTo(0);
    }

    @Test
    void toArrayTest() {
        assertThat(out.toArray()).isEqualTo(new Integer[]{1, 2, 3, 4, 5});
    }

    @AfterEach
    public void setOut() {
        out.clear();
    }
}

