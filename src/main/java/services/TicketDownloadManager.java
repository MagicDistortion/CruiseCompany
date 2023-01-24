package services;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dao.TicketsDAO;
import models.tickets.Ticket;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class TicketDownloadManager {
    private final static Logger logger = Logger.getLogger(TicketDownloadManager.class);
    private final TicketsDAO ticketsDAO = new TicketsDAO();

    public void run(HttpServletRequest request) {
        try {
            Ticket ticket = ticketsDAO.findTicketById(Integer.parseInt(request.getParameter("ticket_id")));
            Document document = new Document();
            OutputStream outputStream = Files.newOutputStream(new File(
                    "C:\\Users\\Magic\\OneDrive\\Рабочий стол\\" + ticket.getCruiseName() + ticket.getId() + ".pdf").toPath());
            PdfWriter.getInstance(document, outputStream);

            document.open();

            Font font = new Font(BaseFont.createFont("c:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
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
            pdfPTable3.addCell(Image.getInstance("image/qr-code.jpeg"));
            pdfPTable3.addCell(Image.getInstance("image/ship.jpeg"));
            pdfPTable4.addCell(Image.getInstance("image/back.jpeg"));

            document.add(pdfPTable);
            document.add(pdfPTable2);
            document.add(pdfPTable3);
            document.add(pdfPTable4);

            document.close();
            outputStream.close();
        } catch (Exception e) {
            logger.error("failed to create ticket in PDF", e);
            throw new RuntimeException(e);
        }
    }
}