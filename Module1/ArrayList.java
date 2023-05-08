import java.util.NoSuchElementException;

import javafx.scene.chart.PieChart.Data;

/**
 * Your implementation of an ArrayList.
 */
public class ArrayList<T> {

    /*
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new ArrayList.
     * 
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the front of the list.
     *
     * This add may require elements to be shifted.
     *
     * Method should run in O(n) time. 
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // Move all elements one position to the right
        // check if size > 9
        // if size > 9, create new array with size * 2
        // all element move to the i+1 location
        // add data to the first location
        if (data == null) {
            throw new IllegalArgumentException("Error: some exception was thrown");
        } else {
            if (backingArray.length == size) {
                T[] newArray = (T[]) new Object[backingArray.length * 2];
                for (int i = 0; i < backingArray.length; i++) {
                    newArray[i + 1] = backingArray[i];
                }
                newArray[0] = data;
                backingArray = newArray;
                size++;
            } else {
                for (int i = 0; i < backingArray.length; i++) {
                    backingArray[i + 1] = backingArray[i];
                }
                backingArray[0] = data;
                size++;

            }
        }


    }

    /**
     * Adds the data to the back of the list.
     *
     * Method should run in amortized O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // 1. check if data is null
        // 2.check if size == length
        // 3. if size == length, create new array with size * 2
        // 4. add data to the last location
        if (data == null) {
            throw new IllegalArgumentException("Error: some exception was thrown");
        } else {
            if (backingArray.length == size) {
                T[] newArray = (T[]) new Object[backingArray.length * 2];    // create new array with size * 2           
                for (int i = 0; i < backingArray.length; i++) { 
                    newArray[i] = backingArray[i]; // copy all element to new array
                }
                newArray[size] = data; // add data to the last location
                backingArray = newArray; // assign new array to backingArray
                size++; // increment size
            } else {
                backingArray[size] = data; // add data to the last location
                size++; // increment size
            }
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Do not shrink the backing array.
     *
     * This remove may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // 1. if data is null, return error
        // 2. set the first element to null
        // 3. move all element to the left
        // 4. decrement size
        if (size == 0) {
            throw new IllegalArgumentException("Error: some exception was thrown");
        } else {
            T removedData = backingArray[0];
            for (int i = 0; i < backingArray.length - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            size--;
            return removedData;
        }


    }

    /**
     * Removes and returns the last data of the list.
     *
     * Do not shrink the backing array.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // 1.if size == 0, return error
        // 2.remove the last element
        // 3.decrement size
        // 4.return last element
        if (size == 0 ){
            throw new IllegalArgumentException("Error: some exception was thrown");
        } else {
            T removedData = backingArray[size - 1];
            backingArray[size - 1] = null;
            size--;
            return removedData;
        }
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
