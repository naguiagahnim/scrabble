package scrabble.model;

import java.util.ArrayList;
import java.util.List;

public class Chevalet {
	private List<Jeton> jetons = new ArrayList();
	
	public Chevalet(List<Jeton> jetons) {
		this.jetons = jetons;
	}
}
