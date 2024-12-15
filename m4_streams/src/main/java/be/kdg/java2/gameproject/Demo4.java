package be.kdg.java2.gameproject;

import be.kdg.java2.gameproject.data.Data;
import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.Games;
import be.kdg.java2.gameproject.model.Genre;
import be.kdg.java2.gameproject.util.GameFunctions;

import java.util.*;
import java.util.stream.Collectors;


public class Demo4 {
    public static void main(String[] args) {
        Games listOfGames = new Games();
        List<Game> myList = Data.getData();
        myList.forEach(listOfGames::add);


        System.out.println("\nGames gesorteerd op naam:");
        listOfGames.sortedBy(Game::getName).forEach(System.out::println);

        System.out.println("\nGames gesorteerd op prijs:");
        listOfGames.sortedBy(Game::getPrice).forEach(System.out::println);

        System.out.println("\nGames gesorteerd op release:");
        listOfGames.sortedBy(Game::getReleaseDate).forEach(System.out::println);

        System.out.println("\nLijst met gratis Games: ");
        System.out.println(GameFunctions.filteredList(myList, a -> a.getPrice() == 0));

        List<Game> freshDataList = Data.getData();

        System.out.printf("\nGemiddeld prijs: %.1f euro\n",
                GameFunctions.average(freshDataList, Game::getPrice));

        System.out.printf("Aantal games met prijs = 0 euro: %d\n",
                GameFunctions.countIf(freshDataList, game -> game.getPrice() == 0));

        System.out.println("------------------------------------------------------------");
        List<Game> streamList = Data.getData();
        System.out.println("Games in gemaakt in 2022");
        long sum = streamList.stream()
                .map(Game::getReleaseDate)
                .filter(l -> l.getYear() == 2022)
                .distinct()
                .count();
        System.out.println(sum);
        System.out.println();
        System.out.println("Sorteert volgens 2 criterias");
        streamList.stream()
                .sorted(Comparator.comparing(Game::getPrice)
                        .thenComparing(Comparator.comparing(Game::getName)))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Alle genre in hoofdletters, omgekeerd gesorteerd en zonder dubbels:");
        String genre = myList.stream()
                .map(game -> game.getGenre().toString().toUpperCase())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining(", "));
        System.out.println(genre);

        System.out.println();

        System.out.println("FindAny Game met M");
        String findAny = streamList.stream().map(Game::getName)
                .filter(name -> name.startsWith("M"))
                .findAny()
                .orElse("Not present");
        System.out.println(findAny);

        System.out.println();

        System.out.println("Duurste Game");
        streamList.stream().max(Comparator.comparing(Game::getPrice)).ifPresent(System.out::println);
        System.out.println("Hoogste playerBase");
        streamList.stream().max(Comparator.comparing(Game::getPlayerBase)).ifPresent(System.out::println);

        System.out.println();

        System.out.println("List met gesorteerde Game namen die beginnen met 'C': ");
        var result = myList.stream()
                .map(Game::getName)
                .filter(name -> name.startsWith("C"))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(result);

        System.out.println();

        System.out.println("Partitioneer volgens datum VOOR 2018");
        Map<Boolean, List<Game>> myMap = streamList.stream()
                .sorted(Comparator.comparing(Game::getReleaseDate))
                .collect(Collectors.partitioningBy(g -> g.getReleaseDate().getYear() > 2018));
        System.out.println(myMap.get(false));
        System.out.println();
        System.out.println("Partitioneer volgens datum NA 2018");
        Map<Boolean, List<Game>> myMap2 = streamList.stream()
                .sorted(Comparator.comparing(Game::getReleaseDate))
                .collect(Collectors.partitioningBy(g -> g.getReleaseDate().getYear() > 2018));
        System.out.println(myMap2.get(true));

        System.out.println();

        System.out.println("Gegroepeerde Map Genre");
        List<Game> newList = Data.getData();
        Map<Genre, List<Game>> myTreeMap = new TreeMap<>(newList.stream()
                .sorted(Comparator.comparing(Game::getGenre)
                        .thenComparing(Game::getName))
                .collect(Collectors.groupingBy(Game::getGenre)));

        myTreeMap.keySet().forEach(s -> {
            System.out.println(s);
            myTreeMap.get(s).forEach(a -> System.out.println("\t" + a));
        });

    }
}
