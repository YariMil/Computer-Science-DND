public class WeatherChain {
    private String weatherType;
    private int numOfThisType;
    private double percentage;

    public WeatherChain(String weather) {
        this.weatherType = weather;
        numOfThisType = 0;
        updatePercentage(numOfThisType);
    }

    public void updatePercentage(int totalWeather) {
        numOfThisType++;
        percentage = (double) (numOfThisType) / totalWeather;
    }

    public String getType() {
        return weatherType;
    }

    public double getPercent() {
        return percentage;
    }
}
