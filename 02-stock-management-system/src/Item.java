public class Item {
    private final String code;
    private final String supplierId;
    private final String category;
    private final String description;
    private final double unitPrice;
    private final int quantity;

    public Item(String code, String supplierId, String category, String description, double unitPrice, int quantity) {
        this.code = code;
        this.supplierId = supplierId;
        this.category = category;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getCode() {
        return this.code;
    }

    public String getSupplierId() {
        return this.supplierId;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
