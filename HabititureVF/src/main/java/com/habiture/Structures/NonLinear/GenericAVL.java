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
        }

        // Manejo de duplicados: puedes decidir qué hacer
        int cmp = comparator.compare(node.data, root.data);
        if (cmp < 0) {
            root.left = insertHelper(root.left, node);
        } else if (cmp > 0) {
            root.right = insertHelper(root.right, node);
        } else {
            // Duplicado encontrado, no se hace nada
            return root; // O puedes optar por actualizar el nodo
        }

        // Actualizar altura
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Balancear el nodo
        return balanceNode(root);
    }

    public void remove(T data) {
        root = removeHelper(root, data);
    }

    private Node<T> removeHelper(Node<T> root, T data) {
        if (root == null) {
            return null;
        }

        int cmp = comparator.compare(data, root.data);
        if (cmp < 0) {
            root.left = removeHelper(root.left, data);
        } else if (cmp > 0) {
            root.right = removeHelper(root.right, data);
        } else {
            // Nodo a eliminar encontrado
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

        // Actualizar altura
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Balancear el nodo
        return balanceNode(root);
    }

    private Node<T> balanceNode(Node<T> root) {
        int balanceFactor = getBalanceFactor(root);

        // Caso izquierdo pesado
        if (balanceFactor > 1) {
            if (getBalanceFactor(root.left) < 0) {
                root.left = leftRotate(root.left);
            }
            return rightRotate(root);
        }

        // Caso derecho pesado
        if (balanceFactor < -1) {
            if (getBalanceFactor(root.right) > 0) {
                root.right = rightRotate(root.right);
            }
            return leftRotate(root);
        }

        return root;
    }

    private Node<T> rightRotate(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        // Realizar rotación
        x.right = y;
        y.left = T2;

        // Actualizar alturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Devolver nueva raíz
        return x;
    }

    private Node<T> leftRotate(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        // Realizar rotación
        y.left = x;
        x.right = T2;

        // Actualizar alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Devolver nueva raíz
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
        if (node.right == null) {
            return null; // Manejo de caso nulo
        }
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    // Métodos adicionales
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
        }
        int cmp = comparator.compare(root.data, data);
        if (cmp == 0) {
            return true;
        } else if (cmp > 0) {
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

    public List<T> getSortedElements() {
        List<T> sortedList = new ArrayList<>();
        inOrderTraversal(root, sortedList);
        return sortedList;
    }

    private void inOrderTraversal(Node<T> node, List<T> sortedList) {
        if (node != null) {
            inOrderTraversal(node.left, sortedList);
            sortedList.add(node.data);
            inOrderTraversal(node.right, sortedList);
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
