package com.gigantic.admin.Config.Export;

import com.gigantic.entity.User;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UserPDFExporter extends AbstractExporter {

    public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
        super.setResponseHeader(response, "application/pdf", ".pdf", "users_");

        // Set Document PDF as A4 size
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // Set Font, Color, Size for Title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        titleFont.setSize(20);
        titleFont.setColor(new Color(0, 102, 204));

        Paragraph title = new Paragraph("List of Users", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setSpacingAfter(20);

        document.add(title);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        table.setWidths(new float[]{1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});

        writeTableHeader(table);
        writeTableData(table, listUsers);

        document.add(table);

        document.close();
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(0, 102, 204));
        cell.setPadding(8);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("User ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-Mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Roles", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Enabled", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table, List<User> listUsers) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        for (int i = 0; i < listUsers.size(); i++) {
            User user = listUsers.get(i);

            PdfPCell cell = new PdfPCell();
            cell.setPadding(5);
            if (i % 2 == 0) {
                cell.setBackgroundColor(new Color(230, 230, 250));
            }

            cell.setPhrase(new Phrase(String.valueOf(user.getId()), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(user.getEmail(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(user.getFirstName(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(user.getLastName(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(user.getRoles().toString(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(String.valueOf(user.isEnabled()), font));
            table.addCell(cell);
        }
    }
}
