public class RLETester {
    public static void main(String[] args) {
        try {
            RLECompression.encode("HelloWorld.txt");
            RLECompression.decode("HelloWorld.txt.rle");
        } catch (Exception e) {

        }
    }
}
