// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {

	private BinaryNode<E> root; // holds the root of this BST

	// Constructor: creates an empty BST.
	public MyBST() {
		root = null;
	}

	public BinaryNode<E> getRoot() {
		return root;
	}

	public int getHeight() {
		return getHeightHelper(root);
	}

	public int getHeightHelper(BinaryNode<E> node) {
		if (node.isLeaf()) {
			return node.getHeight();
		}
		int leftHeight = 0;
		int rightHeight = 0;
		if (node.getLeft() != null) {
			leftHeight = getHeightHelper(node.getLeft());
		}
		if (node.getRight() != null) {
			rightHeight = getHeightHelper(node.getRight());
		}
		return Math.max(leftHeight, rightHeight);
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value) {
		if (root == null) {
			return false;
		}
		if (value == null) {
			return false;
		}
		BinaryNode<E> temp = root;
		while (true) {
			// Check to see if temp is null first
			// If it is, that means at some point we ran out of nodes so it's not here
			if (temp == null) {
				return false;
			}
			if (value.compareTo(temp.getValue()) == 0) {
				return true;
			} else if (value.compareTo(temp.getValue()) < 0) {
				// Value < current node
				temp = temp.getLeft();
			} else if (value.compareTo(temp.getValue()) > 0) {
				temp = temp.getRight();
			}
		}
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		if (root == null) {
			root = new BinaryNode<E>(value, null, 0);
			return true;
		}
		BinaryNode<E> addition = new BinaryNode<E>(value);
		BinaryNode<E> temp = root;
		while (true) {
			if (value.compareTo(temp.getValue()) < 0) {
				if (temp.getLeft() == null) {
					addition.setParent(temp);
					temp.setLeft(addition);
					return true;
				}
				temp = temp.getLeft();
			} else if (value.compareTo(temp.getValue()) > 0) {
				if (temp.getRight() == null) {
					addition.setParent(temp);
					temp.setRight(addition);
					return true;
				}
				temp = temp.getRight();
			} else {
				return false;
			}
		}
	}

	// Removes value from this BST. Returns true if value has been
	// found and removed; otherwise returns false.
	// If removing a node with two children: replace it with the
	// largest node in the right subtree
	public boolean remove(E value) {
		if (root == null) {
			return false;
		}
		if (value == null) {
			return false;
		}
		// Before anything, go to the node
		// Note: I'm creating as many variables as possible just in case I need them
		BinaryNode<E> temp = root;
		BinaryNode<E> previousNode = null;
		boolean wentLeft = false;
		while (true) {
			if (value.compareTo(temp.getValue()) < 0) {
				// Value < current node
				temp = temp.getLeft();
				if (temp == null) {
					// No node here
					return false;
				}
				wentLeft = true;
			} else if (value.compareTo(temp.getValue()) > 0) {
				temp = temp.getRight();
				if (temp == null) {
					return false;
				}
				wentLeft = false;
			} else {
				if (temp.getRight() != null) {
					// Two rights make a dead node
					BinaryNode<E> rightClosest = temp.getRight();
					while (rightClosest.getLeft() != null) {
						rightClosest = rightClosest.getLeft();
					}
					if (previousNode != null) {
						if (wentLeft) {
							previousNode.setLeft(rightClosest);
						} else {
							previousNode.setRight(rightClosest);
						}
					} else {
						rightClosest.updateHeight(0);
						root = rightClosest;
					}
					boolean isChildOfRemoved = rightClosest == temp.getRight();
					rightClosest.detachSelf();
					rightClosest.setParent(previousNode);
					BinaryNode<E> tFix = rightClosest;
					while (tFix.getRight() != null) {
						tFix = tFix.getRight();
					}
					if (!isChildOfRemoved) {
						tFix.setRight(temp.getRight());
						temp.getRight().setParent(tFix);
					}
					rightClosest.setLeft(temp.getLeft());
					if (temp.getLeft() != null) {
						temp.getLeft().setParent(rightClosest);
					}
				} else if (temp.getLeft() != null) {
					BinaryNode<E> leftClosest = temp.getLeft();
					while (leftClosest.getRight() != null) {
						leftClosest = leftClosest.getRight();
					}
					if (previousNode != null) {
						if (wentLeft) {
							previousNode.setLeft(leftClosest);
						} else {
							previousNode.setRight(leftClosest);
						}
					} else {
						leftClosest.updateHeight(0);
						root = leftClosest;
					}
					boolean isChildOfRemoved = leftClosest == temp.getLeft();
					leftClosest.detachSelf();
					leftClosest.setParent(previousNode);
					BinaryNode<E> tFix = leftClosest;
					while (tFix.getLeft() != null) {
						tFix = tFix.getLeft();
					}
					if (!isChildOfRemoved) {
						tFix.setLeft(temp.getLeft());
						temp.getLeft().setParent(tFix);
						leftClosest.setRight(null);
					}
				} else {
					// Pick a lane buddy
					if (previousNode != null) {
						if (wentLeft) {
							previousNode.setLeft(null);
						} else {
							previousNode.setRight(null);
						}
					} else {
						root = null;
					}
				}
				return true;
			}
			previousNode = temp.getParent();
		}
	}

	// Returns the minimum in the tree
	public E min() {
		if (root == null) {
			return null;
		}
		BinaryNode<E> temp = root;
		while (temp.getLeft() != null) {
			temp = temp.getLeft();
		}
		return temp.getValue();
	}

	// Returns the maximum in the tree.
	public E max() {
		if (root == null) {
			return null;
		}
		BinaryNode<E> temp = root;
		while (temp.getRight() != null) {
			temp = temp.getRight();
		}
		return temp.getValue();
	}

	// Returns a bracket-surrounded, comma separated list of the contents of the nodes, in order
	// e.g. [Apple, Cranberry, Durian, Mango]
	public String toString() {
		if (root == null) {
			return "[]";
		}
		StringBuilder sb = toStringHelp(new StringBuilder(), root);
		String str = sb.toString();
		return str.substring(0, str.length() - 2);
	}

	private StringBuilder toStringHelp(StringBuilder sb, BinaryNode<E> temp) {
		if (temp.getLeft() != null) {
			sb = toStringHelp(sb, temp.getLeft());
		}
		sb.append(temp.getValue() + ", ");
		if (temp.getRight() != null) {
			sb = toStringHelp(sb, temp.getRight());
		}
		return sb;
	}

	public String toStringWithDetails() {
		if (root == null) {
			System.out.println("Empty");
		}
		StringBuilder sb = toStringDetailsHelper(new StringBuilder(), root);
		String str = sb.toString();
		return str;
	}

	private StringBuilder toStringDetailsHelper(StringBuilder sb, BinaryNode<E> temp) {
		sb.append(temp.toStringDetails());
		if (temp.getLeft() != null) {
			sb = toStringDetailsHelper(sb, temp.getLeft());
		}

		if (temp.getRight() != null) {
			sb = toStringDetailsHelper(sb, temp.getRight());
		}
		return sb;
	}
}
