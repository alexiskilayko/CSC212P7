package edu.smith.cs.csc212.adtr.real;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.errors.BadIndexError;

public class DoublyLinkedList<T> extends ListADT<T> {
	private Node<T> start;
	private Node<T> end;
	
	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}
	

	@Override
	public T removeFront() {
		checkNotEmpty();
		T removed = this.start.value;
		if (start.after == null) {
			start = end = null;
		} else {
			this.start = this.start.after;
			this.start.before = null;
		}
		return removed;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		T removed = this.end.value;
		if (end.before == null) {
			start = end = null;
		} else {
			this.end = this.end.before;
			this.end.after = null;
		}
		return removed;
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		if (index == 0) {
			return removeFront();
		}
		if (index == size()-1) {
			return removeBack();
		}
		int at = 0;
		for (Node<T> current = this.start; current != null; current = current.after) {
			if (at == index) {
				Node<T> removed = current;
				Node<T> b = current.before;
				Node<T> d = current.after;
				b.after = d;
				d.before = b;
				return removed.value;
			}
			at++;
		}
		throw new BadIndexError(index);
	}

	@Override
	public void addFront(T item) {
		if (start == null) {
			start = end = new Node<T>(item);
		} else {
			Node<T> secondFirst = start;
			start = new Node<T>(item);
			start.after = secondFirst;
			secondFirst.before = start;
		}
	}

	@Override
	public void addBack(T item) {
		if (end == null) {
			start = end = new Node<T>(item);
		} else {
			Node<T> secondLast = end;
			end = new Node<T>(item);
			end.before = secondLast;
			secondLast.after = end;
		}
	}

	@Override
	public void addIndex(int index, T item) {
		checkNotEmpty();
		if (index == 0) {
			this.addFront(item);
			return;
		} 
		if (index == size()) {
			this.addBack(item);
			return;
		} 
		int at = 0;
		for (Node<T> current = this.start; current != null; current = current.after) {
			if (at == index) {
				Node<T> insert = new Node<T>(item);
				Node<T> b = current.before;
				Node<T> c = current;
				insert.before = b;
				insert.after = c;
				b.after = insert;
				c.before = insert;
				return;
			}
			at++;
		}
		throw new BadIndexError(index);
		//throw new TODOErr();
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		return start.value;
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		return end.value;
	}
	
	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at == index) {
				return n.value;
			}
			at++;
		}
		throw new BadIndexError(index);
	}
	
	public void setIndex(int index, T value) {
		checkNotEmpty();
		if (index == 0) {
			start.value = value;
		}
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at == index) {
				n.value = value;
				return;
			}
			at++;
		}
		throw new BadIndexError(index);
	}

	@Override
	public int size() {		
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (count > 100_000) {
				throw new RuntimeException("Too many nodes!");
			}
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		
		static int nodesUsed = 0;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			if (nodesUsed++ > 100_000)
				throw new RuntimeException("Too many nodes!");
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}
