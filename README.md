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
