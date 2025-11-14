/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author youssufathalla
 */
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class EmployeeRole {

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;
    private static final java.time.format.DateTimeFormatter FMT
            = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) {
        addProduct(productID, productName, manufacturerName, supplierName, quantity, 0.0f);
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        String key = productID == null ? "" : productID.trim();
        if (!productsDatabase.contains(key)) {
            productsDatabase.insertRecord(new Product(key, productName, manufacturerName, supplierName, quantity, price));
            productsDatabase.saveToFile();
        }
    }

    public Product[] getListOfProducts() {
        ArrayList<item> list = productsDatabase.returnAllRecords();
        Product[] arr = new Product[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = (Product) list.get(i);
        }
        return arr;
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<item> list = customerProductDatabase.returnAllRecords();
        CustomerProduct[] arr = new CustomerProduct[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = (CustomerProduct) list.get(i);
        }
        return arr;
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        String key = productID == null ? "" : productID.trim();
        Product p = (Product) productsDatabase.getRecord(key);
        if (p == null || p.getQuantity() <= 0) {
            return false;
        }
        p.setQuantity(p.getQuantity() - 1);
        customerProductDatabase.insertRecord(new CustomerProduct(customerSSN, key, purchaseDate));
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        return true;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        if (returnDate.isBefore(purchaseDate)) {
            return -1;
        }
        String key = productID == null ? "" : productID.trim();
        Product p = (Product) productsDatabase.getRecord(key);
        if (p == null) {
            return -1;
        }

        String cpKey = customerSSN + "," + key + "," + purchaseDate.format(FMT);
        CustomerProduct cp = (CustomerProduct) customerProductDatabase.getRecord(cpKey);
        if (cp == null) {
            return -1;
        }

        long days = java.time.temporal.ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (days > 14) {
            return -1;
        }

        p.setQuantity(p.getQuantity() + 1);
        customerProductDatabase.deleteRecord(cpKey);

        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        return p.getPrice();
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        ArrayList<item> list = customerProductDatabase.returnAllRecords();
        for (int i = 0; i < list.size(); i++) {
            CustomerProduct cp = (CustomerProduct) list.get(i);
            if (cp.getCustomerSSN().equals(customerSSN)
                    && cp.getPurchaseDate().equals(purchaseDate)
                    && !cp.isPaid()) {
                cp.setPaid(true);
                customerProductDatabase.saveToFile(); // spec: save the update
                return true;
            }
        }
        return false;
    }

    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}
