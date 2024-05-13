package scrabble.model;

import java.util.ArrayList;
import java.util.List;


public class Chevalet {
	public List<Jeton> jetons = new ArrayList();
	
	
	public List<Jeton> getLettres() {
		return this.jetons;
	}
	

	public Chevalet() {
		this.jetons = new ArrayList();
	}
	
	public void distribuer() {
		int i=0;
		for (int j=0; j<7;j++) {
				this.jetons.add(Sac.jetons.get(i));
				Sac.jetons.remove(i);
			}
		}
	
	public void echanger(Jeton jeton) {
		for(Jeton el : jetons) {
			if(el==jeton) {
				jetons.remove(el);
				Sac.melanger();
				jetons.add(Sac.jetons.get(0));
			}
		}
	}

}
