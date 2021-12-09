package com.lowagie.text.pdf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TextAlignmentTest {
    
    float originalLowerLeftY = ColumnText.getLowerLeftY();
    float originalUpperRightY = ColumnText.getUpperRightY();

    @Test
    // CS427 Issue link: https://github.com/LibrePDF/OpenPDF/issues/175
    void testGetDefaultAlignment() {
        // given
        float expectedLowerLeftY = -1;
        float expectedUpperRightY = 2;

        // when
        float actualLowerLeftY = ColumnText.getLowerLeftY();
        float actualUpperRightY = ColumnText.getUpperRightY();

        // then
        Assertions.assertEquals(expectedLowerLeftY, actualLowerLeftY);
        Assertions.assertEquals(expectedUpperRightY, actualUpperRightY);
    }

    @Test
    // CS427 Issue link: https://github.com/LibrePDF/OpenPDF/issues/175
    void testSetAlignment() {
        // given
        float lowerLeftY = -5;
        float upperRightY = 5;

        // when
        ColumnText.setLowerLeftY(lowerLeftY);
        ColumnText.setUpperRightY(upperRightY);

        // then
        Assertions.assertEquals(lowerLeftY, ColumnText.getLowerLeftY());
        Assertions.assertEquals(upperRightY, ColumnText.getUpperRightY());

        // reset
        ColumnText.setLowerLeftY(originalLowerLeftY);
        ColumnText.setUpperRightY(originalUpperRightY);
    }

}
