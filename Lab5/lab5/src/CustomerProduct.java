import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//contruct
public class CustomerProduct implements  item {
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isPaid() {
        return paid;
    }
@Override
    public String lineRepresentation() {
        return customerSSN + "," + productID + "," +
               purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "," + paid;
    }
    @Override
    public String getSearchKey() {
    return customerSSN + "," + productID + "," +
           purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
}
 
}
