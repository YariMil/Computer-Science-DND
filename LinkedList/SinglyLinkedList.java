// Implements a singly-linked list.


public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	@SuppressWarnings("unchecked")
	public SinglyLinkedList(Object[] values) {
		for (int i = 0; i < values.length; i++) {
			add((E) values[i]);
		}
	}

	public ListNode<E> getHead() {
		return head;
	}

	public ListNode<E> getTail() {
		return tail;
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return false;
	}

	// Returns the number of elements in this list.
	public int size() {
		return 1;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		return 1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		/*
		 * At the start, nodeCount will be 0. This means that the head will BE the tail Therefore,
		 * the tail will point to the head and both will have a null next value because there is no
		 * next value to call.
		 * 
		 * If nodeCount is not 0, the add method will create a new node with the added object, then
		 * set the tail's next node to that new node (when there is one object in the list, this
		 * means the head's next is set to the new node because the tail points to the head). The
		 * tail is now set to the new node, pointing at it.
		 */
		if (nodeCount == 0) {
			head = new ListNode<E>(obj, tail);
			tail = head;
		} else {
			// create new node first
			// This is now the new tail node
			ListNode<E> addition = new ListNode<E>(obj);
			// Set the next node of the tail node to new node
			tail.setNext(addition);
			// new node = tail node
			tail = addition;
		}
		nodeCount++;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		return false;

	}

	// Returns the i-th element.
	public E get(int i) {
		return null;
	}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {
		return null;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		return null;

	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		// While we haven't reached the tail node (next node isn't null)
		ListNode<E> temp = head;
		while (temp.getNext() != null) {
			str.append(temp.getValue() + ", ");
			temp = temp.getNext();

		}
		str.append(temp.getValue() + "]");
		return str.toString();

	}


}
