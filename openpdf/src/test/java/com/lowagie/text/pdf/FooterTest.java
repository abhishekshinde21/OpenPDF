package com.lowagie.text.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import java.io.FileOutputStream;

public class FooterTest {
    public static void main(String[] args) {
        try {
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("testTableInFooter.pdf"));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.addCell(new PdfPCell(new Paragraph("Cell 1")));
            table.addCell(new PdfPCell(new Paragraph("Cell 2")));

            Paragraph footerParagraph = new Paragraph();
//            footerParagraph.add("Test");
            footerParagraph.add(table);
            HeaderFooter footer = new HeaderFooter(footerParagraph, false);
            footer.setAlignment(Element.ALIGN_CENTER);

            document.setFooter(footer);

            document.open();

            document.add(new Paragraph("Test Table in Footer"));

            document.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
