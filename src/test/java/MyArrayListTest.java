import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyArrayListTest {
    private MyArrayList<Integer> list;
    private final int NUMBER_OF_ELEMENTS = 1000;

    @BeforeEach
    public void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    public void testAddAndGetElements() {

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            list.add(i);
        }

        assertEquals(NUMBER_OF_ELEMENTS, list.size());

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    public void testAddAtIndexElements() {

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            list.add(0, i);
        }

        assertEquals(NUMBER_OF_ELEMENTS, list.size());
        int index = NUMBER_OF_ELEMENTS - 1;
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            assertEquals(index, list.get(i));
            index--;
        }
    }

    @Test
    public void testRemoveElements() {
        // Сначала добавляем элементы
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            list.add(i);
        }

        // Теперь удаляем их и проверяем размер списка
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            assertEquals(i, list.remove(0));
            assertEquals(NUMBER_OF_ELEMENTS - i - 1, list.size());
        }

        assertEquals(0, list.size());
    }

    @Test
    public void testClearElements() {

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            list.add(i);
        }

        list.clear();
        assertEquals(0, list.size());

        // Убедимся, что после очистки нельзя получить элемент
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    public void testSetElements() {

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            list.add(i);
        }

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            list.set(i, i * 10);
        }

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            assertEquals(i * 10, list.get(i));
        }
    }

    @Test
    public void testAddAtIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 10));
    }

    @Test
    public void testGetOutOfBounds() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    public void testRemoveOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }


    @Test
    public void testSetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 10));
    }

    @Test
    public void testSortWithComparator() {
        list.add(3);
        list.add(1);
        list.add(2);

        list.sort(Comparator.naturalOrder());

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testSortWithComparable() {
        MyArrayList<String> stringList = new MyArrayList<>();
        stringList.add("b");
        stringList.add("c");
        stringList.add("a");

        stringList.sort();

        assertEquals("a", stringList.get(0));
        assertEquals("b", stringList.get(1));
        assertEquals("c", stringList.get(2));
    }

    @Test
    public void testSortWithoutComparable() {
        class NonComparable {
        }

        MyArrayList<NonComparable> nonComparableList = new MyArrayList<>();
        nonComparableList.add(new NonComparable());

        assertThrows(ClassCastException.class, nonComparableList::sort);
    }
}
