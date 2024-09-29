package com.habiture.Structures.NonLinear;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;

public class GenericMaxHeap<T> {
    private int maxSize;
    private int size;
    private T[] H;
    private Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public GenericMaxHeap(int N, Comparator<T> comparator) {
        H = (T[]) new Object[N];
        size = 0;
        maxSize = N;
        this.comparator = comparator;
    }

    public GenericMaxHeap(Comparator<T> comparator) {
        this(16, comparator);
    }

    public int getSize() {
        return size;
    }

    private void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    public T getMax() {
        if (size == 0)
            throw new IllegalStateException("Heap is empty");
        return H[0];
    }

    private void siftUp(int i) {
        while (i > 0 && comparator.compare(H[parent(i)], H[i]) < 0) {
            swap(H, parent(i), i);
            i = parent(i);
        }
    }

    private void siftDown(int i) {
        int maxIndex = i;
        int l = leftChild(i);
        int r = rightChild(i);

        if (l < size && comparator.compare(H[l], H[maxIndex]) > 0) {
            maxIndex = l;
        }
        if (r < size && comparator.compare(H[r], H[maxIndex]) > 0) {
            maxIndex = r;
        }

        if (i != maxIndex) {
            swap(H, i, maxIndex);
            siftDown(maxIndex);
        }
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == maxSize) {
            T[] newH = (T[]) new Object[maxSize * 2];
            System.arraycopy(H, 0, newH, 0, maxSize);
            H = newH;
            maxSize *= 2;
        }
    }

    public void insert(T key) {
        ensureCapacity();
        H[size] = key;
        siftUp(size);
        size++;
    }

    public T extractMax() {
        if (size == 0)
            throw new IllegalStateException("Heap is empty");
        T max = H[0];
        H[0] = H[size - 1];
        size--;
        siftDown(0);
        return max;
    }

    public void remove(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        H[i] = H[size - 1];
        size--;

        if (i > 0 && comparator.compare(H[i], H[parent(i)]) > 0) {
            siftUp(i);
        } else {
            siftDown(i);
        }
    }

    public void changePriority(int i, T key) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        T oldKey = H[i];
        H[i] = key;
        if (comparator.compare(key, oldKey) > 0) {
            siftUp(i);
        } else {
            siftDown(i);
        }
    }

    public void printHeap() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return;
        }
        System.out.println("HEAP:");
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);

        int level = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Level " + level + ": ");

            for (int i = 0; i < levelSize; i++) {
                int nodeIndex = queue.poll();
                System.out.print(H[nodeIndex] + " ");

                int leftChild = leftChild(nodeIndex);
                int rightChild = rightChild(nodeIndex);

                if (leftChild < size) {
                    queue.add(leftChild);
                }
                if (rightChild < size) {
                    queue.add(rightChild);
                }
            }
            System.out.println();
            level++;
        }
    }
}