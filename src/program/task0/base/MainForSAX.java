package program.task0.base;

import program.task0.base.helpers.AccidentList;
import program.task0.base.parser.SAXParserXML;
import program.task0.base.writers.DOMWriterXML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;

public class MainForSAX {
    public static void main(String[] args) {
        SAXParserXML parserXML = new SAXParserXML();
        AccidentList incidentsList = parserXML.parseFiles("src/program/task0/XMLdocument");
        DOMWriterXML domWriterXML = null;
        try {
            domWriterXML = new DOMWriterXML();
            domWriterXML.write(incidentsList);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
