import java.time.LocalDate;

public class CurrentAccount extends Account {

    private final double OVERDRAFT_LIMIT = 1000.0; 


    public CurrentAccount(String accountHolderName, double balance, String username, String password, LocalDate dob) {
        super(accountHolderName, "Current", balance, username, password,dob); 
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= (getBalance() + OVERDRAFT_LIMIT)) {
            
            setBalance(getBalance() - amount);

          
            String transactionDetails = "Withdrawn: " + amount + " | New Balance: " + getBalance();
            getTransactionHistory().add(transactionDetails);

            System.out.println("Withdrawal successful! New balance: " + getBalance());
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance (including overdraft limit).");
        }
    }

    public String toString() {
        return super.toString(); 
    }
}
