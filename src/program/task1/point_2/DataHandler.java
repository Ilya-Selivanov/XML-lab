package program.task1.point_2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DataHandler extends DefaultHandler {
    private double sumX, sumY, sumXY,sumX2, tmp;
    private double k,b;
    private int numX, numY;
    private String elementValue;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document Parsing");
        sumX = 0; sumY = 0; sumXY = 0; sumX2 = 0; numX = 0; numY = 0;
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document Parsing");
        k=(sumXY - sumX*(sumY/numY))/(sumX2-sumX*(sumX/numX));
        b = sumY/numY - k*sumX/numX;
        System.out.println("k:" + k + "\t" + "b: " + b);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Start Element Parsing: " + qName);
        if (attributes.getLength() > 0){
            for(int i =0; i < attributes.getLength(); i++){
                System.out.println("\t" + attributes.getLocalName(i) + ": " + attributes.getValue(i));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("End Element Parsing: " + qName);
        if (qName.equals("x")){
            double n = Double.parseDouble(elementValue);
            sumX+= n;
            sumX2+=n*n;
            tmp += n;
            numX += 1;
        }
        else if(qName.equals("y")){
            double n = Double.parseDouble(elementValue);
            sumY+=n;
            numY += 1;
            sumXY += tmp*n;
            tmp = 0;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }
}
