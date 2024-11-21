import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Scanner;

public class CurrencyExchange {
    private static final HashMap<String, Double> exchangeRateCache = new HashMap<>();
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        while (true) {
            System.out.print("Enter target currency (or press Enter to exit): ");
            String targetCurrency = scanner.nextLine().toUpperCase();
            if (targetCurrency.isEmpty()) break;

            double amount = getValidAmount(scanner);

            double exchangedAmount = getExchangeRate(baseCurrency, targetCurrency) * amount;
            System.out.printf("You will receive %.2f %s.%n", exchangedAmount, targetCurrency);
        }
    }

    private static double getValidAmount(Scanner scanner) {
        double amount = -1;
        while (amount < 0) {
            System.out.print("Enter the amount to exchange: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount < 0) {
                    System.out.println("Amount must be non-negative. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next(); // Очистити неправильне введення
            }
        }
        scanner.nextLine(); // Очистити буфер введення
        return amount;
    }

    private static double getExchangeRate(String base, String target) {
        String cacheKey = base + "-" + target;
        if (exchangeRateCache.containsKey(cacheKey)) {
            System.out.println("Checking the cache...");
            System.out.println("It is in the cache!");
            return exchangeRateCache.get(cacheKey);
        }

        System.out.println("Checking the cache...");
        System.out.println("Sorry, but it is not in the cache!");

        String apiUrl = "http://www.floatrates.com/daily/" + base.toLowerCase() + ".json";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonResponse = new JSONObject(response.body());
            if (!jsonResponse.has(target.toLowerCase())) {
                System.out.printf("Exchange rate for %s is not available.%n", target);
                return 0.0;
            }

            double rate = jsonResponse.getJSONObject(target.toLowerCase()).getDouble("rate");
            exchangeRateCache.put(cacheKey, rate);
            return rate;
        } catch (Exception e) {
            System.out.println("Failed to retrieve exchange rate: " + e.getMessage());
            return 0.0;
        }
    }
}
