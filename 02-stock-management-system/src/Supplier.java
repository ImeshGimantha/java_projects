import java.util.ArrayList;

public class Supplier {
    private String supplierId;
    private String supplierName;

    public Supplier(String supplierId, String supplierName) {
        this.setSupplierId(supplierId);
        this.setSupplierName(supplierName);
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierId() {
        return this.supplierId;
    }

    public String getSupplierName() {
        return this.supplierName;
    }
}
