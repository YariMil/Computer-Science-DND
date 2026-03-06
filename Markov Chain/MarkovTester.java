public class MarkovTester {
    public static void main(String[] args) {
        MarkovPredictor predictor = new MarkovPredictor();
        for (int i = 0; i < 100; i++) {
            System.out.println(predictor.predictNextState("Sunny"));
        }
    }
}
