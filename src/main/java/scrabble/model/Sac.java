package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.random.*;

public class Sac {
	public static ArrayList<Jeton> jetonspioche = new ArrayList();

	public static ArrayList<Jeton> getJetonspioche() {
		return jetonspioche;
	}
	
	public static Jeton getJetonIndice(int indice) { 
		return jetonspioche.get(indice);
	}

	public void setJetonspioche(ArrayList<Jeton> jetonspioche) {
		this.jetonspioche = jetonspioche;
	}
	
	public static void melanger() {
		Collections.shuffle(jetonspioche);
	}
}
