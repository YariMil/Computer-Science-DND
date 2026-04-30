import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanDecoder {
    private HashMap<String, Character> dictionary;
    private HuffmanCodeGenerator codeGen;

    // Assuming we already have a codeFile created
    public HuffmanDecoder(String codeFile) {
        try {
            makeDictionary(codeFile);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void makeDictionary(String codeFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(codeFile));
        dictionary = new HashMap<String, Character>();
        int line = 0;
        while (br.ready()) {
            String readLine = br.readLine();
            if (!readLine.equals("")) {
                dictionary.put(readLine, (char) line);
            }
            line++;
        }
        System.out.println(dictionary.toString());
    }

    public boolean isCode(String binary) {
        return dictionary.containsKey(binary);
    }

    public char decodeChar(String binary) {
        return dictionary.get(binary);
    }

    public void decodeFileFromHuffmanCodes(String encodedFile, String decodedFile)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(encodedFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(decodedFile));
        String currCode = "";
        while (br.ready()) {
            currCode += (char) br.read();
            if (isCode(currCode)) {
                if (decodeChar(currCode) == (char) 26) {
                    br.close();
                    bw.close();
                    return;
                }
                bw.write(decodeChar(currCode));
                currCode = "";
            }
        }
        br.close();
        bw.close();
    }
}
