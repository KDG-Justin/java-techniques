package be.kdg.java2.gameproject;

import be.kdg.java2.gameproject.kollections.lists.ArrayList;
import be.kdg.java2.gameproject.kollections.Kollections;
import be.kdg.java2.gameproject.kollections.lists.List;
import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.GameFactory;
import be.kdg.java2.gameproject.model.Games;
import be.kdg.java2.gameproject.model.Genre;

import java.time.LocalDate;
import java.util.stream.Stream;

public class Demo8 {
    public static void main(String[] args){
        Games games = new Games();


        Game emptyGame = GameFactory.newEmptyGame();
        System.out.println("Empty game: "+ emptyGame);

        Game filledGame = GameFactory.newFilledGame("God of War 3", 21.5, Genre.ADVENTURE, 1212 ,LocalDate.of(2009, 10, 2), false);
        System.out.println("Filled game: "+ filledGame);

        System.out.println("==================================================");
        System.out.println("Random game: ");
        Stream.generate(GameFactory::newRandomGame).limit(30).forEach(games::add);
        games.sortedOnName().forEach(System.out::println);

        System.out.println("==================================================");
        System.out.println("Via PerformanceTester methode");
        List<Game> gameList = PerformanceTester.randomList(5);
        for (int i = 0; i < gameList.size(); i++){
            System.out.println(gameList.get(i));
        }

        System.out.println("==================================================");
        System.out.println("Performance tester ArrayList and LinkedList");
        PerformanceTester.compareArrayListAndLinkedList(15000);


        System.out.println("==================================================");
        System.out.println("Selection Sort van list");
        PerformanceTester.testSelectionSort();

        System.out.println("==================================================");
        System.out.println("Merge Sort van list");
        //PerformanceTester.testMergeSort(); probleem met mergsort methode

        System.out.println("==================================================");
        System.out.println("Quick Sort");
        List<Game> myList = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            myList.add(GameFactory.newRandomGame());
        }
        Kollections.quickSort(myList);
        for (int i = 0; i < myList.size(); i++){
            System.out.println(myList.get(i));
        }

        System.out.println("==================================================");
        for (int i = 0; i < myList.size(); i++){
            System.out.printf("Index of %s : %d\n", myList.get(i).getName(), Kollections.lineairSearch(myList, myList.get(i)));
            System.out.printf("Index of %s : %d\n", myList.get(i).getName(), Kollections.binarySearch(myList, myList.get(i)));
        }
        System.out.println("==================================================");
        PerformanceTester.compareListMapToHasMap(1000);
        System.out.println("==================================================");
        PerformanceTester.compareArraySetToTreeSet(1000);
    }
}
