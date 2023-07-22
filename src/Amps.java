public class Amps implements ElectricUnit {
    private double value;

    public Amps(double value) {
        this.value = value;
    }

    @Override
    public double toWatts() {
        // Convert amps to watts (assuming voltage is 240 Volt)
        return value*240;
    }

    @Override
    public String getDescription() {
        return value + " amps";  }
}