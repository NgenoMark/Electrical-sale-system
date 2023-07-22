// Volts class
public class Volts implements ElectricUnit {
    private double value;

    public Volts(double value) {
        this.value = value;
    }

    @Override
    public double toWatts() {
        // Convert volts to watts (assuming resistance is 1 Ohm)
        return value * value;
    }

    @Override
    public String getDescription() {
        return value + " volts";
    }
}

