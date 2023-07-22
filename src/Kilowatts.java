public class Kilowatts implements ElectricUnit {
    private double value;

    public Kilowatts(double value) {
        this.value = value;
    }

    @Override
    public double toWatts() {
        // Convert kilowatts to watts
        return value * 1000;
    }

    @Override
    public String getDescription() {
        return value + " kilowatts";
    }}