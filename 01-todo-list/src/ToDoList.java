import java.io.*;
import java.time.LocalDate;
import java.util.*;

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

    public void listAllTaskWithIndex() {
        String displayFormat = "%-5s %-35s %-20s %-12s %-10s";
        System.out.printf((displayFormat) + "%n", "Num", "Title", "Project", "Due Date", "Completed");
        System.out.printf((displayFormat) + "%n", "===", "=====", "=======", "========", "=========");
        taskList.forEach(task -> System.out.printf((displayFormat) + "%n",
                (taskList.indexOf(task) + 1),
                task.getTitle(),
                task.getProject(),
                task.getDueDate(),
                (task.status() ? "Yes" : "No")
        ));

        System.out.print("\n>>> Type a task number: ");
    }

    public void showSelectedTask(int taskNumber) {
            System.out.println("\n>>> Task num " + taskNumber + " is selected:");
            System.out.printf("""
                    Title    : %s
                    Project  : %s
                    Status   : %s
                    Due Date : %s
                    """,
                    taskList.get(taskNumber - 1).getTitle(),
                    taskList.get(taskNumber - 1).getProject(),
                    (taskList.get(taskNumber - 1).status() ? "Yes" : "No"),
                    taskList.get(taskNumber - 1).getDueDate()
            );
    }

    public void editTask(int editTaskChoice, int taskNumber) {
        try {
            switch (editTaskChoice) {
                case 1 -> {
                    System.out.println("ReEnter task details you want to change [default press Enter]: ");
                    System.out.print("Task Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Task Project: ");
                    String project = scanner.nextLine();
                    System.out.print("Due Date: ");
                    String date = this.scanner.nextLine();

                    title = title.isEmpty() ? taskList.get(taskNumber - 1).getTitle() : title;
                    project = project.isEmpty() ? taskList.get(taskNumber - 1).getProject() : project;
                    LocalDate dueDate = date.isEmpty() ? taskList.get(taskNumber - 1).date : LocalDate.parse(date);

                    taskList.set((taskNumber - 1), new Task(title, project, dueDate));

                    System.out.println(Message.GREEN_TEXT + "\nTask " + taskNumber + " is successfully updated!\n" + Message.RESET_TEXT);
                }
                case 2 -> {
                    taskList.get(taskNumber - 1).setStatus(true);
                    System.out.println(Message.GREEN_TEXT + "\nTask " + taskNumber + " is marked as completed!\n" + Message.RESET_TEXT);
                }
                case 3 -> {
                    taskList.remove(taskNumber - 1);
                    System.out.println(Message.GREEN_TEXT + "\nTask " + taskNumber + " is successfully removed!\n" + Message.RESET_TEXT);
                }
            }
        } catch (Exception e) {
            System.out.println("Can't edit task");
        }
    }

    public void saveTasksToFile(String filePath) {
        try (
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("Can't locate file");
        } catch (IOException e) {
            System.out.println("Can't write file: " + e.getMessage());
        }
    }

    public void readTasksFromFile(String filePath) {
        try (
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            taskList = (ArrayList<Task>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Can't locate file");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not Found!");
        } catch (IOException e) {
            System.out.println("Can't read file");
        }
    }

    public int completedTaskCount() {
        return (int) taskList.stream().filter(Task::status).count();
    }

    public int incompletedTaskCount() {
        return (int) taskList.stream().filter(task -> !task.status()).count();
    }
}
