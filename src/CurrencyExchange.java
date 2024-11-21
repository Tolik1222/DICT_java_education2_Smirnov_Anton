import java.util.Scanner;

public class CurrencyExchange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of mycoins: ");
        double mycoins = scanner.nextDouble();

        double arsRate = 0.82; // Аргентинське песо
        double hnlRate = 0.17; // Гондураська лемпіра
        double audRate = 1.9622; // Австралійський долар
        double madRate = 0.208; // Марокканський дирхам

        System.out.printf("I will get %.2f ARS from the sale of %.2f mycoins.%n", mycoins * arsRate, mycoins);
        System.out.printf("I will get %.2f HNL from the sale of %.2f mycoins.%n", mycoins * hnlRate, mycoins);
        System.out.printf("I will get %.2f AUD from the sale of %.2f mycoins.%n", mycoins * audRate, mycoins);
        System.out.printf("I will get %.2f MAD from the sale of %.2f mycoins.%n", mycoins * madRate, mycoins);
    }
}
