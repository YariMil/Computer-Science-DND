import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RLECompression {

    // Creates a compressed version with fileName + ".rle.bw"
    public static void compress(String fileName) throws IOException {
        bwTransform(fileName);
        encode(fileName + ".bw");
    }

    // Decompresses a .rle.bw file
    public static void decompress(String fileName) throws IOException {
        decode(fileName);
        invertBWTransform(fileName.substring(0, fileName.length() - 4));
    }

    // Encodes the contents of a file using the RLE compression scheme:
    // single characters are left alone, and runs of 2+ characters are encoded as
    // that letter twice, followed by the length of the run, cast as a char
    public static void encode(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName + ".rle");

        char previousChar = (char) br.read();
        int count = 1;

        while (br.ready()) {
            char c = (char) br.read();
            // TO-DO
            // Now here: do things with the char you just read, dependent on the char you
            // just read
            if (c == previousChar) {
                count++;
            } else {
                if (count > 1) {
                    pw.write("" + previousChar + previousChar + (char) (count + '0'));
                } else {
                    pw.write("" + previousChar);
                }
                count = 1;
            }
            previousChar = c;
        }
        if (count > 1) {
            pw.write("" + previousChar + previousChar + (char) (count + '0'));
        } else {
            pw.write("" + previousChar);
        }

        br.close();
        pw.close();
    }

    // Decodes the above scheme
    public static void decode(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 4));

        char previousChar = (char) br.read();

        while (br.ready()) {
            char c = (char) br.read();
            // TO-DO
            // Now here: do things with the char you just read, dependent on the char you
            // just read
            if (previousChar == c) {
                // Repeated sequence
                c = (char) br.read();
                for (int i = 0; i < (int) (c - '0'); i++) {
                    pw.write(previousChar);
                }
                c = (char) br.read();
            } else {
                pw.write(previousChar);
            }
            previousChar = c;
        }
        if ((int) (previousChar) != 65535) {
            pw.write(previousChar);
        }
        br.close();
        pw.close();
    }

    public static void bwTransform(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        // Add a null character at the beginning, as a
        // "beginning of file" character
        StringBuilder originalText = new StringBuilder("" + '\0');

        while (br.ready()) {
            char c = (char) br.read();
            originalText.append(c);
        }
        br.close();

        String[] rotations = new String[originalText.length()];
        rotations[0] = originalText.toString();
        // TO-DO
        // Now do the Burrows-Wheeler Transform
        for (int i = 0; i < originalText.length() - 1; i++) {
            char lastChar = rotations[i].charAt(rotations[i].length() - 1);
            StringBuilder currentRotation = new StringBuilder();
            currentRotation.append(lastChar);
            currentRotation.append(rotations[i].substring(0, rotations[i].length() - 1));
            rotations[i + 1] = currentRotation.toString();
        }

        // Sort by first letter
        rotations = sortByFirst(rotations);
        // And then write the transformation into a file
        StringBuilder sb = new StringBuilder();
        for (String rotation : rotations) {
            sb.append(rotation.charAt(rotation.length() - 1));
        }
        PrintWriter pw = new PrintWriter(fileName + ".bw");
        pw.write(sb.toString());
        pw.close();
    }

    private static String[] sortByFirst(String[] rotations) {
        ArrayList<String> sorting = new ArrayList<String>();
        for (String rotation : rotations) {
            sorting.add(rotation);
        }
        sorting.sort(String::compareTo);
        String[] sortedArray = new String[sorting.size()];
        for (int i = 0; i < sorting.size(); i++) {
            sortedArray[i] = sorting.get(i);
        }
        return sortedArray;
    }

    private static StringBuilder[] sortByFirst(StringBuilder[] rotations) {
        ArrayList<String> sorting = new ArrayList<String>();
        for (StringBuilder rotation : rotations) {
            sorting.add(rotation.toString());
        }
        sorting.sort(String::compareTo);
        StringBuilder[] sortedArray = new StringBuilder[sorting.size()];
        for (int i = 0; i < sorting.size(); i++) {
            sortedArray[i] = new StringBuilder(sorting.get(i));
        }
        return sortedArray;
    }

    public static void invertBWTransform(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        StringBuilder originalText = new StringBuilder();

        while (br.ready()) {
            char c = (char) br.read();
            originalText.append(c);
        }
        br.close();

        StringBuilder[] reconstructions = new StringBuilder[originalText.length()];
        for (int i = 0; i < reconstructions.length; i++) {
            reconstructions[i] = new StringBuilder("" + originalText.charAt(i));
        }
        // TO-DO
        // Now undo the Burrows-Wheeler transform
        for (int i = 0; i < reconstructions.length - 1; i++) {
            reconstructions = sortByFirst(reconstructions);
            for (int j = 0; j < reconstructions.length; j++) {
                reconstructions[j] =
                        new StringBuilder(originalText.charAt(j) + reconstructions[j].toString());
            }
        }
        // TO-DO
        // And write the appropriate reconstruction into the file, without the null char
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 3));
        for (int i = 0; i < reconstructions.length; i++) {
            if (reconstructions[i].charAt(0) == '\0') {
                pw.write(reconstructions[i].toString().substring(1));
            }
        }
        pw.close();
    }
}
