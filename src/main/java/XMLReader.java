import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class XMLReader {

    public static List<Student> allStudent = new ArrayList<>();
    public static List<Marks> allMarks = new ArrayList<>();
  //  public static List<Group> allGroups = new ArrayList<>();

    public XMLReader(String input) {

        readInput(input);

    }

    private static void readInput(String input) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Node currentNode;
            Document doc = db.parse(new File(input + ".xml"));

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("student");
            for (int temp = 0; temp < list.getLength(); temp++) {

                Marks mark = new Marks();

                Student student = new Student();

            //    Group group = new Group();

                currentNode = list.item(temp);

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    List<String> titleAndMark = new ArrayList<>();

                    Element element = (Element) node;


                    student.setFirtsname(element.getAttribute("firstname"));
                    student.setLastname(element.getAttribute("lastname"));
                    student.setGroupNumber(element.getAttribute("groupnumber"));

                    NodeList list1 = ((Element) currentNode).getElementsByTagName("subject");

                    for (int temp1 = 0; temp1 < list1.getLength(); temp1++) {

                        Node node1 = list1.item(temp1);

                        Element element1 = (Element) node1;

                        String title = element1.getAttribute("title");
                        String markString = element1.getAttribute("mark");

                        titleAndMark.add(title);
                        titleAndMark.add(markString);

                    }


                    mark.setMarks(titleAndMark);
                    allMarks.add(mark);
                    allStudent.add(student);

                }

            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
