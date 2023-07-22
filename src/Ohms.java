public class Ohms implements ElectricUnit {
    private double value;

    public Ohms(double value) {
        this.value = value;
    }

    @Override
    public double toWatts() {
        // Convert Ohms to watts (assuming voltage is 240v)
        return 57600/value;
    }

    @Override
    public String getDescription() {
        return value + " Ohms";
    }
}


