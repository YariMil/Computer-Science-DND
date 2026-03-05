import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPredictor {
    private HashMap<WeatherChain, WeatherChain[]> predictor;

    // Please define your own variables and data structures
    public ArrayList<String[]> readData(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                if (predictor.containsKey(split[0])) {
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public MarkovPredictor() {
        predictor = new HashMap<WeatherChain, WeatherChain[]>();
        readData("weather.csv");
    }

    private void addToMarkov(String initial, String next) {

    }


    // Method to predict the next state given a current state
    public String predictNextState(String currentState) {
        return "";

    }

}
