package be.kdg.java2.gameproject.model;

import java.util.*;
import java.util.logging.Logger;

public class Games {
    private TreeSet<Game> games = new TreeSet<>();

    public TreeSet<Game> getGames() {
        return games;
    }

    private static final Logger logger = Logger.getLogger("be.kdg.java2.gameproject.model.Games");


    public boolean add(Game game){
        games.add(game);
        logger.finer(String.format("Game %s added", game.getName()));
        return true;
    }

    public boolean remove(String name) throws IllegalArgumentException{
        boolean removed = false;
        Iterator<Game> it = games.iterator();
        while (it.hasNext()){
            if (it.next().getName().equalsIgnoreCase(name)){
                it.remove();
                removed = true;
                logger.finer(String.format("Game %s removed", name));
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
