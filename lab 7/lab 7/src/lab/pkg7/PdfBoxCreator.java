package lab.pkg7f;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class PdfBoxCreator {

    public static void main(String[] args) {
        // Use try-with-resources to ensure the document is closed
        try (PDDocument document = new PDDocument()) {
            
            // 1. Create a new blank page (A4 size is default, or you can specify PDRectangle.LETTER)
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // 2. Create a content stream object
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                // 3. Begin the text block
                contentStream.beginText();
                
                // 4. Set font and size (using a standard built-in font)
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                
                // 5. Set the starting position (X, Y) in PDF points (1/72 inch). 
                // Y is relative to the bottom of the page. 700 is near the top.
                contentStream.newLineAtOffset(50, 700);
                
                // 6. Add the text
                contentStream.showText("Hello, Apache PDFBox!");
                
                // 7. End the text block
                contentStream.endText();
            } // contentStream is automatically closed here
            
            // 8. Save the document to a file
            document.save(new File("PDFBoxHelloWorld.pdf"));
            
            System.out.println("PDF created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}