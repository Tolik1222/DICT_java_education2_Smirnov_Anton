import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;

public class CreditCalculator {

    public static void main(String[] args) {
        Map<String, String> arguments = parseArguments(args);

        if (!validateArguments(arguments)) {
            System.out.println("Incorrect parameters");
            return;
        }

        String type = arguments.get("type");
        double principal = arguments.containsKey("principal") ? Double.parseDouble(arguments.get("principal")) : -1;
        double payment = arguments.containsKey("payment") ? Double.parseDouble(arguments.get("payment")) : -1;
        int periods = arguments.containsKey("periods") ? Integer.parseInt(arguments.get("periods")) : -1;
        double interest = arguments.containsKey("interest") ? Double.parseDouble(arguments.get("interest")) / 100 / 12 : -1;

        if ("diff".equals(type)) {
            calculateDiffPayment(principal, periods, interest);
        } else if ("annuity".equals(type)) {
            if (payment == -1) {
                calculateAnnuityPayment(principal, periods, interest);
            } else if (principal == -1) {
                calculatePrincipal(payment, periods, interest);
            } else if (periods == -1) {
                calculatePeriods(principal, payment, interest);
            }
        }
    }

    private static Map<String, String> parseArguments(String[] args) {
        Map<String, String> arguments = new HashMap<>();
        for (String arg : args) {
            if (arg.startsWith("--")) {
                String[] parts = arg.substring(2).split("=");
                if (parts.length == 2) {
                    arguments.put(parts[0], parts[1]);
                }
            }
        }
        return arguments;
    }

    private static boolean validateArguments(Map<String, String> arguments) {
        String type = arguments.get("type");
        if (type == null || arguments.get("interest") == null) return false;
        if ("diff".equals(type) && arguments.containsKey("payment")) return false;
        if ("annuity".equals(type) && !arguments.containsKey("principal") && !arguments.containsKey("payment") && !arguments.containsKey("periods")) return false;

        if (arguments.containsKey("principal") && Double.parseDouble(arguments.get("principal")) < 0) return false;
        if (arguments.containsKey("periods") && Integer.parseInt(arguments.get("periods")) < 0) return false;
        if (arguments.containsKey("interest") && Double.parseDouble(arguments.get("interest")) < 0) return false;
        if (arguments.containsKey("payment") && Double.parseDouble(arguments.get("payment")) < 0) return false;

        return true;
    }

    private static void calculateDiffPayment(double principal, int periods, double interest) {
        double totalPayment = 0;
        for (int m = 1; m <= periods; m++) {
            double diffPayment = (principal / periods) + interest * (principal - (principal * (m - 1) / periods));
            totalPayment += Math.ceil(diffPayment);
            System.out.printf("Month %d: payment is %d\n", m, (int) Math.ceil(diffPayment));
        }
        printOverpayment(totalPayment, principal);
    }

    private static void calculateAnnuityPayment(double principal, int periods, double interest) {
        double annuityPayment = principal * (interest * Math.pow(1 + interest, periods)) / (Math.pow(1 + interest, periods) - 1);
        System.out.printf("Your annuity payment = %d!\n", (int) Math.ceil(annuityPayment));
        double totalPayment = Math.ceil(annuityPayment) * periods;
        printOverpayment(totalPayment, principal);
    }

    private static void calculatePrincipal(double payment, int periods, double interest) {
        double principal = payment / ((interest * Math.pow(1 + interest, periods)) / (Math.pow(1 + interest, periods) - 1));
        System.out.printf("Your loan principal = %d!\n", (int) Math.floor(principal));
        double totalPayment = payment * periods;
        printOverpayment(totalPayment, Math.floor(principal));
    }

    private static void calculatePeriods(double principal, double payment, double interest) {
        double periods = Math.log(payment / (payment - interest * principal)) / Math.log(1 + interest);
        int months = (int) Math.ceil(periods);
        int years = months / 12;
        months %= 12;

        if (years == 0) {
            System.out.printf("It will take %d months to repay this loan!\n", months);
        } else if (months == 0) {
            System.out.printf("It will take %d years to repay this loan!\n", years);
        } else {
            System.out.printf("It will take %d years and %d months to repay this loan!\n", years, months);
        }

        double totalPayment = payment * Math.ceil(periods);
        printOverpayment(totalPayment, principal);
    }

    private static void printOverpayment(double totalPayment, double principal) {
        System.out.printf("Overpayment = %d\n", (int) (totalPayment - principal));
    }
}
