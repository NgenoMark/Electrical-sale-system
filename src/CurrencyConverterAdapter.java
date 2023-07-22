
    public class CurrencyConverterAdapter implements CurrencyConverter {
        private AdapteeCurrencyConverter adaptee;

        public CurrencyConverterAdapter(AdapteeCurrencyConverter adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        public double convert(double amount, String fromCurrency, String toCurrency) {
            double exchangeRate = adaptee.getExchangeRate(fromCurrency, toCurrency);
            return amount * exchangeRate;
        }
    }
