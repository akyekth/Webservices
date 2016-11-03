package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class ThisHandlerSAX extends DefaultHandler{

    private String originalRoot, newRoot, currentElement;
    private StringBuilder output;
    private String[] bannedElements;
    private int openElements = 0;

    public ThisHandlerSAX(StringBuilder output) {
        super();

        this.output = output;
    }

    public void setOriginalRoot(String originalRoot) {
        this.originalRoot = originalRoot;
    }

    public void setNewRoot(String newRoot) { this.newRoot = newRoot; }

    public void setBannedElements(String[] bannedElements) {
        this.bannedElements = bannedElements;
    }

    private String indent() {
        char[] chars = new char[openElements * 4];
        Arrays.fill(chars, ' ');
        return new String(chars);
    }

    private boolean isBanned(String element) {
        for (String word : bannedElements)
            if(word.equals(element)) return true;
        return false;
    }

    @Override
    public void startDocument() {
        System.out.println("Starting the document merging...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;
        if(!isBanned(qName)) {
            openElements++;

            if(originalRoot.equals(qName))
                output.append("\n" + indent() + "<" + newRoot);
            else
                output.append("\n" + indent() + "<" + qName);
            /*if(attributes != null) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    output.append(" " + attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\"");
                }
            }*/
            output.append(">");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if(!isBanned(currentElement)) {
            String charData = (new String(ch, start, length)).trim();
            if (charData.indexOf("\n") < 0 && charData.length() > 0) {
                output.append("\n" + indent() + charData);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(!isBanned(qName)) {
            if(originalRoot.equals(qName)) {
                output.append("\n" + indent() + "</" + newRoot + ">");
            }
            else {
                output.append("\n" + indent() + "</" + qName + ">");
            }
            openElements--;
        }
    }

    @Override
    public void endDocument() {
        System.out.println("Documents merged...");
        System.out.println("Writing to file...");
    }

}

public class MainSAX {

    public static void main(String[] args) {
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            StringBuilder output = new StringBuilder();
            // Initialize it with the preamble
            output.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            output.append("<applicantProfile xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'\n" +
                    "                   xmlns='http://www.w3schools.com/myns/'\n" +
                    "                   xsi:schemaLocation='http://www.w3schools.com/myns/ file://Resources/schemas/ApplicantProfile.xsd'>\n");
            ThisHandlerSAX handler = new ThisHandlerSAX(output);
            // Parse shortCV
            handler.setOriginalRoot("shortCV");
            handler.setNewRoot("userdata");
            handler.setBannedElements(new String[] {"briefBio"});
            saxParser.parse("Resources/xml/ShortCV.xml", handler);
            // Parse EmploymentRecord
            handler.setBannedElements(new String[] {"personnummer"});
            saxParser.parse("Resources/xml/EmploymentRecord.xml", handler);
            // Parse Transcripts
            handler.setBannedElements(new String[] {"transcript", "courses", "course", "courseName", "score"});
            saxParser.parse("Resources/xml/Transcripts.xml", handler);

            // Close the preamble
            output.append("\n</applicantProfile>");
            // Output - write to file
            BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("Resources/output/ApplicantProfileSAX1.xml")));
            bwr.write(output.toString());
            bwr.flush();
            bwr.close();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
