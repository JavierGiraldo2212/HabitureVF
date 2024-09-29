package com.habiture.Structures.NonLinear;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class GenericAVL<T> {
    private Node<T> root;
    private Comparator<T> comparator;

    public GenericAVL(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public void insert(T data) {
        root = insertHelper(root, new Node<>(data));
    }

    private Node<T> insertHelper(Node<T> root, Node<T> node) {
        if (root == null) {
            return node;
        } else if (comparator.compare(node.data, root.data) < 0) {
            root.left = insertHelper(root.left, node);
        } else {
            root.right = insertHelper(root.right, node);
        }

        // Update height of this ancestor node
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Balance the node if needed
        return balanceNode(root, node.data);
    }

    public void remove(T data) {
        root = removeHelper(root, data);
    }

    private Node<T> removeHelper(Node<T> root, T data) {
        if (root == null) {
            return null;
        }

        if (comparator.compare(data, root.data) < 0) {
            root.left = removeHelper(root.left, data);
        } else if (comparator.compare(data, root.data) > 0) {
            root.right = removeHelper(root.right, data);
        } else {
            // Node to be deleted found
            if (root.left == null || root.right == null) {
                root = (root.left != null) ? root.left : root.right;
            } else {
                T successorData = successor(root);
                root.data = successorData;
                root.right = removeHelper(root.right, successorData);
            }
        }

        if (root == null) {
            return null;
        }

        // Update height
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Balance the node
        return balanceNode(root, data);
    }

    private Node<T> balanceNode(Node<T> root, T data) {
        int balanceFactor = getBalanceFactor(root);

        // Left heavy case
        if (balanceFactor > 1) {
            if (comparator.compare(data, root.left.data) < 0) {
                return rightRotate(root);
            } else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }

        // Right heavy case
        if (balanceFactor < -1) {
            if (comparator.compare(data, root.right.data) > 0) {
                return leftRotate(root);
            } else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }

        return root;
    }

    private Node<T> rightRotate(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    private Node<T> leftRotate(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    private int getBalanceFactor(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private int height(Node<T> node) {
        return node == null ? 0 : node.height;
    }

    private T successor(Node<T> node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    // Additional utility methods
    public void display() {
        displayHelper(root);
    }

    private void displayHelper(Node<T> node) {
        if (node != null) {
            displayHelper(node.left);
            System.out.println(node.data + " ");
            displayHelper(node.right);
        }
    }

    public boolean search(T data) {
        return searchHelper(root, data);
    }

    private boolean searchHelper(Node<T> root, T data) {
        if (root == null) {
            return false;
        } else if (comparator.compare(root.data, data) == 0) {
            return true;
        } else if (comparator.compare(root.data, data) > 0) {
            return searchHelper(root.left, data);
        } else {
            return searchHelper(root.right, data);
        }
    }

    public List<T> findInRange(T min, T max) {
        List<T> result = new ArrayList<>();
        findInRangeHelper(root, min, max, result);
        return result;
    }

    private void findInRangeHelper(Node<T> node, T min, T max, List<T> result) {
        if (node == null) {
            return;
        }

        if (comparator.compare(node.data, min) >= 0 && comparator.compare(node.data, max) <= 0) {
            findInRangeHelper(node.left, min, max, result);
            result.add(node.data);
            findInRangeHelper(node.right, min, max, result);
        } else if (comparator.compare(node.data, min) < 0) {
            findInRangeHelper(node.right, min, max, result);
        } else {
            findInRangeHelper(node.left, min, max, result);
        }
    }

    private static class Node<T> {

        T data;
        Node<T> left;
        Node<T> right;
        int height;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }
}
