import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanEncoder {
    private HashMap<Character, String> dictionary;
    private HuffmanCodeGenerator codeGen;

    // Assuming we already have a codeFile created
    public HuffmanEncoder(String codeFile) {
        try {
            makeDictionary(codeFile);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void makeDictionary(String codeFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(codeFile));
        dictionary = new HashMap<Character, String>();
        int line = 0;
        while (br.ready()) {
            String readLine = br.readLine();
            if (!readLine.equals("")) {
                dictionary.put((char) line, readLine);
            }
            line++;
        }
        br.close();
    }

    public String encodeChar(char input) {
        if (!dictionary.containsKey(input)) {
            return "";
        }
        return dictionary.get(input);
    }

    public void encodeFileToHuffmanCodes(String fileToCompress, String encodedFile)
            throws IOException {
        int charsWritten = 0;
        BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
        BufferedWriter bw = new BufferedWriter(new FileWriter(encodedFile));
        StringBuilder totalString = new StringBuilder();
        while (br.ready()) {
            char c = (char) br.read();
            if (c == '\n') {
                c = (char) 1;
            }
            totalString.append(dictionary.get(c));
            charsWritten += dictionary.get(c).length();
        }
        totalString.append(dictionary.get((char) 26));
        charsWritten += dictionary.get((char) 26).length();
        String str = totalString.toString();
        int zeroesAdded = 0;
        while ((charsWritten + zeroesAdded) % 8 != 0) {
            str += "0";
            zeroesAdded++;
        }
        bw.write(str);
        br.close();
        bw.close();
    }

    public void encodeFile(String fileToCompress) throws IOException {
        int charsWritten = 0;
        BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToCompress + ".huf"));
        StringBuilder totalString = new StringBuilder();
        while (br.ready()) {
            char c = (char) br.read();
            if (c == '\n') {
                c = (char) 1;
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

    public void encodeLong(String fileToCompress, String encodedFile) throws IOException {
        int charsWritten = 0;
        BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
        BufferedWriter bw = new BufferedWriter(new FileWriter(encodedFile));
        StringBuilder totalString = new StringBuilder();
        while (br.ready()) {
            char c = (char) br.read();
            if (c == '\n') {
                c = (char) 1;
            }
            totalString.append(dictionary.get(c));
            charsWritten += dictionary.get(c).length();
        }
        totalString.append(dictionary.get((char) 26));
        charsWritten += dictionary.get((char) 26).length();
        String str = totalString.toString();
        int zeroesAdded = 0;
        while ((charsWritten + zeroesAdded) % 8 != 0) {
            str += "0";
            zeroesAdded++;
        }
        bw.write(str);
        br.close();
        bw.close();
    }

    public int convertCodeToBaseTen(String code) {
        int codeInt = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '1') {
                codeInt += Math.pow(2, code.length() - i - 1);
            }
        }
        return codeInt;
    }


}
