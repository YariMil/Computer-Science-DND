public class GPTTester {
    public static void main(String[] args) {
        MiniGPT g = new MiniGPT("thegreatgatsby.txt", 10);
        g.generateText("sample.txt", 8000);
    }
}
