import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // id format regex to validate id 'bkXXX'
        String idformat = "bk[0-9]{3}";

        // read id from user using scanner
        System.out.println("Enter book id: bkXXX OR x to exit");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String x = sc.next();
            // exit when input = x
            if (x.equals("x")) {
                sc.close();
                System.exit(0);
            }
            // validate id format
            if (!x.matches(idformat)) {
                System.out.println("Invalid id format!");
                System.out.println("Enter book id:  OR x to exit");
                continue;
            }

            try {
                // reading xml file to parse and get element with id = x
                File file = new File("src\\books.xml");

                // parse xml file into doc object
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("book");
                System.out.println("----------------------------");

                // loop through all elements in xml file
                for (int i = 0; i < nList.getLength(); i++) {
                    // get current element
                    Node nNode = nList.item(i);
                    // check if id attribute of current element is equal to x
                    Element element = (Element) nNode;
                    if (element.getAttribute("id").equals(x)) {
                        // print current element
                        printNode(nNode);
                        System.out.println("Book with id = " + x + " found!");
                        System.out.println("----------------------------");
                        break;
                    }

                    // not found
                    if (i == nList.getLength() - 1) {
                        System.out.println("Book with id = " + x + " not found!");
                    }

                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Enter book id:  OR x to exit");
        }

    }

    private static void printNode(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            System.out.println("Book id : " + element.getAttribute("id"));
            System.out.println("Author : " + element.getElementsByTagName("author").item(0).getTextContent());
            System.out.println("Title : " + element.getElementsByTagName("title").item(0).getTextContent());
            System.out.println("Genre : " + element.getElementsByTagName("genre").item(0).getTextContent());
            System.out.println("Price : " + element.getElementsByTagName("price").item(0).getTextContent());
            System.out.println("publish Date : " + element.getElementsByTagName("publish_date").item(0).getTextContent());
            System.out.println("description : " + element.getElementsByTagName("description").item(0).getTextContent());
        }
    }
}