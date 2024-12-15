package be.kdg.java2.gameproject.view;

import be.kdg.java2.gameproject.exceptions.GameException;
import be.kdg.java2.gameproject.model.Game;
import be.kdg.java2.gameproject.service.GamesService;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

import java.util.List;
import java.util.logging.Logger;

public class GamesPresenter {
    private static final Logger logger = Logger.getLogger("be.kdg.java2.gameproject.view.GamesPresenter");
    private GamesView gamesView;
    private GamesService gamesService;

    public GamesPresenter(GamesView gamesView, GamesService gamesService){
        try {
            this.gamesView = gamesView;
            this.gamesService = gamesService;
            loadGames();
            gamesView.getBtnSave().setOnAction(event -> {
                Game game = new Game(gamesView.getTfName().getText(),
                        Double.parseDouble(gamesView.getTfPrice().getText()),
                        gamesView.getDpRelease().getValue());
                gamesService.addGame(game);
                loadGames();
            });

        }catch (GameException e){
            new Alert(Alert.AlertType.ERROR, "Unable to add game");
        }
    }

    private void loadGames() {
        try {
            List<Game> myList = gamesService.getAllGames();
            gamesView.getTbGames().setItems(FXCollections.observableList(myList));
        } catch (GameException e) {
            new Alert(Alert.AlertType.ERROR, "Unable to load games:\n" + e.getMessage()).showAndWait();
        }
    }
}
