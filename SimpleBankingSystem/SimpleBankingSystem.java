import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SimpleBankingSystem {

    // Class to represent each bank account
    static class BankAccount {
        private String accountID;               // Unique ID for each account
        private String accountHolder;           // Name of the account holder
        private double balance;                 // Current balance of the account
        private List<String> transactionHistory; // List to store all transactions for this account

        // Constructor to initialize the account with an ID, name, and initial balance
        public BankAccount(String accountID, String accountHolder, double initialBalance) {
            this.accountID = accountID;
            this.accountHolder = accountHolder;
            this.balance = initialBalance;
            this.transactionHistory = new ArrayList<>();
            transactionHistory.add("Account created with initial balance: " + initialBalance);
        }

        // Method to check the current balance
        public double checkBalance() {
            return balance;
        }

        // Method to deposit money into the account
        public void deposit(double amount) {
            if (amount > 0) {  // Check for a valid deposit amount
                balance += amount;
                transactionHistory.add("Deposited: " + amount); // Record the transaction
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        // Method to withdraw money from the account
        public void withdraw(double amount) {
            if (amount <= 0) {  // Check for valid withdrawal amount
                System.out.println("Invalid withdrawal amount.");
            } else if (amount > balance) {  // Ensure sufficient funds
                System.out.println("Insufficient funds.");
            } else {
                balance -= amount;
                transactionHistory.add("Withdrew: " + amount); // Record the transaction
            }
        }

        // Method to return the transaction history
        public List<String> getTransactionHistory() {
            return transactionHistory;
        }
    }

    // Main class variables
    private static Map<String, BankAccount> accounts = new HashMap<>(); // Map to store accounts with accountID as key
    private static Scanner scanner = new Scanner(System.in);            // Scanner for user input

    public static void main(String[] args) {
        // Menu-driven program
        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after the integer input

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    checkBalance();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to create a new account
    private static void createAccount() {
        System.out.print("Enter Account ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();

        // Ensure the initial balance is non-negative
        if (balance < 0) {
            System.out.println("Initial balance cannot be negative.");
            return;
        }

        // Check if account ID already exists
        if (accounts.containsKey(id)) {
            System.out.println("Account ID already exists. Please choose a different ID.");
            return;
        }

        // Create and store the new account in the HashMap
        BankAccount account = new BankAccount(id, name, balance);
        accounts.put(id, account);
        System.out.println("Account created successfully.");
    }

    // Method to check the balance of an existing account
    private static void checkBalance() {
        BankAccount account = findAccount(); // Retrieve account using ID
        if (account != null) {
            System.out.println("Current balance: " + account.checkBalance());
        }
    }

    // Method to deposit money into an account
    private static void deposit() {
        BankAccount account = findAccount(); // Retrieve account using ID
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Deposit successful.");
        }
    }

    // Method to withdraw money from an account
    private static void withdraw() {
        BankAccount account = findAccount(); // Retrieve account using ID
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
            System.out.println("Withdrawal successful.");
        }
    }

    // Method to view the transaction history of an account
    private static void viewTransactionHistory() {
        BankAccount account = findAccount(); // Retrieve account using ID
        if (account != null) {
            System.out.println("Transaction History:");
            for (String transaction : account.getTransactionHistory()) {
                System.out.println(transaction); // Display each transaction
            }
        }
    }

    // Helper method to find an account by its ID
    private static BankAccount findAccount() {
        System.out.print("Enter Account ID: ");
        String id = scanner.nextLine();
        BankAccount account = accounts.get(id); // Retrieve account from HashMap
        if (account == null) {
            System.out.println("Account not found.");
        }
        return account;
    }
}
