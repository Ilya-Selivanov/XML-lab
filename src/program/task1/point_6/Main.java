package program.task1.point_6;

import program.task1.point_3.ValidationDTDAndXSD;
import program.task1.point_4.DataList;
import program.task1.point_4.HandlerForData;
import program.task1.point_5.DocumentWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        DataList dataList = new DataList();
        HandlerForData handler = new HandlerForData(dataList);
        System.out.println("1. DTD");
        System.out.println("2. XDS");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        switch (n){
            case 1: {
                ValidationDTDAndXSD.dtdValidation(saxParserFactory);
                ValidationDTDAndXSD.parseDocument(saxParserFactory, handler, "src\\program\\task1\\Files\\dtdValidation.xml");
                break;
            }
            case 2:{
                ValidationDTDAndXSD.xsdValidation(saxParserFactory, "src\\program\\task1\\Files\\validation.xsd");
                ValidationDTDAndXSD.parseDocument(saxParserFactory, handler, "src\\program\\task1\\Files\\data.xml");
                break;
            }
        }
        try {
            MyDocumentBuilder builder = new MyDocumentBuilder();
            DocumentWriter.write(builder.build(dataList), "src\\program\\task1\\point_6\\results.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
