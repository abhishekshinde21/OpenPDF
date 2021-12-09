package com.lowagie.text.pdf;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

// CS427 Issue link: https://github.com/LibrePDF/OpenPDF/issues/615
public class NestedHTMLTest {
    public static void main(String[] args) {
        try {
            // Derived this test case from https://github.com/LibrePDF/OpenPDF/issues/615
            // Arrange
            Document doc = new Document(PageSize.A4, 50, 50, 100, 50);

            PdfWriter.getInstance(doc, new FileOutputStream("nested.pdf"));
            doc.open();

            String htmlText = "<br><br><br>" +
                    "<ul>\n" +
                    "\t<li>Level 1</li>\n" +
                    "\t<li> Level 2 items:\n" +
                    "\t\t<ul>\n" +
                    "\t\t\t<li>Level 2</li>\n" +
                    "\t\t\t<li>Level 2</li>\n" +
                    "\t\t</ul>\n" +
                    "\t</li>\n" +
                    "\t<li>Level 1</li>\n" +
                    "</ul>";
            final StringReader reader = new StringReader(htmlText);
            final StyleSheet styleSheet = new StyleSheet();
            final Map<String, Object> interfaceProps = new HashMap<>();

            // Expected Entity
            List expectedPrimaryList = new List(5);
            List subList = new List(5);

            ListItem li1 = new ListItem();
            ListItem li2 = new ListItem();
            ListItem li3 = new ListItem();
            ListItem sli1 = new ListItem();
            ListItem sli2 = new ListItem();

            li1.add(new Chunk("Level 1"));
            li1.add(new Chunk("\n"));
            li2.add(new Chunk("Level 2 items:"));
            li2.add(new Chunk("\n"));
            li3.add(new Chunk("Level 1"));
            li3.add(new Chunk("\n"));
            sli1.add(new Chunk("Level 2"));
            sli1.add(new Chunk("\n"));
            sli2.add(new Chunk("Level 2"));
            sli2.add(new Chunk("\n"));

            subList.add(sli1);
            subList.add(sli2);
            expectedPrimaryList.add(li1);
            expectedPrimaryList.add(li2);
            expectedPrimaryList.add(subList);
            expectedPrimaryList.add(li3);

            // Act
            final java.util.List<Element> elements = HTMLWorker.parseToList(reader, styleSheet, interfaceProps);
            for (Element element : elements) {
                doc.add(element);
            }
            List actualPrimaryList = ((List) elements.get(1));
            List actualSubList = ((List) actualPrimaryList.getItems().get(2));
            List expectedSubList = ((List) expectedPrimaryList.getItems().get(2));

            // Assert
            Assertions.assertEquals(expectedPrimaryList.getItems().size(), actualPrimaryList.getItems().size());
            Assertions.assertEquals(expectedPrimaryList.getSymbolIndent(), actualPrimaryList.getSymbolIndent());
            Assertions.assertEquals(expectedSubList.getSymbolIndent(), actualSubList.getSymbolIndent());

            doc.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
