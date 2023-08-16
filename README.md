# Object-Oriented-Programming

## Project #1: Resizable Array Set
A Java implementation of a resizable array-based set data structure.

### Methods
- int size(): Returns the number of elements in the set.
- boolean isEmpty(): Checks if the set is empty.
- boolean add(String s): Adds a string to the set if it's not already present.
- String remove(): Removes and returns the last element from the set.
- boolean remove(String s): Removes the specified string from the set.
- void clear(): Removes all elements from the set.
- boolean contains(String s): Checks if the set contains a specific string.
- String[] toArray(): Converts the set to an array of strings.
- String toString(): Returns a space-separated list of words in the set.
- intersection(ResizableArraySet other): Returns a new set containing the common elements between this set and another set.
- union(ResizableArraySet other): Returns a new set containing all elements from both sets.
- allPairs(): Returns a set of all possible strings made by joining one element from this set with each element from another set.
- String getEarliestWord(): Returns the lexicographically earliest word in the set.


## Project #2: Linked Extended Set with Generics
An implementation of an extended set data structure with linked nodes, utilizing Java generics.

### Methods
- boolean add(T item): Adds an item to the set.
- boolean remove(T item): Removes the specified item from the set.
- boolean removeAll(T item): Removes all occurrences of the specified item from the set.
- boolean contains(T item): Checks if the set contains the specified item.
- int getFrequency(T item): Returns the frequency of the specified item in the set.
- int size(): Returns the total number of elements in the set.
- int uniqueCount(): Returns the count of unique elements in the set.
- boolean isEmpty(): Checks if the set is empty.
- int maxFrequency(): Returns the maximum allowed frequency for items.
- void clear(): Clears all elements from the set.
- Object[] toArray(): Converts the set to an array of objects.
- Object[] toUniqueArray(): Converts the set to an array of unique objects.
- Object[] toSortedArray(): Converts the set to a sorted array of objects.


## Project #3: Deque, Queue, and Stack
An implementation of a double-ended queue (deque) data structure using linked nodes, an implementation of a Queue, and an implementation of a Stack in Java.

### Methods
#### Deque
- void addToFront(T item): Adds an item to the front of the deque.
- void addToBack(T item): Adds an item to the back of the deque.
- T removeFront(): Removes and returns the item from the front of the deque.
- T removeBack(): Removes and returns the item from the back of the deque.
- T getFront(): Returns the item at the front of the deque without removing it.
- T getBack(): Returns the item at the back of the deque without removing it.
- boolean isEmpty(): Checks if the deque is empty.
- void clear(): Clears all elements from the deque.
- String toString(): Returns a string representation of the deque.

#### Deque Stack 
- void push(T item): Adds an item to the top of the stack.
- T pop(): Removes and returns the item from the top of the stack.
- T peek(): Returns the item at the top of the stack without removing it.
- boolean isEmpty(): Checks if the stack is empty.
- void clear(): Clears all elements from the stack.




















