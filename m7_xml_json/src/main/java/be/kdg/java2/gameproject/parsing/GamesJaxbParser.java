package be.kdg.java2.gameproject.parsing;

import be.kdg.java2.gameproject.model.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class GamesJaxbParser {

    public static void JaxbWriteXml(String file, Object root){
        try {
            JAXBContext context = JAXBContext.newInstance(Games.class);
            Marshaller jaxbMarshaller = context.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(root, new File(file));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static <T> T JaxbReadXml(String file, Class<T> typeParameterClass){
        try {
            JAXBContext context = JAXBContext.newInstance(Games.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            File myFile = new File(file);
            return (T) unmarshaller.unmarshal(myFile);

        } catch(JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
