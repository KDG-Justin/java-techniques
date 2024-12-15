package be.kdg.java2.gameproject.model;

public enum Genre {
    //krijgt geen error als de namen kleine letters zijn
    fps("fps"),
    adventure("adventure"),
    fighting("fighting"),
    party_game("party_game"),
    puzzle("puzzle"),
    horror("horror");

    private final String genre;

    Genre(String genre){
        this.genre = genre;
    }

    @Override
    public String toString(){
        return genre;
    }
}


