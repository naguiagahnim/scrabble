package scrabble.joueur;

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
	
	public void placerLettre(Jeton jeton, int positionx,int positiony, Plateau plateau) throws HorsPlateauException {
		for(Jeton jetonaechanger : this.retourneChevalet().retourneJetons()) {
			if(jetonaechanger==jeton) {
				this.retourneChevalet().retourneJetons().remove(jetonaechanger);
				plateau.placerJeton(jeton, positionx, positiony);
			if(jetonaechanger==Jeton.JOKER) {
				this.estUnJoker(jeton);
				plateau.placerJeton(jeton, positionx, positiony);
			}
				break;
			}
		} 
	}  
	
	
	public void placerMot(String mot, int positionx, int positiony, boolean horizontal, Plateau plateau) throws HorsPlateauException {
        char[] lettres = mot.toCharArray();
        boolean premierMot = plateau.estVide();

        int centre = plateau.retourneTaille() / 2;

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
                    this.placerLettre(jeton, x, y, plateau);
                    break;
                }
            }
        }

        premierMot = false; 
    }
	
	public void estUnJoker(Jeton jeton) {
		System.out.println("quelles lettres voulez vous ?");
		Scanner estJoker = new Scanner(System.in);
		
	}
}
