import java.util.Arrays;
import java.util.Comparator;

/**
 * MyArrayList is a dynamic array implementation that supports operations such as
 * adding, removing, getting, and setting elements. This class also provides a custom
 * implementation of the quicksort algorithm for sorting elements.
 *
 * <p>MyArrayList is a non-thread-safe implementation and is designed for single-threaded environments.</p>
 *
 * @param <T> the type of elements in this list
 */
public class MyArrayList<T> {

    // Массив для хранения элементов списка
    private Object[] elements;
    // Текущий размер списка
    private int size = 0;
    // Начальная вместимость массива
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs an empty list with an initial capacity.
     */
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     * @throws NullPointerException if the specified element is null
     */
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right.
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt; size)
     * @throws NullPointerException      if the specified element is null
     */

    public void add(int index, T element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        checkIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt; size)
     */

    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt; size)
     */
    public T remove(int index) {
        checkIndex(index);
        T oldValue = get(index);
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        elements[--size] = null; // Очистка последнего элемента
        return oldValue;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt; size)
     * @throws NullPointerException      if the specified element is null
     */
    public void set(int index, T element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        checkIndex(index);
        elements[index] = element;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Sorts this list using the quicksort algorithm and the specified comparator.
     *
     * @param comparator the comparator to determine the order of the list. A null value
     *                   indicates that the natural ordering of the elements should be used.
     */
    public void sort(Comparator<? super T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Sorts this list using the quicksort algorithm and the natural order of its elements.
     * Elements must implement {@link Comparable}.
     *
     * @throws ClassCastException if the elements in this list do not implement {@link Comparable}
     */
    public void sort() {
        if (size == 0) {
            return;
        }
        if (!(elements[0] instanceof Comparable)) {
            throw new ClassCastException("Elements must be Comparable");
        }
        quickSort(0, size - 1, (o1, o2) -> ((Comparable<T>) o1).compareTo((T) o2));
    }

    private void quickSort(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pi = partition(low, high, comparator);
            quickSort(low, pi - 1, comparator);
            quickSort(pi + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<? super T> comparator) {
        T pivot = (T) elements[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare((T) elements[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    // Метод для проверки индекса при добавлении элемента (index может быть равен size)
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

}
