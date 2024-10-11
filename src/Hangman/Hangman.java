package Hangman;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {

        }

        HashSet<Character> guessedLetters = new HashSet<>();
        int single letter.");
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