import java.util.Scanner;

public class CoffeeMachine {
    private static int water = 400;
    private static int milk = 540;
    private static int coffeeBeans = 120;
    private static int disposableCups = 9;
    private static int money = 550;


                printStatus();
            } else if (action.equals("exit")) {
                break;
            }
        }
    }

    private static void printStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(disposableCups + " disposable cups");
        System.out.println(money + " of money");
    }

    private static void buyCoffee(Scanner scanner) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scanner.next();

        if (choice.equals("back")) {
            return;
        }

        int coffeeType = Integer.parseInt(choice);

        if (coffeeType == 1) {
            // Espresso
            if (checkResources(250, 0, 16, 1)) {
                water -= 250;
                coffeeBeans -= 16;
                disposableCups--;
                money += 4;
                System.out.println("I have enough resources, making you a coffee!");
            }
        } else if (coffeeType == 2) {
            // Latte
            if (checkResources(350, 75, 20, 1)) {
                water -= 350;
                milk -= 75;
                coffeeBeans -= 20;
                disposableCups--;
                money += 7;
                System.out.println("I have enough resources, making you a coffee!");
            }
        } else if (coffeeType == 3) {
            // Cappuccino
            if (checkResources(200, 100, 12, 1)) {
                water -= 200;
                milk -= 100;
                coffeeBeans -= 12;
                disposableCups--;
                money += 6;
                System.out.println("I have enough resources, making you a coffee!");
            }
        }
    }

    private static boolean checkResources(int waterNeeded, int milkNeeded, int coffeeBeansNeeded, int cupsNeeded) {
        if (water < waterNeeded) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (milk < milkNeeded) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (coffeeBeans < coffeeBeansNeeded) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }
        if (disposableCups < cupsNeeded) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        return true;
    }

    private static void fillMachine(Scanner scanner) {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeans += scanner.nextInt();

        System.out.println("Write how many disposable cups you want to add:");
        disposableCups += scanner.nextInt();
    }

    private static void takeMoney() {
        System.out.println("I gave you " + money);
        money = 0;
    }
}
