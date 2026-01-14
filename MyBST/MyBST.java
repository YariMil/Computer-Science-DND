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
		return root.getHeight();
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value) {
		
		return false;
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		if (root == null) {
			root = new BinaryNode<E>(value, null, 0);
		}
		BinaryNode<E> temp = root;
		int height = 0;
		while (true) {
			if (value.compareTo(temp.getValue()) < 0) {
				if (temp.getLeft() == null) {
					BinaryNode<E> addition = new BinaryNode<E>(value, temp, height);
					temp.setLeft(addition);
					return true;
				}
				temp = temp.getLeft();
				height++;
			} else if (value.compareTo(temp.getValue()) > 0) {
				if (temp.getRight() == null) {
					BinaryNode<E> addition = new BinaryNode<E>(value, temp, height);
					temp.setRight(addition);
					return true;
				}
				temp = temp.getRight();
				height++;
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
		// Before anything, go to the node
		// Note: I'm creating as many variables as possible just in case I need them
		BinaryNode<E> temp = root;
		BinaryNode<E> previousNode = null;
		boolean wentLeft = false;
		while (true) {
			if (value.compareTo(temp.getValue()) < 0) {
				// Value < current node
				temp = temp.getLeft();
				wentLeft = true;
			} else if (value.compareTo(temp.getValue()) > 0) {
				temp = temp.getRight();
				wentLeft = false;
			} else {
				if (temp.getRight() != null) {
					// Two rights make a dead node
					BinaryNode<E> rightClosest = temp.getRight();

					while (rightClosest.getLeft() != null) {
						rightClosest = rightClosest.getLeft();
					}
					if (wentLeft) {
						previousNode.setLeft(rightClosest);
					} else {
						previousNode.setRight(rightClosest);
					}
					BinaryNode<E> tFixMoving = rightClosest;
					BinaryNode<E> tFixSet = rightClosest;
					rightClosest = rightClosest.getParent();
					rightClosest.setLeft(null);

					while (rightClosest != temp) {
						BinaryNode<E> tFix = rightClosest;
						rightClosest = rightClosest.getParent();
						while (tFixMoving.getRight() != null) {
							tFixMoving = tFixMoving.getRight();
						}
						tFixMoving.setRight(tFix);
						tFix.setParent(tFixMoving);
						tFix.setLeft(null);
						tFixMoving = tFix;
					}

					tFixSet.setParent(previousNode);

					// Edge case: The node we removed had a left node we can just attach here
					tFixSet.setLeft(temp.getLeft());
				} else if (temp.getLeft() != null) {
					// Two lefts make a right
					BinaryNode<E> leftClosest = temp.getLeft();
					while (leftClosest.getRight() != null) {
						leftClosest = leftClosest.getRight();
					}
					// The lefts, move everything to the left because we know that all values
					// above leftCLosest will be less than it
					// To Do: this could also be on the right so add an if statement
					if (wentLeft) {
						previousNode.setLeft(leftClosest);
					} else {
						previousNode.setRight(leftClosest);
					}
					BinaryNode<E> tFixMoving = leftClosest;
					BinaryNode<E> tFixSet = leftClosest;
					leftClosest = leftClosest.getParent();
					leftClosest.setRight(null);

					while (leftClosest != temp) {
						BinaryNode<E> tFix = leftClosest;
						leftClosest = leftClosest.getParent();
						while (tFixMoving.getLeft() != null) {
							tFixMoving = tFixMoving.getLeft();
						}
						tFixMoving.setLeft(tFix);
						tFix.setParent(tFixMoving);
						tFix.setRight(null);
						tFixMoving = tFix;
					}
					// Going down one more time to set the remaining nodes
					tFixSet.setParent(previousNode);
					// Edge case doesn't exist because the node has no right anyway
				} else {
					// Pick a lane buddy
					if (wentLeft) {
						previousNode.setLeft(null);
					} else {
						previousNode.setRight(null);
					}
				}
				return true;
			}
			previousNode = temp.getParent();
		}
	}

	// Returns the minimum in the tree
	public E min() {
		BinaryNode<E> temp = root;
		while (temp.getLeft() != null) {
			temp = temp.getLeft();
		}
		return temp.getValue();
	}

	// Returns the maximum in the tree.
	public E max() {
		BinaryNode<E> temp = root;
		while (temp.getRight() != null) {
			temp = temp.getRight();
		}
		return temp.getValue();
	}

	// Returns a bracket-surrounded, comma separated list of the contents of the nodes, in order
	// e.g. [Apple, Cranberry, Durian, Mango]
	public String toString() {
		StringBuilder sb = toStringHelp(new StringBuilder(), root);
		String str = sb.toString();
		return str.substring(0, str.length() - 2);
	}

	public StringBuilder toStringHelp(StringBuilder sb, BinaryNode<E> temp) {
		if (temp.getLeft() != null) {
			sb = toStringHelp(sb, temp.getLeft());
		}
		sb.append(temp.getValue() + ", ");
		if (temp.getRight() != null) {
			sb = toStringHelp(sb, temp.getRight());
		}
		return sb;
	}
}
