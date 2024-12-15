package be.kdg.java2.gameproject.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Games implements Serializable {
    private TreeSet<Game> games = new TreeSet<>();

    @Serial
    public static final long serialVersionUID = 1L;

    public TreeSet<Game> getGames() {
        return games;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Games games1 = (Games) o;
        return getGames().equals(games1.getGames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGames());
    }
}
