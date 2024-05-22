package scrabble.joueur;

import scrabble.model.Chevalet;

public class Joueur {
	private Chevalet chevalet;
	
	public Joueur(Chevalet chevalet) {
		this.chevalet = chevalet;
	}

	public Chevalet retourneChevalet() {
		return this.chevalet;
	}
}
