import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPredictor {
    private HashMap<String, ArrayList<WeatherChain>> predictor;
    private int totalWeather;

    // Please define your own variables and data structures
    public ArrayList<String[]> readData(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                if (!predictor.containsKey(split[0])) {
                    predictor.put(split[0], new ArrayList<WeatherChain>());
                }
                addToMarkov(split[0], split[1]);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public MarkovPredictor() {
        predictor = new HashMap<String, ArrayList<WeatherChain>>();
        readData("weather.csv");
    }

    private void addToMarkov(String initial, String next) {
        totalWeather++;
        ArrayList<WeatherChain> nextWeather = predictor.get(initial);
        if (!containsWeather(nextWeather, next)) {
            nextWeather.add(new WeatherChain(next, totalWeather));
        } else {
            for (WeatherChain weather : nextWeather) {
                if (weather.getType().equals(next)) {
                    weather.addOne();
                }
                weather.updatePercentage(totalWeather);
            }
        }
        
    }

    private boolean containsWeather(ArrayList<WeatherChain> c, String type) {
        for (WeatherChain weather : c) {
            if (weather.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }


    // Method to predict the next state given a current state
    public String predictNextState(String currentState) {
        double randomPercent = Math.random();
        ArrayList<WeatherChain> nextWeather = predictor.get(currentState);
        double lastPercent = 0;
        for (WeatherChain weather : nextWeather) {
            if (randomPercent >= lastPercent
                    && randomPercent < (weather.getPercent() + lastPercent)) {
                return weather.getType();
            }
        }
        return "";
    }

    public String toStringTest() {
        return predictor.toString();
    }

    public void getTotalWeather() {
        
    }

}
