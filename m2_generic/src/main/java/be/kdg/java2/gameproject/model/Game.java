package be.kdg.java2.gameproject.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Deze klasse bevat de attributen en methode van Game
 * @author Justin van Leuvenum
 * @version 2.0
 * @see <a href="https://nl.wikipedia.org/wiki/Game">label</a>
 */
public class Game implements Comparable<Game>{
    private String name;
    private double price;
    private Genre genre;
    private int playerBase;
    private LocalDate releaseDate;
    private boolean multiplayer;

    public Game(){
        this("ONBEKEND", 0.0, Genre.FPS, 0, LocalDate.of(1999, 12, 31), false);
    }

    /**
     *
     * @param name naam van game
     * @param price prijs van game
     * @param genre type genre van game
     * @param playerBase aantal spelers van game
     * @param releaseDate publicatie datum van game
     * @param multiplayer multiplayer of niet
     */
    public Game(String name, double price, Genre genre, int playerBase, LocalDate releaseDate, boolean multiplayer) {
        setName(name);
        setPrice(price);
        setGenre(genre);
        setPlayerBase(playerBase);
        setReleaseDate(releaseDate);
        setMultiplayer(multiplayer);
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Wrong value for name");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        if(price < 0.0) {
            throw new IllegalArgumentException("Wrong value for price");
        }
        this.price = price;
    }

    /**
     *
     * @return Een genre van enum Genre
     */

    public Genre getGenre() {
        return genre;
    }

    /**
     *
     * @see Genre
     */
    public void setGenre(Genre genre){
        if (genre == null){
            throw new IllegalArgumentException("Wrong value for genre");
        }
        this.genre = genre;
    }

    /**
     *
     * @return Een playerbase van type int
     */
    public int getPlayerBase() {
        return playerBase;
    }

    public void setPlayerBase(int playerBase) throws IllegalArgumentException{
        if (playerBase < 0){
            throw new IllegalArgumentException("You have to give a positive int for player base");
        }
        this.playerBase = playerBase;
    }

    /**
     *
     * @return Een releaseDate van Tyoe LocalDate
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate){
        if (releaseDate == null){
            throw new IllegalArgumentException("Wrong value for date");
        }
        this.releaseDate = releaseDate;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer){
        this.multiplayer = multiplayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return getName().equals(game.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public int compareTo(Game otherGame) {
        return this.name.compareTo(otherGame.getName());
    }

    @Override
    public String toString(){
        return String.format("%-30s Category: %-10s %.1f euro\t Released: %s",
                getName(), getGenre(), getPrice(), getReleaseDate());
    }
}
