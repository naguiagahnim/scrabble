package scrabble.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scrabble.controleur.Arbitre;
import scrabble.joueur.Joueur;
import scrabble.model.Chevalet;
import scrabble.model.Plateau;
import scrabble.model.Sac;
import scrabble.view.ScrabbleViewController;

import java.util.Objects;

public class ScrabbleApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialisation des variables nécessaires
        Arbitre arbitre = new Arbitre();
        Sac sac = new Sac();
        Plateau plateau = new Plateau();
        Chevalet chevaletj1 = new Chevalet();
        Joueur j1 = new Joueur(chevaletj1);

        // Remplir et mélanger le sac
        sac.remplirJeuFrancais();
        sac.melanger();

        // Remplir le chevalet du joueur
        arbitre.remplirChevalet(sac, j1);

        // Charger l'interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ScrabbleView.fxml"));
        Parent root = loader.load();
        ScrabbleViewController controller = loader.getController();
        controller.setJoueur(j1);
        controller.setArbitre(arbitre);
        controller.setSac(sac);
        controller.setPlateau(plateau);

        // Configurer et afficher la scène
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/stylesheet_light.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scrabble");
        primaryStage.show();
    }
}
