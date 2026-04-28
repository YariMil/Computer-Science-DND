import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCodeGenerator {
    private HashMap<Character, Integer> map;
    private PriorityQueue<HuffmanNode> heap;
    private HuffmanNode root;
    private HashMap<Character, String> dictionary;

    public HuffmanCodeGenerator(String fileName) {
        try {
            map = countFrequency(fileName);
            heap = sort(map);
            buildTree();
            assignCodes();
        } catch (Exception e) {

        }
    }

    public HashMap<Character, Integer> countFrequency(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        while (br.ready()) {
            Character c = (char) br.read();
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        map.put((char) 26, 1);
        br.close();
        return map;
    }

    public PriorityQueue<HuffmanNode> sort(HashMap<Character, Integer> map) {
        PriorityQueue<HuffmanNode> heap = new PriorityQueue<HuffmanNode>();
        Object[] keySet = map.keySet().toArray();
        for (int i = 0; i < keySet.length; i++) {
            heap.add(new HuffmanNode((char) keySet[i], (int) map.get(keySet[i])));
        }
        return heap;
    }

    public void buildTree() {
        while (!heap.isEmpty()) {
            HuffmanNode leftChild = heap.poll();
            if (!heap.isEmpty()) {
                HuffmanNode rightChild = heap.poll();
                HuffmanNode placeHolderNode =
                        new HuffmanNode(leftChild.getFrequency() + rightChild.getFrequency(),
                                leftChild, rightChild);
                heap.add(placeHolderNode);
            } else {
                root = leftChild;
                return;
            }
        }
    }

    public void assignCodes() {
        if (root == null) {
            System.out.println("Unable to assign codes; tree not made");
            return;
        } else {
            assignCodesHelper(root, false);
        }
        makeDictionary();
    }

    private void assignCodesHelper(HuffmanNode node, boolean wentRight) {
        if (node != root) {
            node.addToCode(node.getParent().getCode() + (wentRight ? "1" : "0"));
        }
        if (node.getLeftChild() != null) {
            assignCodesHelper(node.getLeftChild(), false);
        }
        if (node.getRightChild() != null) {
            assignCodesHelper(node.getRightChild(), true);
        }
    }

    public void makeDictionary() {
        if (root == null) {
            System.out.println("Unable to make dictionary. Tree is unfinished");
        }
        dictionary = new HashMap<Character, String>();
        makeDictionaryHelper(root);
    }

    private void makeDictionaryHelper(HuffmanNode node) {
        if (node.isLeaf()) {
            dictionary.put(node.getChar(), node.getCode());
            return;
        } else {
            if (node.getLeftChild() != null) {
                makeDictionaryHelper(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                makeDictionaryHelper(node.getRightChild());
            }
        }
    }

    public String mapToString() {
        return map.toString();
    }

    // public String showSorted() {
    //     if (heap.size() == 0) {
    //         System.out.println(
    //                 "Can't print the sorted frequencies because you already made the tree!");
    //         return "";
    //     }
    //     HuffmanNode[] arr = new HuffmanNode[map.size()];
    //     for (int i = 0; i < map.size(); i++) {
    //         arr[i] = heap.poll();
    //     }
    //     StringBuilder sorted = new StringBuilder("[");
    //     for (HuffmanNode node : arr) {
    //         sorted.append(node.toString());
    //         sorted.append(", ");
    //     }
    //     String sortedString = sorted.toString();
    //     // Recreating the heap because the toString destroys it
    //     heap = sort(map);
    //     return sortedString.substring(0, sortedString.length() - 2) + "]";
    // }

    public void makeCodeFile(String codeFile) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(codeFile));
        for (int i = 0; i < 127; i++) {
            if (dictionary.containsKey((char) i)) {
                bw.write(dictionary.get((char) i) + "\n");
            } else {
                bw.write("\n");
            }
        }
        bw.close();
    }

    public void printDictionary() {
        System.out.println(dictionary.toString());
    }

    public int getFrequency(char c) {
        return map.get(c);
    }

    public String getCode(char c) {
        return dictionary.get(c);
    }

    public HashMap<Character, String> getDictionary() {
        return dictionary;
    }
}
