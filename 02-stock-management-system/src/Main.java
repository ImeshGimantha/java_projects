import java.util.Scanner;

public class Main {
    private static final String username = "nobody";
    private static String password = "1234";
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int homeChoice = 0;

//        userLogin();

        do {
            Message.homeMenu();
            homeChoice = Integer.parseInt(scanner.nextLine());

            switch (homeChoice) {
                case 1 -> credentialManage();
                case 2 -> new SupplierMenu(scanner);
                case 3 -> new StockMenu(scanner, CategoryMenu.categories, SupplierMenu.suppliers);
                case 4 -> userLogin();
                case 5 -> System.out.println("exit");
            }
        } while (homeChoice != 5);
    }

    /*
        get username and password. after that check it with actual username and password.
        it repeats until get correct username and password
    */
    private static void userLogin() {
        String providedUsername = "";
        String providedPassword = "";

        Message.login();

        do {
            System.out.print("username: ");
            providedUsername = scanner.nextLine();

            if (!username.equals(providedUsername)) System.out.println("Invalid username. Please try again!\n");
        } while (!username.equals(providedUsername));

        do {
            System.out.print("password: ");
            providedPassword = scanner.nextLine();

            if (!password.equals(providedPassword)) System.out.println("Invalid password. Please try again!\n");
        } while (!password.equals(providedPassword));
    }

    // change password
    private static void setPassword(String newPassword) {
        password = newPassword;
    }

    // again set user credentials
    private static void credentialManage() {
        String providedUsername = "";
        String currentPassword = "";
        char returnOption;

        Message.credentialManage();

        do {
            System.out.print("Please enter username to verify it's you: ");
            providedUsername = scanner.nextLine();

            if (!providedUsername.equals(username)) System.out.println("Invalid username. Try again!\n");
        } while (!providedUsername.equals(username));

        do {
            System.out.print("Enter your current password: ");
            currentPassword = scanner.nextLine();

            if (!currentPassword.equals(password)) System.out.println("Incorrect password. Try again!\n");
        } while (!currentPassword.equals(password));

        System.out.print("Enter your new password: ");
        setPassword(scanner.nextLine());

        System.out.print("\nPassword changed successfully! Do you want to go home page (y/n): ");
        returnOption = scanner.nextLine().toLowerCase().charAt(0);

        do {
            switch (returnOption) {
                case 'y' -> {
                    return;
                }
                case 'n' -> System.exit(0);
                default -> {
                    System.out.print("Invalid Choice! Enter valid choice: ");
                    returnOption = scanner.nextLine().toLowerCase().charAt(0);
                }
            }
        } while (returnOption != 'y' && returnOption != 'n');
    }
}
