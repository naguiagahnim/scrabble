package scrabble.joueur;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scrabble.application.ScrabbleApplicationConsole;
import scrabble.exceptions.HorsPlateauException;
import scrabble.model.Chevalet;
import scrabble.model.Constant;
import scrabble.model.Jeton;
import scrabble.model.Plateau;

public class Joueur {
    private Integer score;
    private Chevalet chevalet;
    int TailleChevalet = Constant.retourneNbJetonsChevalet();

    public Chevalet retourneChevalet() {
        return this.chevalet;
    }

    public Joueur(Chevalet chevalet) {
        this.chevalet = chevalet;
        this.score = 0;
    }

    public void placerLettre(Jeton jeton, int positionx, int positiony, Plateau plateau) throws HorsPlateauException {
        for (Jeton jetonAechanger : this.retourneChevalet().retourneJetons()) {
            if (jetonAechanger == jeton) {
                this.retourneChevalet().retourneJetons().remove(jetonAechanger);
                plateau.placerJeton(jeton, positionx, positiony);
                break;
            }
        }
    }

    public void placerMot(Plateau plateau) {
        Integer ptsMot = 0;
        Scanner scanner = new Scanner(System.in);
        List<Jeton> lettresUtilisees = new ArrayList<>();
        boolean premierMot = plateau.estVide();

        ScrabbleApplicationConsole.message("Entrez les coordonnées de la première lettre et son orientation.");
        ScrabbleApplicationConsole.message("Entrez la position x de la lettre : (de 0 à 14)");
        int posx = scanner.nextInt();
        ScrabbleApplicationConsole.message("Entrez la position y de la lettre : (de 0 à 14)");
        int posy = scanner.nextInt();
        String orientation = "";
        while (!orientation.trim().equals("v") && !orientation.trim().equals("h")) {
            ScrabbleApplicationConsole.message("Entrez l'orientation du mot (v pour vertical, h pour horizontal) :");
            orientation = scanner.next();
        }
        boolean horizontal = orientation.trim().equals("h");

        ScrabbleApplicationConsole.message("Entrez les lettres une par une (appuyez sur '0' pour terminer) :");
        List<Character> lettres = new ArrayList<>();
        while (true) {
            String lettre = scanner.next().toUpperCase();
            if (lettre.equals("0")) {
                break;
            }
            if (lettre.length() != 1 || (!Character.isLetter(lettre.charAt(0)) && !lettre.equals("*"))) {
                ScrabbleApplicationConsole.message("Entrée invalide. Veuillez entrer une seule lettre.");
                continue;
            }
            lettres.add(lettre.charAt(0));
        }
        boolean appuieSurLettreExistante = false;
        // Vérification de la validité du mot complet
        try {
            if (premierMot) {
                int centre = plateau.retourneTaille() / 2;
                boolean passeParCentre = false;
                for (int i = 0; i < lettres.size(); i++) {
                    int x = posx + (horizontal ? 0 : i);
                    int y = posy + (horizontal ? i : 0);
                    if (x == centre && y == centre) {
                        passeParCentre = true;
                        break;
                    }
                }
                if (!passeParCentre) {
                    throw new HorsPlateauException("Le premier mot doit passer par le centre du plateau !");
                }
            } else {
                boolean lettreAdjacente = false;
                for (int i = 0; i < lettres.size(); i++) {
                    int x = posx + (horizontal ? 0 : i);
                    int y = posy + (horizontal ? i : 0);
                    if (plateau.CaseOccupe(x, y)) {
                        if (plateau.recupererCase(x, y).toString().charAt(0) == lettres.get(i)) {
                            appuieSurLettreExistante = true;
                            this.retourneChevalet().retourneJetons().add(plateau.recupererCase(x, y).retourneJeton());
                        }
                    } else {
                        if ((x > 0 && plateau.CaseOccupe(x - 1, y)) ||
                            (x < plateau.retourneTaille() - 1 && plateau.CaseOccupe(x + 1, y)) ||
                            (y > 0 && plateau.CaseOccupe(x, y - 1)) ||
                            (y < plateau.retourneTaille() - 1 && plateau.CaseOccupe(x, y + 1))) {
                            lettreAdjacente = true;
                        }
                    }
                }
                if (!lettreAdjacente && !appuieSurLettreExistante) {
                    throw new HorsPlateauException("Les mots suivants doivent s'appuyer sur une lettre existante !");
                }
            }
            Boolean lettreValide = true;
            // Placement des lettres
            for (int i = 0; i < lettres.size(); i++) {
                int x = posx + (horizontal ? 0 : i);
                int y = posy + (horizontal ? i : 0);
                char charLettre = lettres.get(i);
                
                if (!plateau.CaseOccupe(x, y) || (plateau.CaseOccupe(x, y) && plateau.recupererCase(x, y).toString().charAt(0) == charLettre)) {
                    Jeton jeton = null;
                    if (charLettre == '*') {
                        jeton = Jeton.JOKER;
                    } else {
                        for (Jeton j : this.retourneChevalet().retourneJetons()) {
                            if (j.toString().charAt(0) == charLettre) {
                                jeton = j;
                                break;
                            }
                        }
                    }
                    if (jeton == null) {
                        ScrabbleApplicationConsole.message("Jeton non disponible dans votre chevalet.");
                        lettreValide = false;
                    }
                    else {
                    	lettresUtilisees.add(jeton);
                    }
                }
            }
            int i = 0;
            if (lettreValide) {
                List<int[]> positions = new ArrayList<>();
            	for (Jeton jeton : lettresUtilisees) {
            		int x = posx + (horizontal ? 0 : i);
            		int y = posy + (horizontal ? i : 0);
                    if (jeton == Jeton.JOKER) {
                        ScrabbleApplicationConsole.message("Quelle lettre voulez-vous pour le joker à la position (" + x + ", " + y + ") ?");
                        char lettreJoker = scanner.next().toUpperCase().charAt(0);
                        jeton = estUnJoker(jeton, lettreJoker);
                    }
            		this.placerLettre(jeton, x, y, plateau);
                    ptsMot += jeton.valeur();
                    this.score = this.score + jeton.valeur();
                    positions.add(new int[]{x, y});
            		i = i + 1;
            	}
                ScrabbleApplicationConsole.message("Le mot vaut " + ptsMot + " points !");
                
                // Calcul des points des mots croisés
                for (int[] pos : positions) {
                    int x = pos[0];
                    int y = pos[1];
                    int ptsCroise = calculerPointsMotsCroises(x, y, plateau, horizontal);
                    this.score += ptsCroise;
                    if (ptsCroise > 0) {
                        ScrabbleApplicationConsole.message("Un mot croisé vaut " + ptsCroise + " points !");
                    }
                }

                ScrabbleApplicationConsole.message("Score du joueur : " + this.score);
            }	
        } catch (HorsPlateauException e) {
            ScrabbleApplicationConsole.message(e.getMessage());
            return;
        }

        if (lettresUtilisees.isEmpty()) {
            ScrabbleApplicationConsole.message("Aucune lettre placée.");
        }
        while (this.retourneChevalet().retourneJetons().size() > TailleChevalet) {
        	this.retourneChevalet().retourneJetons().remove(TailleChevalet);
        }
    }

