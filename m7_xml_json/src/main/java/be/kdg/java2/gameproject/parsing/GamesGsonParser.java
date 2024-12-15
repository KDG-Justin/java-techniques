package be.kdg.java2.gameproject.parsing;

import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Games;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class GamesGsonParser {
    public static void writeJson(Games games, String fileName){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter().nullSafe());
        Gson gson = builder.setPrettyPrinting().create();

        String jsonString = gson.toJson(games.getGames());
        System.out.println(jsonString);

        try (PrintWriter jsonWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {
            jsonWriter.write(jsonString);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Games readJson(String fileName){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter().nullSafe()); //hier lees je die adapter klasse
        Gson gson = builder.create();
        List<Game> myList = null;
        Games games = new Games();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Game[] myArray = gson.fromJson(reader, Game[].class); //die rechte hakken zijn belangerijk

            myList = Arrays.asList(myArray);

            myList.forEach(games::add);
            return games;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
