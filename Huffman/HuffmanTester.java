import java.util.HashMap;

public class HuffmanTester {
    public static void main(String[] args) {
        try {
            // File has 2 a, 1 b, 3 c, 5 t
            HuffmanEncoding encoding = new HuffmanEncoding("S");
            // This is the file sorted by frequency
            System.out.println(encoding.showSorted());
            // encoding.buildTree();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
