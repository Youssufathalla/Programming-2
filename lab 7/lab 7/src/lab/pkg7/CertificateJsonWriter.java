package lab.pkg7;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class CertificateJsonWriter {

    public static void generateJsonCertificate(int studentId, int courseId, String certificateId) {

        String dateIssued = LocalDate.now().toString();

        // Build JSON manually
        String json = "{\n"
                + "  \"certificateId\": \"" + certificateId + "\",\n"
                + "  \"studentId\": " + studentId + ",\n"
                + "  \"courseId\": " + courseId + ",\n"
                + "  \"dateIssued\": \"" + dateIssued + "\",\n"
                + "  \"message\": \"Congratulations! You have successfully completed the course.\"\n"
                + "}";

        // Save to file
        try (FileWriter writer = new FileWriter("certificate_" + certificateId + ".json")) {
            writer.write(json);
            System.out.println("JSON certificate created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
