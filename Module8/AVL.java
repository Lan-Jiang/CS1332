package Module8;

import java.util.NoSuchElementException;

/**
 * Your implementation of the AVL tree rotations.
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Cannot add data of null value to tree.");
        }
        if (root == null) {
            root = new AVLNode<T>(data);
            updateHeightBalanceFactor(root);
            size++;
        } else {
            root = add(data, root);
            size++;
        }
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     *    simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     *    replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     *    replace the data, NOT predecessor. As a reminder, rotations can occur
     *    after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If the data is null.
     * @throws java.util.NoSuchElementException   If the data is not found.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove 'null' data.");
        }
        if (!contains(data)) {
            throw new NoSuchElementException("Element does not exist in BST.");
        }
        T removed = get(data);
        root = removeNode(data, root);
        size--;
        return removed;

    }

    private AVLNode<T> removeNode(T data, AVLNode<T> current) {
        if (current == null) {
            return null;
        }
        int compare = data.compareTo(current.getData());
        if (compare < 0) {
            current.setLeft(removeNode(data, current.getLeft()));
        } else if (compare > 0) {
            current.setRight(removeNode(data, current.getRight()));
        } else {
            if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            } else {
                current.setData(getPredecessor(current.getLeft()));
                current.setLeft(removeNode(current.getData(), current.getLeft()));
            }
        }
        updateHeightAndBF(current);
        return balance(current);
    }

    private T getPredecessor(AVLNode<T> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getData();
    }

    /**
     * Updates the height and balance factor of a node using its
     * setter methods.
     *
     * Recall that a null node has a height of -1. If a node is not
     * null, then the height of that node will be its height instance
     * data. The height of a node is the max of its left child's height
     * and right child's height, plus one.
     *
     * The balance factor of a node is the height of its left child
     * minus the height of its right child.
     *
     * This method should run in O(1).
     * You may assume that the passed in node is not null.
     *
     * This method should only be called in rotateLeft(), rotateRight(),
     * and balance().
     *
     * @param currentNode The node to update the height and balance factor of.
     */
    public void updateHeightAndBF(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        int leftH = 0;
        int rightH = 0;

        if (currentNode.getLeft() == null)
        {
            leftH = -1;
        } else
        {
            leftH = currentNode.getLeft().getHeight();
        }
        if (currentNode.getRight() == null)
        {
            rightH = -1;
        } else
        {
            rightH = currentNode.getRight().getHeight();
        }

        currentNode.setHeight(Math.max(leftH, rightH) + 1);
        currentNode.setBalanceFactor(leftH - rightH);
    }

    /**
     * Method that rotates a current node to the left. After saving the
     * current's right node to a variable, the right node's left subtree will
     * become the current node's right subtree. The current node will become
     * the right node's left subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is right heavy. Therefore, you do not need to
     * perform any preliminary checks, rather, you can immediately perform a
     * left rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    public AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        AVLNode<T> right = currentNode.getRight();

        currentNode.setRight(right.getLeft());
        right.setLeft(currentNode);

        updateHeightAndBF(currentNode);
        updateHeightAndBF(right);

        return right;
    }

    /**
     * Method that rotates a current node to the right. After saving the
     * current's left node to a variable, the left node's right subtree will
     * become the current node's left subtree. The current node will become
     * the left node's right subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is left heavy. Therefore, you do not need to perform
     * any preliminary checks, rather, you can immediately perform a right
     * rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    public AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        AVLNode<T> left = currentNode.getLeft();

        currentNode.setLeft(left.getRight());
        left.setRight(currentNode);

        updateHeightAndBF(currentNode);
        updateHeightAndBF(left);

        return left;
    }

    /**
     * This is the overarching method that is used to balance a subtree
     * starting at the passed in node. This method will utilize
     * updateHeightAndBF(), rotateLeft(), and rotateRight() to balance
     * the subtree. In part 2 of this assignment, this balance() method
     * will be used in your add() and remove() methods.
     *
     * The height and balance factor of the current node is first recalculated.
     * Based on the balance factor, a no rotation, a single rotation, or a
     * double rotation takes place. The current node is returned.
     *
     * You may assume that the passed in node is not null. Therefore, you do
     * not need to perform any preliminary checks, rather, you can immediately
     * check to see if any rotations need to be performed.
     *
     * This method should run in O(1).
     *
     * @param cur The current node under inspection.
     * @return The AVLNode that the caller should return.
     */
    public AVLNode<T> balance(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        updateHeightAndBF(currentNode);

        if ( currentNode.getBalanceFactor() < -1)
        {
            if ( currentNode.getRight().getBalanceFactor() > 0)
            {
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            currentNode = rotateLeft(currentNode);
        } else if ( currentNode.getBalanceFactor() > 1)
        {
            if ( currentNode.getLeft().getBalanceFactor() < 0)
            {
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            currentNode = rotateRight(currentNode);
        }

        return currentNode;
    }
}
