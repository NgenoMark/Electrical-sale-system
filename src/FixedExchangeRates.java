import java.util.HashMap;
import java.util.Map;

public class FixedExchangeRates implements AdapteeCurrencyConverter {
    private static final Map<String, Double> exchangeRates;

    static {
        exchangeRates = new HashMap<>();
        exchangeRates.put("KES_EUR", 0.0063);
        exchangeRates.put("KES_USD",0.007 );
        exchangeRates.put("KES_GBP", 0.0055);
        // Add more exchange rates for other currency pairs as needed
    }

    @Override
    public double getExchangeRate(String fromCurrency, String toCurrency) {
        String currencyPair = fromCurrency + "_" + toCurrency;
        return exchangeRates.getOrDefault(currencyPair, 1.0); // Default to 1 if rate not found (same currency)
    }
}
