package scrabble.joueur;

//TODO vertical ne marche pas et en plus les mots peuvent être placés sans être connectés

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
        for (Jeton jetonaechanger : new ArrayList<>(this.retourneChevalet().retourneJetons())) {
            if (jetonaechanger == jeton) {
                this.retourneChevalet().retourneJetons().remove(jetonaechanger);
                plateau.placerJeton(jeton, positionx, positiony);
                if (jetonaechanger == Jeton.JOKER) {
                    this.estUnJoker(jeton);
                    plateau.placerJeton(jeton, positionx, positiony);
                }
                break;
            }
        }
    }

    public void placerMot(String mot, int positionx, int positiony, boolean horizontal, Plateau plateau) {
        mot = mot.toUpperCase();
    	char[] lettres = mot.toCharArray();
        boolean premierMot = plateau.estVide();
        List<Jeton> lettresUtilisees = new ArrayList<>();
        int centre = plateau.retourneTaille() / 2;

        try {
            if (premierMot) {
                boolean passeParCentre = false;
                for (int i = 0; i < lettres.length; i++) {
                    int x = horizontal ? positionx + i : positionx;
                    int y = horizontal ? positiony : positiony + i;
                    if (x == centre && y == centre) {
                        passeParCentre = true;
                        break;
                    }
                }
                if (!passeParCentre) {
                    throw new HorsPlateauException("Le premier mot doit passer par le centre du plateau !");
                }
            } else {
                boolean appuieSurLettreExistante = false;
                for (int i = 0; i < lettres.length; i++) {
                    int x = horizontal ? positionx + i : positionx;
                    int y = horizontal ? positiony : positiony + i;
                    if (plateau.recupererJeton(x, y) != null) {
                        appuieSurLettreExistante = true;
                        break;
                    }
                }
                if (!appuieSurLettreExistante) {
                    throw new HorsPlateauException("Les mots suivants doivent s'appuyer sur une lettre existante !");
                }
            }

            for (int i = 0; i < lettres.length; i++) {
                int x = horizontal ? positionx + i : positionx;
                int y = horizontal ? positiony : positiony + i;
                if (x >= plateau.retourneTaille() || y >= plateau.retourneTaille()) {
                    throw new HorsPlateauException("Le mot sort du plateau !");
                }
            }

            for (char lettre : lettres) {
                boolean trouveJeton = false;
                for (Jeton jeton : this.retourneChevalet().retourneJetons()) {
                    if (jeton.name().charAt(0) == lettre) {
                        trouveJeton = true;
                        break;
                    }
                }
                if (!trouveJeton) {
                    throw new HorsPlateauException("Le joueur n'a pas tous les jetons nécessaires pour former ce mot !");
                }
            }

            for (int i = 0; i < lettres.length; i++) {
                int x = horizontal ? positionx + i : positionx;
                int y = horizontal ? positiony : positiony + i;
                for (Jeton jeton : this.retourneChevalet().retourneJetons()) {
                    if (jeton.toString().charAt(0) == lettres[i]) {
                        lettresUtilisees.add(jeton);
                        this.placerLettre(jeton, x, y, plateau);
                        break;
                    }
                }
            }

            premierMot = false; 
        } catch (HorsPlateauException e) {
            System.out.println(e.getMessage());
            this.retourneChevalet().retourneJetons().addAll(lettresUtilisees);
            System.out.println("Souhaitez-vous rejouer (r) ou passer votre tour (p) ?");
            Scanner scanner = new Scanner(System.in);
            String choix = scanner.nextLine().toLowerCase();
            if (choix.equals("p")) {
                System.out.println("Tour passé.");
                return;
            } else if (choix.equals("r")) {
                System.out.println("Rejouez votre tour.");
            }
           
        }
        
    }

    public void estUnJoker(Jeton jeton) {
        System.out.println("Quelle lettre voulez vous ?");
        Scanner estJoker = new Scanner(System.in);
        // Logique pour gérer la sélection de la lettre pour le joker
    }
}
