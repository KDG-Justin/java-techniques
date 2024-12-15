package be.kdg.java2.gameproject.util;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;


public class GameFunctions {
    public static <T> List<T> filteredList(List<T> gameList, Predicate<T> predicate){
        gameList.stream()
                .filter(predicate)
                .forEach(System.out::println);
        return gameList;
    }

    public static <T> Double average (List<T> gameList, ToDoubleFunction<T> mapper){
        return gameList.stream()
                .distinct()
                .mapToDouble(mapper)
                .average()
                .orElse(0);

    }

    public static <T> long countIf(List<T> gameList, Predicate<T> predicate){
        return gameList.stream()
                .filter(predicate)
                .count();
    }
}
