public class BankingMenu {
    private Manager manager;

    public BankingMenu() {
        manager = new Manager();
    }

    public void showMainMenu() {
        System.out.println("****************************************");
        System.out.println("       Welcome to the Indian Bank!   ");
        System.out.println("****************************************");

        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.println("           Banking System Menu          ");
            System.out.println("----------------------------------------");
            System.out.println("1. Manager Login");
            System.out.println("2. Account Holder Login");
            System.out.println("3. Exit");

            int choice = InputUtility.readInt("Choose an option: ");
            System.out.println("----------------------------------------");

            switch (choice) {
                case 1:
                    System.out.println("#### You chose Manager Login ####");
                    manager.managerMenu();
                    break;

                case 2:
                    System.out.println("#### You chose Account Holder Login ####");
                    manager.accountHolderLogin();
                    break;

                case 3:
                    System.out.println("Exiting the Banking System. Goodbye!");
                    System.out.println("****************************************");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println("----------------------------------------");
            }
        }
    }
}
