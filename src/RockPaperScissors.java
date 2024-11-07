import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Map<String, Integer> ratings = new HashMap<>();
        String fileName = "rating.txt";

        // Зчитуємо ім'я користувача
        System.out.print("Enter your name: ");
        String userName = scanner.next();
        System.out.println("Hello, " + userName);

        // Зчитуємо рейтинг з файлу
        try {
            File file = new File(fileName);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNext()) {
                    String name = fileScanner.next();
                    int score = fileScanner.nextInt();
                    ratings.put(name, score);
                }
                fileScanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Rating file not found. Starting with 0 score.");
        }

        int userScore = ratings.getOrDefault(userName, 0);

        // Зчитуємо варіанти для гри
        System.out.print("Enter the options (comma-separated) or press Enter for default (rock, paper, scissors): ");
        scanner.nextLine(); // Move to next line
        String optionsInput = scanner.nextLine().trim();
        List<String> options = optionsInput.isEmpty() ? Arrays.asList("rock", "paper", "scissors") :
                Arrays.asList(optionsInput.split(",")).stream().map(String::trim).toList();
        System.out.println("Okay, let's start");

        // Основний цикл гри
        while (true) {
            System.out.print("Enter your choice or !rating or !exit: ");
            String userChoice = scanner.next().toLowerCase();

            if (userChoice.equals("!exit")) {
                System.out.println("Bye!");
                break;
            } else if (userChoice.equals("!rating")) {
                System.out.println("Your rating: " + userScore);
                continue;
            }

            if (!options.contains(userChoice)) {
                System.out.println("Invalid input");
                continue;
            }

            String computerChoice = options.get(random.nextInt(options.size()));
            int userChoiceIndex = options.indexOf(userChoice);
            int halfSize = (options.size() - 1) / 2;

            List<String> beatenByUser = new ArrayList<>();
            List<String> beatsUser = new ArrayList<>();

            // Формуємо списки перемог і програшів для вибраної опції
            for (int i = 1; i <= halfSize; i++) {
                beatsUser.add(options.get((userChoiceIndex + i) % options.size()));
                beatenByUser.add(options.get((userChoiceIndex - i + options.size()) % options.size()));
            }

            // Визначаємо результат гри
            if (computerChoice.equals(userChoice)) {
                System.out.println("There is a draw (" + computerChoice + ")");
                userScore += 50;
            } else if (beatenByUser.contains(computerChoice)) {
                System.out.println("Well done. The computer chose " + computerChoice + " and failed");
                userScore += 100;
            } else {
                System.out.println("Sorry, but the computer chose " + computerChoice);
            }
        }

        // Зберігаємо оновлений рейтинг у файл
        ratings.put(userName, userScore);
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Map.Entry<String, Integer> entry : ratings.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
            System.out.println("Ratings saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the rating.");
        }
    }
}
