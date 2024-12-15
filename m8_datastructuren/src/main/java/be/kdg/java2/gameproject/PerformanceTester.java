package be.kdg.java2.gameproject;
import be.kdg.java2.gameproject.kollections.lists.ArrayList;
import be.kdg.java2.gameproject.kollections.Kollections;
import be.kdg.java2.gameproject.kollections.lists.LinkedList;
import be.kdg.java2.gameproject.kollections.lists.List;
import be.kdg.java2.gameproject.kollections.maps.HashMap;
import be.kdg.java2.gameproject.kollections.maps.ListMap;
import be.kdg.java2.gameproject.kollections.sets.ArraySet;
import be.kdg.java2.gameproject.kollections.sets.TreeSet;
import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.model.GameFactory;


import java.util.Random;

public class PerformanceTester {

    public static List<Game> randomList(int n) {
        //List<Game> myList = new ArrayList<>();
        List<Game> myList = new LinkedList<>();
        new Random().ints(n).forEach(i -> myList.add(GameFactory.newRandomGame()));
        return myList;
    }

    public static void compareArrayListAndLinkedList(int n) {
        List<Game> myList = new ArrayList<>();
        List<Game> linkedList = new LinkedList<>();
        //add at beginning
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n;i++){
            myList.add(GameFactory.newRandomGame());
        }
        long duration = System.currentTimeMillis() - startTime;
        System.out.printf("Adding %d to Arraylist: %d ms\n", n, duration);

        for (int i = 0; i < n;i++){
            linkedList.add(GameFactory.newRandomGame());
        }
        duration = System.currentTimeMillis() - startTime;
        System.out.printf("Adding %d to LinkedList: %d ms\n", n, duration);


        //get at end
        long startTimeEnd = System.currentTimeMillis();
        for (int i = 0; i < myList.size(); i++){
            myList.get(i);
        }
        duration = System.currentTimeMillis() - startTimeEnd;
        System.out.printf("Getting %d from ArrayList: %d ms\n", n, duration);

        for (int i = 0; i < linkedList.size(); i++){
            linkedList.get(i);
        }
        duration = System.currentTimeMillis() - startTimeEnd;
        System.out.printf("Getting %d from LinkedList: %d ms\n", n, duration);

    }

    public static void testSelectionSort() {
        List<Game> myList = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            myList.add(GameFactory.newRandomGame());
            Kollections.selectionSort(myList);
            System.out.printf("%d : %d\n", myList.size(), Game.compareCounter);
        }
        /*
        for (int i = 0; i < myList.size(); i++){
            System.out.printf("%d : %d", Game.teller,myList.get(i));
        }

         */
    }

    public static void testMergeSort() {
        List<Game> myList = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            myList.add(GameFactory.newRandomGame());
        }
        Kollections.mergeSort(myList);
        for (int i = 0; i < myList.size(); i++){
            System.out.println(myList.get(i));
        }
    }

    public static void compareListMapToHasMap(int n){
        Game[] gameArray = new Game[n];
        //mappen aanmaken
        ListMap<Game, String> gameList = new ListMap<>();
        HashMap<Game, String> gameHash = new HashMap<>();

        //mappen fullen door methode
        fillMap(gameArray, n, gameList, gameHash);


        // vergelijking performance
        // listmap
        long listStart = System.nanoTime();
        for (int i = 0; i < n; i++) {
            gameList.get(gameArray[i]);
        }
        long listEnd = System.nanoTime();
        int equalsCounterList = Game.equalsCounter;
        long listDuration = listEnd - listStart ;

        // hasmap
        long hashStart = System.nanoTime();
        Game.setEqualsCounter(0);
        for (int i = 0; i < n; i++) {
            gameHash.get(gameArray[i]);
        }
        long hasEnd = System.nanoTime();
        int equalsCounterHash = Game.equalsCounter;
        long hashDuration = hasEnd - hashStart;

        System.out.printf("ListMap: n = %d, equalscount = %d, nanosec = %d\n"
                ,n, equalsCounterList, listDuration);

        System.out.printf("HashMap: n = %d, equalscount = %d, nanosec = %d\n"
                ,n, equalsCounterHash, hashDuration);
    }

    private static void fillMap(Game[] games, int n, ListMap<Game, String> listMap, HashMap<Game, String> hashMap){
        for(int i = 0; i < n; i++){
            Game game = GameFactory.newEmptyGame();
            game.setName("Poop Killer " + i);
            listMap.put(game, "This is Poop Killer " + i);
            hashMap.put(game, "This is Poop Killer " + i);
            games[i] = game;
        }
    }

    public static void compareArraySetToTreeSet(int n){
        ArraySet<Game> gameArraySet = new ArraySet<>();
        TreeSet<Game> gameTreeSet = new TreeSet<>();

        long arraySetStartNano = System.nanoTime();
        for (int i = 0; i<n; i++){
            gameArraySet.add(GameFactory.newRandomGame());
        }
        int equalsCounterArraySet = Game.equalsCounter;
        int compareCounterArraySet = Game.compareCounter;
        long arraySetEndNano = System.nanoTime();
        long durationArraySet = arraySetEndNano - arraySetStartNano;

        Game.setEqualsCounter(0);
        Game.setCompareCounter(0);

        long treeSetStartNano = System.nanoTime();
        for (int i = 0; i<n; i++){
            gameTreeSet.add(GameFactory.newRandomGame());
        }
        long treeSetEndNano = System.nanoTime();
        long durationTreeSet = treeSetEndNano - treeSetStartNano;
        int equalsCounterTreeSet = Game.equalsCounter;
        int compareCounterTreeSet = Game.compareCounter;

        System.out.printf("""
                ArraySet, n = %d: equalscount: %d
                Arrayset, n = %d: comparecount: %d
                Arrayset, n = %d: nanosec: %d
                """, n, equalsCounterArraySet, n, compareCounterArraySet, n, durationArraySet);
        System.out.printf("""
                TreeSet, n = %d: equalscount: %d
                TreeSet, n = %d: comparecount: %d
                TreeSet, n = %d: nanosec: %d
                """, n, equalsCounterTreeSet, n, compareCounterTreeSet, n, durationTreeSet);
    }
}