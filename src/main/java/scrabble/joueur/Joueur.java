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
			}
				break;
			}
		} 
	}   
	public void estUnJoker(Jeton jeton) {
		System.out.println("quelles lettres voulez vous ?");
		Scanner estJoker = new Scanner(System.in);

	}
}
