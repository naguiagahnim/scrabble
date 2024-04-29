package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sac {
	static ArrayList<Jeton> jetonspioche = new ArrayList();

	public ArrayList<Jeton> getJetonspioche() {
		return jetonspioche;
	}

	public void setJetonspioche(ArrayList<Jeton> jetonspioche) {
		this.jetonspioche = jetonspioche;
	}
	
	public void melanger() {
		Collections.shuffle(jetonspioche);
	}
}
