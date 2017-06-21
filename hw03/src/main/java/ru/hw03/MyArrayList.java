package ru.hw03;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Created by nik on 6/19/2017.
 */
public class MyArrayList<T> implements List<T> {
    /**
     * Массив элементов коллекции.
     */
    private Object[] elements;
    /**
     * Размер массива по умолчанию, при создании и расширении.
     */
    private final static int DefaultFullSize = 10;
    /**
     * Позиция ячейки, следующей за последним элементом в массиве.
     */
    private int position;
    /**
     * Конструктор.
     * @param size - первоначальный размер массива.
     */
    public MyArrayList(final int size) {
        if (size > 0) {
            this.elements = new Object[size];
            this.position = 0;
        } else {
            throw new IllegalArgumentException("Argument must be greater than 0");
        }
    }
    /**
     * Конструктор без параметров, использующий значение по умолчанию для размера.
     */
    public MyArrayList() {
        this.elements = new Object[this.DefaultFullSize];
        this.position = 0;
    }
    /**
     * Метод расширения массива, когда он занят польностью.
     * @param resize - значение на которое нужно увеличить емкость массива.
     * @return - новый массив.
     */
    private Object[] makeResize(int resize) {
        int fullSize = this.elements.length + resize;
        Object[] tmpArray = new Object[fullSize];
        System.arraycopy(this.elements, 0, tmpArray, 0, this.elements.length);
        return tmpArray;
    }

    @Override
    public boolean add(T t) {
        if (this.position < this.elements.length) {
            this.elements[this.position] = t;
            this.position++;
        } else {
            this.elements = makeResize(this.DefaultFullSize);
            this.elements[this.position] = t;
            this.position++;
        }
        return false;
    }

    @Override
    public int size() {
        return this.position;
    }

    @Override
    public T get(int index) {
        if (index < 0 && index >= this.position) {
            throw new IllegalArgumentException("Index should be greater than 0 and less than size()");
        }
        return (T) this.elements[index];
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iter = new Iterator<T>() {
            private int point = 0;
            @Override
            public boolean hasNext() {
                if (this.point < position) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                return (T) elements[this.point++];
            }
        };
        return iter;
    }

    @Override
    public ListIterator<T> listIterator() {
        ListIterator<T> lIter = new ListIterator<T>() {
            private int point = 0;
            @Override
            public boolean hasNext() {
                if (this.point < position) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                return (T) elements[this.point++];
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public T previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(T t) {

            }

            @Override
            public void add(T t) {

            }
        };
        return lIter;
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) this.elements, 0, this.position, c);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elements, this.position);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] obj = c.toArray();
        int lenght = obj.length;
        this.elements = makeResize(this.elements.length + lenght);
        System.arraycopy(obj, 0, this.elements, this.position, lenght);
        this.position += lenght;
        return true;
    }

    // Взято из класса AbstractList.
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        ListIterator<T> e1 = listIterator();
        ListIterator<?> e2 = ((List<?>) o).listIterator();
        while (e1.hasNext() && e2.hasNext()) {
            T o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    //Методы - заглушки.
    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        throw new OpsException("replaceAll");
    }

    @Override
    public Spliterator<T> spliterator() {
        throw new OpsException("spliterator");
    }

    @Override
    public boolean isEmpty() {
        throw new OpsException("isEmpty");
    }

    @Override
    public boolean contains(Object o) {
        throw new OpsException("contains");
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new OpsException("toArray");
    }

    @Override
    public boolean remove(Object o) {
        throw new OpsException("remove");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new OpsException("containsAll");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new OpsException("addAll");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new OpsException("removeAll");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new OpsException("retainAll");
    }

    @Override
    public void clear() {
        throw new OpsException("clear");
    }

    @Override
    public T set(int index, T element) {
        throw new OpsException("set");
    }

    @Override
    public void add(int index, T element) {
        throw new OpsException("add");
    }

    @Override
    public T remove(int index) {
        throw new OpsException("remove");
    }

    @Override
    public int indexOf(Object o) {
        throw new OpsException("indexOf");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new OpsException("lastIndexOf");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new OpsException("listIterator");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new OpsException("subList");
    }
}
