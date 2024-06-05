package scrabble.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import scrabble.controleur.Arbitre;
import scrabble.joueur.Joueur;
import scrabble.model.Chevalet;
import scrabble.model.Plateau;
import scrabble.model.Sac;
import scrabble.view.ScrabbleViewController;

import java.util.Objects;

public class ScrabbleApplication extends Application {
    public static void main(String[] args){
        //Lancement de l'interface
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Initialisation des variables nécessaires
        Arbitre arbitre = new Arbitre();
        Sac sac = new Sac();
        Plateau plateau = new Plateau();
        Chevalet chevaletj1 = new Chevalet();
        Joueur j1 = new Joueur(chevaletj1);

        //On remplit le sac, qu'on mélange ensuite
        sac.remplirJeuFrancais();
        sac.melanger();

        //On remplit ensuite le chevalet du joueur
        arbitre.remplirChevalet(sac, j1);

        //Init interface
        BorderPane root = new BorderPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ScrabbleView.fxml"));
        Parent content = loader.load();
        ScrabbleViewController controller = loader.getController();
        controller.setJoueur(j1);
        root.setCenter(content);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/stylesheet_light.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scrabble");
        primaryStage.show();
    }
}
