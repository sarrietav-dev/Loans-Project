package logic.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

class ReceiptTest {
    @Test
    void createPDF() {
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("pdf_files/receipts/test.pdf"));
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();

        Paragraph paragraph = new Paragraph();
        paragraph.add(new Phrase("hehe: ", smallBold));
        paragraph.add(new Phrase("Haha", smallBold));
        paragraph.add(new Paragraph("Hihi", normalFont));

        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }
}