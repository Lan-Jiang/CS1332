package Module5;

import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        if (data == null)
        {
            throw new IllegalArgumentException("ERROR : Data can't be null");
        }
        if (root == null)
        {
            root = new BSTNode<>(data);
            size++;
        } else
        {
            addHelper(data, root);
        }
    }

    private void addHelper(T data, BSTNode<T> node) {
        if (data.compareTo(node.getData()) > 0)
        {
            if (node.getRight() == null)
            {
                node.setRight(new BSTNode<>(data));
                size++;
            } else
            {
                addHelper(data, node.getRight());
            }
        } else if (data.compareTo(node.getData()) < 0)
        {
            if (node.getLeft() == null)
            {
                node.setLeft(new BSTNode<>(data));
                size++;
            } else {
                addHelper(data, node.getLeft());
            }
        }
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null){
         throw new IllegalArgumentException("data is null");
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        root = find(root, data, dummy);
        return dummy.getData();
    }

    private BSTNode<T> find(BSTNode<T> cur, T data, BSTNode<T> dummy) {
        if (cur == null) {
            // Data not found case
            throw new NoSuchElementException("Data not found in BST.");
        } else if (data.compareTo(cur.getData()) < 0){
            cur.setLeft(find(cur.getLeft(), data, dummy));
        } else if (data.compareTo(cur.getData()) > 0){
            cur.setRight(find(cur.getRight(), data, dummy));
        } else if (data.compareTo(cur.getData()) == 0){
            // found node in BST
            // dummy = cur; <- this wont work
            dummy.setData(cur.getData());
            size--;
            // case 1: remove a leaf
            if (cur.getLeft() == null && cur.getRight() == null){
                return null;
            }
            // case 2: remove a node with 1 child
            else if (cur.getLeft() != null && cur.getRight() == null) {
                cur = cur.getLeft();
            }else if (cur.getRight() != null && cur.getLeft() == null) {
                cur = cur.getRight();
            }
            // case 3: remove a node with 2 children
            else{
                BSTNode<T> temp = new BSTNode<>(null);
                cur.setRight(successor(cur.getRight(), temp));
                cur.setData(temp.getData());
            }
        }
        return cur;
    }

    private BSTNode<T> successor(BSTNode<T> cur, BSTNode<T> temp) {
        if (cur.getLeft() == null){
            temp.setData(cur.getData());
            return cur.getRight();

        } else {
           cur.setLeft(successor(cur.getLeft(), temp));
           return cur;
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}


// Some remove() examples
// Tests Passed: 21 / 32

// [Test Failure: remove] [-0.31] : Unexpected content after removing 1 from the tree.

// +----------------+
// | Initial Tree:  |
// |                |
// |   1            |
// |  /   \         |
// | 0     2        |
// |                |
// |                |
// | Expected Tree: |
// |                |
// |   2            |
// |  /             |
// | 0              |
// |                |
// |                |
// | Actual Tree:   |
// |                |
// |   0            |
// |      \         |
// |       2        |
// +----------------+


// [Test Failure: remove] [-0.31] : Unexpected content after removing 1 from the tree.

// +---------------------------------+
// | Initial Tree:                   |
// |                                 |
// |               1                 |
// |        /               \        |
// |       0                 4       |
// |                    /            |
// |                   3             |
// |                  /              |
// |                 2               |
// |                                 |
// |                                 |
// | Expected Tree:                  |
// |                                 |
// |       2                         |
// |    /       \                    |
// |   0         4                   |
// |          /                      |
// |         3                       |
// |                                 |
// |                                 |
// | Actual Tree:                    |
// |                                 |
// |               0                 |
// |                        \        |
// |                         4       |
// |                    /            |
// |                   3             |
// |                  /              |
// |                 2               |
// +---------------------------------+


// [Test Failure: remove] [-0.31] : Unexpected content after removing 1 from the tree.

// +-----------------+
// | Initial Tree:   |
// |                 |
// |       1         |
// |    /       \    |
// |   0         2   |
// |              \  |
// |               3 |
// |                 |
// |                 |
// | Expected Tree:  |
// |                 |
// |   2             |
// |  /   \          |
// | 0     3         |
// |                 |
// |                 |
// | Actual Tree:    |
// |                 |
// |       0         |
// |            \    |
// |             2   |
// |              \  |
// |               3 |
// +-----------------+


// [Test Failure: remove] [-0.31] : Unexpected content after removing 1 from the tree.

// +-----------------------------------------------------------------+
// | Initial Tree:                                                   |
// |                                                                 |
// |                               1                                 |
// |                /                               \                |
// |               0                                 5               |
// |                                        /                        |
// |                                       4                         |
// |                                    /                            |
// |                                   2                             |
// |                                      \                          |
// |                                       3                         |
// |                                                                 |
// |                                                                 |
// | Expected Tree:                                                  |
// |                                                                 |
// |               2                                                 |
// |        /               \                                        |
// |       0                 5                                       |
// |                    /                                            |
// |                   4                                             |
// |                  /                                              |
// |                 3                                               |
// |                                                                 |
// |                                                                 |
// | Actual Tree:                                                    |
// |                                                                 |
// |                               0                                 |
// |                                                \                |
// |                                                 5               |
// |                                        /                        |
// |                                       4                         |
// |                                    /                            |
// |                                   2                             |
// |                                      \                          |
// |                                       3                         |
// +-----------------------------------------------------------------+


// [Test Failure: remove] [-0.31] : Unexpected content after removing 4 from the tree.

// +---------------------------------+
// | Initial Tree:                   |
// |                                 |
// |               6                 |
// |        /               \        |
// |       2                 7       |
// |    /       \                    |
// |   1         4                   |
// |  /       /   \                  |
// | 0       3     5                 |
// |                                 |
// |                                 |
// | Expected Tree:                  |
// |                                 |
// |               6                 |
// |        /               \        |
// |       2                 7       |
// |    /       \                    |
// |   1         5                   |
// |  /       /                      |
// | 0       3                       |
// |                                 |
// |                                 |
// | Actual Tree:                    |
// |                                 |
// |               6                 |
// |        /               \        |
// |       2                 7       |
// |    /       \                    |
// |   1         3                   |
// |  /           \                  |
// | 0             5                 |
// +---------------------------------+


// [Test Failure: remove] [-0.31] : Unexpected content after removing 2 from the tree.

// +---------------------------------+
// | Initial Tree:                   |
// |                                 |
// |               6                 |
// |        /               \        |
// |       2                 7       |
// |    /       \                    |
// |   1         4                   |
// |  /       /   \                  |
// | 0       3     5                 |
// |                                 |
// |                                 |
// | Expected Tree:                  |
// |                                 |
// |               6                 |
// |        /               \        |
// |       3                 7       |
// |    /       \                    |
// |   1         4                   |
// |  /           \                  |
// | 0             5                 |
// |                                 |
// |                                 |
// | Actual Tree:                    |
// |                                 |
// |               6                 |
// |        /               \        |
// |       1                 7       |
// |    /       \                    |
// |   0         4                   |
// |          /   \                  |
// |         3     5                 |
// +---------------------------------+


// [Test Failure: remove] [-0.31] : Unexpected content after removing 2 from the tree.

// +---------------------------------+
// | Initial Tree:                   |
// |                                 |
// |               5                 |
// |        /               \        |
// |       2                 6       |
// |    /       \                    |
// |   1         3                   |
// |  /           \                  |
// | 0             4                 |
// |                                 |
// |                                 |
// | Expected Tree:                  |
// |                                 |
// |               5                 |
// |        /               \        |
// |       3                 6       |
// |    /       \                    |
// |   1         4                   |
// |  /                              |
// | 0                               |
// |                                 |
// |                                 |
// | Actual Tree:                    |
// |                                 |
// |               5                 |
// |        /               \        |
// |       1                 6       |
// |    /       \                    |
// |   0         3                   |
// |              \                  |
// |               4                 |
// +---------------------------------+


// [Test Failure: remove] [-0.31] : Unexpected content after removing 2 from the tree.

// +-----------------------------------------------------------------+
// | Initial Tree:                                                   |
// |                                                                 |
// |                               6                                 |
// |                /                               \                |
// |               2                                 7               |
// |        /               \                                        |
// |       1                 5                                       |
// |    /               /                                            |
// |   0               3                                             |
// |                      \                                          |
// |                       4                                         |
// |                                                                 |
// |                                                                 |
// | Expected Tree:                                                  |
// |                                                                 |
// |               6                                                 |
// |        /               \                                        |
// |       3                 7                                       |
// |    /       \                                                    |
// |   1         5                                                   |
// |  /       /                                                      |
// | 0       4                                                       |
// |                                                                 |
// |                                                                 |
// | Actual Tree:                                                    |
// |                                                                 |
// |                               6                                 |
// |                /                               \                |
// |               1                                 7               |
// |        /               \                                        |
// |       0                 5                                       |
// |                    /                                            |
// |                   3                                             |
// |                      \                                          |
// |                       4                                         |
// +-----------------------------------------------------------------+

