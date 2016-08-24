package com.realdolmen.jxp010.sax;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Loads movies.xml and applies the MovieHandler to the XML.
 *
 */
public class SaxExample {

    // Shortcut to generate main method in Intellij -> psvm + TAB
    public static void main(String[] args) {
        SaxExample example = new SaxExample();
        example.doSax();
    }

    public void doSax(){
        XMLReader parser;

        try{
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            parser = saxParserFactory.newSAXParser().getXMLReader();
            parser.setContentHandler(new MovieHandler());
            parser.parse("src/main/resources/movies.xml");

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
