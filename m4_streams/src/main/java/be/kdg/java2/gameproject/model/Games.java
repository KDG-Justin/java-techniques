package be.kdg.java2.gameproject.model;


import java.util.*;
import java.util.function.Function;

public class Games {
    private TreeSet<Game> games = new TreeSet<>();


    public TreeSet<Game> getGames() {
        return games;
    }

    public boolean add(Game game) {
        games.add(game);
        return true;
    }

    public boolean remove(String name) {
        return games.removeIf(g -> g.getName().equalsIgnoreCase(name));
    }

    public Game search(String name) {
        games.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Geen game gevonden!"));
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Game> sortedBy(Function<Game, Comparable> function) {
        List<Game> myList = new ArrayList<>(games);
        myList.sort(Comparator.comparing(function));
        return myList;
    }

    public int getSize() {
        return games.size();
    }
}
