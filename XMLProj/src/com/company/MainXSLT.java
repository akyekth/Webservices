/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author aruna
 */
public class MainXSLT {

    public static void main(String[] args) {

        try {
            String stylesheetPathname = "Resources/xslt/applicationProfile.xsl";
            Source stylesheetSource = new StreamSource(new File(stylesheetPathname).getAbsoluteFile());
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer(stylesheetSource);
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            String inputPathname = "Resources/xml/Transcripts.xml";
            Source inputSource = new StreamSource(new File(inputPathname).getAbsoluteFile());

            StreamResult result = new StreamResult(new File("Resources/output/ApplicantProfileXSLT1.xml"));

            t.transform(inputSource, result);
            
        } catch (TransformerException ex) {
            Logger.getLogger(MainXSLT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
