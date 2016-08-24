package com.realdolmen.jxp010.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A simple SAX XML Handler which prints
 * a the title for each movie and shows
 * the total number of movie elements in the XML-file.
 */
public class MovieHandler extends DefaultHandler {

    private boolean movieFlag = false;
    private boolean titleFlag = false;
    private String titles = "";
    private int nMovies;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // When setXML
        if(localName.equals("movie")){
            movieFlag = true;
            nMovies++;
        }

        if(movieFlag && localName.equals("title")){
            titleFlag = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(localName.equals("movie")){
            movieFlag = false;
        }
        if(localName.equals("title")){
            titleFlag = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(movieFlag && titleFlag){
            String title = new String(ch, start, length);
            titles += title.trim() + "\n";
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(titles);

        System.out.println("Total number of Movies: " + nMovies);
    }
}
