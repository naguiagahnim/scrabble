package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;

public class Sac {
	
	private ArrayList<Jeton> jetons;

	public Sac() {
		this.jetons = new ArrayList<Jeton>() ;
	}
	
	public ArrayList<Jeton> retourneJetons() {
		return this.jetons;
	}
	
	public Jeton getJeton(int indice) {
		return this.jetons.get(indice);
	}
	
	public void ajout1Jeton(Jeton jeton) {
		this.jetons.add(jeton);
	}
	
	public void setJetonspioche(ArrayList<Jeton> jetons) {
		this.jetons = jetons;
	}
	
	public void supprimerJeton(int indice) {
		this.jetons.remove(indice);
	};
	
	
	public void melanger() {
		Collections.shuffle(jetons);
	}
}
