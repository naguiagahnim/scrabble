package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;

public class Sac {
	
	private ArrayList<Jeton> jetons;

	public Sac() {
		this.jetons = new ArrayList<Jeton>() ;
	}

	public ArrayList<Jeton> getJetons() {
		return this.jetons;
	}
	
	public void ajout1Jeton(Jeton jeton) {
		this.jetons.add(jeton);
	}
	
	public void setJetonspioche(ArrayList<Jeton> jetons) {
		this.jetons = jetons;
	}
	
	public void melanger() {
		Collections.shuffle(jetons);
	}
}
