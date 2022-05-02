import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlToJSON {

    public static List<Employee> parseXML(String fileXML) {
        List<Employee> result = new ArrayList<>();
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new File(fileXML));
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("employee");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Employee employee = getEmployeeFromXML(nodeList.item(i));
                result.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Employee getEmployeeFromXML(Node root) {

        if (root.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) root;
            long id = Long.parseLong(getTagValue("id", element));
            String firstName = getTagValue("firstName", element);
            String lastName = getTagValue("lastName", element);
            String country = getTagValue("country", element);
            int age = Integer.parseInt(getTagValue("age", element));
            return new Employee(id, firstName, lastName, country, age);
        }
        return null;
    }

    public static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
