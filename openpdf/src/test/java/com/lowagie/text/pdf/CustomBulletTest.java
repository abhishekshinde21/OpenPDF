package com.lowagie.text.pdf;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class CustomBulletTest {
    public static void main(String[] args) {
        try {
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("customBullet.pdf"));

            document.open();

            InputStream imageStream = CustomBulletTest.class.getClassLoader().getResourceAsStream("bullet-image.png");
            Image listSymbol = Image.getInstance(ImageIO.read(imageStream), null, false);
            listSymbol.scaleAbsolute(6, 6);
            List list = new List();
            Chunk symbolChunk = new Chunk(listSymbol, 0, 0, true);
            list.setListSymbol(symbolChunk);
            list.setAutoindent(true);
            list.add("item one");
            list.add("item two");
            list.add("item three");
            document.add(list);

            document.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
