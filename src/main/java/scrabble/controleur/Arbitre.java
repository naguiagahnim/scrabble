package scrabble.controleur;

import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Sac;


public class Arbitre {
	
	public void echanger(Sac sac, Chevalet chevalet, Jeton jeton) {
		for(Jeton el : chevalet.retourneJetons()) {
			if(el==jeton) {
				chevalet.retourneJetons().remove(el);
				sac.melanger();
				chevalet.retourneJetons().add(sac.retourneJeton(0));
				break;
			}
		}
	}
	
	public void distribuer(Sac sac, Chevalet chevalet) {
		int i=0;
		for (int j=0; j<7;j++) {
				chevalet.retourneJetons().add(sac.retourneJeton(i));
				sac.supprimerJeton(i);
			}
	}
}
