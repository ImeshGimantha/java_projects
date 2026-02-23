import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class StockMenu {
    Scanner scanner;
    int stockChoice;
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Category> categories;
    ArrayList<Supplier> suppliers;

    public StockMenu(Scanner scanner, ArrayList<Category> categories, ArrayList<Supplier> suppliers) {
        this.scanner = scanner;
        this.categories = categories;
        this.suppliers = suppliers;

        do {
            Message.stockManagementMenu();
            stockChoice = Integer.parseInt(scanner.nextLine());

            switch (stockChoice) {
                case 1 -> new CategoryMenu(scanner);
                case 2 -> new AddItem(scanner, categories, suppliers, items);
                case 3 -> getItemsSupplierWise();
                case 4 -> viewItems();
                case 5 -> rankItemsPerPrice();
                case 6 -> System.out.println("Back");
            }
        } while (stockChoice != 6);
    }

    public void getItemsSupplierWise() {
        char returnOption;
        String supplierId;
        Supplier foundSupplier = null;
        ArrayList<Item> foundItems = new ArrayList<>();
        AtomicBoolean status = new AtomicBoolean(false);

        String displayFormat = "|   %-9s   |   %-11s   |   %-10s   |  %-11s  |  %-8s  |";

        do {
            foundItems.clear();
            Message.getItemsSupplierWise();

            do {
                System.out.print("Supplier ID  : ");
                supplierId = this.scanner.nextLine();

                String finalSupplierId = supplierId;
                status.set(false);

                if (supplierId.isEmpty()) {
                    System.out.println("You must enter supplier ID. Try again!\n");
                    status.set(true);
                    continue;
                }

                if (!suppliers.isEmpty()) {
                    foundSupplier = suppliers.stream()
                            .filter(supplier -> supplier.getSupplierId().equals(finalSupplierId))
                            .findFirst()
                            .orElse(null);

                    if (foundSupplier == null) {
                        System.out.println("Supplier not Found. Try again!\n");
                        status.set(true);
                    }
                }

            } while (status.get());

            assert foundSupplier != null;
            System.out.println("\nSupplier ID  : " + foundSupplier.getSupplierId());
            System.out.println("Supplier Name: " + foundSupplier.getSupplierName());

            if (!items.isEmpty()) {
                Supplier finalFoundSupplier = foundSupplier;

                items.forEach(item -> {
                    if (item.getSupplierId().equals(finalFoundSupplier.getSupplierId())) {
                        foundItems.add(item);
                    }
                });
            }

            System.out.println("\n+---------------+-----------------+----------------+---------------+------------+");
            System.out.printf((displayFormat) + "%n", "ITEM CODE", "DESCRIPTION", "UNIT PRICE", "QTY ON HAND", "CATEGORY");
            System.out.println("+---------------+-----------------+----------------+---------------+------------+");

            foundItems.forEach(item -> System.out.printf((displayFormat) + "%n",
                    item.getCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQuantity(),
                    item.getCategory()
            ));

            System.out.println("+---------------+-----------------+----------------+---------------+------------+");

            System.out.print("\nItem search successful! Do you want to search another? (y/n): ");
            returnOption = scanner.nextLine().toLowerCase().charAt(0);

            while (!(returnOption == 'y' || returnOption == 'n')) {
                System.out.print("Invalid choice! Please enter valid choice (y/n): ");
                returnOption = this.scanner.nextLine().charAt(0);
            }
        } while (returnOption == 'y');
    }

    public void viewItems() {
        char returnOption;
        ArrayList<Item> foundItems = new ArrayList<>();

        String displayFormat = "|   %-4s   |   %-4s   |   %-11s   |   %-7s   |   %-5s   |";

        Message.viewItems();

        for (Category category: categories) {
            foundItems.clear();

            if (!items.isEmpty()) {
                items.forEach(item -> {
                    if (item.getCategory().equals(category.getCategory())) {
                        foundItems.add(item);
                    }
                });
            }

            System.out.println(category.getCategory() + ":");

            System.out.println("+----------+----------+-----------------+-------------+--------------+");
            System.out.printf((displayFormat) + "%n", "SID", "CODE", "DESCRIPTION", "PRICE", "QUANTITY");
            System.out.println("+----------+----------+-----------------+-------------+--------------+");

            foundItems.forEach(item -> System.out.printf((displayFormat) + "%n",
                    item.getSupplierId(),
                    item.getCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQuantity()
            ));

            System.out.println("+----------+----------+-----------------+-------------+--------------+\n");
        }

        System.out.print("Do you want to go back? (y/n): ");
        returnOption = scanner.nextLine().toLowerCase().charAt(0);

        while (!(returnOption == 'y' || returnOption == 'n')) {
            System.out.print("Invalid choice! Please enter valid choice (y/n): ");
            returnOption = scanner.nextLine().toLowerCase().charAt(0);
        }

        if (returnOption == 'n') System.exit(0);
    }

    public void rankItemsPerPrice() {
        char returnOption;
        ArrayList<Item> sortedItems = new ArrayList<>(items);
        String displayFormat = "|   %-4s   |   %-4s   |   %-11s   |   %-7s   |   %-5s   |   %-8s   |";

        sortedItems.sort(Comparator.comparingDouble(Item::getUnitPrice));

        Message.rankItemsPerPrice();

        System.out.println("+----------+----------+-----------------+-------------+-----------+--------------+");
        System.out.printf((displayFormat) + "%n", "SID", "CODE", "DESCRIPTION", "PRICE", "QTY", "CATEGORY");
        System.out.println("+----------+----------+-----------------+-------------+-----------+--------------+");

        sortedItems.forEach(item -> System.out.printf((displayFormat) + "%n",
                item.getSupplierId(),
                item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getCategory()
        ));

        System.out.println("+----------+----------+-----------------+-------------+-----------+--------------+\n");

        System.out.print("Do you want to go stock page? (y/n): ");
        returnOption = scanner.nextLine().toLowerCase().charAt(0);

        while (!(returnOption == 'y' || returnOption == 'n')) {
            System.out.print("Invalid choice! Please enter valid choice (y/n): ");
            returnOption = scanner.nextLine().toLowerCase().charAt(0);
        }

        if (returnOption == 'n') System.exit(0);
    }
}
