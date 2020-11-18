package program.task0.base;

import org.xml.sax.SAXException;
import program.task0.base.parser.DOMParserXML;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MainForDOM {
    public static void main(String[] args) {
        DOMParserXML domParserXML = new DOMParserXML();
        try {
            domParserXML.parse("src/program/task0/XSDDocument/roadAccident.xsd");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
