package logic.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import logic.exceptions.IllegalOperationException;
import logic.loan_classes.DateFormatter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Makes the receipt of each payment.
 */
public class Receipt {
    private final InformationPack info;

    private String  filePath = "pdf_files"+ File.separator +"receipts" + File.separator;
    private File path;

    private final Document document;

    private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private static final Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    private PdfWriter writer;

    public Receipt(InformationPack info) {
        this.info = info;
        document = new Document();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH_mm_ss_aa");
        String fileName = "receipt-" + info.getLoan().getLoanNumber() + "-" + dateFormat.format(new Date()) + ".pdf";
        path = new File(filePath + fileName); 
    }

    public void generateReceipt() {

        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            addImage();
            addTitle();
            addContent();

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addImage() throws IOException, DocumentException {
        Image image = Image.getInstance("pdf_files/images/log-icetex.png");
        image.setWidthPercentage(50);
        document.add(image);
    }

    private void addTitle() throws DocumentException {
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);

        paragraph.add(new Paragraph("Payment Receipt", catFont));
        addEmptyLine(paragraph, 1);

        paragraph.add(new Paragraph("Installment collected by " + info.getCollector().getName()
                    + ", " + DateFormatter.format(new Date()), smallBold));
        addEmptyLine(paragraph, 3);

        document.add(paragraph);
    }

    private void addContent() throws DocumentException {
        Paragraph pLoanID = new Paragraph();
        pLoanID.add(new Phrase("Loan ID: ", smallBold));
        pLoanID.add(new Phrase(String.valueOf(info.getLoan().getLoanNumber()), normalFont));

        Paragraph pPaymentDate = new Paragraph();
        pPaymentDate.add(new Phrase("Payment Date: ", smallBold));
        pPaymentDate.add(new Phrase(DateFormatter.format(info.getPaymentDate()), normalFont));

        Paragraph pCapital = new Paragraph();
        pCapital.add(new Phrase("Capital credit: ", smallBold));
        pCapital.add(new Phrase(String.valueOf(info.getMoneyToCapital()), normalFont));

        Paragraph pInterests = new Paragraph();
        pInterests.add(new Phrase("Interests credit: ", smallBold));
        pInterests.add(new Phrase(String.valueOf(info.getMoneyToInterests()), normalFont));

        Paragraph pBalance = new Paragraph();
        pBalance.add(new Phrase("Loan Balance: ", smallBold));
        pBalance.add(new Phrase(String.valueOf(info.getLoanBalance()), normalFont));

        document.add(pLoanID);
        document.add(pPaymentDate);
        document.add(pCapital);
        document.add(pInterests);
        document.add(pBalance);
    }

    private static void addEmptyLine(Paragraph paragraph, int numberOfParagraphs) {
        for (int i = 0; i < numberOfParagraphs; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    /**
     * It returns the place where the receipt was stored in the system.
     * @throws IllegalOperationException if the receipt hasn't been generated.
     */
    public String getReceiptPath() {
        if (writer == null)
            throw new IllegalOperationException("The receipt has not been generated yet");
        return path.getAbsolutePath();
    }
}
