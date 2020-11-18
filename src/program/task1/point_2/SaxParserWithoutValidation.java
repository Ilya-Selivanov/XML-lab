package program.task1.point_2;

import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxParserWithoutValidation {
    public static void main(String[] args) {
        String documentPath = "src/program/task1/Files/data.xml";
        SAXParserFactory sax = SAXParserFactory.newInstance();
        DataHandler handler = new DataHandler();
        System.out.println("Finished handler");
        try {
            SAXParser parser = sax.newSAXParser();
            parser.parse(new File(documentPath), handler);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
