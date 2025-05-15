import java.util.Scanner;

public class ATM {
    private double balance;
    private int pin;

    public ATM(double initialBalance, int pin) {
        this.balance = initialBalance;
        this.pin = pin;
    }

    private boolean verifyPin(Scanner scanner) {
        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();
        if (enteredPin == pin) {
            return true;
        } else {
            System.out.println("Incorrect PIN. Please try again.");
            return false;
        }
    }

    private void displayMenu() {
        System.out.println("\nWelcome to the ATM");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    private void checkBalance() {
        System.out.printf("Your balance is: $%.2f%n", balance);
    }

    private void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Deposited: $%.2f%n", amount);
        } else {
            System.out.println("Invalid deposit amount. Please try again.");
        }
    }

    private void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("Withdrew: $%.2f%n", amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds. Please try again.");
        } else {
            System.out.println("Invalid withdrawal amount. Please try again.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(1000.00, 1234);

        // Authenticate PIN
        boolean authenticated = false;
        for (int attempts = 0; attempts < 3; attempts++) {
            if (atm.verifyPin(scanner)) {
                authenticated = true;
                break;
            }
        }

        if (!authenticated) {
            System.out.println("Too many failed attempts. Exiting.");
            scanner.close();
            return;
        }

        // Main menu loop
        int choice;
        do {
            atm.displayMenu();
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input. Please enter a valid amount.");
                        scanner.next(); // Clear invalid input
                    }
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input. Please enter a valid amount.");
                        scanner.next(); // Clear invalid input
                    }
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
