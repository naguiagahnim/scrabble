package scrabble.model;

import java.util.ArrayList;
import java.util.List;


public class Chevalet {
	private List<Jeton> lettres = new ArrayList();
	
	public Chevalet(List<Jeton> lettres) {
		this.lettres = lettres;
	}
	
	public void distribuer() {
		for(Jeton jeton : Sac.jetonspioche) {
			System.out.println("Jeton");
		}
	}
}
