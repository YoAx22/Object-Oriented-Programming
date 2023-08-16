import java.util.Arrays;

public class ResizableArraySet implements SetInterface {

	private int size;
	private String[] words;
	public static final int DEFAULT_CAPACITY = 10;

	public ResizableArraySet() {
		this(DEFAULT_CAPACITY);
	}

	public ResizableArraySet(int capacity) {
		words = new String[capacity]; // check back
		size = 0;
	}

	public ResizableArraySet(String[] words) {
		this(DEFAULT_CAPACITY);
		for (String s : words) {
			add(s);
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean add(String s) {
		int foundAt = indexOf(s);

		if (foundAt != -1) {
			return false;
		} else {
			if (isFull() == true) {
				resize();
			}
			words[size++] = s;
			return true;
		}

	}

	@Override
	public String remove() {
		if (size == 0) {
			return null;
		} else {
			String result = words[size - 1];
			words[size - 1] = null;
			size--;
			return result;
		}
	}

	@Override
	public boolean remove(String s) {
		int foundAt = indexOf(s);
		if (foundAt == -1) {
			return false;
		}
		words[foundAt] = words[size - 1];
		words[size - 1] = null;
		size--;
		return true;
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			words[i] = null;
		}
		size = 0;
	}

	@Override
	public boolean contains(String s) {
		return indexOf(s) != -1;
	}

	@Override
	public String[] toArray() {
		String[] result = new String[size];
		for (int i = 0; i < size; i++) {
			result[i] = words[i];
		}
		return result;
	}

	/*
	 * Returns a space-separated list of words in the bag.
	 */
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < size; i++) {
			result += words[i] + " ";
		}
		return result.trim();
	}

	/*
	 * Returns a new ResizableArraySet containing all the elements that are
	 * contained in both this set and the other set.
	 * 
	 * If there are no common elements, return an empty ResizableArraySet
	 * 
	 * @param - other The ResizableArraySet with other elements
	 * 
	 * @return - arraySet The ResizableArraySet containing the result of the method
	 */
	public ResizableArraySet intersection(ResizableArraySet other) {
		ResizableArraySet arraySet = new ResizableArraySet();
		String s = "";

		for (int i = 0; i < size; i++) {
			if (other.contains(s) && this.contains(s)) {
				arraySet.add(s);
			}
		}
		return arraySet;
	}

	/*
	 * Returns a new ResizableArraySet containing all the elements that are
	 * contained in either or both sets.
	 * 
	 * @param - other The ResizableArraySet with other elements
	 * 
	 * @return - arraySet The ResizableArraySet containing the result of the method
	 */
	public ResizableArraySet union(ResizableArraySet other) {
		ResizableArraySet arraySet = new ResizableArraySet();
		String[] toAddthis;
		String[] toAddother;

		toAddthis = this.toArray();
		toAddother = other.toArray();

		for (String str : toAddthis) {
			arraySet.add(str);
		}

		for (String str : toAddother) {
			arraySet.add(str);
		}
		return arraySet;
	}

	/*
	 * Returns a new ResizableArraySet containing all possible strings that can be
	 * made by joining one element of this set with each of the other elements.
	 * 
	 * @param - other The ResizableArraySet with other elements
	 * 
	 * @return - arraySet The ResizableArraySet containing the result of the method
	 */
	public ResizableArraySet allPairs() {
		ResizableArraySet other = new ResizableArraySet();
		ResizableArraySet arraySet = new ResizableArraySet();

		for (int i = 0; i < this.size; i++) {
			String str = this.words[i];
			for (int j = 0; j < other.size; i++) {
				if (str != this.words[j]) {
					String result = str + this.words[j];
					arraySet.add(result);
				}
			}
		}
		return arraySet;
	}

	/*
	 * Returns (but does not remove) the word in the set that is first,
	 * lexicographically (basically, this means the word that is first
	 * alphabetically).
	 */
	public String getEarliestWord() {
		String str = "";

		for (int i = 0; i < words.length; i++) {
			int check = words[i].compareTo(words[i + 1]);
			if (check < 0) {
				str = words[i];
			}
		}
		return str;
	}

	////////////////////////////////////////////
	// helper methods
	////////////////////////////////////////////

	/*
	 * A useful helper method for any method that needs to know the location of a
	 * specified word.
	 */
	private int indexOf(String word) {
		for (int i = 0; i < size; i++) {
			if (word.equals(words[i])) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * A useful helper method for the add method to check if the array is full. If
	 * true, make a new array with double the size
	 */
	private boolean isFull() {
		if (words.length == size) {
			return true;
		}
		return false;
	}

	/*
	 * Checks if the array is full, if yes it makes a copy of the array and double
	 * the size of the new one
	 */
	private void resize() {
		if (isFull() == true) {
			String[] temp = Arrays.copyOf(words, (size * 2));
			words = temp;
		}
	}

}
