package Hangman;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("HANGMAN");
            System.out.println("Type 'play' to play the game, 'exit' to quit: ");
            String input = scanner.nextLine();

            if (input.equals("play")) {
                playGame(scanner);
            } else if (input.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid option. Please type 'play' or 'exit'.");
            }
        }
    }

    public static void playGame(Scanner scanner) {
        String[] words = {"python", "java", "javascript", "kotlin"};
        Random random = new Random();
        String word = words[random.nextInt(words.length)];

        char[] hiddenWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            hiddenWord[i] = '-';
        }

        HashSet<Character> guessedLetters = new HashSet<>();
        int attempts = 8;

        while (attempts > 0 && new String(hiddenWord).contains("-")) {
            System.out.println("Word: " + new String(hiddenWord));
            System.out.println("Input a letter: ");
            String guessInput = scanner.nextLine();

            if (guessInput.length() != 1) {
                System.out.println("You should input a single letter.");
                continue;
            }

            char guess = guessInput.charAt(0);

            if (!Character.isLowerCase(guess)) {
                System.out.println("Please enter a lowercase English letter.");
                continue;
            }

            if (guessedLetters.contains(guess)) {
                System.out.println("You've already guessed this letter.");
                continue;
            }

            guessedLetters.add(guess);

            if (word.indexOf(guess) >= 0) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        hiddenWord[i] = guess;
                    }
                }
            } else {
                System.out.println("That letter doesn't appear in the word.");
                attempts--;
            }

            System.out.println("Attempts left: " + attempts);
        }

        if (!new String(hiddenWord).contains("-")) {
            System.out.println("You guessed the word " + word + "!");
            System.out.println("You survived!");
        } else {
            System.out.println("You lost! The word was: " + word);
        }
    }
}