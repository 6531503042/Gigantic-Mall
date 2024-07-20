//package com.gigantic.user.config;
//
//import com.gigantic.entity.User;
//import com.lowagie.text.*;
//import com.lowagie.text.Font;
//import com.lowagie.text.pdf.*;
//import com.gigantic.admin.Config.Export.AbstractExporter;
//
//import javax.servlet.http.HttpServletResponse;
//import java.awt.*;
//import java.io.IOException;
//import java.util.List;
//
//public class UserPDFExporter extends AbstractExporter {
//
//    public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
//        super.setResponseHeader(response, "application/pdf", ".pdf", "users_");
//
//        Document document = new Document(PageSize.A4);
//        PdfWriter.getInstance(document, response.getOutputStream());
//
//        document.open();
//
//        addTitle(document);
//        addTable(document, listUsers);
//        addFooter(document);
//
//        document.close();
//    }
//
//    private void addTitle(Document document) throws DocumentException {
//        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//        titleFont.setSize(20);
//        titleFont.setColor(new Color(0, 102, 204));
//
//        Paragraph title = new Paragraph("List of Users", titleFont);
//        title.setAlignment(Paragraph.ALIGN_CENTER);
//        title.setSpacingAfter(20);
//
//        document.add(title);
//    }
//
//    private void addTable(Document document, List<User> listUsers) throws DocumentException {
//        PdfPTable table = new PdfPTable(6);
//        table.setWidthPercentage(100f);
//        table.setSpacingBefore(10);
//        table.setWidths(new float[]{1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});
//        table.setHeaderRows(1);
//
//        addTableHeader(table);
//        addTableData(table, listUsers);
//
//        document.add(table);
//    }
//
//    private void addTableHeader(PdfPTable table) {
//        PdfPCell cell = new PdfPCell();
//        cell.setBackgroundColor(new Color(0, 102, 204));
//        cell.setPadding(8);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//        font.setColor(Color.WHITE);
//
//        String[] headers = {"User ID", "E-Mail", "First Name", "Last Name", "Roles", "Enabled"};
//        for (String header : headers) {
//            cell.setPhrase(new Phrase(header, font));
//            table.addCell(cell);
//        }
//    }
//
//    private void addTableData(PdfPTable table, List<User> listUsers) {
//        Font font = FontFactory.getFont(FontFactory.HELVETICA);
//        font.setColor(Color.BLACK);
//
//        for (int i = 0; i < listUsers.size(); i++) {
//            User user = listUsers.get(i);
//
//            PdfPCell cell = new PdfPCell();
//            cell.setPadding(5);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            if (i % 2 == 0) {
//                cell.setBackgroundColor(new Color(230, 230, 250));
//            }
//
//            cell.setPhrase(new Phrase(String.valueOf(user.getId()), font));
//            table.addCell(cell);
//
//            cell.setPhrase(new Phrase(user.getEmail(), font));
//            table.addCell(cell);
//
//            cell.setPhrase(new Phrase(user.getFirstName(), font));
//            table.addCell(cell);
//
//            cell.setPhrase(new Phrase(user.getLastName(), font));
//            table.addCell(cell);
//
//            cell.setPhrase(new Phrase(user.getRoles().toString(), font));
//            table.addCell(cell);
//
//            cell.setPhrase(new Phrase(user.isEnabled() ? "Yes" : "No", font));
//            table.addCell(cell);
//        }
//    }
//
//    private void addFooter(Document document) throws DocumentException {
//        Font footerFont = FontFactory.getFont(FontFactory.HELVETICA);
//        footerFont.setSize(10);
//        footerFont.setColor(Color.GRAY);
//
//        Paragraph footer = new Paragraph("End of User List", footerFont);
//        footer.setAlignment(Paragraph.ALIGN_CENTER);
//        footer.setSpacingBefore(20);
//
//        document.add(footer);
//    }
//}
