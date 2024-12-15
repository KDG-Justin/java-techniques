package be.kdg.java2.gameproject.parsing;

import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Games;
import be.kdg.java2.gameproject.model.Genre;
import org.w3c.dom.Element;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

public class GamesDomParser {
    public static Games domReadXML(String fileName){
        Games games = new Games();
        boolean foundNameElement = false;
        boolean foundPriceElement = false;
        boolean foundPlayerbaseElement = false;
        boolean foundReleaseElement = false;
        boolean foundMulitplayerElement = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(fileName));



            Game game = null;
            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();

                if (event.getEventType() == XMLStreamConstants.START_ELEMENT){
                    StartElement startElement = event.asStartElement();
                    String tagName = startElement.getName().getLocalPart();

                    if (tagName.equalsIgnoreCase("game")){
                        game = new Game();
                        Iterator<Attribute> attributes = startElement.getAttributes(); // attribuut opvragen
                        String genre = attributes.next().getValue();
                        game.setGenre(Genre.valueOf(genre));
                    }
                    if (tagName.equals("name"))
                        foundNameElement = true;

                    if (tagName.equals("price"))
                        foundPriceElement = true;

                    if (tagName.equals("playerbase"))
                        foundPlayerbaseElement = true;

                    if (tagName.equals("releaseDate"))
                        foundReleaseElement = true;

                    if (tagName.equals("multiplayer"))
                        foundMulitplayerElement = true;


                    
                } else if (event.getEventType() == XMLStreamConstants.CHARACTERS) {
                    Characters characters = event.asCharacters();

                    if (foundNameElement) {
                        game.setName(characters.getData());
                        foundNameElement = false;
                    }

                    if (foundPriceElement) {
                        game.setPrice(Double.parseDouble(characters.getData()));
                        foundPriceElement = false;
                    }

                    if (foundPlayerbaseElement) {
                        game.setPlayerbase(Integer.parseInt(characters.getData()));
                        foundPlayerbaseElement = false;
                    }

                    if (foundReleaseElement) {
                        game.setRelease(LocalDate.parse(characters.getData()));
                        foundReleaseElement = false;
                    }

                    if (foundMulitplayerElement) {
                        game.setMultiplayer(Boolean.parseBoolean(characters.getData()));
                        foundMulitplayerElement = false;
                    }

                } else if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                    EndElement endElement = event.asEndElement();

                    if (endElement.getName().getLocalPart().equals("game"))
                        games.add(game);
                }
            }

        }catch (XMLStreamException | IOException e){
            e.printStackTrace();
        }

        return games;
    }
}
