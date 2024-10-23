import java.util.Scanner;

public class CoffeeMachine {
    private int water = 400;
    private int milk = 540;
    private int coffeeBeans = 120;
    private int disposableCups = 9;
    private int money = 550;
    private String state = "choosing action";

    public void processInput(String input) {
        switch (state) {
            case "choosing action":
                handleAction(input);
                break;
            case "choosing coffee":
                handleCoffeeType(input);
                break;
            case "filling water":
                water += Integer.parseInt(input);
                System.out.println("Write how many ml of milk you want to add:");
                state = "filling milk";
                break;
            case "filling milk":
                milk += Integer.parseInt(input);
                System.out.println("Write how many grams of coffee beans you want to add:");
                state = "filling beans";
                break;
            case "filling beans":
                coffeeBeans += Integer.parseInt(input);
                System.out.println("Write how many disposable cups you want to add:");
                state = "filling cups";
                break;
            case "filling cups":
                disposableCups += Integer.parseInt(input);
                state = "choosing action";
                printStatus();
                break;
        }
    }

    private void handleAction(String action) {
        switch (action) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                state = "choosing coffee";
                break;
            case "fill":
                System.out.println("Write how many ml of water you want to add:");
                state = "filling water";
                break;
            case "take":
                takeMoney();
                break;
            case "remaining":
                printStatus();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Try again");
                break;
        }
    }

    private void handleCoffeeType(String coffeeType) {
        if (coffeeType.equals("back")) {
            state = "choosing action";
            return;
        }

        int waterNeeded = 0;
        int milkNeeded = 0;
        int coffeeBeansNeeded = 0;
        int cost = 0;

        switch (coffeeType) {
            case "1": // Espresso
                waterNeeded = 250;
                coffeeBeansNeeded = 16;
                cost = 4;
                break;
            case "2": // Latte
                waterNeeded = 350;
                milkNeeded = 75;
                coffeeBeansNeeded = 20;
                cost = 7;
                break;
            case "3": // Cappuccino
                waterNeeded = 200;
                milkNeeded = 100;
                coffeeBeansNeeded = 12;
                cost = 6;
                break;
            default:
                System.out.println("Try again");
                return;
        }

        if (checkResources(waterNeeded, milkNeeded, coffeeBeansNeeded)) {
            water -= waterNeeded;
            milk -= milkNeeded;
            coffeeBeans -= coffeeBeansNeeded;
            disposableCups--;
            money += cost;
            System.out.println("I have enough resources, making you a coffee!");
        }

        state = "choosing action";
    }

    private boolean checkResources(int waterNeeded, int milkNeeded, int coffeeBeansNeeded) {
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
        if (disposableCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        return true;
    }

    private void printStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(disposableCups + " disposable cups");
        System.out.println(money + " of money");
    }

    private void takeMoney() {
        System.out.println("I gave you " + money);
        money = 0;
        state = "choosing action";
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        machine.printStatus();

        while (true) {
            if (machine.state.equals("choosing action")) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
            }
            String input = scanner.nextLine();
            machine.processInput(input);
        }
    }
}
