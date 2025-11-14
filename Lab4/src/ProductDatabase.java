
public class ProductDatabase extends database {

    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Product createRecordFrom(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 6) {
            return null;
        }
        String productID = parts[0].trim();
        String productName = parts[1].trim();
        String manufacturerName = parts[2].trim();
        String supplierName = parts[3].trim();
        int quantity = Integer.parseInt(parts[4].trim());
        float price = Float.parseFloat(parts[5].trim());
        return new Product(productID, productName, manufacturerName, supplierName, quantity, price);
    }

}
