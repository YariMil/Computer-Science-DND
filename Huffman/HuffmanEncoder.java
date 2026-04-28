import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanEncoder {
    private HashMap<Character, String> dictionary;
    private HuffmanCodeGenerator codeGen;

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
                System.out.println(readLine);
                dictionary.put((char) line, readLine);
            }
            line++;
        }
        System.out.println(dictionary.toString());
    }

    public String encodeChar(char input) {
        return "";
    }



}
