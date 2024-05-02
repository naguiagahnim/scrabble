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
		while (Sac.jetonspioche.get(i) == null) {
			i=i+1;
		}
		for (int j=i+7; i<j;i++) {
			this.lettres.add(Sac.jetonspioche.get(i));
		}
	
	}

}
