package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

class DocumentParser {

    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    public DocumentParser() {

    }

    public void process() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setValidating(false); //true
        dbFactory.setNamespaceAware(true);
        dbFactory.setIgnoringElementContentWhitespace(true);
        dbFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
        dbFactory.setAttribute(JAXP_SCHEMA_SOURCE, "Resources/schemas/ApplicantProfile.xsd");

        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        // Create the output document
        Document output = dBuilder.newDocument();
        Element output_root = output.createElement("applicantProfile");
        output.appendChild(output_root);

        try {
            Document shortCV = dBuilder.parse(new File("Resources/xml/ShortCV.xml"));
            Document companyInfo = dBuilder.parse(new File("Resources/xml/CompanyInfo.xml"));
            Document employmentRecord = dBuilder.parse(new File("Resources/xml/EmploymentRecord.xml"));
            Document transcripts = dBuilder.parse(new File("Resources/xml/Transcripts.xml"));

            // Process the first file(ShortCV.xml)
            // Create the root element
            Element shortCVRoot = output.createElement("userdata");
            Element inputRoot = (Element) shortCV.getChildNodes().item(0); // It should be only 1
            // Fullname
            Element fullname = (Element) inputRoot.getElementsByTagName("fullname").item(0);
            shortCVRoot.appendChild(output.importNode(fullname, true));
            // Personnummer
            Element pn = (Element) inputRoot.getElementsByTagName("personnummer").item(0);
            shortCVRoot.appendChild(output.importNode(pn, true));
            // Personal letter
            Element personalLetter = (Element) inputRoot.getElementsByTagName("personalLetter").item(0);
            shortCVRoot.appendChild(output.importNode(personalLetter, true));

            // Process the second file(EmploymentRecord.xml)
            // TODO: replace the code with company name
            Element erRoot = output.createElement("employmentRecord");
            inputRoot = employmentRecord.getDocumentElement();
            NodeList erNodes = inputRoot.getElementsByTagName("employment");
            for (int i = 0; i < erNodes.getLength(); i++) {
                Element e = (Element) erNodes.item(i);
                erRoot.appendChild(output.importNode(e, true));
            }

            // Process the third file(Transcripts.xml)
            Element tRoot = output.createElement("transcript");
            inputRoot = transcripts.getDocumentElement();
            Element degreesNode = (Element) inputRoot.getElementsByTagName("degrees").item(0);
            tRoot.appendChild(output.importNode(degreesNode, true));

            // Append it to the output tree
            output_root.appendChild(shortCVRoot);
            output_root.appendChild(erRoot);
            output_root.appendChild(tRoot);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Write the output file
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(output);
            StreamResult result = new StreamResult(new File("Resources/output/ApplicantProfileDOM1.xml"));
            t.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}

public class MainDOM {
    public static void main(String[] args) {
        DocumentParser doc = new DocumentParser();
        doc.process();
    }
}