    public Jeton estUnJoker(Jeton jeton, char lettre) {
        Jeton nouveauJeton = Jeton.valueOf(String.valueOf(lettre));
        nouveauJeton.defValeur(0);
        this.retourneChevalet().retourneJetons().remove(jeton);
        this.retourneChevalet().retourneJetons().add(nouveauJeton);

        return nouveauJeton;
    }

    private int calculerPointsMotsCroises(int x, int y, Plateau plateau, boolean horizontal) {
        int points = 0;
        if (horizontal) {
            points += calculerPointsMotVertical(x, y, plateau);
        } else {
            points += calculerPointsMotHorizontal(x, y, plateau);
        }
        return points;
    }

    private int calculerPointsMotHorizontal(int x, int y, Plateau plateau) {
        int points = 0;
        int start = y;
        try {
        while (start > 0 && plateau.CaseOccupe(x, start - 1)) {
            start--;
        }
        int end = y;
        while (end < plateau.retourneTaille() - 1 && plateau.CaseOccupe(x, end + 1)) {
            end++;
        }
        if (start != end) {
            for (int i = start; i <= end; i++) {
                points += plateau.recupererCase(x, i).retourneJeton().valeur();
            }
        }
        } catch (HorsPlateauException e) {
        	ScrabbleApplicationConsole.message("Problème de coordonnées");
        }
        return points;
    }

    private int calculerPointsMotVertical(int x, int y, Plateau plateau) {
        int points = 0;
        int start = x;
        try {
        while (start > 0 && plateau.CaseOccupe(start - 1, y)) {
            start--;
        }
        int end = x;
        while (end < plateau.retourneTaille() - 1 && plateau.CaseOccupe(end + 1, y)) {
            end++;
        }
        if (start != end) {
            for (int i = start; i <= end; i++) {
                points += plateau.recupererCase(i, y).retourneJeton().valeur();
            }
        }
    	} catch (HorsPlateauException e) {
    	ScrabbleApplicationConsole.message("Problème de coordonnées");
    	}
        return points;
    }

    public Integer retourneScore(){
        return this.score;
    }
    
    public void defScore(int score) {
    	this.score = score;
    }
}