// Decorator abstract class
public abstract class ElectricUnitDecorator implements ElectricUnit {
    protected ElectricUnit electricUnit;

    public ElectricUnitDecorator(ElectricUnit electricUnit) {
        this.electricUnit = electricUnit;
    }

    @Override
    public String getDescription() {
        return electricUnit.getDescription();
    }
}



