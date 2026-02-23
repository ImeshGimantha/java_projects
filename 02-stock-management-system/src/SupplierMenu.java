import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class SupplierMenu {
    Scanner scanner;
    public static ArrayList<Supplier> suppliers = new ArrayList<>();
    int supplierChoice;

    public SupplierMenu(Scanner scanner) {
        this.scanner = scanner;

        do {
            Message.supplierManageMenu();
            supplierChoice = Integer.parseInt(scanner.nextLine());

            switch (supplierChoice) {
                case 1 -> addSupplier();
                case 2 -> updateSupplier();
                case 3 -> deleteSupplier();
                case 4 -> viewSupplier();
                case 5 -> searchSupplier();
                case 6 -> System.out.println("Back");
            }
        } while (supplierChoice != 6);
    }

    public void addSupplier() {
        char returnOption;
        String supplierId;
        String supplierName;
        AtomicBoolean status = new AtomicBoolean(false);

        Message.addSupplier();

        do {
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
                    suppliers.forEach(supplier -> {
                        if (supplier.getSupplierId().equals(finalSupplierId)) {
                            System.out.println("This ID already exists. Please try another ID!\n");
                            status.set(true);
                        }
                    });
                }
            } while (status.get());

            do {
                System.out.print("Supplier Name: ");
                supplierName = this.scanner.nextLine();

                status.set(false);

                if (supplierName.isEmpty()) {
                    System.out.println("You must enter supplier Name. Try again!\n");
                    status.set(true);
                }
            } while (status.get());

            suppliers.add(new Supplier(supplierId, supplierName));

            System.out.print("\nNew supplier added successfully! Do you want to add another supplier? (y/n): ");
            returnOption = this.scanner.nextLine().charAt(0);

            while (!(returnOption == 'y' || returnOption == 'n')) {
                System.out.print("Invalid choice! Please enter valid choice: ");
                returnOption = this.scanner.nextLine().charAt(0);
            }

            System.out.println();
        } while (returnOption == 'y');

        return;
    }

    public void updateSupplier() {
        char returnOption;
        String supplierId;
        String supplierName;
        Supplier foundSupplier = null;
        AtomicBoolean status = new AtomicBoolean(false);

        Message.updateSupplier();

        do {
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

            do {
                System.out.print("\nEnter new supplier Name: ");
                supplierName = this.scanner.nextLine();

                status.set(false);

                if (supplierName.isEmpty()) {
                    System.out.println("You must enter supplier Name. Try again!");
                    status.set(true);
                }
            } while (status.get());

            suppliers.set(suppliers.indexOf(foundSupplier), new Supplier(supplierId, supplierName));

            System.out.print("\nSupplier updated successfully! Do you want to update another supplier? (y/n): ");
            returnOption = this.scanner.nextLine().charAt(0);

            while (!(returnOption == 'y' || returnOption == 'n')) {
                System.out.print("Invalid choice! Please enter valid choice: ");
                returnOption = this.scanner.nextLine().charAt(0);
            }

            System.out.println();
        } while (returnOption == 'y');
    }

    public void deleteSupplier() {
        char returnOption;
        String supplierId;
        Supplier foundSupplier = null;
        AtomicBoolean status = new AtomicBoolean(false);

        Message.deleteSupplier();

        do {
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

            if (foundSupplier != null) {
                suppliers.remove(foundSupplier);
                System.out.print("\nSupplier deleted successfully! Do you want to delete another supplier? (y/n): ");
            }
            returnOption = this.scanner.nextLine().charAt(0);

            while (!(returnOption == 'y' || returnOption == 'n')) {
                System.out.print("Invalid choice! Please enter valid choice: ");
                returnOption = this.scanner.nextLine().charAt(0);
            }

            System.out.println();
        } while (returnOption == 'y');
    }

    public void viewSupplier() {
        char returnOption;
        Message.viewSupplier();
        String displayFormat = "|      %-10s|      %-10s |";

        System.out.println("+----------------+-----------------+");
        System.out.println("|  SUPPLIER ID   |  SUPPLIER NAME  |");
        System.out.println("+----------------+-----------------+");
        suppliers.forEach(supplier -> {
            System.out.printf((displayFormat) + "\n", supplier.getSupplierId(), supplier.getSupplierName());
        });
        System.out.println("+----------------+-----------------+\n");

        System.out.print("Do you want to go supplier manage page? (y/n): ");
        returnOption = scanner.nextLine().toLowerCase().charAt(0);

        while (!(returnOption == 'y' || returnOption == 'n')) {
            System.out.print("Invalid choice! Please enter valid choice: ");
            returnOption = scanner.nextLine().toLowerCase().charAt(0);
        }

        if (returnOption == 'n') System.exit(0);
    }

    public void searchSupplier() {
        char returnOption;
        String supplierId;
        String supplierName;
        Supplier foundSupplier = null;
        AtomicBoolean status = new AtomicBoolean(false);

        Message.searchSupplier();

        do {
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

            System.out.print("\nDo you want to find another supplier? (y/n): ");
            returnOption = this.scanner.nextLine().charAt(0);

            while (!(returnOption == 'y' || returnOption == 'n')) {
                System.out.print("Invalid choice! Please enter valid choice: ");
                returnOption = this.scanner.nextLine().charAt(0);
            }

            System.out.println();
        } while (returnOption == 'y');
    }
}
