import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLCreator {

    public XMLCreator(String output) {

        createOutput(output);
    }

    private static void createOutput(String output) {

        try {

            List<Student> dataAllStudents = XMLReader.allStudent;
            List<Marks> dataAllMarks = XMLReader.allMarks;


            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document document = db.newDocument();


            Element root = document.createElement("group");
            document.appendChild(root);

            for (int i = 0; i < dataAllStudents.size(); i++) {

                Element student = document.createElement("student");

                Element average = document.createElement("average");

                student.setAttribute("firstname", dataAllStudents.get(i).getFirstname());
                student.setAttribute("lastname", dataAllStudents.get(i).getLastname());
                student.setAttribute("groupnumber", dataAllStudents.get(i).getGroupNumber());

                root.appendChild(student);

                for (String key : dataAllMarks.get(i).getCountTitle().keySet()) {

                    for (int j = 0; j < dataAllMarks.get(i).getCountTitle().get(key); j++) {

                        Element marks = document.createElement("subject");

                        marks.setAttribute("title", key);
                        marks.setAttribute("mark", dataAllMarks.get(i).getTitleMark().get(key).get(j).toString());

                        student.appendChild(marks);
                    }
                    average.setTextContent(dataAllMarks.get(i).getTrueAverage().toString());
                    student.appendChild(average);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(output + ".xml"));

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");


        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

}
