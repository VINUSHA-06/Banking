import java.time.LocalDate;

public class SavingsAccount extends Account {

   
    public SavingsAccount(String accountHolderName, double balance, String username, String password, LocalDate dob) {
        super(accountHolderName, "Savings", balance, username, password, dob);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Current Balance: " + getBalance());

        if (amount <= 0) {
            System.out.println("Invalid amount. Withdrawal must be greater than zero.");
            return;
        }
        if (amount > getBalance()) {
            System.out.println("Insufficient balance. Cannot withdraw more than the current balance.");
            return;
        }

        if (amount > getMaxWithdrawalLimit()) {
            System.out.println("Cannot withdraw more than " + getMaxWithdrawalLimit() + " for your age group.");
            return;
        }

      
        setBalance(getBalance() - amount);

     
        String transactionDetails = "Withdrawn: " + amount + " | New Balance: " + getBalance();
        getTransactionHistory().add(transactionDetails);

        System.out.println("Withdrawal successful! New balance: " + getBalance());
    }

    private double getMaxWithdrawalLimit() {
        int age = getAge(); 
        if (age < 18) {
            return 5000;
        } else if (age >= 60) {
            return 100000; 
        } else {
            return 10000; 
        }
    }

 
    public String toString() {
        return super.toString(); // Use the parent class's toString method
    }
}
