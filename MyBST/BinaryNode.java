
public class BinaryNode<E extends Comparable<E>> {

	private E value;
	private BinaryNode<E> left;
	private BinaryNode<E> right;
	private BinaryNode<E> parent;
	private int height;

	public BinaryNode(E value) {
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.height = 0;
	}

	public BinaryNode(E value, BinaryNode<E> parent, int height) {
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = parent;
		this.height = height;
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
		updateHeight(height);
	}

	public void setRight(BinaryNode<E> right) {
		this.right = right;
		if (right == null) {
			return;
		}
		updateHeight(height);
	}

	public void updateHeight(int height) {
		this.height = height;
		if (left != null) {
			left.updateHeight(height + 1);
		}
		if (right != null) {
			right.updateHeight(height + 1);
		}
	}

	public void detachSelf() {
		if (parent.getLeft() == this) {
			parent.setLeft(null);
		} else {
			parent.setRight(null);
		}
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

	public String toStringDetails() {
		return "\n" + value + " believes its parent to be " + parent
				+ ". It believes its children to be " + left + " on the left and " + right
				+ " on the right. Its height is " + height + ".\n";
	}

}
