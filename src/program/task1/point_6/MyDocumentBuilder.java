package program.task1.point_6;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import program.task1.point_4.Data;
import program.task1.point_4.DataList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MyDocumentBuilder {
    private DocumentBuilderFactory documentBuilderFactory;
    private javax.xml.parsers.DocumentBuilder documentBuilder;
    private Document document;

    public MyDocumentBuilder() throws ParserConfigurationException {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
    }


    public Document build(DataList dataList){
        Element root = document.createElement("analyser");
        Element dataTable = document.createElement("dataTable");
        document.appendChild(root);
        root.appendChild(dataTable);
        for (int i = 0; i < dataList.size() ; i++) {
            createDataPoint(dataList.get(i), dataTable);
        }
        Element line = document.createElement("line");
        line.setAttribute("b", Double.toString(dataList.getB()));
        line.setAttribute("k", Double.toString(dataList.getK()));
        root.appendChild(line);
        return document;
    }

    private void createDataPoint(Data data, Element dataTable) {
        Element dt = document.createElement("dataPoint");
        dt.setAttribute("date", data.getDate());
        Element x = document.createElement("x");
        Text xText = document.createTextNode(Double.toString(data.getX()));
        Element y = document.createElement("y");
        Text yText = document.createTextNode(Double.toString(data.getY()));
        dataTable.appendChild(dt);
        dt.appendChild(x);
        dt.appendChild(y);
        x.appendChild(xText);
        y.appendChild(yText);
    }
}
