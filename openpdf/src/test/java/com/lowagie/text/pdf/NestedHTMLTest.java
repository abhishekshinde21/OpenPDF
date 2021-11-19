package com.lowagie.text.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.List;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class NestedHTMLTest {
    public static void main(String[] args) {
        try {
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

            final java.util.List<Element> elements = HTMLWorker.parseToList(reader, styleSheet, interfaceProps);
            for (Element element : elements) {
                doc.add(element);
            }
            doc.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    // Initial testing of recursively applying indentation
    public static Element applyIndent(Element e, int symbolIndent) {
        if (e instanceof List) {
            ((List) e).setSymbolIndent(symbolIndent);

            for (int i = 0; i < ((List) e).size(); i++) {
                ((List) e).getItems().set(i, applyIndent(((List) e).getItems().get(i), symbolIndent));
            }
        }
        return e;
    }
}
