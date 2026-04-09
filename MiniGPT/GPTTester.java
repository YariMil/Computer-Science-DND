public class GPTTester {
    public static void main(String[] args) {
        MiniGPT g = new MiniGPT("thegreatgatsby.txt", 15);
        // g.generateText("sample.txt", 8000);
        // MiniGPT g2 = new MiniGPT("sample.txt", 15);
        // g2.generateText("sample2.txt", 4000);
        // MiniGPT g3 = new MiniGPT("sample2.txt", 10);
        // g3.generateText("sample3.txt", 2000);
        // MiniGPT g4 = new MiniGPT("sample3.txt", 5);
        // g4.generateText("sample4.txt", 1000);
        MiniGPT weirdAi = new MiniGPT("albequrque.txt", 10);
        weirdAi.generateText("betterAL.txt", 6000);
    }
}
