import java.util.Scanner;

public class Main {
    private static final String username = "nobody";
    private static final String password = "1234";
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        userLogin();
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
}
