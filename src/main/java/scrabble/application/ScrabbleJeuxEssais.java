package scrabble.application;

import scrabble.controleur.Arbitre;
import scrabble.joueur.Joueur;
import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Plateau;
import scrabble.model.Sac;

public class ScrabbleJeuxEssais {

	public static void main(String[] args) {
		
		Sac sac = new Sac();
		Chevalet chevalet1 = new Chevalet();
		Chevalet chevalet2 = new Chevalet();
		Joueur j1 = new Joueur(chevalet1);
		Joueur j2 = new Joueur(chevalet2);
		Arbitre arbitre = new Arbitre();
		sac.remplirJeuFrançais();
		System.out.println("Avant de mélanger");
		System.out.println(sac.retourneJetons());
		sac.melanger();
		System.out.println("Après mélanger");
		System.out.println(sac.retourneJetons());
		
	
		arbitre.distribuer(sac, j1);
		System.out.println(chevalet1.retourneJetons());
		System.out.println(sac.retourneJetons());
		arbitre.distribuer(sac, j2);
		System.out.println(chevalet2.retourneJetons());
		arbitre.echanger(sac, j2, Jeton.E);
		System.out.println(chevalet2.retourneJetons());
		
		Plateau plateau = new Plateau();
		System.out.println(plateau.toString());
		//fin v1
	}
	
}
