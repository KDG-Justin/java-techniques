package be.kdg.java2.gameproject.model;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameFactory {

    private GameFactory(){}

    public static Game newEmptyGame(){
        return new Game("UNKNOWN", 0.0, Genre.FPS, 0, LocalDate.of(1999, 12, 31), false);
    }

    public static Game newFilledGame(String name, double price, Genre genre, int playerBase, LocalDate releaseDate, boolean multiplayer){
        return new Game(name, price, genre, playerBase, releaseDate, multiplayer);
    }

    public static Game newRandomGame(){
        Random random = new Random();
        //string name
        String randomName = generateString(10, 2, true);
        //double price
        int maxPrice = 100;
        double randomPrice = random.nextDouble(maxPrice);

        //Genre genre
        Genre[] genres = Genre.values();
        int randomNumberForGenre = random.nextInt(genres.length);
        Genre randomGenre = genres[randomNumberForGenre];

        //Int playerBase
        int maxPlayerBase = 600000;
        int randomPlayerBase = random.nextInt(maxPlayerBase);

        //LocalDate releaseDate
        long minDay = LocalDate.of(1990, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 2).toEpochDay();
        long randomDate = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomReleaseDate = LocalDate.ofEpochDay(randomDate);

        //Boolean multiplayer
        boolean randomMultiplayer = random.nextBoolean();

        return new Game(randomName, randomPrice, randomGenre, randomPlayerBase, randomReleaseDate, randomMultiplayer);
    }

    private static String generateString(int maxWordLength, int wordCount, boolean camelCase){
        //char[] klinkers = {'a', 'e', 'i', 'o', 'u'};
        // dit is niet echt een complete implementatie van een random string
        Random random = new Random();
        String alleKlinkers = "abcdefghijklmnopqrstuvwxyz";
        String alleKlinkersHoofdletter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String spatie = " ";
        boolean meerdereWoorden = random.nextBoolean();
        StringBuilder stringBuilder = new StringBuilder();
        int randomMinGetal = random.nextInt(maxWordLength);

        if (camelCase){
            stringBuilder.append(alleKlinkersHoofdletter.charAt(random.nextInt(26)));
        }
        for (int i = 0; i < maxWordLength-randomMinGetal; i++){
            stringBuilder.append(alleKlinkers.charAt(random.nextInt(26)));
        }
        if (meerdereWoorden){
            if (wordCount == 2){
                stringBuilder.append(spatie);
                if (camelCase){
                    stringBuilder.append(alleKlinkersHoofdletter.charAt(random.nextInt(26)));
                }
                for (int i = 0; i < maxWordLength-randomMinGetal; i++){
                    stringBuilder.append(alleKlinkers.charAt(random.nextInt(26)));
                }
            }
        }
        return stringBuilder.toString();
    }
}
