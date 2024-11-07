import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter rock, paper, or scissors: ");
        String userChoice = scanner.next().toLowerCase();
        String computerChoice;

        switch (userChoice) {
            case "rock":
                computerChoice = "paper";
                break;
            case "paper":
                computerChoice = "scissors";
                break;
            case "scissors":
                computerChoice = "rock";
                break;
            default:
                System.out.println("Invalid input");
                return;
        }

        System.out.println("Sorry, but the computer chose " + computerChoice);
    }
}
