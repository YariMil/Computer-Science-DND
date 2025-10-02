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
		if (values == null) {
			throw new IllegalArgumentException("Values array can't be null!");
		}
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
		return nodeCount == 0;
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
		return indexOf(obj) != -1;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		ListNode<E> temp = head;
		for (int i = 0; i < nodeCount; i++) {
			if (obj != null) {
				if (temp.getValue().equals(obj)) {
					return i;
				}
			} else {
				if (temp.getValue() == null) {
					return i;
				}
			}
			temp = temp.getNext();
		}
		return -1;
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
		add(nodeCount, obj);
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		int index = indexOf(obj);
		if (index != -1) {
			remove(index);
			return true;
		}
		return false;

	}

	// Returns the i-th element.
	public E get(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> temp = getNode(i);
		return temp.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> temp = getNode(i);
		E val = temp.getValue();
		temp.setValue((E) obj);
		return val;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {
		/*
		 * At the start, nodeCount will be 0. This means that the head will BE the tail Therefore,
		 * the tail will point to the head and both will have a null next value because there is no
		 * next value to call.
		 * 
		 * If nodeCount is not 0, the add method will iterate through the linked list, stopping at
		 * the node right before the actual index where the object will be added. It makes a new
		 * node with the next node being the temp.getNext() node. The next node of temp then gets
		 * set to the new node.
		 */
		if (i < 0 || i > nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		if (nodeCount == 0) {
			head = new ListNode<E>((E) obj, tail);
			tail = head;
		} else {
			ListNode<E> temp = getNode(i - 1);
			ListNode<E> newObj = new ListNode<E>((E) obj, temp.getNext());
			if (temp.getNext() == null) {
				tail = newObj;
			}
			temp.setNext(newObj);
		}
		nodeCount++;
	}

	public ListNode<E> getNode(int i) {
		ListNode<E> temp = head;
		for (int j = 0; j < i; j++) {
			temp = temp.getNext();
		}
		return temp;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		/*
		 * Go to the index - 1 Set the next node of the list to the index + 2
		 */
		// Edge case when i == 0
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> temp = head;
		if (i == 0) {
			head = head.getNext();
		} else {
			ListNode<E> previous = null;
			for (int j = 0; j < i; j++) {
				previous = temp;
				temp = temp.getNext();

			}
			if (temp.getNext() == null) {
				previous = tail;
			}
			previous.setNext(temp.getNext());
		}
		nodeCount--;
		return temp.getValue();


	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		// While we haven't reached the tail node (next node isn't null)
		ListNode<E> temp = head;
		for (int i = 0; i < nodeCount - 1; i++) {
			str.append(temp.getValue() + ", ");
			temp = temp.getNext();
		}
		// Last node has no "next," needs to be added manually
		str.append(temp.getValue() + "]");
		return str.toString();

	}


}
