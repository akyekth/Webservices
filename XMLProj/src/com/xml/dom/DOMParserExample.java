/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.dom;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author sulluru
 */
public class DOMParserExample {
    
    public static void main(String[] args){
        try {
            File file = new File("Resources\\Employee.xml");
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            
            NodeList nList = doc.getElementsByTagName("Employee");
			
            System.out.println("-------------- nList.getLength()--------------"+ nList.getLength());

            for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                          Element eElement = (Element) nNode;
                        
                        //NodeList childnList  = eElement.getChildNodes();
                        System.out.println("ID : "  +eElement.getAttribute("id"));
                        
                        //eElement.getAt

                        System.out.println("Staff id : " + eElement.getAttribute("id"));
                        //Text text = (Text) eElement.getElementsByTagName("Name").item(0);
                       
                        System.out.println("Name : " + eElement.getElementsByTagName("Name").item(0).getTextContent());
                        System.out.println("Name : " + eElement.getElementsByTagName("Name").item(1).getTextContent());
                        System.out.println("Department : " + eElement.getElementsByTagName("Department").item(0).getTextContent());
                        System.out.println("Company: " + eElement.getElementsByTagName("Company").item(0).getTextContent());
                        System.out.println("Salary : " + eElement.getElementsByTagName("Salary").item(0).getTextContent());

                    }
            }
              
        }  catch (SAXException ex) {
            Logger.getLogger(DOMParserExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DOMParserExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMParserExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
