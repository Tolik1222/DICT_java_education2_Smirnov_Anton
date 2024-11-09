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

        // Показуємо доступні 15 варіантів
        System.out.print("Enter the options (comma-separated) or press Enter for default (rock, paper, scissors).\n" +
                "Available options: rock, fire, scissors, snake, human, tree, wolf, sponge, paper, air, water, dragon, devil, lightning, gun: ");
        scanner.nextLine(); // Перехід на наступну лінію після введення імені
        String optionsInput = scanner.nextLine().trim();

        // Перевіряємо, чи введені користувачем варіанти або вибираємо стандартний набір
        List<String> options = optionsInput.isEmpty() ?
                Arrays.asList("rock", "paper", "scissors") :
                Arrays.asList(optionsInput.split(",")).stream().map(String::trim).toList();

        // Повний список для правил на випадок, якщо користувач вибрав всі 15 варіантів
        List<String> fullOptions = Arrays.asList(
                "rock", "fire", "scissors", "snake", "human", "tree", "wolf", "sponge",
                "paper", "air", "water", "dragon", "devil", "lightning", "gun"
        );

        // Генеруємо правила перемоги для кожного предмета
        Map<String, List<String>> winningRules = new HashMap<>();
        for (int i = 0; i < fullOptions.size(); i++) {
            List<String> beats = new ArrayList<>();
            for (int j = 1; j <= 7; j++) {
                beats.add(fullOptions.get((i + j) % fullOptions.size()));
            }
            winningRules.put(fullOptions.get(i), beats);
        }

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

            // Визначаємо результат гри
            if (computerChoice.equals(userChoice)) {
                System.out.println("There is a draw (" + computerChoice + ")");
                userScore += 50;
            } else if (winningRules.get(userChoice).contains(computerChoice)) {
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
