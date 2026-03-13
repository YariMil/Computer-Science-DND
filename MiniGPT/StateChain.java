public class StateChain {
    private String weatherType;
    private int numOfThisType;
    private double percentage;

    public StateChain(String weather, int totalWeather) {
        this.weatherType = weather;
        numOfThisType = 1;
        updatePercentage(totalWeather);
    }

    public void updatePercentage(int totalWeather) {
        percentage = (double) (numOfThisType) / totalWeather;
    }

    public String getType() {
        return weatherType;
    }

    public double getPercent() {
        return percentage;
    }

    public void addOne() {
        numOfThisType++;
    }

    public int getNumOfType() {
        return numOfThisType;
    }

    public String toString() {
        return weatherType;
    }
}
