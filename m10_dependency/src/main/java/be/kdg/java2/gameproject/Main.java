package be.kdg.java2.gameproject;

import be.kdg.java2.gameproject.database.GameDao;
import be.kdg.java2.gameproject.database.GameDbDao;
import be.kdg.java2.gameproject.service.GamesService;
import be.kdg.java2.gameproject.service.GamesServiceImpl;
import be.kdg.java2.gameproject.view.GamesPresenter;
import be.kdg.java2.gameproject.view.GamesView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class Main extends Application {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args){
        logger.info("Starting database...");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("Start methode is being started...");

        GamesView gamesView = new GamesView();
        GameDao gameDao = GameDbDao.getInstance();
        GamesService gamesService = new GamesServiceImpl(gameDao);

        new GamesPresenter(gamesView, gamesService);
        primaryStage.setScene(new Scene(gamesView));
        primaryStage.isResizable();
        primaryStage.show();
    }
}
