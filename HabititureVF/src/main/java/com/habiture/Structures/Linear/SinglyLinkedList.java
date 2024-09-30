package com.habiture.Structures.Linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element, null);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            private Node<T> prev = null;
            private Node<T> next = null;

            public boolean hasNext() {
                return current != null;
            }

            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.getKey();
                prev = current;
                current = current.getNext();
                next = (current != null) ? current.getNext() : null;
                return data;
            }

            public void remove() {
                if (prev == null) {
                    throw new IllegalStateException();
                }
                if (prev == head) {
                    head = next;
                } else {
                    Node<T> node = head;
                    while (node.getNext() != prev) {
                        node = node.getNext();
                    }
                    node.setNext(next);
                }
                prev = null;
                size--;
            }
        };
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(" ");
        }
        return sb.toString();
    }

    private static class Node<T> {
        private T key;
        private Node<T> next;

        public Node(T key, Node<T> next) {
            this.key = key;
            this.next = next;
        }

        public T getKey() {
            return key;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}

