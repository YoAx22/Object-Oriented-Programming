
public class DequeQueue<T> implements QueueInterface<T> {
	
	private LinkedDeque<T> deque;
	
	public DequeQueue() {
		this.deque = null;
	}

	@Override
	public void add(T newEntry) {
		deque.addToBack(newEntry);
	}

	@Override
	public T remove() {
		return deque.removeFront();
	}

	@Override
	public T peek() {
		return deque.getFront();
	}

	@Override
	public boolean isEmpty() {
		return deque.isEmpty();
	}

	@Override
	public void clear() {
		deque.clear();
	}

}
