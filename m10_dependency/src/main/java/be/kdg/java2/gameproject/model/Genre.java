package be.kdg.java2.gameproject.model;

public enum Genre {
    FPS("FPS"),
    ADVENTURE("adventure"),
    FIGHTING("fighting"),
    PARTY_GAME("party game"),
    PUZZLE("puzzle"),
    HORROR("horror");

    private final String genre;

    Genre(String genre){
        this.genre = genre;
    }

    @Override
    public String toString(){
        return genre;
    }
}


