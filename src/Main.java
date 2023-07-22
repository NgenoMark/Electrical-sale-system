import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AdapteeCurrencyConverter adaptee = new FixedExchangeRates();
        CurrencyConverter adapter = new CurrencyConverterAdapter(adaptee);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Kindly enter the amount in KES: ");
        double amountInKES = scanner.nextDouble();

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

        double convertedAmount = adapter.convert(amountInKES, "KES", toCurrency);

        System.out.println("Converted amount: " + convertedAmount + " " + toCurrency);
    }
}
