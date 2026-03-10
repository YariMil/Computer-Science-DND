import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPredictor {
    private HashMap<String, ArrayList<StateChain>> predictor;

    // Please define your own variables and data structures
    private ArrayList<String[]> readData(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                if (!predictor.containsKey(split[0])) {
                    predictor.put(split[0], new ArrayList<StateChain>());
                }
                addToMarkov(split[0], split[1]);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public MarkovPredictor(String filepath) {
        predictor = new HashMap<String, ArrayList<StateChain>>();
        readData(filepath);
    }

    private void addToMarkov(String initial, String next) {
        int weatherSum = getTotalWeather(initial);
        ArrayList<StateChain> nextWeather = predictor.get(initial);
        weatherSum++;
        boolean newAddition = false;
        if (!containsWeather(nextWeather, next)) {
            nextWeather.add(new StateChain(next, weatherSum));
            newAddition = true;
        }
        for (StateChain weather : nextWeather) {
            if (weather.getType().equals(next) && !newAddition) {
                weather.addOne();
            }
            weather.updatePercentage(weatherSum);
        }


    }

    private boolean containsWeather(ArrayList<StateChain> c, String type) {
        for (StateChain weather : c) {
            if (weather.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }


    // Method to predict the next state given a current state
    public String predictNextState(String currentState) {
        double randomPercent = Math.random();
        ArrayList<StateChain> nextWeather = predictor.get(currentState);
        double lastPercent = 0;
        for (StateChain weather : nextWeather) {
            if (randomPercent >= lastPercent
                    && randomPercent < (weather.getPercent() + lastPercent)) {
                return weather.getType() + ": " + weather.getPercent();
            }
            lastPercent += weather.getPercent();
        }
        return "";
    }

    public String toStringTest() {
        return predictor.toString();
    }

    private int getTotalWeather(String initial) {
        int weatherSum = 0;
        ArrayList<StateChain> nextWeather = predictor.get(initial);
        for (StateChain weather : nextWeather) {
            weatherSum += weather.getNumOfType();
        }
        return weatherSum;
    }

}
