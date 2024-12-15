package be.kdg.java2.gameproject.model;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Games {
    //private TreeSet<Game> games = new TreeSet<>();
    private ArrayBlockingQueue<Game> games;

    //public Games(){}
    public Games(int capacity) {
        this.games = new ArrayBlockingQueue<>(capacity);
    }
    public boolean add(Game game){
        games.add(game);
        return true;
    }

    public boolean remove(String name){
        boolean removed = false;
        Iterator<Game> it = games.iterator();
        while (it.hasNext()){
            if (it.next().getName().equalsIgnoreCase(name)){
                it.remove();
                removed = true;
            }
        }
        return removed;
    }

    // methode werkt niet volledig, het pakt de volgende maar ik wil de vorige
    public Game search(String name){
        Iterator<Game> it = games.iterator();
        Game searchedGame = null;
        while (it.hasNext()){
            Game current = it.next();
            if (name.equalsIgnoreCase(current.getName())){
                searchedGame = current;
            }
        }
        return searchedGame;
    }

    public List<Game> sortedOnName(){
        List<Game> myList = new ArrayList<>(games);
        Collections.sort(myList);
        return myList;
    }

    public List<Game> sortedOnRelease(){
        List<Game> myList = new ArrayList<>(games);
        Collections.sort(myList, new releaseComparator());
        return myList;
    }

    public List<Game> sortedOnPlayerBase(){
        List<Game> myList = new ArrayList<>(games);
        Collections.sort(myList, new priceComparator());
        return myList;
    }


    private class releaseComparator implements Comparator<Game>{
        @Override
        public int compare(Game one, Game two) {
            return one.getReleaseDate().compareTo(two.getReleaseDate());
        }
    }

    private class priceComparator implements Comparator<Game>{
        @Override
        public int compare(Game one, Game two) {
            return Double.compare(one.getPrice(), two.getPrice());
        }
    }

    public int getSize(){
        return games.size();
    }
}
