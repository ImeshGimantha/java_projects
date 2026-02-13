import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList todoList = new ToDoList(scanner);
        String filePath = "/home/imesh/Documents/programming/java_projects/01-todo-list/resources/tasks.txt";
        int menuChoice = 0;

        try {
            System.out.println(">>> Welcome to TODO List");

            todoList.readTasksFromFile(filePath);

            while (menuChoice != 4) {
                Message.mainMenu(todoList.completedTaskCount(), todoList.incompletedTaskCount());
                menuChoice = Integer.parseInt(scanner.nextLine());

                switch (menuChoice) {
                    case 1 -> {
                        Message.listAllTaskMenu();
                        todoList.showTaskList(scanner.nextLine());
                    }
                    case 2 -> todoList.addTask();
                    case 3 -> {
                        todoList.listAllTaskWithIndex();
                        int taskNumber = Integer.parseInt(scanner.nextLine());
                        todoList.showSelectedTask(taskNumber);
                        Message.editTaskMenu();
                        todoList.editTask(Integer.parseInt(scanner.nextLine()), taskNumber);
                    }
                    case 4 -> {
                        todoList.saveTasksToFile(filePath);
                        System.out.println(Message.GREEN_TEXT + "\nAll tasks are saved to data file" + Message.GREEN_TEXT);
                    }
                    default -> System.out.println(Message.RED_TEXT + ">>> Incorrect choice! Please choose valid choice" + Message.RESET_TEXT);
                }
            }

            System.out.println("Good Bye!");

        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

        scanner.close();
    }
}
