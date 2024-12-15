package be.kdg.java2.gameproject.parsing;

import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Games;
import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.*;
import java.io.FileWriter;
import java.io.IOException;


public class GamesStaxParser {
    private Games games;
    private static XMLStreamWriter streamWriter;

    public GamesStaxParser(Games games, String FILENAME) {
        this.games = games;

        try {
            FileWriter fileWriter = new FileWriter(FILENAME);
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            streamWriter = xmlOutputFactory.createXMLStreamWriter(fileWriter);
            streamWriter = new IndentingXMLStreamWriter(streamWriter);

        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    public void staxWriteXML() {
        try {
            streamWriter.writeStartDocument();
            streamWriter.writeStartElement("games"); //root element

            for (Game current : games.getGames()) {
                writeElement(current);
            }
            streamWriter.writeEndElement();
            streamWriter.writeEndDocument();
            streamWriter.close();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void writeElement(Game game) {
        try {
            streamWriter.writeStartElement("game");
            streamWriter.writeAttribute("genre", game.getGenre().toString());

            streamWriter.writeStartElement("name");
            streamWriter.writeCharacters(game.getName());
            streamWriter.writeEndElement();

            streamWriter.writeStartElement("price");
            streamWriter.writeCharacters(String.valueOf(game.getPrice()));
            streamWriter.writeEndElement();

            streamWriter.writeStartElement("playerbase");
            streamWriter.writeCharacters(String.valueOf(game.getPlayerbase()));
            streamWriter.writeEndElement();

            streamWriter.writeStartElement("releaseDate");
            streamWriter.writeCharacters(String.valueOf(game.getRelease()));
            streamWriter.writeEndElement();

            streamWriter.writeStartElement("multiplayer");
            streamWriter.writeCharacters(String.valueOf(game.isMultiplayer()));
            streamWriter.writeEndElement();

            streamWriter.writeEndElement();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
