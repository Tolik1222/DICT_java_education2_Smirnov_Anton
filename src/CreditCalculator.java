import java.util.Scanner;

public class CreditCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the loan principal:");
        int principal = scanner.nextInt();

        System.out.println("What do you want to calculate?");
        System.out.println("type \"m\" – for number of monthly payments,");
        System.out.println("type \"p\" – for the monthly payment:");

        String option = scanner.next();

        if ("m".equals(option)) {
            System.out.println("Enter the monthly payment:");
            int monthlyPayment = scanner.nextInt();
            int months = (int) Math.ceil((double) principal / monthlyPayment);
            System.out.println("It will take " + months + " months to repay the loan");
        } else if ("p".equals(option)) {
            System.out.println("Enter the number of months:");
            int months = scanner.nextInt();
            int monthlyPayment = (int) Math.ceil((double) principal / months);
            int lastPayment = principal - (months - 1) * monthlyPayment;
            System.out.println("Your monthly payment = " + monthlyPayment +
                    (lastPayment != monthlyPayment ? " and the last payment = " + lastPayment : ""));
        }
        scanner.close();
    }
}
