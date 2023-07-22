import java.util.Scanner;



public class FinalCalculator {
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

        ElectricUnitDecorator electricUnitToWatts = new WattsToCostDecorator(new VoltsToWattsDecorator(electricUnit));

        // Show the list of available currencies
        System.out.println("Select your preferred currency:");
        System.out.println("1. USD (United States Dollar)");
        System.out.println("2. EUR (Euro)");
        System.out.println("3. GBP (Great British Pound)");
        // Add more currencies as needed

        int currencyChoice = scanner.nextInt();
        String toCurrency;


        switch (currencyChoice) {
            case 1:
                toCurrency = "USD";

                break;
            case 2:
                toCurrency = "EUR";
                break;
            case 3:
                toCurrency = "GBP";
                break;
            // Add more cases for other currencies
            default:
                System.out.println("Invalid choice. USD will be used as the default currency.");

                toCurrency = "USD";
        }


        // Calculate cost and convert to the chosen currency
        double amountInKES = ((WattsToCostDecorator) electricUnitToWatts).cost();
        AdapteeCurrencyConverter adaptee = new FixedExchangeRates();
        CurrencyConverter adapter = new CurrencyConverterAdapter(adaptee);
        double beforeAmount= amountInKES;
        double convertedAmount = adapter.convert(amountInKES, "KES", toCurrency);

        System.out.println("Initial amount:" + beforeAmount);
        System.out.println("Converted amount: " + convertedAmount);
        scanner.close();
    }
}


