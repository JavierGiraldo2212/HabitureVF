package com.habiture.Structures.Linear;

public class DoubleLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;

    public DoubleLinkedList() {
        head = tail = null;
    }

    public boolean empty() {
        return head == null;
    }

    public E getHeadElement() {
        return empty() ? null : head.item;
    }

    public E getTailElement() {
        return empty() ? null : tail.item;
    }

    public void add(E element) {
        Node<E> newNode = new Node<>(element, null, tail);

        if (empty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
    }

    public void addAfter(E target, E element) {
        Node<E> current = head;

        while (current != null && !current.item.equals(target)) {
            current = current.next;
        }

        if (current != null) {
            Node<E> newNode = new Node<>(element, current.next, current);
            if (current.next != null) {
                current.next.prev = newNode;
            }
            current.next = newNode;

            if (current == tail) {
                tail = newNode;
            }
        }
    }

    public void addBefore(E target, E element) {
        Node<E> current = head;

        while (current != null && !current.item.equals(target)) {
            current = current.next;
        }

        if (current != null) {
            Node<E> newNode = new Node<>(element, current, current.prev);
            if (current.prev != null) {
                current.prev.next = newNode;
            } else {
                head = newNode;
            }
            current.prev = newNode;
        }
    }

    public E remove() {
        if (empty()) {
            return null;
        }
        E element = head.item;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        return element;
    }

    public E removeLast() {
        if (empty()) {
            return null;
        }
        E element = tail.item;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        return element;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}

