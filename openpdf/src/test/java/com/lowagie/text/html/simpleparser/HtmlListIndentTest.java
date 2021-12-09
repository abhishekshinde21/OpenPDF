package com.lowagie.text.html.simpleparser;

import com.lowagie.text.Chunk;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HtmlListIndentTest {

    @Test
    // CS427 Issue link: https://github.com/LibrePDF/OpenPDF/issues/615
    void testEmptyListAppliesIndentation() {
        // given
        int symbolIndent = 5;
        List l = new List();

        // when
        List actualList = HTMLWorker.reconfigureListWithIndentation(l, symbolIndent);

        // then
        Assertions.assertEquals(actualList.getSymbolIndent(), symbolIndent);
    }

    @Test
    // CS427 Issue link: https://github.com/LibrePDF/OpenPDF/issues/615
    void testEmptyIndentedListUpdatesIndentation() {
        // given
        int symbolIndent = 5;
        List l = new List(10);

        // when
        List actualList = HTMLWorker.reconfigureListWithIndentation(l, symbolIndent);

        // then
        Assertions.assertEquals(actualList.getSymbolIndent(), symbolIndent);
    }

    @Test
    // CS427 Issue link: https://github.com/LibrePDF/OpenPDF/issues/615
    void testSingleNestedListAppliesIndentation() {
        // given
        int symbolIndent = 5;
        List primaryList = new List();
        List subList = new List();
        primaryList.add(subList);

        // when
        List actualList = HTMLWorker.reconfigureListWithIndentation(primaryList, symbolIndent);

        // then
        Assertions.assertEquals(actualList.getSymbolIndent(), symbolIndent);
        Assertions.assertEquals(((List) actualList.getItems().get(0)).getSymbolIndent(), symbolIndent);

    }

    @Test
    // CS427 Issue link: https://github.com/LibrePDF/OpenPDF/issues/615
    void testSingleNestedAndIndentedListUpdatesIndentation() {
        // given
        int symbolIndent = 5;
        List primaryList = new List(10);
        List subList = new List(10);
        primaryList.add(subList);

        // when
        List actualList = HTMLWorker.reconfigureListWithIndentation(primaryList, symbolIndent);

        // then
        Assertions.assertEquals(actualList.getSymbolIndent(), symbolIndent);
        Assertions.assertEquals(((List) actualList.getItems().get(0)).getSymbolIndent(), symbolIndent);

    }

    @Test
    // CS427 Issue link: https://github.com/LibrePDF/OpenPDF/issues/615
    void testSingleNestedListWithElementsAndAppliesIndentation() {
        // given
        int symbolIndent = 5;
        List primaryList = new List();
        List subList = new List();
        Chunk c = new Chunk("Hello World");
        ListItem li = new ListItem("Test List Item");
        primaryList.add(subList);
        primaryList.add(c);
        primaryList.add(li);

        // when
        List actualList = HTMLWorker.reconfigureListWithIndentation(primaryList, symbolIndent);

        // then
        Assertions.assertEquals(actualList.getSymbolIndent(), symbolIndent);
        Assertions.assertEquals(((List) actualList.getItems().get(0)).getSymbolIndent(), symbolIndent);

    }

    @Test
    // CS427 Issue link: https://github.com/LibrePDF/OpenPDF/issues/615
    void testMultiNestedListAppliesIndentation() {
        // given
        int symbolIndent = 5;
        List primaryList = new List();
        List subList = new List();
        List nestedSubList = new List();
        subList.add(nestedSubList);
        primaryList.add(subList);

        // when
        List actualList = HTMLWorker.reconfigureListWithIndentation(primaryList, symbolIndent);
        List actualSubList = ((List) actualList.getItems().get(0));
        List actualNestedSubList = ((List) actualSubList.getItems().get(0));

        // then
        Assertions.assertEquals(actualList.getSymbolIndent(), symbolIndent);
        Assertions.assertEquals(actualSubList.getSymbolIndent(), symbolIndent);
        Assertions.assertEquals(actualNestedSubList.getSymbolIndent(), symbolIndent);
    }

}
