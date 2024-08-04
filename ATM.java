import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private Map<String, Account> accounts = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public ATM() {
        // Initialize accounts with account numbers and PIN numbers
        accounts.put("8247319200", new Account("8247319200", 1234, 3000.0));
        accounts.put("6305509790", new Account("6305509790", 5678, 5000.0));
        accounts.put("9390201142", new Account("9390201142", 7113, 150.0));
        accounts.put("9491846923", new Account("9491846923", 1975, 3000.0));
        // Add more accounts as needed
    }

    public void run() {
        while (true) {
            System.out.println("Welcome to ATM");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void login() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();
        Account account = accounts.get(accountNumber);
        if (account != null && account.getPin() == pin) {
            accountMenu(account);
        } else {
            System.out.println("Invalid account number or PIN. Please try again.");
        }
    }

    private void accountMenu(Account account) {
        while (true) {
            System.out.println("Account Menu");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Change PIN");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Your balance is: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    if (amount > account.getBalance()) {
                        System.out.println("Insufficient balance.");
                    } else {
                        account.withdraw(amount);
                        System.out.println("Withdrawal successful. Remaining balance: " + account.getBalance());
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful. New balance: " + account.getBalance());
                    break;
                case 4:
                    System.out.print("Enter new PIN: ");
                    int newPin = scanner.nextInt();
                    account.setPin(newPin);
                    System.out.println("PIN changed successfully.");
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }
}

class Account {
    private String accountNumber;
    private int pin;
    private double balance;

    public Account(String accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}