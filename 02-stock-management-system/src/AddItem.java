import java.util.ArrayList;
import java.util.Scanner;

public class AddItem {
    Scanner scanner;
    ArrayList<Item> items;
    ArrayList<Supplier> suppliers;
    ArrayList<Category> categories;

    public AddItem(Scanner scanner, ArrayList<Category> categories, ArrayList<Supplier> suppliers, ArrayList<Item> items) {
        char returnOption;
        String itemCode;
        int supplierNumber;
        int categoryNumber;
        String description;
        double unitPrice;
        int quantity;

        this.scanner = scanner;
        this.suppliers = suppliers;
        this.categories = categories;
        this.items = items;

        String supplierDisplayFormat = "|   %-3s |     %-15s |    %-18s |";
        String categoryDisplayFormat = "|   %-3s |   %-13s   |";

        do {
            Message.addItem();
            boolean categoryAvailability = checkCategoryAvailability(categories);
            System.out.println();
            boolean supplierAvailability = checkSupplierAvailability(suppliers);

            if (!(categoryAvailability && supplierAvailability)) return;

            System.out.print("Item code: ");
            itemCode = scanner.nextLine();

            System.out.println("\nSupplier List:");
            System.out.println("+-------+---------------------+-----------------------+");
            System.out.printf((supplierDisplayFormat) + "%n", "#", "SUPPLIER ID", "SUPPLIER NAME");
            System.out.println("+-------+---------------------+-----------------------+");

            suppliers.forEach(supplier ->
                    System.out.printf((supplierDisplayFormat) + "%n",
                        (suppliers.indexOf(supplier) + 1),
                        supplier.getSupplierId(),
                        supplier.getSupplierName()
                    )
            );

            System.out.println("+-------+---------------------+-----------------------+\n");

            System.out.print("Enter the supplier number: ");
            supplierNumber = Integer.parseInt(scanner.nextLine());

            System.out.println("\nItem Categories:");
            System.out.println("+-------+-------------------+");
            System.out.printf((categoryDisplayFormat) + "%n", "#", "CATEGORY NAME");
            System.out.println("+-------+-------------------+");

            categories.forEach(category ->
                    System.out.printf((categoryDisplayFormat) + "%n",
                            (categories.indexOf(category) + 1),
                            category.getCategory()
                    )
            );

            System.out.println("+-------+-------------------+");

            System.out.print("\nEnter the category number: ");
            categoryNumber = Integer.parseInt(scanner.nextLine());

            System.out.print("\nDescription: ");
            description = scanner.nextLine();

            System.out.print("Unit price: ");
            unitPrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Quantity: ");
            quantity = Integer.parseInt(scanner.nextLine());

            addNewItem(itemCode, supplierNumber, categoryNumber, description, unitPrice, quantity);

            System.out.print("\nNew item added successfully! Do you want to add another item? (y/n): ");
            returnOption = scanner.nextLine().toLowerCase().charAt(0);

            while (!(returnOption == 'y' || returnOption == 'n')) {
                System.out.print("Invalid choice! Please enter valid choice (y/n): ");
                returnOption = this.scanner.nextLine().charAt(0);
            }

            System.out.println(items);
        } while (returnOption == 'y');
    }

    public boolean checkCategoryAvailability(ArrayList<Category> categories) {
        char categoryOption;

        if (categories.isEmpty()) {
            System.out.println("Oops! It seems that you don't have any item categories in the system");
            System.out.print("Do you want to add a new item category? (y/n): ");
            categoryOption = scanner.nextLine().toLowerCase().charAt(0);

            while (!(categoryOption == 'y' || categoryOption == 'n')) {
                System.out.print("Invalid choice! Please enter valid choice: ");
                categoryOption = this.scanner.nextLine().charAt(0);
            }

            if (categoryOption == 'y') new CategoryMenu(scanner).addCategory();
            else return false;

        }

        return true;
    }

    public boolean checkSupplierAvailability(ArrayList<Supplier> suppliers) {
        char supplierOption;

        if (suppliers.isEmpty()) {
            System.out.println("Oops! It seems that you don't have any suppliers in the system");
            System.out.print("Do you want to add a new supplier? (y/n): ");
            supplierOption = scanner.nextLine().toLowerCase().charAt(0);

            while (!(supplierOption == 'y' || supplierOption == 'n')) {
                System.out.print("Invalid choice! Please enter valid choice: ");
                supplierOption = scanner.nextLine().toLowerCase().charAt(0);
            }

            if (supplierOption == 'y') new SupplierMenu(scanner).addSupplier();
            else return false;
        }
        return true;
    }

    public void addNewItem(String code, int supplierNumber, int categoryNumber, String description, double unitPrice, int quantity) {
        String supplierId = this.suppliers.get(supplierNumber - 1).getSupplierId();
        String category = this.categories.get(categoryNumber - 1).getCategory();

        items.add(new Item(code, supplierId, category, description, unitPrice, quantity));
    }
}
