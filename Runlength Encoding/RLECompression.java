import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
                    pw.write("" + previousChar + previousChar + (char) (count));
                } else {
                    pw.write("" + previousChar);
                }
                count = 1;
            }
            previousChar = c;
        }
        if (count > 1) {
            pw.write("" + previousChar + previousChar + (char) (count));
        } else {
            pw.write("" + previousChar);
        }

        br.close();
        pw.close();
    }

    // Decodes the above scheme
    public static void decode(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 4));

        char previousChar = (char) br.read();
        boolean decodedADouble = false;

        while (br.ready()) {
            char c = (char) br.read();
            decodedADouble = false;
            // TO-DO
            // Now here: do things with the char you just read, dependent on the char you
            // just read
            if (previousChar == c) {
                // Repeated sequence
                decodedADouble = false;
                c = (char) br.read();
                for (int i = 0; i < (int) c; i++) {
                    pw.write(previousChar);
                }
                decodedADouble = true;
                c = (char) br.read();
            } else {
                pw.write(previousChar);
            }
            previousChar = c;
        }
        System.out.println(decodedADouble);
        if (!decodedADouble) {
            pw.write(previousChar);
        }
        br.close();
        pw.close();
    }

    public static void bwTransform(String fileName) throws IOException {
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
        System.out.println(rotations[0]);
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
        PrintWriter pw = new PrintWriter(fileName + ".bw");
        for (int i = 0; i < rotations.length; i++) {
            pw.write(rotations[i] + "\n");
        }
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

    public static void invertBWTransform(String fileName) throws IOException {
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

        // TO-DO
        // And write the appropriate reconstruction into the file, without the null char
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 3));
        pw.close();
    }
}
