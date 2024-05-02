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
		for (int j=0; j<7;i++) {
			if (Sac.jetonspioche.get(i) != null) {
				this.lettres.add(Sac.jetonspioche.get(i));
				j=j+1;
			}
		}
	
	}

}
