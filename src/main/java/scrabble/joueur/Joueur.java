package scrabble.joueur;

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
		for(Jeton el : this.retourneChevalet().retourneJetons()) {
			if(el==jeton) {
				this.retourneChevalet().retourneJetons().remove(el);
				plateau.placerJeton(jeton, positionx, positiony);
				break;
			}
		} 
	}   
}
