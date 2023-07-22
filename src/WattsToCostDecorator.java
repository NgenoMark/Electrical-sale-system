public class WattsToCostDecorator extends VoltsToWattsDecorator {
double cost;
    public WattsToCostDecorator thecost;
    public WattsToCostDecorator(ElectricUnit electricUnit) {
        super(electricUnit);
        this.cost=cost();
    }

    @Override
    public double toWatts() {
        return electricUnit.toWatts();
    }

    public double cost() {
        return (electricUnit.toWatts() / 1000) * 25;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Cost: KES" + cost ;
    }
}

