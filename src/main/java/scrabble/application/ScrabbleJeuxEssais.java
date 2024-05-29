package scrabble.application;

import scrabble.controleur.Arbitre;
import scrabble.exceptions.HorsPlateauException;
import scrabble.joueur.Joueur;
import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Plateau;
import scrabble.model.Sac;

public class ScrabbleJeuxEssais {

	public static void main(String[] args)  {
		
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
		
	
		arbitre.remplirChevalet(sac, j1);
		System.out.println(j1.retourneChevalet().retourneJetons());
		System.out.println(sac.retourneJetons());
		arbitre.remplirChevalet(sac, j2);
		System.out.println(j2.retourneChevalet().retourneJetons());
		arbitre.echanger(sac, j2, Jeton.E);
		System.out.println(j2.retourneChevalet().retourneJetons());
		
		Plateau plateau = new Plateau();
		System.out.println(plateau.toString());
		//fin v1
		try {
			j1.placerLettre(Jeton.E, 6, 9, plateau);
		} catch (HorsPlateauException e) {
			System.out.println("Le jeton n'est pas dans le chevalet");
		}
		System.out.println(plateau.toString());
		System.out.println(j1.retourneChevalet().retourneJetons());
		arbitre.jouerUnTour(j2, sac, plateau);
	}
	
}
