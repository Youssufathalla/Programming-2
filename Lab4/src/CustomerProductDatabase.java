
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends database {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 4) {
            return null;
        }
        try {
            String customerSSN = parts[0].trim();
            String productID = parts[1].trim();
            LocalDate date = LocalDate.parse(parts[2].trim(), FORMATTER);
            String paidTxt = parts[3].trim();
            if (paidTxt.endsWith(".")) {
                paidTxt = paidTxt.substring(0, paidTxt.length() - 1); // tolerate "true."
            }
            boolean paid = Boolean.parseBoolean(paidTxt);
            CustomerProduct cp = new CustomerProduct(customerSSN, productID, date);
            cp.setPaid(paid);
            return cp;
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }
}
