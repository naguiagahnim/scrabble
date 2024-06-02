package scrabble.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class ScrabbleViewController {

    @FXML
    public GridPane grillePlateau;

    @FXML
    private Button btnQuit;

    @FXML
    private void initialize() {
        //initialisation
    }

    @FXML
    private void clicQuitter(ActionEvent event) {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
    }

}

