package be.kdg.java2.gameproject.model;

import be.kdg.java2.gameproject.reflection.CanRun;

import java.time.LocalDate;
import java.util.Objects;

public class Game extends Item {
    private Genre genre;
    private int playerBase;
    private boolean multiplayer;

    public Game(String name, double price, Genre genre, int playerBase, LocalDate releaseDate, boolean multiplayer) {
        super(name, price, releaseDate);
        this.genre = genre;
        this.playerBase = playerBase;
        this.multiplayer = multiplayer;
    }

    @CanRun("Dummy Game")
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Wrong value for name");
        }
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) throws IllegalArgumentException{
        this.genre = genre;
        throw new IllegalArgumentException("Not the right type");
    }

    public int getPlayerBase() {
        return playerBase;
    }

    public void setPlayerBase(int playerBase) throws IllegalArgumentException{
        this.playerBase = playerBase;
        throw new IllegalArgumentException("Not the right type");
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) throws IllegalArgumentException{
        this.multiplayer = multiplayer;
        throw new IllegalArgumentException("Not the right type");
    }

    @Override
    public String toString() {
        String itemString = super.toString();
        return String.format("%s\t%s %d", itemString, getGenre(), getPlayerBase());
    }

}
