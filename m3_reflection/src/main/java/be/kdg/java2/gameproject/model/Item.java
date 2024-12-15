package be.kdg.java2.gameproject.model;

import be.kdg.java2.gameproject.reflection.CanRun;

import java.time.LocalDate;
import java.util.Objects;

public class Item implements Comparable<Game> {
    protected String name;
    protected double price;
    protected LocalDate releaseDate;

    public Item(String name, double price, LocalDate releaseDate) {
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    @CanRun("Dummy Game")
    public void setName(String name) throws IllegalArgumentException {
        this.name = name;
        throw new IllegalArgumentException("Not the right type");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws IllegalArgumentException {
        this.price = price;
        throw new IllegalArgumentException("Not the right type");
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) throws IllegalArgumentException {
        this.releaseDate = releaseDate;
        throw new IllegalArgumentException("Not the right type");
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
    public String toString() {
        return String.format("%-30s %-10.1f euro\t Released: %s",
                getName(), getPrice(), getReleaseDate());
    }
}
