package program.task1.point_3;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ValidationDTDAndXSD {

    public static void dtdValidation(SAXParserFactory saxParserFactory){
        saxParserFactory.setValidating(true);
        saxParserFactory.setNamespaceAware(true);
    }

    public static void xsdValidation(SAXParserFactory saxParserFactory, String filesPath){
        Schema schema = null;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            schema = factory.newSchema(new File(filesPath));
        } catch (SAXException e) {
            e.printStackTrace();
        }
        saxParserFactory.setSchema(schema);
        saxParserFactory.setNamespaceAware(true);
        saxParserFactory.setValidating(false);
    }

    public static void parseDocument(SAXParserFactory saxParserFactory, DefaultHandler defaultHandler, String documentPath){
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            InputStream xmlInputStream = new FileInputStream(documentPath);
            saxParser.parse(xmlInputStream, defaultHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        MyErrorHandler myErrorHandler = new MyErrorHandler();
        System.out.println("1. DTD");
        System.out.println("2. XDS");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        switch (n){
            case 1: {
                dtdValidation(saxParserFactory);
                parseDocument(saxParserFactory, myErrorHandler, "src\\program\\task1\\Files\\dtdValidation.xml");
                break;
            }
            case 2:{
                xsdValidation(saxParserFactory, "src\\program\\task1\\Files\\validation.xsd");
                parseDocument(saxParserFactory, myErrorHandler, "src\\program\\task1\\Files\\data.xml");
                break;
            }
        }
    }

}
