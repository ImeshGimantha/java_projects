import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList todoList = new ToDoList(scanner);
        int menuChoice = 0;

        try {
            System.out.println(">>> Welcome to TODO List");

            while (menuChoice != 4) {
                Message.mainMenu(todoList.completedTaskCount(), todoList.incompletedTaskCount());
                menuChoice = Integer.parseInt(scanner.nextLine());

                switch (menuChoice) {
                    case 1 -> {
                        Message.listAllTaskMenu();
                        todoList.showTaskList(scanner.nextLine());
                    }
                    case 2 -> todoList.addTask();
                    default -> System.out.println("Bye");
                }
            }

        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        scanner.close();
    }
}
