import java.io.IOException;

public class Message {

    // clear terminal
    public static void clearTerminal() {
        try {
            new ProcessBuilder("clear").inheritIO().start();
            Thread.sleep(10);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void login() {
        clearTerminal();
        System.out.println("+--------------------------------------------------------+");
        System.out.println("|                       LOGIN PAGE                       |");
        System.out.println("+--------------------------------------------------------+");
    }

    public static void homeMenu() {
        clearTerminal();
        System.out.println("+----------------------------------------------------------------+");
        System.out.println("|               WELCOME TO STOCK MANAGEMENT SYSTEM               |");
        System.out.println("+----------------------------------------------------------------+\n");

        System.out.println("""
                [1] Change the Credentials                 [4] Logout
                [2] Supplier Manage                        [5] Exit
                [3] Stock Manage
                """
        );

        System.out.print("Enter an option to continue: ");
    }

    public static void credentialManage() {
        clearTerminal();
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                    CREDENTIAL MANAGE                    |");
        System.out.println("+---------------------------------------------------------+\n");
    }

    public static void supplierManageMenu() {
        clearTerminal();
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                     SUPPLIER MANAGE                     |");
        System.out.println("+---------------------------------------------------------+\n");

        System.out.println("""
                [1] Add Supplier                     [4] View Supplier
                [2] Update Supplier                  [5] Search Supplier
                [3] Delete Supplier                  [6] Back
                """
        );

        System.out.print("Enter an option to continue: ");
    }

    public static void addSupplier() {
        clearTerminal();
        System.out.println("+--------------------------------------------------------+");
        System.out.println("|                      ADD SUPPLIER                      |");
        System.out.println("+--------------------------------------------------------+\n");
    }

    public static void updateSupplier() {
        clearTerminal();
        System.out.println("+-------------------------------------------------------+");
        System.out.println("|                    UPDATE SUPPLIER                    |");
        System.out.println("+-------------------------------------------------------+\n");
    }

    public static void deleteSupplier() {
        clearTerminal();
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                     DELETE SUPPLIER                     |");
        System.out.println("+---------------------------------------------------------+\n");
    }

    public static void viewSupplier() {
        clearTerminal();
        System.out.println("+------------------------------------------------------------+");
        System.out.println("|                       VIEW SUPPLIERS                       |");
        System.out.println("+------------------------------------------------------------+\n");
    }

    public static void searchSupplier() {
        clearTerminal();
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                     SEARCH SUPPLIER                     |");
        System.out.println("+---------------------------------------------------------+\n");
    }

    public static void stockManagementMenu() {
        clearTerminal();
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|                          STOCK MANAGEMENT                          |");
        System.out.println("+--------------------------------------------------------------------+\n");

        System.out.println("""
                [1] Manage Item Categories                [4] View Items
                [2] Add Item                              [5] Rank Items per Unit Price
                [3] Get Items Supplier Wise               [6] Back
                """
        );

        System.out.print("Enter an option to continue: ");
    }

    public static void manageCategoryMenu() {
        clearTerminal();
        System.out.println("+------------------------------------------------------------+");
        System.out.println("|                    MANAGE ITEM CATEGORY                    |");
        System.out.println("+------------------------------------------------------------+\n");

        System.out.println("""
                [1] Add new item category           [3] Update item category
                [2] Delete item category            [4] Back
                """
        );

        System.out.print("Enter an option to continue: ");
    }

    public static void addCategory() {
        clearTerminal();
        System.out.println("+---------------------------------------------------------------------+");
        System.out.println("|                          ADD ITEM CATEGORY                          |");
        System.out.println("+---------------------------------------------------------------------+\n");
    }

    public static void deleteCategory() {
        clearTerminal();
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("|                       DELETE CATEGORY                       |");
        System.out.println("+-------------------------------------------------------------+\n");
    }

    public static void updateCategory() {
        clearTerminal();
        System.out.println("+---------------------------------------------------------------------+");
        System.out.println("|                           UPDATE CATEGORY                           |");
        System.out.println("+---------------------------------------------------------------------+\n");
    }

    public static void addItem() {
        clearTerminal();
        System.out.println("+----------------------------------------------------------------+");
        System.out.println("|                            ADD ITEM                            |");
        System.out.println("+----------------------------------------------------------------+\n");
    }

    public static void getItemsSupplierWise() {
        clearTerminal();
        System.out.println("+---------------------------------------------------------------------+");
        System.out.println("|                           SEARCH SUPPLIER                           |");
        System.out.println("+---------------------------------------------------------------------+\n");
    }

    public static void viewItems() {
        clearTerminal();
        System.out.println("+------------------------------------------------------------------+");
        System.out.println("|                            VIEW ITEMS                            |");
        System.out.println("+------------------------------------------------------------------+\n");
    }

    public static void rankItemsPerPrice() {
        clearTerminal();
        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("|                         RANKED UNIT PRICE                         |");
        System.out.println("+-------------------------------------------------------------------\n+");
    }
}
