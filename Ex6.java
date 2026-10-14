import java.util.Scanner;
interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientBalanceException;
    void checkBalance();
}
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
class Bank implements BankAccount {
    String name;
    int accountNumber;
    double balance;

    Bank(String name, int accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Exception: Invalid Deposit Amount");
            return;
        }

        balance = balance + amount;
        System.out.println("Amount Deposited Successfully");
        System.out.println("Available Balance: Rs. " + balance);
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new InsufficientBalanceException("Invalid Withdraw Amount");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        balance = balance - amount;
        System.out.println("Amount Withdrawn Successfully");
        System.out.println("Available Balance: Rs. " + balance);
    }

    public void checkBalance() {
        System.out.println("Available Balance: Rs. " + balance);
    }
}
public class BankOperations {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== BANK ACCOUNT OPERATIONS =====");

        System.out.print("Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Account Number: ");
        int accountNumber = sc.nextInt();

        System.out.print("Initial Balance: ");
        double balance = sc.nextDouble();

        Bank account = new Bank(name, accountNumber, balance);

        System.out.println("\n1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Balance Enquiry");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        try {
            switch (choice) {

                case 1:
                    System.out.print("Deposit Amount: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Withdraw Amount: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    account.checkBalance();
                    break;

                default:
                    System.out.println("Exception: Invalid Choice");
            }
        }
        catch (InsufficientBalanceException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Transaction Failed");
            System.out.println("Available Balance: Rs. " + account.balance);
        }

        sc.close();
    }
}
