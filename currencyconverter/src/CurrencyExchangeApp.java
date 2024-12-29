import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CurrencyExchangeApp {

    public static void main(String[] args) {
        HashMap<String, Double> exchangeRates = initializeRates();
        displayCurrencyList(exchangeRates);

        Scanner scanner = new Scanner(System.in);

        int sourceChoice = -1;
        int targetChoice = -1;
        String sourceCurrency = null;
        String targetCurrency = null;

        try {
            while (sourceChoice < 1 || sourceChoice > exchangeRates.size()) {
                System.out.print("Enter the number for the currency you want to exchange from: ");
                try {
                    sourceChoice = scanner.nextInt();
                    sourceCurrency = getCurrencyFromChoice(sourceChoice, exchangeRates);
                    if (sourceCurrency == null) {
                        System.out.println("Invalid choice. Please pick a valid source currency.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number.");
                    scanner.next();
                }
            }

            while (targetChoice < 1 || targetChoice > exchangeRates.size()) {
                System.out.print("Enter the number for the currency you want to exchange to: ");
                try {
                    targetChoice = scanner.nextInt();
                    targetCurrency = getCurrencyFromChoice(targetChoice, exchangeRates);
                    if (targetCurrency == null) {
                        System.out.println("Invalid choice. Please pick a valid target currency.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number.");
                    scanner.next();
                }
            }

            System.out.print("Enter the amount you want to convert: ");
            double amount = 0;
            try {
                amount = scanner.nextDouble();
                if (amount <= 0) {
                    throw new IllegalArgumentException("Amount must be positive.");
                }
            } catch (Exception e) {
                System.out.println("Invalid amount. Please restart the program with a valid number.");
                return;
            }

            if (sourceCurrency != null && targetCurrency != null) {
                double result = convertCurrency(sourceCurrency, targetCurrency, amount, exchangeRates);
                System.out.printf("Converted amount: %.2f %s%n", result, targetCurrency);
            } else {
                System.out.println("Error: Invalid selection. Please try again.");
            }
        } finally {
            scanner.close();
        }
    }

    private static HashMap<String, Double> initializeRates() {
        HashMap<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("EUR", 0.92);
        rates.put("INR", 82.5);
        rates.put("GBP", 0.74);
        rates.put("JPY", 131.2);
        rates.put("CAD", 1.27);
        rates.put("AUD", 1.36);
        rates.put("CNY", 7.09);
        rates.put("CHF", 0.91);
        rates.put("ZAR", 15.43);
        rates.put("SGD", 1.35);
        rates.put("MXN", 20.15);
        rates.put("BRL", 5.37);
        rates.put("KRW", 1300.1);
        rates.put("THB", 33.1);
        rates.put("PHP", 55.1);
        rates.put("SEK", 10.2);
        rates.put("NOK", 10.5);
        rates.put("DKK", 6.85);
        rates.put("NZD", 1.44);
        return rates;
    }

    private static void displayCurrencyList(HashMap<String, Double> exchangeRates) {
        System.out.println("Available currencies for conversion:");
        int i = 1;
        for (String currency : exchangeRates.keySet()) {
            System.out.println(i + ". " + currency + " - " + getCountryName(currency));
            i++;
        }
        System.out.println();
    }

    private static String getCurrencyFromChoice(int choice, HashMap<String, Double> exchangeRates) {
        if (choice < 1 || choice > exchangeRates.size()) {
            return null;
        }
        int count = 1;
        for (String currency : exchangeRates.keySet()) {
            if (count == choice) {
                return currency;
            }
            count++;
        }
        return null;
    }

    private static String getCountryName(String currency) {
        switch (currency) {
            case "USD": return "United States";
            case "EUR": return "Eurozone";
            case "INR": return "India";
            case "GBP": return "United Kingdom";
            case "JPY": return "Japan";
            case "CAD": return "Canada";
            case "AUD": return "Australia";
            case "CNY": return "China";
            case "CHF": return "Switzerland";
            case "ZAR": return "South Africa";
            case "SGD": return "Singapore";
            case "MXN": return "Mexico";
            case "BRL": return "Brazil";
            case "KRW": return "South Korea";
            case "THB": return "Thailand";
            case "PHP": return "Philippines";
            case "SEK": return "Sweden";
            case "NOK": return "Norway";
            case "DKK": return "Denmark";
            case "NZD": return "New Zealand";
            default: return "Unknown";
        }
    }

    private static double convertCurrency(String source, String target, double amount, HashMap<String, Double> rates) {
        double sourceRate = rates.get(source);
        double targetRate = rates.get(target);
        return amount * (targetRate / sourceRate);
    }
}
