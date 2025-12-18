public class SortedArrayList<E extends Comparable<E>> extends MyArrayList<E> {

	public SortedArrayList() {
		super();
	}

	public SortedArrayList(int initialCapacity) {
		super(initialCapacity);
	}

	public int binarySearch(Comparable<E> obj) {
		int low = 0; 
		int high = size() - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (obj.compareTo(get(mid)) == 0) {
				return mid;
			} else if (obj.compareTo(get(mid)) > 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}

	public int binarySpecific(Comparable<E> obj) {
		int low = 0; 
		int high = size() - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (obj.compareTo(get(mid)) == 0) {
				return mid;
			} else if (obj.compareTo(get(mid)) > 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	@Override
	public boolean contains(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException();
		}
		return binarySearch((Comparable<E>) obj) != -1;

	}

	// May not contain more than one of the same object
	@Override
	public boolean add(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException();
		}
		if (contains(obj)) {
			return false;
		}
		int index = binarySpecific((Comparable<E>) obj);
		add(index, obj);
		return true;
	}

	public void add(int index, E obj) {
		E temp = obj;
		for (int i = index; i < objectCount; i++) {
			temp = set(i, temp);
		}
		super.add(temp);
	}


	@Override
	public boolean remove(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException();
		}
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
