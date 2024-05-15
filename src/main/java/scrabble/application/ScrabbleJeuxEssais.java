package scrabble.application;

import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Plateau;
import scrabble.model.Sac;

public class ScrabbleJeuxEssais {

	public static void main(String[] args) {
		Sac sac = new Sac();
		sac.remplirJeuFrançais();
		System.out.println("Avant de mélanger");
		System.out.println(sac.retourneJetons());
		sac.melanger();
		System.out.println("Après mélanger");
		System.out.println(sac.retourneJetons());
		
		
		Chevalet chevalet1 = new Chevalet(sac);
		chevalet1.distribuer();
		System.out.println(chevalet1.getLettres());
		System.out.println(sac.retourneJetons());
		Chevalet chevalet2 = new Chevalet(sac);
		chevalet2.distribuer();
		System.out.println(chevalet2.getLettres());
		chevalet2.echanger(Jeton.E);
		System.out.println(chevalet2.getLettres());
		
		Plateau plateau = new Plateau();
		System.out.println(plateau.toString());
	}
	
}
