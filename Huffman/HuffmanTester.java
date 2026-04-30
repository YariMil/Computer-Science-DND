import java.util.HashMap;

public class HuffmanTester {
    public static void main(String[] args) {
        try {
            // File has 2 a, 1 b, 3 c, 5 t
            HuffmanCodeGenerator encoding = new HuffmanCodeGenerator("huffmanTest.txt");
            encoding.makeCodeFile("huffmanDictionary.txt");
            // This is the file sorted by frequency
            HuffmanEncoder encoder = new HuffmanEncoder("huffmanDictionary.txt");
            // encoding.printDictionary();
            encoder.encodeFileToHuffmanCodes("huffmanTest.txt", "huffmanEncoded.txt");
            HuffmanDecoder decoder = new HuffmanDecoder("huffmanDictionary.txt");
            decoder.decodeFileFromHuffmanCodes("huffmanEncoded.txt", "huffmanDecoded.txt");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
