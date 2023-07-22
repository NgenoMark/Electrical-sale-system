
    import java.util.Scanner;

    public class CostCalculator {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Select the electric unit to convert to watts:");
            System.out.println("1. Volts");
            System.out.println("2. Amps");
            System.out.println("3. Kilowatts");
            System.out.println("4. Ohms");

            int choice = scanner.nextInt();
            ElectricUnit electricUnit;

            switch (choice) {
                case 1:
                    System.out.println("Enter the value in volts:");
                    double voltsValue = scanner.nextDouble();
                    electricUnit = new Volts(voltsValue);
                    break;
                case 2:
                    System.out.println("Enter the value in amps:");
                    double ampsValue = scanner.nextDouble();
                    electricUnit = new Amps(ampsValue);
                    break;
                case 3:
                    System.out.println("Enter the value in kilowatts:");
                    double kilowattsValue = scanner.nextDouble();
                    electricUnit = new Kilowatts(kilowattsValue);
                    break;
                case 4:
                    System.out.println("Enter the value in ohms:");
                    double ohmsValue = scanner.nextDouble();
                    electricUnit = new Ohms(ohmsValue);
                    break;
                default:
                    System.out.println("Invalid choice. Exiting...");
                    return;
            }

            ElectricUnitDecorator electricUnitToWatts = new VoltsToWattsDecorator(electricUnit);
            ElectricUnitDecorator wattsToCost = new WattsToCostDecorator(electricUnitToWatts);
            System.out.println(wattsToCost.getDescription());

            scanner.close();
        }
    }


