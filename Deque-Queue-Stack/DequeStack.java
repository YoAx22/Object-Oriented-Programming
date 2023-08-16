
public class DequeStack<T> implements StackInterface<T> {
	
	private LinkedDeque<T> deque;
	
	public DequeStack() {
		deque = new LinkedDeque<>();
	}
	
	@Override
	public void push(T item) {
		 deque.addToFront(item);
	}

	@Override
	public T pop() {
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
