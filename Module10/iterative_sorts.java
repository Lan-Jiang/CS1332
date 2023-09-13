import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr == null || comparator == null)
            {
                throw new IllegalArgumentException("ERROR : Null array or comparator");
            }

        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swapped = true;
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr == null || comparator == null)
            {
                throw new IllegalArgumentException("ERROR : Null array or comparator");
            }
        for (int i = 0; i < arr.length - 1; i++)
            {
                int min = i;
                for (int j = i + 1; j < arr.length; j++)
                {
                    if (comparator.compare(arr[j], arr[min]) < 0)
                    {
                        min = j;
                    }
                }
                swap(arr, min, i);
            }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr == null || comparator == null)
            {
                throw new IllegalArgumentException("ERROR : Null array or comparator");
            }
        for (int i = 1; i < arr.length; i++)
            {
                T tmp = arr[i];
                int j = i - 1;
                while (j >= 0 && comparator.compare(arr[j], tmp) > 0)
                {
                    arr[j + 1] = arr[j--];
                }
                arr[j + 1] = tmp;
        }
    }

    private static <T > void swap (T[]arr,int a, int b)
        {
            T tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }
}
