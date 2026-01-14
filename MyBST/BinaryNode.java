
public class BinaryNode<E extends Comparable<E>> {

	private E value;
	private BinaryNode<E> left;
	private BinaryNode<E> right;
	private BinaryNode<E> parent;
	private int height;
	private int balanceFactor;

	public BinaryNode(E value) {
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.height = 0;
		this.balanceFactor = 0;
	}

	public BinaryNode(E value, BinaryNode<E> parent, int height) {
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = parent;
		this.height = height;
		this.balanceFactor = 0;
	}

	public E getValue() {
		return value;
	}

	public BinaryNode<E> getLeft() {
		return left;
	}

	public BinaryNode<E> getRight() {
		return right;
	}

	public BinaryNode<E> getParent() {
		return parent;
	}

	public int getHeight() {
		return height;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public void setLeft(BinaryNode<E> left) {
		this.left = left;
		if (left == null) {
			return;
		}
		int index = 0;
		BinaryNode<E> temp = this;
		while (temp.getLeft() != null) {
			temp.setHeight(height + index);
			index++;
			temp = temp.getLeft();
		}
		temp.setHeight(height + index);
		// YOU CODE: Update height
	}

	public void setRight(BinaryNode<E> right) {
		this.right = right;
		if (right == null) {
			return;
		}
		int index = 0;
		BinaryNode<E> temp = this;
		while (temp.getRight() != null) {
			temp.setHeight(height + index);
			index++;
			temp = temp.getRight();
		}
		temp.setHeight(height + index);
		// YOU CODE: Update height
	}

	public void setParent(BinaryNode<E> parent) {
		this.parent = parent;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	public boolean isLeaf() {
		return !hasLeft() && !hasRight();
	}

	public String toString() {
		return value.toString();

	}

}
