package program.task1.point_4;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import program.task1.point_4.Data;
import program.task1.point_4.DataList;

public class HandlerForData extends DefaultHandler {
    private Data data;
    private DataList dataList;
    private String elementValue;

    public HandlerForData() {
        data = null;
        dataList = null;
        elementValue = null;
    }

    public HandlerForData(DataList dataList) {
        this.dataList = dataList;
    }

    @Override
    public void startDocument() {
        System.out.println("Start Document Parsing");
        if(dataList == null) {
            dataList = new DataList();
        }
    }

    @Override
    public void endDocument() {
        System.out.println("End Document Parsing");
        dataList.calculateCoefficient();
        System.out.println(dataList.toString());
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws org.xml.sax.SAXException {
        System.out.println("Star element (" + qName + ") processing");
        if (qName.equals("data")) {
            data = new Data();
            String date = attributes.getValue("date");
            data.setDate(date);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println("Stop element (" + qName + ") processing");
        if (qName.equals("data")) {
            dataList.addData(data);
        }
        if (qName.equals("x")) {
            data.setX(Double.parseDouble(elementValue));
        }
        if (qName.equals("y")) {
            data.setY(Double.parseDouble(elementValue));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        elementValue = new String(ch, start, length).trim();
    }

    @Override
    public void warning(SAXParseException ex) {
        System.err.println("Warning: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " column = " + ex.getColumnNumber());
    }

    @Override
    public void error(SAXParseException ex) {
        System.err.println("Error: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " column = " + ex.getColumnNumber());
    }

    @Override
    public void fatalError(SAXParseException ex) {
        System.err.println("Fatal error: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " column = " + ex.getColumnNumber());
    }
}
