import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public abstract class Account {
    private static int accountNumberSeed = 100000; 
    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;
    private String username;
    private String password;
    private LocalDate dob; 
    private ArrayList<String> transactionHistory = new ArrayList<>();

    public Account(String accountHolderName, String accountType, double balance, String username, String password, LocalDate dob) {
        this.accountNumber = generateAccountNumber();
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        setBalance(balance);  
        this.username = username;
        this.password = password;
        this.dob = dob; 
    }

    
    private String generateAccountNumber() {
        accountNumberSeed++; 
        return "AC" + accountNumberSeed;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

   
    public int getAge() {
        if (dob != null) {
            return Period.between(dob, LocalDate.now()).getYears();
        }
        return 0; 
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        if (newPassword != null && !newPassword.isEmpty()) {
            this.password = newPassword;
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Password update failed. New password cannot be empty.");
        }
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Invalid balance. Balance cannot be negative.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            System.out.println("Amount to Deposit: " + amount);

            setBalance(getBalance() + amount);

            String transactionDetails = "Deposited: " + amount + "  | New Balance: " + getBalance();
            transactionHistory.add(transactionDetails);

            System.out.println("Deposit successful! New balance: " + getBalance());
        } else {
            System.out.println("Invalid deposit amount. Amount must be greater than zero.");
        }
    }

    public abstract void withdraw(double amount);

    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History: ");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public String toString() {
        return "Account Holder: " + accountHolderName + "\n" +
               "Account Number: " + accountNumber + "\n" +
               "Account Type: " + accountType + "\n" +
               "Date of Birth: " + (dob != null ? dob : "Not Provided") + "\n" +
               "Age: " + getAge() + "\n" +
               "Balance: " + balance + "\n" +
               "Username: " + username;
    }
}
