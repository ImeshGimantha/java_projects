import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CategoryMenu {
    public static ArrayList<Category> categories = new ArrayList<>();
    Scanner scanner;

    public CategoryMenu(Scanner scanner) {
        int categoryChoice;
        this.scanner = scanner;

        do {
            Message.manageCategoryMenu();
            categoryChoice = Integer.parseInt(scanner.nextLine());

            switch (categoryChoice) {
                case 1 -> addCategory();
                case 2 -> deleteCategory();
                case 3 -> updateCategory();
                case 4 -> System.out.println("Back");
            }
        } while (categoryChoice != 4);
    }

    public void addCategory() {
        String category;
        char returnOption;

        Message.addCategory();

        do {
            System.out.print("Enter the new item category: ");
            category = scanner.nextLine();

            categories.add(new Category(category));

            System.out.print("New category added successfully! Do you want to add another category? (y/n): ");
            returnOption = scanner.nextLine().toLowerCase().charAt(0);

            while (!(returnOption == 'y' || returnOption == 'n')) {
                System.out.print("Invalid choice! Please enter valid choice: ");
                returnOption = this.scanner.nextLine().charAt(0);
            }

            System.out.println();
        } while (returnOption == 'y');

        return;
    }

    public void deleteCategory() {
        String providedCategory;
        char returnOption;
        Category foundCategory = null;
        AtomicBoolean status = new AtomicBoolean(false);

        Message.deleteCategory();

        do {
            do {
                System.out.print("Enter category you want to delete: ");
                providedCategory = scanner.nextLine();

                String finalCategory = providedCategory;
                status.set(false);

                if (providedCategory.isEmpty()) {
                    System.out.println("You must enter category name. Try again!\n");
                    status.set(true);
                    continue;
                }

                if (!categories.isEmpty()) {
                    foundCategory = categories.stream()
                            .filter(category -> category.getCategory().equals(finalCategory))
                            .findFirst()
                            .orElse(null);

                    if (foundCategory == null) {
                        System.out.println("Category not Found. Try again!\n");
                        status.set(true);
                    }
                }
            } while (status.get());

            if (foundCategory != null) {
                categories.remove(foundCategory);
                System.out.print("\nCategory deleted successfully! Do you want to delete another category? (y/n): ");
            }
            returnOption = this.scanner.nextLine().charAt(0);

            while (!(returnOption == 'y' || returnOption == 'n')) {
                System.out.print("Invalid choice! Please enter valid choice: ");
                returnOption = this.scanner.nextLine().charAt(0);
            }

            System.out.println();
        } while (returnOption == 'y');
    }

    public void updateCategory() {
        String providedCategory;
        char returnOption;
        Category foundCategory = null;
        AtomicBoolean status = new AtomicBoolean(false);

        Message.updateCategory();

        do {
            do {
                System.out.print("Enter category you want to update: ");
                providedCategory = scanner.nextLine();

                String finalCategory = providedCategory;
                status.set(false);

                if (providedCategory.isEmpty()) {
                    System.out.println("You must enter category name. Try again!\n");
                    status.set(true);
                    continue;
                }

                if (!categories.isEmpty()) {
                    foundCategory = categories.stream()
                            .filter(category -> category.getCategory().equals(finalCategory))
                            .findFirst()
                            .orElse(null);

                    if (foundCategory == null) {
                        System.out.println("Category not Found. Try again!\n");
                        status.set(true);
                    }
                }
            } while (status.get());

            if (foundCategory != null) {
                System.out.print("Enter new category name: ");
                String newCategory = scanner.nextLine();

                categories.set(categories.indexOf(foundCategory), new Category(newCategory));

                System.out.print("\nCategory updated successfully! Do you want to update another category? (y/n): ");
            }
            returnOption = this.scanner.nextLine().charAt(0);

            while (!(returnOption == 'y' || returnOption == 'n')) {
                System.out.print("Invalid choice! Please enter valid choice: ");
                returnOption = this.scanner.nextLine().charAt(0);
            }

            System.out.println();
        } while (returnOption == 'y');
    }
}