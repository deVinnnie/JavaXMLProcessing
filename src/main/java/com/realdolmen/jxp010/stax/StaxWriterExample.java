package com.realdolmen.jxp010.stax;


import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StaxWriterExample {

    public static void main(String[] args) {
        StaxWriterExample example = new StaxWriterExample();
        try {
            example.doStaxWrite();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read movies-dom-edit.xml and write a new xml-file where a modified attribute is added for each movie.
     *
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    public void doStaxWrite() throws FileNotFoundException, XMLStreamException {
        FileInputStream inputStream = new FileInputStream(new File("src/main/resources/movies-dom-edit.xml"));
        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(inputStream);

        XMLEventWriter xmlEventWriter = XMLOutputFactory.newFactory().createXMLEventWriter(System.out);
        XMLEventFactory eventFactory = XMLEventFactory.newFactory();

        while(xmlEventReader.hasNext()){
            XMLEvent xmlEvent = xmlEventReader.nextEvent();

            // Pass xmlEvent to writer.
            xmlEventWriter.add(xmlEvent);

            // Edit stuff if a movie element started.
            if(xmlEvent.isStartElement() && xmlEvent.asStartElement().getName().toString().equals("movie")){
                //xmlEvent.asStartElement().;/

                DateFormat formatter = new SimpleDateFormat("YYYY-MM-d");

                xmlEventWriter.add(eventFactory.createAttribute("modified", formatter.format(Calendar.getInstance().getTime())));
            }
        }
    }
}
