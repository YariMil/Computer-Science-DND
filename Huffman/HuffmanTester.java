import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class HuffmanTester {
    public static void main(String[] args) {
        try {
            // File has 2 a, 1 b, 3 c, 5 t
            HuffmanCodeGenerator encoding = new HuffmanCodeGenerator("huffmanTest.txt");
            for (int i = 0; i < 128; i++) {
                if ((char) (i) == encoding.getFrequency((char) i)) {
                    // System.out.println("WOOO");
                }
            }
            encoding.makeCodeFile("huffmanDictionary.txt");
            BufferedReader br = new BufferedReader(new FileReader("huffmanDictionary.txt"));
            for (int i = 0; i < 128; i++) {
                if (br.readLine() == null) {
                    System.out.println(i);
                    System.out.println("WEEEE");
                }
            }
            br.close();
            // // This is the file sorted by frequency
            HuffmanEncoder encoder = new HuffmanEncoder("huffmanDictionary.txt");
            // encoding.printDictionary();
            encoder.encodeFileToHuffmanCodes("huffmanTest.txt", "huffmanEncoded.txt");
            HuffmanDecoder decoder = new HuffmanDecoder("huffmanDictionary.txt");
            decoder.decodeFileFromHuffmanCodes("huffmanEncoded.txt", "huffmanDecoded.txt");
            encoder.encodeFile("huffmanTest.txt");
            decoder.decodeFile("huffmanTest.txt.huf");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
