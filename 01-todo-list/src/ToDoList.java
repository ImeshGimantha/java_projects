import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> taskList;
    private final Scanner scanner;

    public ToDoList(Scanner scanner){
         taskList = new ArrayList<>();
         this.scanner = scanner;
    }

    public void showTaskList(String sortBy){
        try {
            Message.separator('=', 81);
            System.out.println("      Total Task = " + taskList.size() + "              ( "  + Message.GREEN_TEXT + "Completed = " + completedTaskCount() + "      " + Message.RED_TEXT + "Not Completed = " + incompletedTaskCount() + Message.RESET_TEXT + " )");
            Message.separator('=', 81);

            if (!taskList.isEmpty()) {
                if (sortBy.equals("2")) {
                    String displayFormat = "%-20s %-35s %-12s %-10s";
                    System.out.printf((displayFormat) + "%n", "Project", "Title", "Due Date", "Completed");
                    System.out.printf((displayFormat) + "%n", "=======", "=====", "========", "=========");
                    taskList.stream()
                            .sorted(Comparator.comparing(Task::getProject))
                            .forEach(task -> System.out.printf((displayFormat) + "%n",
                                    task.getProject(),
                                    task.getTitle(),
                                    task.getDueDate(),
                                    (task.status() ? "Yes" : "No")
                            ));
                } else {
                    String displayFormat = "%-12s %-35s %-20s %-10s";
                    System.out.printf((displayFormat) + "%n", "Due Date", "Title", "Project", "Completed");
                    System.out.printf((displayFormat) + "%n", "========", "=====", "=======", "=========");
                    taskList.stream()
                            .sorted(Comparator.comparing(Task::getDueDate))
                            .forEach(task -> System.out.printf((displayFormat) + "%n",
                                    task.getDueDate(),
                                    task.getTitle(),
                                    task.getProject(),
                                    (task.status() ? "Yes" : "No")
                            ));
                }
            } else {
                System.out.println(Message.RED_TEXT + "0 Task(s) Found" + Message.RESET_TEXT);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Can't display task list");
        }
    }

    public void addTask(){
        try {
            System.out.println("Please enter the following details to add a task");
            System.out.print(">>> Task Title: ");
            String title = this.scanner.nextLine();
            System.out.print(">>> Project Name: ");
            String project = this.scanner.nextLine();
            System.out.print(">>> Due Date [example: 2019-02-14]: ");
            LocalDate dueDate = LocalDate.parse(this.scanner.nextLine());

            this.taskList.add(new Task(title, project, dueDate));
            System.out.println(Message.GREEN_TEXT + "New task added successfully\n" + Message.RESET_TEXT);
        } catch (Exception e) {
            System.out.println(Message.RED_TEXT + "Can't create new Task\n" + Message.RESET_TEXT);
        }
    }

    public int completedTaskCount() {
        return (int) taskList.stream().filter(Task::status).count();
    }

    public int incompletedTaskCount() {
        return (int) taskList.stream().filter(task -> !task.status()).count();
    }
}
