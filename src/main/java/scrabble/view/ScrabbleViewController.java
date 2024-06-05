package scrabble.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class ScrabbleViewController {

    @FXML
    private GridPane grillePlateau;

    @FXML
    private Label lettreChevalet1;

    @FXML
    private Label lettreChevalet2;

    @FXML
    private Label lettreChevalet3;

    @FXML
    private Label lettreChevalet4;

    @FXML
    private Label lettreChevalet5;

    @FXML
    private Label lettreChevalet6;

    @FXML
    private Label lettreChevalet7;

    @FXML
    private Button btnQuit;

    @FXML
    private void initialize() {
        int numRows = 15;
        int numCols = 15;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Label label;
                if (row == 7 && col == 7) {
                    label = new Label("*");
                } else {
                    label = new Label();
                }
                label.setId("lbl" + row + "_" + col);
                grillePlateau.add(label, col, row);
                GridPane.setHalignment(label, HPos.CENTER);
                GridPane.setValignment(label, VPos.CENTER);
            }
        }
    }

    @FXML
    private void clicQuitter(ActionEvent event) {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
    }
}