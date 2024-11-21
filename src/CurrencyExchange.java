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
