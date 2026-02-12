
public class HeapPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {
	// We're doing min heap
	private E[] heap;
	private int objectCount;

	public HeapPQ() {
		this.heap = (E[]) new Comparable[3];
		this.objectCount = 0;
	}

	// Returns the number of elements in the priority queue
	public int size() {
		return objectCount;
	}

	// DO NOT CHANGE MY JANKY TOSTRING!!!!!
	public String toString() {
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++) {
			stringbuf.append(heap[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for (int rowLength = 1, j = 0; j < objectCount; rowLength *= 2) {
			for (int i = 0; i < rowLength && j < objectCount; i++, j++) {
				stringbuf.append(heap[j] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount) {
				for (int i = 0; i < Math.min(objectCount - j, rowLength * 2); i++) {
					if (i % 2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}

	// Doubles the size of the heap array
	private void increaseCapacity() {
		E[] betterCapacity = (E[]) new Comparable[objectCount * 2];
		for (int i = 0; i < objectCount; i++) {
			betterCapacity[i] = heap[i];
		}
		heap = betterCapacity;
	}

	// Returns the index of the "parent" of index i
	private int parent(int i) {
		// (i + 1) / 2 - 1
		return (i - 1) / 2;
	}

	// Returns the index of the *smaller child* of index i
	private int smallerChild(int i) {
		int leftChild = i * 2 + 1;
		int rightChild = i * 2 + 2;
		if (leftChild >= objectCount) {
			return -1;
		}
		if (rightChild >= objectCount) {
			return leftChild;
		}
		if (heap[leftChild] == null) {
			// No children :(
			return -1;
		}
		if (heap[rightChild] == null) {
			return leftChild;
		}
		return heap[leftChild].compareTo(heap[rightChild]) < 0 ? leftChild : rightChild;
	}

	// Swaps the contents of indices i and j
	private void swap(int i, int j) {
		E temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i) {
		int parentIndex = parent(i);
		while (heap[i].compareTo(heap[parentIndex]) < 0) {
			swap(i, parentIndex);
			i = parentIndex;
			parentIndex = parent(i);
		}
	}

	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i) {
		int smallChild = smallerChild(i);
		// For my own sanity (i'm tired), just writing it out here that there is NO point where the
		// object will be somehow greater than the largest child but smaller than the smaller child.
		// While index i is greater than the smaller child
		while (smallChild >= 0 && heap[i].compareTo(heap[smallChild]) > 0) {
			swap(i, smallChild);
			i = smallChild;
			smallChild = smallerChild(i);
		}
	}

	@Override
	public void add(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException();
		}
		if (objectCount == heap.length) {
			increaseCapacity();
		}
		heap[objectCount] = obj;
		bubbleUp(objectCount);
		objectCount++;

	}

	@Override
	public E peek() {
		return heap[0];
	}

	@Override
	public E removeMin() {
		int removingIndex = size() - 1;
		swap(0, removingIndex);
		E temp = heap[removingIndex];
		heap[removingIndex] = null;
		objectCount--;
		bubbleDown(0);
		return temp;
	}

	@Override
	public boolean isEmpty() {
		return objectCount == 0;
	}

}
