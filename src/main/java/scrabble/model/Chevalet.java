package scrabble.model;

import java.util.ArrayList;
import java.util.List;


public class Chevalet {
	private List<Jeton> lettres = new ArrayList();
	
	
	public List<Jeton> getLettres() {
		return this.lettres;
	}
	

	public Chevalet() {
		this.lettres = new ArrayList();
	}
	
	public void distribuer() {
		int i=0;
		for (int j=0; j<7;j++) {
				this.lettres.add(Sac.jetonspioche.get(i));
				Sac.jetonspioche.remove(i);
			}
		}
	
	public void echanger(Jeton jeton) {
		for(Jeton el : lettres) {
			if(el==jeton) {
				lettres.remove(el);
				Sac.melanger();
				lettres.add(Sac.jetonspioche.get(0));
			}
		}
	}

}
