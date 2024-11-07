import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] choices = {"rock", "paper", "scissors"};
        Map<String, Integer> ratings = new HashMap<>();

        System.out.print("Enter your name: ");
        String userName = scanner.next();
        System.out.println("Hello, " + userName);

        // Читання рейтингу з файлу
        try {
            File file = new File("rating.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String name = fileScanner.next();
                int score = fileScanner.nextInt();
                ratings.put(name, score);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Rating file not found. Starting with 0 score.");
        }

        int userScore = ratings.getOrDefault(userName, 0);

        while (true) {
            System.out.print("Enter rock, paper, scissors, !rating or !exit: ");
            String userChoice = scanner.next().toLowerCase();

            if (userChoice.equals("!exit")) {
                System.out.println("Bye!");
                break;
            } else if (userChoice.equals("!rating")) {
                System.out.println("Your rating: " + userScore);
                continue;
            }

            if (!userChoice.equals("rock") && !userChoice.equals("paper") && !userChoice.equals("scissors")) {
                System.out.println("Invalid input");
                continue;
            }

            String computerChoice = choices[random.nextInt(3)];

            if (userChoice.equals(computerChoice)) {
                System.out.println("There is a draw (" + computerChoice + ")");
                userScore += 50;
            } else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                    (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                    (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
                System.out.println("Well done. The computer chose " + computerChoice + " and failed");
                userScore += 100;
            } else {
                System.out.println("Sorry, but the computer chose " + computerChoice);
            }
        }
    }
}
