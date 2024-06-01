package scrabble.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class ScrabbleApplication extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Parent content = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ScrabbleView.fxml")));
        root.setCenter(content);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/stylesheet_light.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scrabble");
        primaryStage.show();
    }
}
