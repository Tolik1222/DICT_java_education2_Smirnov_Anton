import java.lang.Math;

public class CreditCalculator {
    public static void main(String[] args) {
        String type = System.getProperty("type");
        String principalStr = System.getProperty("principal");
        String periodsStr = System.getProperty("periods");
        String interestStr = System.getProperty("interest");

        if (type == null || (!type.equals("annuity") && !type.equals("diff")) ||
                principalStr == null || periodsStr == null || interestStr == null) {
            System.out.println("Incorrect parameters");
            return;
        }

        double principal = Double.parseDouble(principalStr);
        int periods = Integer.parseInt(periodsStr);
        double interest = Double.parseDouble(interestStr) / 100 / 12;

        if (type.equals("diff")) {
            calculateDifferentiatedPayments(principal, periods, interest);
        } else if (type.equals("annuity")) {
            // Можна реалізувати функціонал для ануїтетних платежів тут
        }
    }

    public static void calculateDifferentiatedPayments(double principal, int periods, double interest) {
        double totalPayment = 0;
        for (int month = 1; month <= periods; month++) {
            double differentiatedPayment = (principal / periods) +
                    interest * (principal - (principal * (month - 1) / periods));
            System.out.printf("Month %d: payment is %.0f\n", month, Math.ceil(differentiatedPayment));
            totalPayment += Math.ceil(differentiatedPayment);
        }
        System.out.printf("Overpayment = %.0f\n", totalPayment - principal);
    }
}
