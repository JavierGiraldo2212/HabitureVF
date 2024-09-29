package com.habiture.Structures.NonLinear;

import java.util.Comparator;

public class GenericBinarySearchTree<T> {
    private Node<T> root;
    private Comparator<T> comparator;

    public GenericBinarySearchTree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public void insert(T data) {
        root = insertHelper(root, new Node<>(data));
    }

    private Node<T> insertHelper(Node<T> root, Node<T> node) {
        if (root == null) {
            root = node;
            return root;
        } else if (comparator.compare(node.data, root.data) < 0) {
            root.left = insertHelper(root.left, node);
        } else {
            root.right = insertHelper(root.right, node);
        }
        return root;
    }

    public void display() {
        displayHelper(root);
    }

    private void displayHelper(Node<T> root) {
        if (root != null) {
            displayHelper(root.left);
            System.out.println(root.data + " ");
            displayHelper(root.right);
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

    public void remove(T data) {
        if (search(data))
            root = removeHelper(root, data);
        else
            System.out.println(data + "  Not in the tree");
    }

    private Node<T> removeHelper(Node<T> root, T data) {
        if (root == null) {
            return root;
        } else if (comparator.compare(data, root.data) < 0) {
            root.left = removeHelper(root.left, data);
        } else if (comparator.compare(data, root.data) > 0) {
            root.right = removeHelper(root.right, data);
        } else {
            if (root.right == null && root.left == null) {
                root = null;
            } else if (root.right != null) {
                root.data = successor(root);
                root.right = removeHelper(root.right, root.data);
            } else {
                root.data = predecessor(root);
                root.left = removeHelper(root.left, root.data);
            }
        }
        return root;
    }

    private T successor(Node<T> root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    private T predecessor(Node<T> root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }

    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
            left = right = null;
        }
    }
}