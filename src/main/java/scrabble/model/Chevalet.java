package scrabble.model;

import java.util.ArrayList;
import java.util.List;


public class Chevalet {
	private List<Jeton> jetons = new ArrayList();
	private Sac sac;
	
	
	public List<Jeton> retourneLettres() {
		return this.jetons;
	}
	

	public Chevalet(Sac sac) {
		this.jetons = new ArrayList();
		this.sac = sac;
	}
	
	public void distribuer() {
		int i=0;
		for (int j=0; j<7;j++) {
				this.jetons.add(this.sac.retourneJeton(i));
				this.sac.supprimerJeton(i);
			}
		}
	
	public void echanger(Jeton jeton) {
		for(Jeton el : this.jetons) {
			if(el==jeton) {
				this.jetons.remove(el);
				this.sac.melanger();
				this.jetons.add(this.sac.retourneJeton(0));
				break;
			}
		}
	}

}
