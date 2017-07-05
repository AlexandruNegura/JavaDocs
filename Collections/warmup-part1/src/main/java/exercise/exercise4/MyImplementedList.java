package exercise.exercise4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * You should implement from zero a data structure that acts as an ArrayList.
 * We have a default capacity of {@link MyImplementedList#DEFAULT_CAPACITY} elements of type <code>E</code>.
 * We have a {@link MyImplementedList#size} property that stores the number of elements of type <code>E</code> from the data structure.
 * We have an array property, {@link MyImplementedList#elementData}, that keeps the elements from the data structure.
 * We have a {@link MyImplementedList#LOAD_FACTOR} property that specify the maximum accepted load of the data structure.
 * We have a {@link MyImplementedList#INCREASE_SIZE_FACTOR} property to use it when we must increase the capacity of the data structure.
 * We have a {@link MyImplementedList#capacity} property to use it to retain the new capacity after increasing it.
 * <p>
 * Starting with this properties we must implement a data structure that acts ~ as an ArrayList for some objects of type <code>E</code>.
 * <p>
 * TODO if you need to throw some exceptions YOU MUST create them!
 * TODO if you get some warning from the compiler you can use @SuppressWarnings("all") before the method name!
 * TODO if you get this error "usage of api documented as @since 1.6+" you should go to File > Project Structure > Modules and make sure you have the Language level >= 1.6!
 * TODO you should expose as <code>public</code> only the methods that you usually use over a collection!
 * TODO if you need a getter/setter for the properties you must define then, but keep in mind the java concepts!
 * TODO make sure you cover all the possible use-cases for your data structure!
 *
 * @author Cristian.Dumitru
 * @since 7/3/2017.
 */
public class MyImplementedList<E> implements Iterable<E> {
    public class WhateverComparator implements Comparator<E> {
        public int compare(E o1, E o2) {
            if (o1 != null && o2 != null) {
                if (o1.hashCode() < o2.hashCode()) {
                    return -1;
                } else if (o1.hashCode() == o2.hashCode()) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }
        }
    }


    /**
     * The maximum accepted load property of the data structure.
     */
    private static final double LOAD_FACTOR = 0.75d;

    /**
     * The factor for increasing the size of the data structure.
     */
    private static final int INCREASE_SIZE_FACTOR = 2;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * The array buffer into which the elements of the {@link MyImplementedList} are stored.
     */
    private Object[] elementData;

    /**
     * Property to keep the extended capacity.
     * TODO if you choose another way to implement the extending capacity you can define your own properties,
     * TODO but the rest of them must remain as they are.
     */
    private int capacity;

    //TODO a) implement the empty constructor for the your data structure
    public MyImplementedList() {
        //TODO a) HINT - DEFAULT_CAPACITY, capacity and elementData properties
        this.capacity = DEFAULT_CAPACITY;
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    //TODO b) create the int size() method that returns the size of the data structure
    public int size() {
        return size;
    }

    //TODO c) create the boolean add(E e) method that adds at the end of the data structure an element
    //TODO pay attention to the LOAD_FACTOR of the data structure

    public boolean add(E e) {
        int actualLoadFactor = size() / capacity;
        if (actualLoadFactor > LOAD_FACTOR) {
            extendCapacity();
        }

        elementData[size()] = e;
        size++;
        return true;
    }

    //TODO d) create the boolean isEmpty() method that checks if the data structure have elements
    public boolean isEmpty() {
        return size() == 0;
    }

    //TODO e) create the boolean contains(Object o_O) method that checks if the data structure contains the object o_O

    public boolean contains(Object o_O) {
        for (Object o : elementData) {
            if (o.equals(o_O)) {
                return true;
            }
        }

        return false;
    }

    //TODO f) create the int indexOf(Object o_O) method that returns the position in the data structure of the object o_O
    //TODO if exists, otherwise return -1

    public int indexOf(Object o_O) {
        int i = 0;
        for (int index = 0; index < size(); index++) {
            Object o = elementData[index];
            if (o != null && o.equals(o_O)) {
                return i;
            }
            i++;
        }

        return -1;
    }

    //TODO g) create the int lastIndexOf(Object o_O) method that returns the last position in the data structure of the object o_O
    //TODO if exists, otherwise return -1

    @SuppressWarnings("all")
    public int lastIndexOf(Object o_O) {
        int i = 0;
        int location = 0;
        boolean found = false;
        for (int index = 0; index < size(); index++) {
            Object o = elementData[index];

            if (o != null & o.equals(o_O)) {
                found = true;
                location = i;
            }
            i++;
        }

        if (found) {
            return location;
        } else {
            return -1;
        }

    }

    //TODO h) create the E get(int index) method that returns the object from the given index
    //TODO pay attention to the size property

    @SuppressWarnings("all")
    public E get(int index) {
        if (index > size || index < 0) {
            throw new OutOfBoundsException();
        } else {
            return ((E) elementData[index]);
        }
    }

    //TODO i) create the E set(int index, E element) method that updates the value of the element from the given index
    //TODO pay attention to the size property

    @SuppressWarnings("all")
    public E set(int index, E element) {
        if (index > size || index < 0) {
            throw new OutOfBoundsException();
        } else {
            elementData[index] = element;
        }

        return (E) elementData[index];
    }

    //TODO j) create the E remove(int index) method that removes the element from the given index
    @SuppressWarnings("all")
    public E remove(int index) {
        if (index > size || index < 0) {
            throw new OutOfBoundsException();
        } else {
            E removedElement = (E) elementData[index];

            for (int i = index + 1; i < size(); i++) {
                elementData[i - 1] = elementData[i];
            }

            elementData[size() - 1] = null;
            size--;
            return removedElement;
        }
    }

    //TODO k) extend the current default capacity, if the number of elements in the data structure is > 75% of it
    //TODO you should name it: void extendCapacity(int capacity) - HINT use capacity, DEFAULT_CAPACITY, LOAD_FACTOR and INCREASE_SIZE_FACTOR

    private void extendCapacity() {
        capacity *= INCREASE_SIZE_FACTOR;
        Object[] newElementData = new Object[capacity];
        java.lang.System.arraycopy(elementData, 0, newElementData, 0, size());
        elementData = newElementData;
    }


    //TODO l) implement the iterator() method in order to use the foreach statement over your data structure - HINT Iterable interface
    //TODO and implement a custom iterator for your custom data structure - methods boolean hasNext(), Object next() and void remove()
    @SuppressWarnings("all")
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = -1;
            boolean removed;

            public void remove() {
                MyImplementedList.this.remove(index);
                removed = true;
            }

            public boolean hasNext() {
                return (index < size() && elementData[index + 1] != null) || size == 1;
            }

            public E next() {
                if (!removed) {
                    index++;
                }
                removed = false;
                return ((E) elementData[index]);
            }
        };
    }

    //TODO m) implement a method, that uses a Comparator, for your data structure to sort the elements
    //TODO you should name it: void sort(Comparator<? super E> c)
    //TODO create a custom comparator that compares objects by their "what you want" :D - HINT Comparator interface
    @SuppressWarnings("all")
    public void sort(Comparator<? super E> c) {
        Arrays.sort(elementData, (Comparator<? super Object>) c);
    }
}
