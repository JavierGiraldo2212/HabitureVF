package com.habiture.Structures.Linear;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack<E> {
    private E[] vector;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public Stack() {
        vector = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public E push(E item) {
        if (size == vector.length) {
            resize();
        }
        vector[size++] = item;
        return item;
    }

    public E pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        E item = vector[--size];
        vector[size] = null; // Clear the reference for garbage collection
        return item;
    }

    public E peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return vector[size - 1];
    }

    public boolean empty() {
        return size == 0;
    }

    public int search(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (vector[i].equals(o)) {
                return size - i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        E[] newVector = (E[]) new Object[vector.length * 2];
        System.arraycopy(vector, 0, newVector, 0, vector.length);
        vector = newVector;
    }
    
    public List<E> toList() {
        List<E> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(vector[i]);
        }
        return list;
    }
}
