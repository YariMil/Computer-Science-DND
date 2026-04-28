public class HuffmanNode implements Comparable {
    private Character c;
    private int frequency;
    private HuffmanNode leftChild;
    private HuffmanNode rightChild;
    private StringBuilder code;


    private HuffmanNode parent;

    public HuffmanNode(char c, int frequency) {
        // Sorting
        this.c = c;
        this.frequency = frequency;
        leftChild = null;
        rightChild = null;
        parent = null;
        code = new StringBuilder();
    }

    public HuffmanNode(int frequency, HuffmanNode leftChild, HuffmanNode rightChild) {
        // Making the tree
        c = null;
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        leftChild.setParent(this);
        rightChild.setParent(this);
        code = new StringBuilder();
    }

    public void addToCode(String c) {
        code.append(c);
    }

    public String getCode() {
        return code.toString();
    }

    @Override
    public int compareTo(Object o) {
        HuffmanNode n = (HuffmanNode) o;
        return frequency - n.getFrequency();
    }

    public int getFrequency() {
        return frequency;
    }

    public char getChar() {
        return c;
    }

    /**
     * @return the leftChild
     */
    public HuffmanNode getLeftChild() {
        return leftChild;
    }

    /**
     * @param leftChild the leftChild to set
     */
    public void setLeftChild(HuffmanNode leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * @return the rightChild
     */
    public HuffmanNode getRightChild() {
        return rightChild;
    }

    /**
     * @param rightChild the rightChild to set
     */
    public void setRightChild(HuffmanNode rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * @return the parent
     */
    public HuffmanNode getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }

    public String toString() {
        return "char " + c + "; frequency: " + frequency;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
}
