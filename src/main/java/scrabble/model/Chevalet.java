package scrabble.model;

import java.util.ArrayList;
import java.util.List;


public class Chevalet {
	private List<Jeton> jetons = new ArrayList();
	
	
	public List<Jeton> retourneJetons() {
		return this.jetons;
	}
	

	public Chevalet() {
		this.jetons = new ArrayList();
	}
}
