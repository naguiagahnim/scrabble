package scrabble.model;

import java.util.ArrayList;
import java.util.List;


public class Chevalet {
	private List<Jeton> lettres = new ArrayList();
	
	
	public List<Jeton> getLettres() {
		return lettres;
	}

	public Chevalet(List<Jeton> lettres) {
		this.lettres = lettres;
	}
	
	public void distribuer() {
		int i=0;
		while (Sac.jetonspioche.get(i) != null) {
			i=i+1;
		}
		for (int j=i+7; i<j;i++) {
			System.out.println("Jeton");
		}
	}
}
