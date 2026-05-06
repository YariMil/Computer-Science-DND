import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class HuffmanTester {
    public static void main(String[] args) {
        try {
            // Small huffman test
            HuffmanCodeGenerator encoding = new HuffmanCodeGenerator("smallHuffmanTest.txt");
            // Making sure we don't have null stuff in the dictionary
            for (int i = 0; i < 128; i++) {
                if ((char) (i) == encoding.getFrequency((char) i)) {
                    // System.out.println("WOOO");
                }
            }
            encoding.makeCodeFile("smallHuffmanDictionary.txt");
            BufferedReader br = new BufferedReader(new FileReader("smallHuffmanDictionary.txt"));
            // Making we sure we don't have any weird null lines in the codefile
            for (int i = 0; i < 128; i++) {
                if (br.readLine() == null) {
                    System.out.println(i);
                    System.out.println("WEEEE");
                }
            }
            br.close();
            // Small file just testing special characters and stuff
            HuffmanEncoder encoder = new HuffmanEncoder("smallHuffmanDictionary.txt");
            // encoding.printDictionary();
            encoder.encodeFileToHuffmanCodes("smallHuffmanTest.txt", "smallHuffmanEncoded.txt");
            HuffmanDecoder decoder = new HuffmanDecoder("smallHuffmanDictionary.txt");
            decoder.decodeFileFromHuffmanCodes("smallHuffmanEncoded.txt",
                    "smallHuffmanDecoded.txt");
            encoder.encodeFile("smallHuffmanTest.txt");
            decoder.decodeFile("smallHuffmanTest.txt.huf");

            // Big file - The Great Gatsby 
            HuffmanCodeGenerator bigEncoding = new HuffmanCodeGenerator("huffmanTest.txt");
            bigEncoding.makeCodeFile("huffmanDictionary.txt");
            HuffmanEncoder bigEncoder = new HuffmanEncoder("huffmanDictionary.txt");
            bigEncoder.encodeFileToHuffmanCodes("huffmanTest.txt", "huffmanEncoded.txt");
            HuffmanDecoder bigDecoder = new HuffmanDecoder("huffmanDictionary.txt");
            bigDecoder.decodeFileFromHuffmanCodes("huffmanEncoded.txt", "huffmanDecoded.txt");
            bigEncoder.encodeFile("huffmanTest.txt");
            bigDecoder.decodeFile("huffmanTest.txt.huf");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
