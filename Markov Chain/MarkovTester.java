public class MarkovTester {
    public static void main(String[] args) {
        MarkovPredictor predictor = new MarkovPredictor("weather.csv");
        for (int i = 0; i < 10; i++) {
            System.out.println(predictor.predictNextState("Sunny"));
        }
        MarkovPredictor predictor2 = new MarkovPredictor("activites.csv");
        for (int i = 0; i < 4; i++) {
            System.out.println(predictor2.predictNextState("Sleeping"));
        }
    }
}
