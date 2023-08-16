import java.util.*;

public class WordTree {

	private Node root;

	public WordTree() {
		root = new Node(' ');
	}

	/*
	 * adds a word to the tree, returning true if the word was added, and false
	 * otherwise (return false if the word is already in the tree). Return false if
	 * the string is empty
	 */
	boolean add(String word) {
		word = word.trim();
		if (word.isEmpty() || contains(word)) {
			return false;
		} else {
			Node top = root;
			for (int i = 0; i < word.length(); i++) {
				top.addChild(word.charAt(i));
				top = top.addChild(word.charAt(i));
			}
			top.endOfWord = true;
			return true;
		}
	}

	/**
	 * return true if the tree contains the word, false otherwise. Return false if
	 * the string is empty.
	 */
	boolean contains(String word) {
		word = word.trim();
		if (word.isEmpty()) {
			return false;
		}
		
		Node top = root;
		
		for (int i = 0; i < word.length(); i++) {
			top = top.getChild(word.charAt(i));
			if (top == null) {
				return false;
			}
		}
		return top.endOfWord;
	}

	/*
	 * Remove the given word from the tree, Returning true if the string existed in
	 * the tree, and false otherwise. Return false if the string is empty. I
	 * recommend saving this method as your last method that you write.
	 */
	boolean remove(String word) {
		if (word.isEmpty() || (!contains(word))) {
			return false;
		}

		Node curr = root;
		Stack<Node> nodes = new Stack<>();
		
		for (int i = 0; i < word.length(); i++) {
			curr = curr.getChild(word.charAt(i));
			nodes.push(curr);
		}
		
		while (!nodes.isEmpty()) {
			curr = nodes.pop();
			if (curr.children.isEmpty()) {
				curr = null;
			}
			
			if (curr != null && !curr.children.isEmpty()) {
				if (!curr.endOfWord) {
					curr = nodes.pop();
				} else {
					curr.endOfWord = false;
				}
			}
			return true;
		}
		return false;
	}

	/*
	 * Returns the number of nodes in the tree except for the root node. Do not
	 * create extra instance variables to solve this, or you will not receive
	 * credit. Your WordTree class should have exactly one instance variable. Solve
	 * this by traversing the tree.
	 */
	int nodeCount() {
		return nodeCount(root);
	}

	// Recursive Helper
	private int nodeCount(Node top) {
		if (top == null) {
			return 0;
		}
		int count = 1;

		if (top == root) {
			count--;
		}
		for (Node n : top.children) {
			count += nodeCount(n);
		}
		return count;
	}

	/*
	 * Returns the number of words in the tree. Note that this is just like counting
	 * all nodes, but here you only need to count the number of nodes that are
	 * marked as being the end of a word.
	 */
	int wordCount() {
		return wordCount(root);

	}

	private int wordCount(Node top) {
		if (top == null) {
			return 0;
		}
		int count = 1;

		if (!top.endOfWord) {
			count--;
		}
		for (Node n : top.children) {
			count += wordCount(n);
		}
		return count;
	}

	/*
	 * This method takes the number of letters in all the words (if those words were
	 * not stored in the tree), and subtracts the number of letters actually used in
	 * the tree to store those words (this second number comes from your
	 * letterCount() nodeCount() method). This is a good way to measure the space
	 * efficiency of your tree.
	 */
	int lettersSaved() {
		int count = 0;
		for (String s : allWords()) {
			char[] arr = s.toCharArray();
			count += arr.length;
		}
		return count - nodeCount();
	}

	/*
	 * Removes all words from the tree. This can be solved with one line of code.
	 */
	void clear() {
		root = new Node(' ');
	}

	/*
	 * Returns a set of all the words in the tree
	 */
	Set<String> allWords() {
		Set<String> words = new TreeSet<>();
		for (Node n : root.children) {
			allWords(n, "", words);
		}
		return words;
	}

	private void allWords(Node top, String word, Set<String> st) {
		word = word + top.data;
		if (top.endOfWord) {
			st.add(word);
		}
		for (Node n : top.children) {
			allWords(n, word, st);
		}
	}

	/*
	 * Returns a set of all the words in the tree that begin with the given prefix.
	 * In the tree shown on the previous page, allStartingWith("CA") should return a
	 * set containing exactly 4 words: CARS, CASH, CAT, CATCHY. If no words begin
	 * with the specified prefix, return an empty set. If prefix is an empty string,
	 * "", return all words.
	 */
	Set<String> allStartingWith(String prefix) {
		Set<String> words = new TreeSet<>();

		for (String s : allWords()) {
			String check = s.substring(0, prefix.length());
			if (check.equals(prefix)) {
				words.add(s);
			}
		}
		return words;
	}

	/*
	 * Returns a map where the key is a letter of the alphabet, and the value is the
	 * set of all words in the tree that begin with that letter. If there are no
	 * words that begin with a particular letter, don't include it in the map. Solve
	 * this one AFTER solving allStartingWith() above.
	 */
	Map<Character, Set<String>> wordMap() {

		Map<Character, Set<String>> words = new TreeMap<>();

		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		for (char c : alphabet) {
			words.put(c, allStartingWith(c + ""));
		}
		return words;
	}

	/*
	 * Inner Node class
	 */
	private class Node {
		private char data;
		private Set<Node> children;
		private boolean endOfWord;

		private Node(char letter) {
			this.data = letter;
			children = new HashSet<>();
			endOfWord = false;
		}

		// Returns a string representation of Node
		public String toString() {
			return (endOfWord ? (data + " ").toUpperCase() : data) + " " + children;
		}

		/*
		 * This adds a specified letter as a child node, and returns the resulting new
		 * node. Or, if the letter already exists as a child node, it returns that child
		 * node.
		 */
		Node addChild(char letter) {
			for (Node n : children) {
				if (n.data == letter) {
					return n;
				}
			}
			Node child = new Node(letter);
			children.add(child);
			return child;
		}

		/*
		 * This method checks if one of the node's child nodes contains the given
		 * letter. If it does, the Node containing that letter is returned. Otherwise,
		 * null is returned.
		 */
		Node getChild(char letter) {
			for (Node n : children) {
				if (n.data == letter) {
					return n;
				}
			}
			return null;
		}
	}

	// Returns a String representation of the entire tree
	public String toString() {
		return root + "";
	}
}
