import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.util.ArrayList;

public class Manager {
    private final String managerUsername = "admin";
    private final String managerPassword = "admin123";
    private ArrayList<Account> accounts = new ArrayList<>();

    public void managerMenu() {
        String inputUsername = InputUtility.readString("Enter manager username: ");
        String inputPassword = InputUtility.readString("Enter manager password: ");

        if (inputUsername.equals(managerUsername) && inputPassword.equals(managerPassword)) {
            System.out.println("******Manager Login Successful!*******");

            while (true) {
                System.out.println("*******************************");
                System.out.println("\nManager Menu:");
                System.out.println("1. Create Account");
                System.out.println("2. View All Accounts");
                System.out.println("3. Delete Account");
                System.out.println("4. Exit");
                System.out.println("*******************************");

                int choice = InputUtility.readInt("Choose an option: ");

                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        viewAllAccounts();
                        break;
                    case 3:
                        deleteAccount();
                        break;
                    case 4:
                        System.out.println("********Exiting Manager Menu.********");
                        return;
                    default:
                        System.out.println("********Invalid option, please try again.**********");
                }
            }
        } else {
            System.out.println("********Invalid credentials, try again.*******");
        }
    }

    public void createAccount() {
        String name = InputUtility.readString("Enter Account Holder Name: ");

        System.out.println("Choose Account Type:");
        System.out.println("1. Savings");
        System.out.println("2. Current");
        int accountChoice = InputUtility.readInt("Choose 1 or 2: ");

        String type = "";
        if (accountChoice == 1) {
            type = "Savings";
        } else if (accountChoice == 2) {
            type = "Current";
        } else {
            System.out.println("Invalid choice. Account not created.");
            return;
        }

        double balance = InputUtility.readDouble("Enter Initial Balance: ");
        String username = InputUtility.readString("Enter Username for Account: ");

        String tempPassword = "Temp@123";
        System.out.println("A temporary password has been set: " + tempPassword);

       
        String dobString = InputUtility.readString("Enter Date of Birth (yyyy-MM-dd): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate dob = LocalDate.parse(dobString, formatter);
            int age = calculateAge(dob);
            System.out.println("Calculated Age: " + age);

            Account account = null;
            if (type.equalsIgnoreCase("Savings")) {
                account = new SavingsAccount(name, balance, username, tempPassword,dob);
            } else if (type.equalsIgnoreCase("Current")) {
                account = new CurrentAccount(name, balance, username, tempPassword,dob);
            }

            accounts.add(account);
            System.out.println("******Account created successfully! Account Number: " + account.getAccountNumber() + "*********");
        } catch (Exception e) {
            System.out.println("Invalid date format. Account not created.");
        }
    }

    private int calculateAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dob, currentDate).getYears();
    }

    public void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : accounts) {
                System.out.println("\n" + account);
                System.out.println("------------------------------------");
            }
        }
    }

    public void deleteAccount() {
        String accountNumber = InputUtility.readString("Enter the account number of the account to delete: ");
        boolean accountFound = false;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber().equals(accountNumber)) {
                accounts.remove(i);
                System.out.println("Account with account number " + accountNumber + " has been deleted.");
                accountFound = true;
                break;
            }
        }

        if (!accountFound) {
            System.out.println("******Account not found.********");
        }
    }

    public void accountHolderLogin() {
        String username = InputUtility.readString("Enter account holder username: ");
        String password = InputUtility.readString("Enter account holder password: ");

        ArrayList<Account> userAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                userAccounts.add(account);
            }
        }

        if (userAccounts.isEmpty()) {
            System.out.println("******Invalid username or password.********");
            return;
        }

        System.out.println("*****Login successful!****\n**** Welcome " + username + " ******");

        if (password.equals("Temp@123")) {
            System.out.println("You are using a temporary password. Please change your password.");
            changePassword(userAccounts.get(0)); 
        }

        Account selectedAccount = selectAccount(userAccounts);

        accountHolderMenu(selectedAccount);
    }

    private Account selectAccount(ArrayList<Account> userAccounts) {
        Account selectedAccount = userAccounts.get(0); 
        if (userAccounts.size() > 1) {
            System.out.println("You have multiple accounts. Please select one:");
            for (int i = 0; i < userAccounts.size(); i++) {
                System.out.println((i + 1) + ". " + userAccounts.get(i).getAccountType() +
                        " Account (Account Number: " + userAccounts.get(i).getAccountNumber() + ")");
            }

            int choice = InputUtility.readInt("Enter your choice: ");
            if (choice >= 1 && choice <= userAccounts.size()) {
                selectedAccount = userAccounts.get(choice - 1);
            } else {
                System.out.println("******Invalid choice. Login aborted.******");
                return null;
            }
        }
        return selectedAccount;
    }

    private void changePassword(Account account) {
        while (true) {
            System.out.print("Enter new password: ");
            String newPassword = InputUtility.readString("");
            System.out.print("Confirm new password: ");
            String confirmPassword = InputUtility.readString("");

            if (newPassword.equals(confirmPassword)) {
                account.setPassword(newPassword);
                System.out.println("******Password changed successfully.******");
                break;
            } else {
                System.out.println("******Passwords do not match. Please try again.******");
            }
        }
    }

    public void accountHolderMenu(Account account) {
        while (true) {
            System.out.println("========================================");
            System.out.println("\nAccount Holder Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Transaction History");
            System.out.println("4. Change Password");
            System.out.println("5. Logout");
            System.out.println("6. Switch Account");
            System.out.println("========================================");
            int choice = InputUtility.readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = InputUtility.readDouble("");
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = InputUtility.readDouble("");
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.viewTransactionHistory();
                    break;
                case 4:
                    changePassword(account);
                    break;
                case 5:
                    System.out.println("*****Logging out...*****");
                    return;
                case 6:
                    System.out.println("Switching account...");
                    account = switchAccount(account);
                    if (account == null) {
                        System.out.println("No valid account selected. Logging out...");
                        return;
                    }
                    break;
                default:
                    System.out.println("******Invalid option, please try again.*****");
            }
        }
    }

    private Account switchAccount(Account currentAccount) {
        ArrayList<Account> userAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getUsername().equals(currentAccount.getUsername())) {
                userAccounts.add(account);
            }
        }

        return selectAccount(userAccounts); 
    }
}
