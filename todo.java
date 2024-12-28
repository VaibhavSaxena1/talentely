
import java.util.*;

public class InteractiveApp {

    public static void main(String[] args) {
        System.out.println("Welcome to the Interactive App!");
        
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Perform Arithmetic Operations");
            System.out.println("2. Manage To-Do List");
            System.out.println("3. Play a Number Guessing Game");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    performArithmetic(scanner);
                    break;
                case 2:
                    manageToDoList(scanner);
                    break;
                case 3:
                    playNumberGuessingGame(scanner);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting the app. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Arithmetic Operations
    private static void performArithmetic(Scanner scanner) {
        System.out.println("\n--- Arithmetic Operations ---");
        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();

        System.out.println("\nSelect an operation:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");

        int operation = scanner.nextInt();
        double result = 0;
        boolean validOperation = true;

        switch (operation) {
            case 1:
                result = num1 + num2;
                break;
            case 2:
                result = num1 - num2;
                break;
            case 3:
                result = num1 * num2;
                break;
            case 4:
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Division by zero is not allowed.");
                    validOperation = false;
                }
                break;
            default:
                System.out.println("Invalid operation selected.");
                validOperation = false;
        }

        if (validOperation) {
            System.out.println("Result: " + result);
        }
    }

    // To-Do List Management
    private static void manageToDoList(Scanner scanner) {
        System.out.println("\n--- To-Do List Management ---");
        List<String> toDoList = new ArrayList<>();
        boolean back = false;

        while (!back) {
            System.out.println("\nSelect an action:");
            System.out.println("1. Add a task");
            System.out.println("2. View tasks");
            System.out.println("3. Remove a task");
            System.out.println("4. Back to main menu");

            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (action) {
                case 1:
                    System.out.print("Enter a new task: ");
                    String task = scanner.nextLine();
                    toDoList.add(task);
                    System.out.println("Task added successfully.");
                    break;
                case 2:
                    System.out.println("\nCurrent To-Do List:");
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.println((i + 1) + ". " + toDoList.get(i));
                    }
                    break;
                case 3:
                    System.out.print("Enter the task number to remove: ");
                    int taskNum = scanner.nextInt();
                    if (taskNum > 0 && taskNum <= toDoList.size()) {
                        toDoList.remove(taskNum - 1);
                        System.out.println("Task removed successfully.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
            }
        }
    }

    // Number Guessing Game
    private static void playNumberGuessingGame(Scanner scanner) {
        System.out.println("\n--- Number Guessing Game ---");
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;
        boolean guessed = false;

        System.out.println("I have selected a number between 1 and 100. Try to guess it!");

        while (!guessed) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == numberToGuess) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                guessed = true;
            } else if (guess < numberToGuess) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }
        }
    }
}
