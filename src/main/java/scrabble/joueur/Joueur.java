package scrabble.joueur;

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
    private Chevalet chevalet;
    int TailleChevalet = Constant.retourneNbJetonsChevalet();
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
        boolean premierMot = plateau.estVide();

        ScrabbleApplicationConsole.message("Entrez les coordonnées de la première lettre et son orientation.");
        ScrabbleApplicationConsole.message("Entrez la position x de la lettre :");
        int posx = scanner.nextInt();
        ScrabbleApplicationConsole.message("Entrez la position y de la lettre :");
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
            if (lettre.length() != 1 || !Character.isLetter(lettre.charAt(0))) {
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
                    for (Jeton j : this.retourneChevalet().retourneJetons()) {
                        if (j.toString().charAt(0) == charLettre) {
                            jeton = j;
                            break;
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
            int i =0;
            if(lettreValide) {
            	for(Jeton jeton : lettresUtilisees) {
            		int x = posx + (horizontal ? 0 : i);
            		int y = posy + (horizontal ? i : 0);
            		this.placerLettre(jeton, x, y, plateau);
            		i = i+1;
            	}
            }	
        } catch (HorsPlateauException e) {
            ScrabbleApplicationConsole.message(e.getMessage());
            return;
        }

        if (lettresUtilisees.isEmpty()) {
            ScrabbleApplicationConsole.message("Aucune lettre placée.");
        }
        while ( this.retourneChevalet().retourneJetons().size() > TailleChevalet) {
        	this.retourneChevalet().retourneJetons().remove(TailleChevalet);
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