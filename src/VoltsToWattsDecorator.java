public class VoltsToWattsDecorator extends ElectricUnitDecorator {
    public VoltsToWattsDecorator(ElectricUnit electricUnit) {
        super(electricUnit);
    }

    @Override
    public double toWatts() {
        return electricUnit.toWatts();
    }

    @Override
    public String getDescription() {
        return electricUnit.getDescription() + " = " + electricUnit.toWatts() + " watts";
    }
}