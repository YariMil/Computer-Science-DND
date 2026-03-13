import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPredictor {
    private ArrayList<String> entries;
    private HashMap<String, ArrayList<StateChain>> predictor;

    public MarkovPredictor() {
        predictor = new HashMap<String, ArrayList<StateChain>>();
        entries = new ArrayList<String>();
    }

    public void addToMarkov(String initial, String next) {
        entries.add(initial);
        if (!predictor.containsKey(initial)) {
            predictor.put(initial, new ArrayList<StateChain>());
        }
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
                return weather.getType();
            }
            lastPercent += weather.getPercent();
        }
        return "";
    }

    public String toStringTest() {
        System.out.println("NUM ENTRIES IS " + predictor.size());
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

    public String getRanEntry() {
        int random = (int) (Math.random() * entries.size());
        return (String) (entries.get(random));
    }



}
