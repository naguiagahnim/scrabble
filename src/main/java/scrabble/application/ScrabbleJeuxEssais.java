package scrabble.application;

import scrabble.controleur.Arbitre;
import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Plateau;
import scrabble.model.Sac;

public class ScrabbleJeuxEssais {

	public static void main(String[] args) {
		
		Sac sac = new Sac();
		Chevalet chevalet1 = new Chevalet();
		Chevalet chevalet2 = new Chevalet();
		Arbitre arbitre = new Arbitre();
		sac.remplirJeuFrançais();
		System.out.println("Avant de mélanger");
		System.out.println(sac.retourneJetons());
		sac.melanger();
		System.out.println("Après mélanger");
		System.out.println(sac.retourneJetons());
		
	
		arbitre.distribuer(sac, chevalet1);
		System.out.println(chevalet1.retourneJetons());
		System.out.println(sac.retourneJetons());
		arbitre.distribuer(sac, chevalet2);
		System.out.println(chevalet2.retourneJetons());
		arbitre.echanger(sac, chevalet2, Jeton.E);
		System.out.println(chevalet2.retourneJetons());
		
		Plateau plateau = new Plateau();
		System.out.println(plateau.toString());
	}
	
}
