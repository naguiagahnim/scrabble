package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;

public class Sac {
	
	private ArrayList<Jeton> jetons;

	public Sac() {
		this.jetons = new ArrayList<Jeton>() ;
	}

	public ArrayList<Jeton> getJetonspioche() {
		return this.jetons;
	}

	public void setJetonspioche(ArrayList<Jeton> jetonspioche) {
		this.jetons = jetonspioche;
	}
	
	public void melanger() {
		Collections.shuffle(jetons);
	}
}
