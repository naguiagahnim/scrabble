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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import scrabble.controleur.Arbitre;
import scrabble.joueur.Joueur;
import scrabble.model.Jeton;
import scrabble.model.Plateau;
import scrabble.model.Sac;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<StackPane, Boolean> stackPaneStates = new HashMap<>(); // Stocke l'état de chaque StackPane

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
            setDragEvents(labels[i]);
        }
    }

    @FXML
    private void initialize() {
        int numRows = 15;
        int numCols = 15;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                StackPane stackPane = new StackPane();
                Label label;
                if (row == 7 && col == 7) {
                    label = new Label("*");
                } else {
                    label = new Label("");
                }
                label.setId("lbl" + row + "_" + col);
                label.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
                setDropEvents(stackPane);
                stackPane.getChildren().add(label);
                grillePlateau.add(stackPane, col, row);
                GridPane.setHalignment(stackPane, HPos.CENTER);
                GridPane.setValignment(stackPane, VPos.CENTER);

                // Initialiser l'état de chaque StackPane à false
                stackPaneStates.put(stackPane, false);
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

    private void setDropEvents(StackPane stackPane) {
        stackPane.setOnDragOver(event -> {
            if (event.getGestureSource() != stackPane && event.getDragboard().hasString() && !stackPane.getStyle().contains("-fx-background-color: #e5ce8b")) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        stackPane.setOnDragEntered(event -> {
            if (!stackPaneStates.get(stackPane) && event.getGestureSource() != stackPane && event.getDragboard().hasString()) {
                stackPane.setStyle("-fx-background-color: lightblue;");
            }
        });

        stackPane.setOnDragExited(event -> {
            if (!stackPaneStates.get(stackPane)) {
                stackPane.setStyle("-fx-background-color: #15733a;");
            }
        });

        stackPane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                Label label = (Label) stackPane.getChildren().get(0);
                if (!stackPane.getStyle().contains("-fx-background-color: #e5ce8b")) { // Vérifier si le fond est différent de #e5ce8b
                    label.setText(db.getString());
                    stackPane.setStyle("-fx-background-color: #e5ce8b;");
                    stackPaneStates.put(stackPane, true); // Mettre à jour l'état de la StackPane
                    success = true;
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });

        stackPane.setOnDragDone(event -> {
            // Réinitialiser l'état de chaque StackPane à false après chaque drag-and-drop
            stackPaneStates.replaceAll((sPane, value) -> false);
            event.consume();
        });
    }


    @FXML
    private void clicQuitter(ActionEvent event) {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
    }
}
