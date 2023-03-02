package services;

import java.net.URL;
import java.util.Objects;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import models.Ticket;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;

/* класс генерує квиток у пдф форматі  */
public class TicketPdfDownloadGenerator {
    private final static Logger logger = Logger.getLogger(TicketPdfDownloadGenerator.class);

    /* метод генерації квитка*/
    public void generate(Ticket ticket, HttpServletResponse response) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font font = new Font(BaseFont.createFont("pdf/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
            PdfPTable pdfPTable = new PdfPTable(4);
            PdfPTable pdfPTable2 = new PdfPTable(1);
            PdfPTable pdfPTable4 = new PdfPTable(1);
            PdfPTable pdfPTable3 = new PdfPTable(2);

            pdfPTable.addCell(new PdfPCell(new Paragraph("Номер квитка", font)));
            pdfPTable.addCell(new PdfPCell(new Paragraph("Кількість пас-рів", font)));
            pdfPTable.addCell(new PdfPCell(new Paragraph("Вартість квитка", font)));
            pdfPTable.addCell(new PdfPCell(new Paragraph("Тривалість круїзу", font)));
            pdfPTable.addCell(new PdfPCell(new Paragraph(String.valueOf(ticket.getId()))));
            pdfPTable.addCell(new PdfPCell(new Paragraph(String.valueOf(ticket.getNumberOfPassengers()))));
            pdfPTable.addCell(new PdfPCell(new Paragraph(ticket.getTotalPrice() + "$")));
            pdfPTable.addCell(new PdfPCell(new Paragraph(String.valueOf(ticket.getDuration()))));
            pdfPTable2.addCell(new PdfPCell(new Paragraph("Ласкаво просимо на круїз '"
                    + ticket.getCruiseName() + "', приємної подорожі", font)));

            pdfPTable3.addCell(Image.getInstance(getUrl("pdf/qr-code.jpeg")));
            pdfPTable3.addCell(Image.getInstance(getUrl("pdf/ship.jpeg")));
            pdfPTable4.addCell(Image.getInstance(getUrl("pdf/back.jpeg")));

            document.add(pdfPTable);
            document.add(pdfPTable2);
            document.add(pdfPTable3);
            document.add(pdfPTable4);

            document.close();
        } catch (Exception e) {
            logger.error("failed to create ticket in PDF", e);
            throw new RuntimeException(e);
        }
    }

    private URL getUrl(String resourceFile) {
        return Objects.requireNonNull(getClass().getClassLoader().getResource(resourceFile));
    }
}