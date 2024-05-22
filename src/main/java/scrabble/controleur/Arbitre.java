package scrabble.controleur;

import scrabble.joueur.Joueur;
import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Sac;


public class Arbitre {
	
	public void echanger(Sac sac, Joueur joueur, Jeton jeton) {
		for(Jeton el : joueur.retourneChevalet().retourneJetons()) {
			if(el==jeton) {
				joueur.retourneChevalet().retourneJetons().remove(el);
				sac.melanger();
				joueur.retourneChevalet().retourneJetons().add(sac.retourneJeton(0));
				break;
			}
		}
	}
	
	public void distribuer(Sac sac, Joueur joueur) {
		int i=0;
		for (int j=0; j<7;j++) {
				joueur.retourneChevalet().retourneJetons().add(sac.retourneJeton(i));
				sac.supprimerJeton(i);
			}
	}
}
