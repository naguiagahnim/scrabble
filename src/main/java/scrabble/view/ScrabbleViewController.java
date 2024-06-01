package scrabble.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.Objects;

public class ScrabbleViewController {
    @FXML
    private Button btnTheme;

    private boolean isLightTheme = true;


    @FXML
    private void initialize() {
        //initialisation
    }

    @FXML
    private void clicTheme(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        if (isLightTheme) {
            scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("src/main/resources/stylesheet_light.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/stylesheet_dark.css.css")).toExternalForm());
        } else {
            scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("src/main/resources/stylesheet_dark.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("src/main/resources/stylesheet_light.css")).toExternalForm());
        }
        isLightTheme = !isLightTheme;
    }
}

