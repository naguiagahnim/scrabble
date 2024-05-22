package scrabble.joueur;

import java.util.Scanner;

import scrabble.exceptions.HorsPlateauException;
import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Plateau;

public class Joueur {
	private Chevalet chevalet;
	
	
	public Joueur(Chevalet chevalet) {
		this.chevalet = chevalet;
	}

	public Chevalet retourneChevalet() {
		return this.chevalet;
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
        // Convertir le mot en tableau de char
        char[] lettres = mot.toCharArray();
  

        // Vérifier que toutes les lettres peuvent être placées sur le plateau
        for (int i = 0; i < lettres.length; i++) {
            int x = horizontal ? positionx + i : positionx;
            int y = horizontal ? positiony : positiony + i;
            if (x >= plateau.retourneTaille() || y >= plateau.retourneTaille()) {
                throw new HorsPlateauException("Le mot sort du plateau !");
            }
        }

        // Vérifier que le joueur possède tous les jetons nécessaires
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

        // Placer les jetons sur le plateau et les retirer du chevalet
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
    }
	
	public void estUnJoker(Jeton jeton) {
		System.out.println("quelles lettres voulez vous ?");
		Scanner estJoker = new Scanner(System.in);
		
	}
}