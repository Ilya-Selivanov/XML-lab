package program.task1.point_5;


import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Operator {
    DocumentBuilderFactory documentBuilderFactory;
    boolean validation;

    public Operator() {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
    }

    public static void dtdValidation(DocumentBuilderFactory documentBuilderFactory) {
        documentBuilderFactory.setValidating(true);
    }

    public static void xsdValidation(DocumentBuilderFactory documentBuilderFactory, String filesPath) {
        Schema schema = null;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            schema = factory.newSchema(new File(filesPath));
        } catch (SAXException e) {
            e.printStackTrace();
        }

        documentBuilderFactory.setSchema(schema);
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(false);
    }

    public void validation() {
        MyErrorHandler errorHandler = new MyErrorHandler();
        Document document = null;
        System.out.println("1. DTD");
        System.out.println("2. XDS");
        System.out.println("3. Не проверять");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        switch (n) {
            case 1: {
                dtdValidation(documentBuilderFactory);
                validation = true;
                document = createDocument(documentBuilderFactory, errorHandler, "src\\program\\task1\\Files\\dtdValidation.xml");
                analyze(document);
                break;
            }
            case 2: {
                xsdValidation(documentBuilderFactory, "src\\program\\task1\\Files\\validation.xsd");
                validation = true;
                document = createDocument(documentBuilderFactory, errorHandler, "src\\program\\task1\\Files\\data.xml");
                analyze(document);
                break;
            }
            case 3: {
                validation = false;
                document = createDocument(documentBuilderFactory, errorHandler, "src\\program\\task1\\Files\\data.xml");
                analyze(document);
                break;
            }
        }
        changeDocument(document);
        DocumentWriter.write(document, "src\\program\\task1\\point_5\\outFile.xml");
    }

    private void analyze(Document document) {
        DocumentParser documentParser = new DocumentParser(document);
        if (validation == true) {
            documentParser.processDocumentWithValidating();
        } else {
            documentParser.processDocumentWithoutValidating();
        }

    }

    private Document createDocument(DocumentBuilderFactory documentBuilderFactory, ErrorHandler errorHandler, String fileXpath) {
        Document document = null;
        File file = new File(fileXpath);
        try {
            if(validation == true){
                documentBuilderFactory.setIgnoringElementContentWhitespace(true);
            }
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            if (validation == true) {
                documentBuilder.setErrorHandler(errorHandler);
            }
            document = documentBuilder.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private static void changeDocument(Document document) {
        DocumentParser documentParser = new DocumentParser(document);
        Scanner scanner = new Scanner(System.in);
        DataSheet dataSheet = new DataSheet(document);
        int pos;
        double value;
        boolean bool = true;
        while (bool) {
            documentParser.getSelectInformation();
            System.out.println("1. Set X");
            System.out.println("2. Set Y");
            System.out.println("3. Get X");
            System.out.println("4. Get Y");
            System.out.println("5. Add element");
            System.out.println("6. Insert");
            System.out.println("7. Remove");
            System.out.println("8. Replace");
            System.out.println("9.Exit");
            int n = scanner.nextInt();
            switch (n) {
                case 1: {
                    System.out.print("Pos: ");
                    pos = scanner.nextInt();
                    System.out.println("Value: ");
                    value = scanner.nextDouble();
                    dataSheet.setX(pos, value);
                    break;
                }
                case 2: {
                    System.out.print("Pos: ");
                    pos = scanner.nextInt();
                    System.out.println("Value: ");
                    value = scanner.nextDouble();
                    dataSheet.setY(pos, value);
                    break;
                }
                case 3: {
                    System.out.print("Pos: ");
                    pos = scanner.nextInt();
                    System.out.println("X: " + dataSheet.getX(pos));
                    break;
                }
                case 4: {
                    System.out.print("Pos: ");
                    pos = scanner.nextInt();
                    System.out.println("Y: " + dataSheet.getY(pos));
                    break;
                }
                case 5: {
                    System.out.print("X: ");
                    double x = scanner.nextDouble();
                    System.out.print("Y: ");
                    double y = scanner.nextDouble();
                    System.out.print("Data: ");
                    String date = scanner.next();
                    dataSheet.addElement(dataSheet.newElement(date, x, y));
                    break;
                }
                case 6: {
                    System.out.print("Pos: ");
                    pos = scanner.nextInt();
                    System.out.print("X: ");
                    double x = scanner.nextDouble();
                    System.out.print("Y: ");
                    double y = scanner.nextDouble();
                    System.out.print("Data: ");
                    String date = scanner.next();
                    dataSheet.insertElement(pos, dataSheet.newElement(date, x, y));
                    break;
                }
                case 7: {
                    System.out.print("Pos: ");
                    pos = scanner.nextInt();
                    dataSheet.removeElement(pos);
                    break;
                }
                case 8: {
                    System.out.print("Pos: ");
                    pos = scanner.nextInt();
                    System.out.print("X: ");
                    double x = scanner.nextDouble();
                    System.out.print("Y: ");
                    double y = scanner.nextDouble();
                    System.out.print("Data: ");
                    String date = scanner.next();
                    dataSheet.replaceElementAt(pos, dataSheet.newElement(date, x, y));
                    break;
                }
                case 9:{
                    bool = false;
                    break;
                }

            }
        }
    }

}
