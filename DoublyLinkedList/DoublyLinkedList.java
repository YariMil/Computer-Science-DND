
public class DoublyLinkedList {
	// Implements a circular doubly-linked list.

	private final ListNode2<Nucleotide> SENTINEL = new ListNode2<Nucleotide>(null);
	private int nodeCount;

	// Constructor: creates an empty list
	public DoublyLinkedList() {
		nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public DoublyLinkedList(Nucleotide[] values) {
		if (values == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < values.length; i++) {
			add(values[i]);
		}
	}

	public ListNode2<Nucleotide> getSentinel() {
		return SENTINEL;
	}

	public ListNode2<Nucleotide> getHead() {
		return SENTINEL.getNext();
	}

	public ListNode2<Nucleotide> getTail() {
		return SENTINEL.getPrevious();
	}


	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return size() == 0;
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(Nucleotide obj) {
		return indexOf(obj) != -1;
	}

	public void checkForIllegalArgument(Nucleotide obj) {
		if (obj == null) {
			throw new IllegalArgumentException();
		}
	}

	public void checkForIndexOutOfBounds(int i) {
		if (i < 0 || i > size()) {
			throw new IllegalArgumentException();
		}
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(Nucleotide obj) {
		checkForIllegalArgument(obj);
		ListNode2<Nucleotide> temp = getHead();
		for (int i = 0; i < nodeCount; i++) {
			if (temp.getValue().equals(obj)) {
				return i;
			}
			temp = temp.getNext();
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(Nucleotide obj) {
		checkForIllegalArgument(obj);
		if (nodeCount == 0) {
			ListNode2<Nucleotide> addition =
					new ListNode2<Nucleotide>(obj, getSentinel(), getSentinel());
			SENTINEL.setNext(addition);
			SENTINEL.setPrevious(addition);
		} else {
			ListNode2<Nucleotide> addition =
					new ListNode2<Nucleotide>(obj, getTail(), getSentinel());
			getTail().setNext(addition);
			SENTINEL.setPrevious(addition);
		}
		nodeCount++;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(Nucleotide obj) {
		checkForIllegalArgument(obj);
		int index = indexOf(obj);
		if (index != -1) {
			remove(index);
			return true;
		}
		return false;
	}

	// Returns the i-th element.
	public Nucleotide get(int i) {
		checkForIndexOutOfBounds(i);
		ListNode2<Nucleotide> node = getNode(i);
		return node.getValue();
	}

	public ListNode2<Nucleotide> getNode(int i) {
		checkForIndexOutOfBounds(i);
		ListNode2<Nucleotide> temp = getHead();
		for (int j = 0; j < i; j++) {
			temp = temp.getNext();
		}
		return temp;
	}

	// Replaces the i-th element with obj and returns the old value.
	public Nucleotide set(int i, Nucleotide obj) {
		checkForIndexOutOfBounds(i);
		checkForIllegalArgument(obj);
		ListNode2<Nucleotide> node = getNode(i);
		Nucleotide removed = node.getValue();
		node.setValue(obj);
		return removed;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Nucleotide obj) {
		checkForIllegalArgument(obj);
		if (nodeCount == 0 || i == nodeCount) {
			add(obj);
		} else {
			ListNode2<Nucleotide> temp = getNode(i);
			ListNode2<Nucleotide> addition =
					new ListNode2<Nucleotide>(obj, temp.getPrevious(), temp);
			temp.getPrevious().setNext(addition);
			temp.setPrevious(addition);
			nodeCount++;
		}
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Nucleotide remove(int i) {
		checkForIndexOutOfBounds(i);
		ListNode2<Nucleotide> temp = getNode(i);
		temp.getPrevious().setNext(temp.getNext());
		temp.getNext().setPrevious(temp.getPrevious());
		nodeCount--;
		return temp.getValue();
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		ListNode2<Nucleotide> temp = getHead();
		for (int i = 0; i < nodeCount - 1; i++) {
			sb.append(temp.getValue());
			sb.append(", ");
			temp = temp.getNext();
		}
		if (temp != SENTINEL) {
			sb.append(temp.getValue());
		}
		sb.append("]");
		return sb.toString();
	}

	// Like question 7 on the SinglyLinkedList test:
	// Add a "segment" (another list) onto the end of this list
	public void addSegmentToEnd(DoublyLinkedList seg) {
		if (seg == null) {
			throw new IllegalArgumentException();
		}
		if (!seg.isEmpty()) {
			getTail().setNext(seg.getHead());
			seg.getHead().setPrevious(getTail());
			SENTINEL.setPrevious(seg.getTail());
			nodeCount += seg.size();
		}
	}

	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {
		removeMultiple(nodeBefore, 16);
	}

	// Removes next num nodes
	public void removeMultiple(ListNode2<Nucleotide> nodeBefore, int num) {
		ListNode2<Nucleotide> temp = nodeBefore.getNext();
		for (int i = 0; i < num; i++) {
			if (temp == SENTINEL) {
				throw new IllegalArgumentException();
			}
			temp = temp.getNext();
		}
		nodeBefore.setNext(temp);
		temp.setPrevious(nodeBefore);
		nodeCount -= num;

	}

	public String limitedToString(int start, int size) {
		if (start + size <= nodeCount) {
			StringBuilder sb = new StringBuilder("[");
			ListNode2<Nucleotide> temp = getNode(start);
			for (int i = 0; i < size - 1; i++) {
				sb.append(temp.getValue());
				sb.append(", ");
				temp = temp.getNext();
			}
			sb.append(temp.getValue());
			sb.append("]");
			return sb.toString();
		}
		return "";
	}

	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(DoublyLinkedList seg) {
		// Get the segment size, plus its toString
		// Go through list, take a limited toString()
		// Compare the two toStrings
		// If they're equal, slice out the segment
		int size = seg.size();
		String segToString = seg.toString();
		for (int i = 0; i < nodeCount; i++) {
			if (limitedToString(i, size).equals(segToString)) {
				removeMultiple(getNode(i).getPrevious(), size);
				return true;
			}
		}
		return false;

	}

	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {
		ListNode2<Nucleotide> nodeThreeAway = null;
		try {
			nodeThreeAway = getNode(size() - 3);
		} catch (Exception e) {
			return false;
		}
		removeMultiple(nodeThreeAway.getPrevious(), 3);
		return true;

	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {

	}

}
