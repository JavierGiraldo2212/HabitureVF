package com.habiture.Structures.Linear;

public class LinkedList<E> {

	private Node<E> head;
	private Node<E> tail;

	public LinkedList() {
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
		Node<E> newnode = new Node<>(element, null);

		if (head == null)
			head = newnode;
		else
			tail.next = newnode;

		tail = newnode;
	}

	public void addAfter(E target, E element) {
		Node<E> current = head;
		while (current != null && !current.item.equals(target)) {
			current = current.next;
		}
		if (current != null) {
			Node<E> newNode = new Node<>(element, current.next);
			current.next = newNode;
			if (current == tail) {
				tail = newNode;
			}
		}
	}

	public void addBefore(E target, E element) {
		if (head == null)
			return;

		if (head.item.equals(target)) {
			Node<E> newNode = new Node<>(element, head);
			head = newNode;
			return;
		}

		Node<E> current = head;
		while (current.next != null && !current.next.item.equals(target)) {
			current = current.next;
		}
		if (current.next != null) {
			Node<E> newNode = new Node<>(element, current.next);
			current.next = newNode;
		}
	}

	public E remove() {
		if (empty())
			return null;
		E Element = head.item;
		head = head.next;
		if (empty())
			tail = null;
		return Element;
	}

	private static class Node<E> {
		E item;
		Node<E> next;

		Node(E element, Node<E> next) {
			this.item = element;
			this.next = next;
		}
	}

}
