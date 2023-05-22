package com.savely;

import com.savely.exception.IncorrectIndexException;
import com.savely.exception.StringNotFoundException;
import org.junit.jupiter.api.Test;

import static com.savely.constants.StringListImplConstants.*;
import static org.assertj.core.api.Assertions.*;

class StringListImplTest {

    private final StringList out = new StringListImpl();

    @Test
    void addTest() {
        int initialSize = out.size();

        assertThat(out.add(NEW_STRING)).isEqualTo(NEW_STRING);
        assertThat(out.size()).isEqualTo(initialSize + 1);
    }

    @Test
    void addTestWhenArrayIsFull() {
        out.add(NEW_STRING);

        int initialSize = out.size();

        assertThat(out.add(NEW_STRING)).isEqualTo(NEW_STRING);
        assertThat(out.size()).isEqualTo(initialSize + 1);

    }

    @Test
    void addByIndexTest() {
        out.add(NEW_STRING);

        int initialSize = out.size();

        assertThat(out.add(0, NEW_STRING)).isEqualTo(NEW_STRING);
        assertThat(out.size()).isEqualTo(initialSize + 1);
    }


    @Test
    void addByIndexWhenIncorrectIndexTest() {
        assertThatExceptionOfType(IncorrectIndexException.class).isThrownBy(
                () -> out.add(0, NEW_STRING)
        );
    }

    @Test
    void setTest() {
        out.add(NEW_STRING);

        int initialSize = out.size();

        assertThat(out.set(0, CHANGE_STRING)).isEqualTo(CHANGE_STRING).isNotEqualTo(NEW_STRING);
        assertThat(out.size()).isEqualTo(initialSize);
    }

    @Test
    void setWhenIncorrectIndexTest() {
        assertThatExceptionOfType(IncorrectIndexException.class).isThrownBy(
                () -> out.set(out.size() + 1, NEW_STRING)
        );
        assertThatExceptionOfType(IncorrectIndexException.class).isThrownBy(
                () -> out.set(NEGATIVE_INDEX, NEW_STRING)
        );
    }

    @Test
    void removeTest() {
        out.add(NEW_STRING);

        int initialSize = out.size();

        assertThat(out.remove(NEW_STRING)).isEqualTo(NEW_STRING);
        assertThat(out.size()).isEqualTo(initialSize - 1);
    }

    @Test
    void removeWhenStringIsNotFoundTest() {
        assertThatExceptionOfType(StringNotFoundException.class).isThrownBy(
                () -> out.remove(NEW_STRING)
        );
    }

    @Test
    void removeByIndexTest() {
        out.add(NEW_STRING);

        int initialSize = out.size();

        assertThat(out.remove(0)).isEqualTo(NEW_STRING);
        assertThat(out.size()).isEqualTo(initialSize - 1);
    }

    @Test
    void removeByIndexWhenIncorrectIndexTest() {
        assertThatExceptionOfType(IncorrectIndexException.class).isThrownBy(
                () -> out.remove(out.size())
        );
        assertThatExceptionOfType(IncorrectIndexException.class).isThrownBy(
                () -> out.remove(NEGATIVE_INDEX)
        );
    }

    @Test
    void containsTest() {
        out.add(NEW_STRING);

        assertThat(out.contains(NEW_STRING)).isTrue();
        assertThat(out.contains(CHANGE_STRING)).isFalse();
    }

    @Test
    void indexOfTest() {
        out.add(NEW_STRING);
        out.add(CHANGE_STRING);

        assertThat(out.indexOf(NEW_STRING)).isZero();
        out.remove(CHANGE_STRING);
        assertThat(out.indexOf(CHANGE_STRING)).isEqualTo(-1);
    }

    @Test
    void lastIndexOfTest() {
        out.add(CHANGE_STRING);
        out.add(NEW_STRING);

        assertThat(out.lastIndexOf(NEW_STRING)).isZero();
        out.remove(CHANGE_STRING);
        assertThat(out.indexOf(CHANGE_STRING)).isEqualTo(-1);
    }


    @Test
    void getTest() {
        out.add(NEW_STRING);
        out.add(CHANGE_STRING);

        assertThat(out.get(0)).isEqualTo(NEW_STRING);
        assertThat(out.get(1)).isEqualTo(CHANGE_STRING);
    }

    @Test
    void getTestWhenIncorrectIndexTest() {
        assertThatExceptionOfType(IncorrectIndexException.class).isThrownBy(
                () -> out.get(out.size())
        );
        assertThatExceptionOfType(IncorrectIndexException.class).isThrownBy(
                () -> out.get(NEGATIVE_INDEX)
        );
    }

    @Test
    void equalsTest() {
        out.add(NEW_STRING);
        StringList list = new StringListImpl();
        list.add(NEW_STRING);

        assertThat(out.equals(list)).isTrue();
        assertThat(list.equals(out)).isTrue();
    }

    @Test
    void notEqualsTest() {
        out.add(NEW_STRING);
        StringList list = new StringListImpl();
        list.add(CHANGE_STRING);

        assertThat(out.equals(list)).isFalse();
        assertThat(list.equals(out)).isFalse();

        list.remove(CHANGE_STRING);
        assertThat(out.equals(list)).isFalse();
        assertThat(list.equals(out)).isFalse();
    }

    @Test
    void sizeTest() {
        int initialSize = 0;

        assertThat(out.size()).isEqualTo(initialSize);

        out.add(NEW_STRING);

        assertThat(out.size()).isEqualTo(initialSize + 1);

    }


    @Test
    void isEmptyTest() {
        assertThat(out.isEmpty()).isTrue();

        out.add(NEW_STRING);

        assertThat(out.isEmpty()).isFalse();
    }

    @Test
    void clear() {
        out.add(NEW_STRING);

        out.clear();

        assertThat(out.size()).isZero();
    }

    @Test
    void toArray() {
        out.add(NEW_STRING);
        out.add(CHANGE_STRING);

        assertThat(out.toArray()).isEqualTo(new String[]{NEW_STRING, CHANGE_STRING}).hasSize(2);

    }
}