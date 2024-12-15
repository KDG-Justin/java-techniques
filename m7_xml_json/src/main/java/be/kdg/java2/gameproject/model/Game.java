package be.kdg.java2.gameproject.model;

import be.kdg.java2.gameproject.parsing.LocalDateXmlAdapter;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement()
public class Game implements Comparable<Game>{
    private String name;
    private double price;
    private Genre genre;

    private int playerbase;
    private LocalDate release;
    private boolean multiplayer;

    public Game() {

    }

    public Game(String name, double price, Genre genre, int playerbase, LocalDate releaseDate, boolean multiplayer) {
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.playerbase = playerbase;
        this.release = releaseDate;
        this.multiplayer = multiplayer;
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

    public Genre getGenre() {
        return genre;
    }

    @XmlAttribute
    public void setGenre(Genre genre){
        if (genre == null){
            throw new IllegalArgumentException("Wrong value for genre");
        }
        this.genre = genre;
    }

    public int getPlayerbase() {
        return playerbase;
    }

    public void setPlayerbase(int playerbase){
        this.playerbase = playerbase;
        //throw new IllegalArgumentException("You have to give a positive int for player base");
    }

    public LocalDate getRelease() {
        return release;
    }

    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    public void setRelease(LocalDate release){
        if (release == null){
            throw new IllegalArgumentException("Wrong value for date");
        }
        this.release = release;
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
                getName(), getGenre(), getPrice(), getRelease());
    }
}
