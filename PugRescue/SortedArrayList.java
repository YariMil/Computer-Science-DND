public class SortedArrayList<E extends Comparable<E>> extends MyArrayList<E> {

	public SortedArrayList() {
		super();
	}

	public int binarySearch(Comparable<E> obj) {
		return binarySearchNonSpecific(obj, 0, super.size() - 1);
	}

	/**
	 * Implements the binary search algorithm to find and return the index of a given element in a
	 * sorted array if it is between low and high, or -1 if it is not found between low and high.
	 */
	public int binarySearchNonSpecific(Comparable<E> key, int low, int high) {
		if (high - low <= 1) {
			if (key.compareTo(super.get(low)) == 0) {
				return low;
			} else if (key.compareTo(super.get(high)) == 0) {
				return high;
			} else {
				return -1;
			}
		} else {
			if (key.compareTo(get((high + low) / 2)) == 0) {
				return (high + low) / 2;
			}
			if (key.compareTo(get((high + low) / 2)) < 0) {
				return binarySearchHelper(key, low, high - (high - low) / 2);
			} else if (key.compareTo(get((high + low) / 2)) > 0) {
				return binarySearchHelper(key, low + (high - low) / 2, high);
			}
		}
		return low; // Element not found
	}


	/**
	 * DO NOT EDIT! Write the recursive helper method below instead.
	 */
	public int binarySearchSpecific(Comparable<E> obj) {
		return binarySearchHelper(obj, 0, super.size() - 1);
	}

	/**
	 * Implements the binary search algorithm to find and return the index of a given element in a
	 * sorted array if it is between low and high, or -1 if it is not found between low and high.
	 */
	public int binarySearchHelper(Comparable<E> key, int low, int high) {
		if (low >= high) {
			if (key.compareTo(super.get(low)) == 0) {
				return low;
			} else if (key.compareTo(super.get(high)) == 0) {
				return high;
			} else {
				return high;
			}
		} else {
			if (key.compareTo(get((high + low) / 2)) == 0) {
				return (high + low) / 2;
			}
			if (key.compareTo(get((high + low) / 2)) < 0) {
				return binarySearchHelper(key, low, high - (high - low) / 2);
			} else if (key.compareTo(get((high + low) / 2)) > 0) {
				return binarySearchHelper(key, low + (high - low) / 2, high);
			}
		}
		return high; // Element not found
	}

	@Override
	public boolean contains(E obj) {
		return binarySearch((Comparable<E>) obj) != -1;

	}

	// May not contain more than one of the same object
	@Override
	public boolean add(E obj) {
		if (size() == 0) {
			super.add(obj);
			return true;
		}
		if (contains(obj)) {
			return false;
		}
		int index = binarySearchSpecific((Comparable<E>) obj);
		// for (int i = 0; i < size(); i++) {
		// 	if (obj.compareTo(get(i)) < 0) {
		// 		super.add(i, obj);
		// 		return true;
		// 	}
		// }
		add(index, obj);
		return true;
	}

	public void add(int index, E obj) {
		if (index > objectCount || index < 0) {
			throw new IndexOutOfBoundsException(
					"Index " + index + "out of range for length " + size());
		}
		E temp = obj;
		for (int i = index; i < objectCount; i++) {
			temp = set(i, temp);
		}
		super.add(temp);
	}


	@Override
	public boolean remove(E obj) {
		int removeIndex = binarySearch(obj);
		if (removeIndex != -1) {
			remove(removeIndex);
			return true;
		}
		return false;
	}

	public E min() {
		return internalArray[0];
	}

	public E max() {
		return internalArray[size() - 1];
	}
}
