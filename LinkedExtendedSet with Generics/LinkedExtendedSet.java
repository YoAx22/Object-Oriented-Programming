import java.util.Arrays;

public class LinkedExtendedSet<T> implements ExtendedSet<T> {

	private Node<T> head;
	private int size;
	private final int MAX_FREQUENCY;

	public LinkedExtendedSet(int Frequency) {
		this.head = null;
		this.size = 0;
		this.MAX_FREQUENCY = Frequency;
	}

	public LinkedExtendedSet() {
		this.head = null;
		size = 0;
		MAX_FREQUENCY = 1;
	}

	@Override
	public boolean add(T item) {
		Node<T> temp = new Node<T>(item);

		if (contains(item)) {
			if (getFrequency(item) >= MAX_FREQUENCY) {
				return false;
			} else {
				temp.next = head;
				head = temp;
				size++;
				return true;
			}
		} else {
			if (contains(item) == false && MAX_FREQUENCY == 0) {
				return false;
			}
			temp.next = head;
			head = temp;
			size++;
			return true;
		}
	}

	@Override
	public boolean remove(T item) {
		Node<T> foundAt = find(item);

		if (foundAt == null) {
			return false;
		}
		foundAt.data = head.data;
		head = head.next;
		size--;
		return true;
	}

	@Override
	public boolean removeAll(T item) {

		if (contains(item) == true) {
			while (contains(item)) {
				remove(item);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(T item) {
		return find(item) != null;
	}

	@Override
	public int getFrequency(T item) {
		int count = 0;
		Node<T> curr = head;

		while (curr != null) {
			if (curr.data.equals(item)) {
				count++;
			}
			curr = curr.next;
		}
		return count;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int uniqueCount() {
		Node<T> curr = head;
		Node<T> check = head;
		int count = size;

		while (curr != null) {
			while (!check.equals(curr)) {
				if (curr.data.equals(check.data)) {
					count--;
					break;
				}
				check = check.next;
			}
			curr = curr.next;
		}

		return count;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int maxFrequency() {
		return this.MAX_FREQUENCY;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		Node<T> curr = head;

		for (int i = 0; i < size; i++) {
			result[i] = curr.data;
			curr = curr.next;
		}
		return result;

	}

	@Override
	public Object[] toUniqueArray() {
		Node<T> curr = head;
		ExtendedSet<T> exSet = new LinkedExtendedSet<>(1);

		while (curr != null) {
			exSet.add(curr.data);
			curr = curr.next;
		}
		return exSet.toArray();
	}

	@Override
	public Object[] toSortedArray() {
		Object[] result = toArray();
		Arrays.sort(result);
		return result;
	}

	/**
	 * A private inner Node class that uses generics.
	 * 
	 */
	private class Node<E> {
		private E data;
		private Node<E> next;

		public Node(E data) {
			this.data = data;
			this.next = null;

		}
	}

	/**
	 * This helper method looks for the specified item
	 * 
	 * @param item to be found
	 * @return the node where the item is found
	 */
	private Node<T> find(T item) {
		Node<T> curr = head;

		while (curr != null) {
			if (curr.data.equals(item)) {
				return curr;
			}
			curr = curr.next;
		}
		return null;
	}

}
