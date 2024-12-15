import be.kdg.java2.gameproject.data.Data;
import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Games;
import be.kdg.java2.gameproject.model.Genre;

import java.time.LocalDate;


public class Demo1 {
    public static void main(String[] args) {
        try {
            Games listOfGames = new Games();
            for (Game currentGame : Data.getData()){
                listOfGames.add(currentGame);
            }

            listOfGames.add(new Game("Gmod", 9.99, Genre.PUZZLE, 44439, LocalDate.of(2006, 11, 29), true));

            System.out.println(listOfGames.search("smite"));
            System.out.println("Before remove");
            System.out.println(listOfGames.getSize());
            System.out.println();
            listOfGames.remove("smite");
            System.out.println("After remove");
            System.out.println(listOfGames.getSize());
            System.out.println("====================================================================");
            System.out.println("VOLGENS NAAM");
            for (Game currentGame : listOfGames.sortedOnName()){
                System.out.println(currentGame);
            }

            System.out.println(" ");
            System.out.println("VOLGENS RELEASE");
            for (Game currentGame : listOfGames.sortedOnRelease()){
                System.out.println(currentGame);
            }

            System.out.println(" ");
            System.out.println("VOLGENS PRIJS");
            for (Game currentGame : listOfGames.sortedOnPlayerBase()){
                System.out.println(currentGame);
            }

            System.out.println();
            Game gameNoValue = new Game();
            System.out.println(gameNoValue);
            System.out.println();
            Game game = new Game("", 10.0, Genre.ADVENTURE, 10, LocalDate.of(2001, 12, 2), false);


        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
