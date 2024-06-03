package scrabble.joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scrabble.application.ScrabbleApplicationConsole;
import scrabble.exceptions.HorsPlateauException;
import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Plateau;

public class Joueur {
    private Chevalet chevalet;

    public Chevalet retourneChevalet() {
        return this.chevalet;
    }

    public Joueur(Chevalet chevalet) {
        this.chevalet = chevalet;
    }

    public void placerLettre(Jeton jeton, int positionx, int positiony, Plateau plateau) throws HorsPlateauException {
        for (Jeton jetonAechanger : this.retourneChevalet().retourneJetons()) {
            if (jetonAechanger == jeton) {
                this.retourneChevalet().retourneJetons().remove(jetonAechanger);
                if (jetonAechanger == Jeton.JOKER) {
                    jeton = estUnJoker(jetonAechanger);
                }
                plateau.placerJeton(jeton, positionx, positiony);
                break;
            }
        }
    }

    public void placerMot(Plateau plateau) {
        Scanner scanner = new Scanner(System.in);
        List<Jeton> lettresUtilisees = new ArrayList<>();

        ScrabbleApplicationConsole.message("Entrez les lettres une par une avec leurs coordonnées.");
        while (true) {
            ScrabbleApplicationConsole.message("Entrez une lettre (ou appuyez sur '0' pour terminer) :");
            String lettre = scanner.nextLine().toUpperCase();
            if (lettre.equals("0")) {
                break;
            }
            if (lettre.length() != 1 || !Character.isLetter(lettre.charAt(0))) {
                ScrabbleApplicationConsole.message("Entrée invalide. Veuillez entrer une seule lettre.");
                continue;
            }

            char charLettre = lettre.charAt(0);
            Jeton jeton = null;
            for (Jeton j : this.retourneChevalet().retourneJetons()) {
                if (j.toString().charAt(0) == charLettre) {
                    jeton = j;
                    break;
                }
            }
            if (jeton == null) {
                ScrabbleApplicationConsole.message("Jeton non disponible dans votre chevalet.");
                continue;
            }

            ScrabbleApplicationConsole.message("Entrez la position x de la lettre :");
            int posx = scanner.nextInt();
            ScrabbleApplicationConsole.message("Entrez la position y de la lettre :");
            int posy = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            try {
                if (posx >= plateau.retourneTaille() || posy >= plateau.retourneTaille()) {
                    throw new HorsPlateauException("La lettre sort du plateau !");
                }
                if (plateau.CaseOccupe(posx,posy)) {
                    throw new HorsPlateauException("Il est impossible de placer une lettre sur une case occupée !");
                }
                this.placerLettre(jeton, posx, posy, plateau);
                lettresUtilisees.add(jeton);
            } catch (HorsPlateauException e) {
                ScrabbleApplicationConsole.message(e.getMessage());
            }
        }

        if (lettresUtilisees.isEmpty()) {
            ScrabbleApplicationConsole.message("Aucune lettre placée.");
        }
    }

    public Jeton estUnJoker(Jeton jeton) {
        Scanner scanner = new Scanner(System.in);
        ScrabbleApplicationConsole.message("Quelles lettres voulez-vous pour le joker ?");
        String lettre = scanner.nextLine().toUpperCase();

        while (lettre.length() != 1 || !Character.isLetter(lettre.charAt(0))) {
            ScrabbleApplicationConsole.message("Entrée invalide. Veuillez entrer une seule lettre.");
            lettre = scanner.nextLine().toUpperCase();
        }

        Jeton nouveauJeton = Jeton.valueOf(lettre);

        this.retourneChevalet().retourneJetons().remove(jeton);
        this.retourneChevalet().retourneJetons().add(nouveauJeton);

        return nouveauJeton;
    }
}
