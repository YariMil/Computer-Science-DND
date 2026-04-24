import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanEncoding {
    private HashMap<Character, Integer> map;
    private PriorityQueue<HuffmanNode> heap;
    private HuffmanNode root;

    public HuffmanEncoding(String fileName) {
        try {
            map = countFrequency(fileName);
            heap = sort(map);
        } catch (Exception e) {

        }
    }

    public static void huffman(String fileName) {

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
        map.put((char) br.read(), 1);
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

    public String mapToString() {
        return map.toString();
    }

    public String showSorted() {
        HuffmanNode[] arr = new HuffmanNode[map.size()];
        for (int i = 0; i < map.size(); i++) {
            arr[i] = heap.poll();
        }
        StringBuilder sorted = new StringBuilder("[");
        for (HuffmanNode node : arr) {
            sorted.append(node.toString());
            sorted.append(", ");
        }
        String sortedString = sorted.toString();
        // Recreating the heap because the toString destroys it
        heap = sort(map);
        return sortedString.substring(0, sortedString.length() - 2) + "]";
    }
}
