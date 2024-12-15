package be.kdg.java2.gameproject.view;

import be.kdg.java2.gameproject.model.Game;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class GamesView extends BorderPane {
    private TableView tvGames;
    private TextField tfName;
    private TextField tfPrice;
    private DatePicker dpRelease;
    private Button btnSave;

    public GamesView(){
        tvGames = new TableView<>();
        tfName = new TextField();
        tfName.setPromptText("Name");
        tfPrice = new TextField();
        tfPrice.setPromptText("Price");
        dpRelease = new DatePicker();
        dpRelease.setPromptText("Release");
        btnSave = new Button("Save");
        btnSave.setMinWidth(Button.USE_PREF_SIZE);
        super.setCenter(tvGames);
        BorderPane.setMargin(tvGames, new Insets(10));
        TableColumn<String, Game> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<LocalDate, Game> column2 = new TableColumn<>("Release");
        column2.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        TableColumn<Double, Game> column3 = new TableColumn<>("Price");
        column3.setCellValueFactory(new PropertyValueFactory<>("price"));

        tvGames.getColumns().addAll(column1, column2, column3);
        HBox hbBottom = new HBox(tfName, dpRelease, tfPrice, btnSave);
        hbBottom.setSpacing(10);
        super.setBottom(hbBottom);
        BorderPane.setMargin(hbBottom, new Insets(10));
    }
    TableView<Game> getTbGames() {
        return tvGames;
    }

    TextField getTfName() {
        return tfName;
    }

    TextField getTfPrice() {
        return tfPrice;
    }

    DatePicker getDpRelease() {
        return dpRelease;
    }

    Button getBtnSave() {
        return btnSave;
    }
}
