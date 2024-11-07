import java.util.Locale;
import java.util.Scanner;

public class CreditCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // Встановлюємо локаль для коректного форматування

        System.out.println("What do you want to calculate?");
        System.out.println("type \"n\" for number of monthly payments,");
        System.out.println("type \"a\" for annuity monthly payment amount,");
        System.out.println("type \"p\" for loan principal:");

        String option = scanner.next();

        if ("n".equals(option)) {
            System.out.println("Enter the loan principal:");
            double principal = scanner.nextDouble();

            System.out.println("Enter the monthly payment:");
            double monthlyPayment = scanner.nextDouble();

            System.out.println("Enter the loan interest:");
            double interest = scanner.nextDouble();

            double i = interest / (12 * 100);
            double months = Math.ceil(Math.log(monthlyPayment / (monthlyPayment - i * principal)) / Math.log(1 + i));

            int years = (int) months / 12;
            int remainingMonths = (int) months % 12;

            if (years > 0) {
                System.out.println("It will take " + years + " years and " + remainingMonths + " months to repay this loan!");
            } else {
                System.out.println("It will take " + remainingMonths + " months to repay this loan!");
            }

        } else if ("a".equals(option)) {
            System.out.println("Enter the loan principal:");
            double principal = scanner.nextDouble();

            System.out.println("Enter the number of periods:");
            int months = scanner.nextInt();

            System.out.println("Enter the loan interest:");
            double interest = scanner.nextDouble();

            double i = interest / (12 * 100);
            double annuityPayment = principal * i * Math.pow(1 + i, months) / (Math.pow(1 + i, months) - 1);

            System.out.println("Your annuity payment = " + Math.ceil(annuityPayment) + "!");
        } else if ("p".equals(option)) {
            System.out.println("Enter the annuity payment:");
            double annuityPayment = scanner.nextDouble();

            System.out.println("Enter the number of periods:");
            int months = scanner.nextInt();

            System.out.println("Enter the loan interest:");
            double interest = scanner.nextDouble();

            double i = interest / (12 * 100);
            double principal = annuityPayment / (i * Math.pow(1 + i, months) / (Math.pow(1 + i, months) - 1));

            System.out.println("Your loan principal = " + Math.floor(principal) + "!");
        }

        scanner.close();
    }
}
