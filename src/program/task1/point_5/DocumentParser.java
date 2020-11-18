package program.task1.point_5;

import org.w3c.dom.*;


public class DocumentParser {
    private Document document;

    public DocumentParser(Document document) {
        this.document = document;
    }

    private void stepThrough(Node start) {
        System.out.println(start.getNodeName() + " = " + start.getNodeValue());
        if (start instanceof Element) {
            NamedNodeMap startAttr = start.getAttributes();
            for (int i = 0; i < startAttr.getLength(); i++) {
                Node attr = startAttr.item(i);
                System.out.println("Attribute: " + attr.getNodeName() + " = " + attr.getNodeValue());
            }
        }


        for (Node child = start.getFirstChild(); child != null; child = child.getNextSibling())
            stepThrough(child);
    }

    public void processDocumentWithoutValidating() {
        Element rootElement = document.getDocumentElement();
        System.out.println("Root element: " + rootElement.getNodeName());
        System.out.println("Child elements: ");
        stepThrough(rootElement);
    }

    public void getSelectInformation() {
        NodeList nodeList1 = document.getDocumentElement().getElementsByTagName("x");
        NodeList nodeList2 = document.getDocumentElement().getElementsByTagName("y");

        if (nodeList1.getLength() == nodeList2.getLength()) {
            for (int i = 0; i < nodeList1.getLength(); i++) {
                System.out.println(nodeList1.item(i).getNodeName() + " "
                        + nodeList1.item(i).getTextContent() + "\t"
                        + nodeList2.item(i).getNodeName() + " "
                        + nodeList2.item(i).getTextContent());
            }
        }
    }

    public void processDocumentWithValidating() {
        Element rootElement = document.getDocumentElement();
        NodeList childs = rootElement.getChildNodes();

        for (int i = 0; i < childs.getLength(); i++) {
            Element child1 = (Element) childs.item(i);
            System.out.println(child1.getTagName() + ": date " + child1.getAttribute("date"));

            Element child2 = (Element) child1.getFirstChild();
            System.out.println("\t" + child2.getTagName() + ": " + ((Text) child2.getFirstChild()).getData().trim());

            Element child3 = (Element) child2.getNextSibling();
            System.out.println("\t" + child3.getTagName() + ": " + ((Text) child3.getFirstChild()).getData().trim());
        }
    }

}
