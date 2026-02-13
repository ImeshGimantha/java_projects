public class Message {
    public static final String RESET_TEXT = "\u001B[0m";
    public static final String RED_TEXT = "\u001B[31m";
    public static final String GREEN_TEXT = "\u001B[32m";

    public static void mainMenu(int completedTaskCount, int incompletedTaskCount){
        System.out.println("Main Menu");
        System.out.println("=========");

        System.out.println("\nYou have " + incompletedTaskCount + " task(s) todo and " + completedTaskCount + " completed task(s)");

        System.out.println("\nPick an option: ");
        System.out.println("(1) Show Task List (by date or project)");
        System.out.println("(2) Add New Task");
        System.out.println("(3) Edit Task (update, mark as done, remove)");
        System.out.println("(4) Save and Quit");

        System.out.print("\nPlease enter your choice [1-4]: ");
    }

    public static void listAllTaskMenu(){
        System.out.println("List All Tasks");
        System.out.println("==============");

        System.out.println("\nPick an option:");
        System.out.println("(1) Show task list by Date [default choice, just press Enter key]");
        System.out.println("(2) Show task list by project");

        System.out.print("\nPlease enter your choice [1-2]: ");
    }

    public static void editTaskMenu(){
        System.out.println("\nEdit Task Options");
        System.out.println("=================");
        System.out.println("\nPick an option:");
        System.out.println("(1) Modify selected task");
        System.out.println("(2) Mark selected task as COMPLETED");
        System.out.println("(3) Delete selected task");
        System.out.println("(4) Return to main menu [default choice, just press Enter]");

        System.out.print("\nPlease enter your choice [1-4]: ");
    }

    public static void separator(char character, int times) {
        for (int i = 0; i < times; i++) System.out.print(character);
        System.out.println();
    }
}