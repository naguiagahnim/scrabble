package scrabble.model;

import java.util.ArrayList;
import java.util.List;


public class Chevalet {
	private List<Jeton> jetons = new ArrayList();
	protected Sac sac;
	
	
	public List<Jeton> getLettres() {
		return this.jetons;
	}
	

	public Chevalet(Sac sac) {
		this.jetons = new ArrayList();
		this.sac = sac;
	}
	
	public void distribuer() {
		int i=0;
		for (int j=0; j<7;j++) {
				this.jetons.add(this.sac.jetons.get(i));
				this.sac.jetons.remove(i);
			}
		}
	
	public void echanger(Jeton jeton) {
		for(Jeton el : jetons) {
			if(el==jeton) {
				jetons.remove(el);
				sac.melanger();
				jetons.add(this.sac.jetons.get(0));
			}
		}
	}

}
