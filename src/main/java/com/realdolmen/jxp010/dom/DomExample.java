package com.realdolmen.jxp010.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class DomExample {

    public static void main(String[] args) {
        DomExample example = new DomExample();
        try {
            example.doDom();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doDom() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/main/resources/movies.xml"));

        NodeList movieList = document.getElementsByTagName("movie");

        for(int i = 0; i < movieList.getLength(); i++){
            Node node = movieList.item(i);

            Element description = document.createElement("description");
            Node descriptionText = document.createTextNode("Description of this movie");
            description.appendChild(descriptionText);
            node.appendChild(description);

            Element score = document.createElement("score");
            Random random = new Random();
            int maxScore = 5;
            int scoreValue = random.nextInt(maxScore+1);
            Node scoreTextNode = document.createTextNode(""+scoreValue);
            score.appendChild(scoreTextNode);

            node.appendChild(score);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        FileOutputStream outputStream = new FileOutputStream(new File("src/main/resources/movies-dom-edit.xml"));

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(outputStream);
        transformer.transform(source, result);
    }
}
