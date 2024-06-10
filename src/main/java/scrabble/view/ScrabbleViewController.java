package scrabble.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import scrabble.controleur.Arbitre;
import scrabble.joueur.Joueur;
import scrabble.model.Jeton;
import scrabble.model.Plateau;
import scrabble.model.Sac;

import java.util.List;

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

    private Joueur joueur;
    private Arbitre arbitre;
    private Sac sac;
    private Plateau plateau;

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
        afficherLettresChevalet();
    }

    public void setArbitre(Arbitre arbitre) {
        this.arbitre = arbitre;
    }

    public void setSac(Sac sac) {
        this.sac = sac;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    private void afficherLettresChevalet() {
        List<Jeton> jetons = joueur.retourneChevalet().retourneJetons();
        Label[] labels = {lettreChevalet1, lettreChevalet2, lettreChevalet3, lettreChevalet4, lettreChevalet5, lettreChevalet6, lettreChevalet7};

        for (int i = 0; i < jetons.size() && i < labels.length; i++) {
            labels[i].setText(jetons.get(i).name().substring(0, 1)); // Utilisation du nom de l'énumérateur
            setDragEvents(labels[i]); // Configure le drag and drop pour chaque lettre du chevalet
        }
    }

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
                    label = new Label("");
                }
                label.setId("lbl" + row + "_" + col);
                label.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-background-color: white;"); // Set default background color
                setDropEvents(label); // Configure le drop pour chaque case du plateau
                grillePlateau.add(label, col, row);
                GridPane.setHalignment(label, HPos.CENTER);
                GridPane.setValignment(label, VPos.CENTER);
            }
        }
    }

    private void setDragEvents(Label label) {
        label.setOnDragDetected(event -> {
            Dragboard db = label.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(label.getText());
            db.setContent(content);
            event.consume();
        });
    }

    private void setDropEvents(Label label) {
        label.setOnDragOver(event -> {
            if (event.getGestureSource() != label && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        label.setOnDragEntered(event -> {
            if (event.getGestureSource() != label && event.getDragboard().hasString()) {
                label.setStyle("-fx-background-color: lightblue;"); // Change background color when dragged over
            }
        });

        label.setOnDragExited(event -> {
            label.setStyle("-fx-background-color: white;"); // Revert to default background color
        });

        label.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                label.setText(db.getString());
                label.setStyle("-fx-background-color: lightgreen;"); // Change background color when dropped
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        label.setOnDragDone(DragEvent::consume);
    }

    @FXML
    private void clicQuitter(ActionEvent event) {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
    }
}
