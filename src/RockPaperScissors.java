import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] choices = {"rock", "paper", "scissors"};

        System.out.print("Enter rock, paper, or scissors: ");
        String userChoice = scanner.next().toLowerCase();

        if (!userChoice.equals("rock") && !userChoice.equals("paper") && !userChoice.equals("scissors")) {
            System.out.println("Invalid input");
            return;
        }

        String computerChoice = choices[random.nextInt(3)];

        if (userChoice.equals(computerChoice)) {
            System.out.println("There is a draw (" + computerChoice + ")");
        } else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            System.out.println("Well done. The computer chose " + computerChoice + " and failed");
        } else {
            System.out.println("Sorry, but the computer chose " + computerChoice);
        }
    }
}
