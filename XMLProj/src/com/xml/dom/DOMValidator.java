/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.dom;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author sulluru
 */
public class DOMValidator {

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
        String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
        String schemaSource = "file:/C:/Users/sulluru/Xml/shiporder.xsd";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema"); 
        factory.setAttribute( JAXP_SCHEMA_SOURCE, schemaSource);

//        SchemaFactory schemaFactory
//                = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

       // factory.setSchema(schemaFactory.newSchema(
         //       new Source[]{new StreamSource("Resources\\shiporder.xsd")}));

        DocumentBuilder builder = factory.newDocumentBuilder();

        builder.setErrorHandler(new SimpleErrorHandler());
        
         File file = new File("Resources\\shiporder.xml");

        Document document = builder.parse(file);
    }

    private static class SimpleErrorHandler implements ErrorHandler {

        public SimpleErrorHandler() {
        }

        @Override
        public void warning(SAXParseException exception) throws SAXException {
            exception.printStackTrace();
            throw new UnsupportedOperationException("Not supported yet."+exception); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
              exception.printStackTrace();
            throw new UnsupportedOperationException("Not supported yet."+exception); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
              exception.printStackTrace();
            throw new UnsupportedOperationException("Not supported yet."+exception); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
