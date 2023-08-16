import java.util.NoSuchElementException;

public class LinkedDeque<T> implements DequeInterface<T> {
	private Node<T> firstNode;

	public LinkedDeque() {
		firstNode = null;
	}

	@Override
	public void addToFront(T item) {
		Node<T> temp = new Node<>(item);

		if (firstNode == null) {
			firstNode = temp;
			firstNode.next = firstNode.previous = firstNode;
		} else {
			temp.next = firstNode;
			temp.previous = firstNode.previous;
			firstNode.previous.next = temp;
			firstNode.previous = temp;
			firstNode = temp;
		}
	}

	@Override
	public void addToBack(T item) {
		Node<T> temp = new Node<>(item);

		if (firstNode == null) {
			firstNode = temp;
			firstNode.next = firstNode.previous = firstNode;
		} else {
			temp.next = firstNode;
			temp.previous = firstNode.previous;
			firstNode.previous.next = temp;
			firstNode.previous = temp;

		}

	}

	@Override
	public T removeFront() {
		if (firstNode == null) {
			throw new NoSuchElementException("Queue is empty");
		}
		T item = firstNode.data;
		firstNode.previous.next = firstNode.next;
		firstNode.next.previous = firstNode.previous;
		firstNode = firstNode.next;
		return item;
	}

	@Override
	public T removeBack() {
		if (firstNode == null) {
			throw new NoSuchElementException("Queue is empty");
		}
		T item = firstNode.previous.data;
		firstNode.previous = firstNode.previous.previous;
		firstNode.previous.next = firstNode;

		return item;
	}

	@Override
	public T getFront() {
		if (firstNode == null) {
			throw new NoSuchElementException("Queue is empty");
		}

		return firstNode.data;
	}

	@Override
	public T getBack() {
		if (firstNode == null) {
			throw new NoSuchElementException("Queue is empty");
		}
		return firstNode.previous.data;
	}

	@Override
	public boolean isEmpty() {
		return firstNode == null;
	}

	@Override
	public void clear() {
		firstNode = null;
	}

	@Override
	public String toString() {

		String result = "FRONT-TO-BACK: [";
		Node<T> curr = firstNode;

		if (curr != null) {
			result = "FRONT-TO-BACK: [" + curr.data + " ";
			curr = curr.next;
		}
		while (curr != firstNode) {
			result += curr.data + " ";
			curr = curr.next;
		}
		result = result.trim() + "], ";

		String s = "BACK-TO-FRONT: [";
		if (curr != null) {
			curr = firstNode.previous;
			s = "BACK-TO-FRONT: [" + curr.data + " ";
			curr = curr.previous;

			while (curr != firstNode.previous) {
				s += curr.data + " ";
				curr = curr.previous;
			}
		}
		s = s.trim() + "]";

		return result + s;
	}

	private class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> previous;

		public Node(E data) {
			this.data = data;
			this.next = null;
			this.previous = null;
		}
	}

}
