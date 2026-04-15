public class RLETester {
    public static void main(String[] args) {
        try {
            // Running encode test first on "JavaIssscooooollllll!!!!!"
            // Should be "JavaIss3coo5ll6!!3"
            RLECompression.encode("encodeTest.txt");
            // We are now decoding it and should go back to normal
            RLECompression.decode("encodeTest.txt.rle");
            // Also testing encoding and decoding where the last character
            // is non-repeated
            RLECompression.encode("encodeTest2.txt");
            RLECompression.decode("encodeTest2.txt.rle");
            // Now testing general encode and decode processes
            // If both ciphers work correctly, HelloWorld.txt should remain exactly the same
            // Here there is also a check for numbers and special characters
            RLECompression.compress("HelloWorld.txt");
            RLECompression.decompress("HelloWorld.txt.bw.rle");
            // Also running compress and decompress on a small word to show the rotations work
            // correctly
            // dog.txt.bw.rle should read "g_od" where the _ is the null character
            // dog.txt.bw should read the same because there is nothing to compress in that rotation
            RLECompression.compress("dog.txt");
            // decompression should undo that
            RLECompression.decompress("dog.txt.bw.rle");
        } catch (Exception e) {
            System.out.println(e);
        }
        // Testing out exceptions
        try {
            RLECompression.compress(null);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            RLECompression.bwTransform(null);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            RLECompression.encode(null);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            RLECompression.decompress(null);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            RLECompression.decode(null);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            RLECompression.invertBWTransform(null);
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
