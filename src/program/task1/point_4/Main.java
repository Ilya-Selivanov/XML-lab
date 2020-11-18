package program.task1.point_4;

import program.task0.base.handlers.Handler;
import program.task1.point_3.ValidationDTDAndXSD;

import javax.xml.parsers.SAXParserFactory;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        HandlerForData handler = new HandlerForData();
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
    }

}
