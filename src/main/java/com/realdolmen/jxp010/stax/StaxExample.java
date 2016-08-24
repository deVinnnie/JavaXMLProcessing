package com.realdolmen.jxp010.stax;


import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class StaxExample {
    private Map<String, Integer> typeHistogram = new HashMap<>();
    private Map<String, Integer> formatHistogram = new HashMap<>();

    private boolean movieFlag = false;
    private boolean typeFlag = false;
    private boolean formatFlag = false;

    public static void main(String[] args) {
        StaxExample example = new StaxExample();
        try {
            example.doStax();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * Count the occurrence of type and format.
     * Prints statistics to System.out.
     *
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    public void doStax() throws FileNotFoundException, XMLStreamException {
        FileInputStream inputStream = new FileInputStream(new File("src/main/resources/movies-dom-edit.xml"));
        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(inputStream);

        while(xmlEventReader.hasNext()){
            XMLEvent xmlEvent = xmlEventReader.nextEvent();

            if(xmlEvent.isStartElement() || xmlEvent.isEndElement()){
                String name;
                if(xmlEvent.isEndElement()){
                    name = xmlEvent.asEndElement().getName().toString();
                }
                else{
                    name = xmlEvent.asStartElement().getName().toString();
                }

                switch(name){
                    case "movie" :  movieFlag = !movieFlag;
                                    break;
                    case "type"  :  typeFlag = !typeFlag;
                                    break;
                    case "format":  formatFlag = !formatFlag;
                                    break;
                    default:
                                    break;
                }
            }

            if(xmlEvent.isCharacters() && movieFlag){
                String val = xmlEvent.asCharacters().getData().trim();

                if(typeFlag) {
                    String types = (val.isEmpty()) ? "Not Specified" : val;

                    for(String type : types.split("/")) {
                        Integer count = 0;
                        if (typeHistogram.containsKey(type)) {
                            count = typeHistogram.get(type);
                        }
                        typeHistogram.put(type, ++count);
                    }
                }
                else if(formatFlag){
                    String format = (val.isEmpty()) ? "N/A" : val;

                    Integer count = 0;
                    if(formatHistogram.containsKey(format)){
                        count = formatHistogram.get(format);
                    }
                    formatHistogram.put(format, ++count);
                }
            }

            if(xmlEvent.isEndDocument()){
                typeHistogram = this.sortMap(this.typeHistogram);
                formatHistogram = this.sortMap(this.formatHistogram);

                System.out.println("---------- Type ----------");
                for(Map.Entry<String, Integer> entry : typeHistogram.entrySet()){
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }

                System.out.println("\n\n");

                System.out.println("---------- Format ----------");
                for(Map.Entry<String, Integer> entry : formatHistogram.entrySet()){
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
            }
        }
    }

    /**
     * Sort entries by count. Reverse order (highest count first)
     * @param map
     * @return
     */
    public Map<String, Integer> sortMap(Map<String, Integer> map){
        Map<String, Integer> result = new LinkedHashMap<>();
        Stream<Map.Entry<String, Integer>> st = map.entrySet().stream();

        st.sorted( Map.Entry.comparingByValue(
                (o1, o2) -> (-1 * Integer.compare(o1, o2))
        )).forEachOrdered( e -> result.put(e.getKey(), e.getValue()) );
        return result;
    }
}
