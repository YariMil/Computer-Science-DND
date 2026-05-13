import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

// This is the entirety of the encoding process in one file (not including nodes themselves)
// Also it now does more than 128 characters
public class HuffmanEncodingFull {

    public static void encode(String fileName) {
        try {
            HashMap<Character, Integer> map = countFrequency(fileName);
            PriorityQueue<HuffmanNode> sortedMap = sort(map);
            HuffmanNode root = buildTree(sortedMap);
            assignCodes(root);
            HashMap<Character, String> encodingDictionary = makeEncodingDictionary(root);
            HashMap<String, Character> decodingDictionary = makeDecodingDictionary(root);
            encodeFile(fileName, encodingDictionary);
        } catch (Exception e) {

        }
    }

    private static HashMap<Character, Integer> countFrequency(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        while (br.ready()) {
            Character c = (char) br.read();
            if (!map.containsKey(c)) {
                if (c == '\n') {
                    c = (char) 10;
                }
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        map.put((char) 26, 1);
        br.close();
        return map;
    }

    private static PriorityQueue<HuffmanNode> sort(HashMap<Character, Integer> map) {
        PriorityQueue<HuffmanNode> heap = new PriorityQueue<HuffmanNode>();
        Object[] keySet = map.keySet().toArray();
        for (int i = 0; i < keySet.length; i++) {
            heap.add(new HuffmanNode((char) keySet[i], (int) map.get(keySet[i])));
        }
        return heap;
    }

    private static HuffmanNode buildTree(PriorityQueue<HuffmanNode> heap) {
        while (!heap.isEmpty()) {
            HuffmanNode leftChild = heap.poll();
            if (!heap.isEmpty()) {
                HuffmanNode rightChild = heap.poll();
                HuffmanNode placeHolderNode =
                        new HuffmanNode(leftChild.getFrequency() + rightChild.getFrequency(),
                                leftChild, rightChild);
                heap.add(placeHolderNode);
            } else {
                // the root of the tree is the only thing left in the heap
                return leftChild;
            }
        }
        // This will never run but its just for compilation
        return new HuffmanNode('0', 0);
    }

    private static void assignCodes(HuffmanNode root) {
        if (root == null) {
            System.out.println("Unable to assign codes; tree not made");
            return;
        } else {
            assignCodesHelper(root, root, false);
        }
    }

    private static void assignCodesHelper(HuffmanNode node, HuffmanNode root, boolean wentRight) {
        if (node != root) {
            node.addToCode(node.getParent().getCode() + (wentRight ? "1" : "0"));
        }
        if (node.getLeftChild() != null) {
            assignCodesHelper(node.getLeftChild(), root, false);
        }
        if (node.getRightChild() != null) {
            assignCodesHelper(node.getRightChild(), root, true);
        }
    }

    private static HashMap<Character, String> makeEncodingDictionary(HuffmanNode root) {
        if (root == null) {
            System.out.println("Unable to make dictionary. Tree is unfinished");
        }
        HashMap<Character, String> encodingDictionary = new HashMap<Character, String>();
        makeEncodingDictionaryHelper(root, encodingDictionary);
        return encodingDictionary;
    }

    private static void makeEncodingDictionaryHelper(HuffmanNode node,
            HashMap<Character, String> dictionary) {
        if (node.isLeaf()) {
            dictionary.put(node.getChar(), node.getCode());
            return;
        } else {
            if (node.getLeftChild() != null) {
                makeEncodingDictionaryHelper(node.getLeftChild(), dictionary);
            }
            if (node.getRightChild() != null) {
                makeEncodingDictionaryHelper(node.getRightChild(), dictionary);
            }
        }
    }

    private static HashMap<String, Character> makeDecodingDictionary(HuffmanNode root) {
        if (root == null) {
            System.out.println("Unable to make dictionary. Tree is unfinished");
        }
        HashMap<String, Character> decodingDictionary = new HashMap<String, Character>();
        makeDecodingDictionaryHelper(root, decodingDictionary);
        return decodingDictionary;
    }

    private static void makeDecodingDictionaryHelper(HuffmanNode node,
            HashMap<String, Character> dictionary) {
        if (node.isLeaf()) {
            dictionary.put(node.getCode(), node.getChar());
            return;
        } else {
            if (node.getLeftChild() != null) {
                makeDecodingDictionaryHelper(node.getLeftChild(), dictionary);
            }
            if (node.getRightChild() != null) {
                makeDecodingDictionaryHelper(node.getRightChild(), dictionary);
            }
        }
    }

    private static void encodeFile(String fileToCompress, HashMap<Character, String> dictionary)
            throws IOException {
        if (fileToCompress == null) {
            throw new IllegalArgumentException();
        }
        int charsWritten = 0;
        BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToCompress + ".huf"));
        StringBuilder totalString = new StringBuilder();
        while (br.ready()) {
            char c = (char) br.read();
            if (c == '\n') {
                c = (char) 10;
            }
            totalString.append(dictionary.get(c));
            charsWritten += dictionary.get(c).length();
        }
        totalString.append(dictionary.get((char) 26));
        charsWritten += dictionary.get((char) 26).length();
        int zeroesAdded = 0;
        while ((charsWritten + zeroesAdded) % 8 != 0) {
            totalString.append("0");
            zeroesAdded++;
        }
        String str = totalString.toString();
        String currChar = "";
        for (int i = 0; i < str.length(); i++) {
            currChar += str.charAt(i);
            if (currChar.length() == 8) {
                int charAscii = convertCodeToBaseTen(currChar);
                bw.write((char) charAscii);
                currChar = "";
            }
        }
        br.close();
        bw.close();
    }

    private static int convertCodeToBaseTen(String code) {
        int codeInt = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '1') {
                codeInt += Math.pow(2, code.length() - i - 1);
            }
        }
        return codeInt;
    }

}
