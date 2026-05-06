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
        br.close();
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
                if (decodeChar(currCode) == (char) 1) {
                    bw.write("\n");
                } else if (decodeChar(currCode) == (char) 26) {
                    br.close();
                    bw.close();
                    return;
                } else {
                    bw.write(decodeChar(currCode));
                }
                currCode = "";
            }
        }
        br.close();
        bw.close();
    }

    public void decodeFile(String encodedFile) throws IOException {
        if (encodedFile.indexOf(".huf", encodedFile.length() - 4) == -1) {
            throw new IllegalArgumentException();
        }
        BufferedReader br = new BufferedReader(new FileReader(encodedFile));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(encodedFile.substring(0, encodedFile.length() - 4)));
        StringBuilder charsToInts = new StringBuilder();
        while (br.ready()) {
            int c = br.read();
            String toBinary = Integer.toBinaryString(c);
            while (toBinary.length() < 8) {
                toBinary = "0" + toBinary;
            }
            charsToInts.append(toBinary);
        }
        String currCode = "";
        for (int i = 0; i < charsToInts.length(); i++) {
            currCode += charsToInts.charAt(i);
            if (isCode(currCode)) {
                if (decodeChar(currCode) == (char) 1) {
                    bw.write("\n");
                } else if (decodeChar(currCode) == (char) 26) {
                    br.close();
                    bw.close();
                    return;
                } else {
                    bw.write(decodeChar(currCode));
                }
                currCode = "";
            }
        }
        br.close();
        bw.close();
    }
}
