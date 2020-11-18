package program.task1.point_5;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DataSheet {
    private Document document;

    public DataSheet(Document document){
        super();
        this.document = document;
    }

    public DataSheet(){
        this(null);
    }

    public Document getDocument(){
        return document;
    }

    public void setDocument(Document document){
        this.document = document;
    }

    public int numData(){
        return document.getDocumentElement().getElementsByTagName("data").getLength();
    }

    public double getX(int pos){
        String stringX = document.getDocumentElement().getElementsByTagName("x").item(pos).getTextContent();
        return Double.parseDouble(stringX);
    }

    public void setX(int pos, double newValue){
        document.getDocumentElement().getElementsByTagName("x").item(pos).setTextContent(newValue + "");
    }

    public double getY(int pos){
        String stringY = document.getDocumentElement().getElementsByTagName("y").item(pos).getTextContent();
        return Double.parseDouble(stringY);
    }

    public void setY(int pos, double newValue){
        document.getDocumentElement().getElementsByTagName("y").item(pos).setTextContent(newValue + "");
    }

    public Element newElement(String date, double x, double y){
        Element data = document.createElement("data");
        Attr attr = document.createAttribute("date");
        attr.setValue(date.trim());
        data.setAttributeNode(attr);

        Element elementX = document.createElement("x");
        elementX.appendChild(document.createTextNode(x + ""));
        data.appendChild(elementX);

        Element elementY = document.createElement("y");
        elementY.appendChild(document.createTextNode(y + ""));
        data.appendChild(elementY);

        return data;
    }

    public void addElement(Element data){
        this.document.getDocumentElement().appendChild(data);
    }

    public void removeElement(int pos){
        Node element = document.getDocumentElement().getElementsByTagName("data").item(pos);
        document.getDocumentElement().removeChild(element);
    }

    public void insertElement(int pos, Node node){
        Node element = document.getDocumentElement().getElementsByTagName("data").item(pos);
        document.getDocumentElement().insertBefore(node, element);
    }

    public void replaceElementAt(int pos, Node node){
        Node element = document.getDocumentElement().getElementsByTagName("data").item(pos);
        document.getDocumentElement().replaceChild(node, element);
    }

}
