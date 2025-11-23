package lab.pkg7;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class PdfBoxCreator {

    public static void generatePDFCertificate(int studentId, int courseId, String certificateId) {

        String dateIssued = LocalDate.now().toString();

        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                contentStream.newLineAtOffset(50, 750);

                contentStream.showText("Course Completion Certificate");
                contentStream.newLineAtOffset(0, -40);

                contentStream.setFont(PDType1Font.HELVETICA, 14);

                contentStream.showText("Certificate ID: " + certificateId);
                contentStream.newLineAtOffset(0, -25);

                contentStream.showText("Student ID: " + studentId);
                contentStream.newLineAtOffset(0, -25);

                contentStream.showText("Course ID: " + courseId);
                contentStream.newLineAtOffset(0, -25);

                contentStream.showText("Date Issued: " + dateIssued);
                contentStream.newLineAtOffset(0, -40);

                contentStream.showText("Congratulations! You have successfully completed the course.");

                contentStream.endText();
            }

            document.save(new File("certificate_" + certificateId + ".pdf"));
            System.out.println("PDF certificate created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
