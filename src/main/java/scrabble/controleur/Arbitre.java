package scrabble.controleur;

import java.util.Scanner;

import scrabble.exceptions.HorsPlateauException;
import scrabble.joueur.Joueur;
import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Plateau;
import scrabble.model.Sac;


public class Arbitre {
	
	public void echanger(Sac sac, Joueur joueur, Jeton jeton) {
		for(Jeton jetonaechanger : joueur.retourneChevalet().retourneJetons()) {
			if(jetonaechanger==jeton) {
				joueur.retourneChevalet().retourneJetons().remove(jetonaechanger);
				sac.melanger();
				joueur.retourneChevalet().retourneJetons().add(sac.retourneJeton(0));
				break;
			}
		}
	}
	
	public void distribuer(Sac sac, Joueur joueur) {
		int i=0;
		for (int j=0; j<7;j++) {
				joueur.retourneChevalet().retourneJetons().add(sac.retourneJeton(i));
				sac.supprimerJeton(i);
			}
	}
	
	public void jouerUnTour(Joueur joueur,Sac sac, Plateau plateau) {
        Scanner scanner = new Scanner(System.in);
        String rep = "";
        System.out.println("Voici les options possibles pour ce tour :");
        System.out.println("1 - Jouer un mot");
        System.out.println("2 - Echanger des lettres");
        System.out.println("3 - Quitter la partie");
        while(rep.trim() != "1" && rep.trim() != "2" && rep.trim() != "3") {
            System.out.println("Quel est votre choix (1,2 ou 3) ?");
            rep = scanner.nextLine();
        }
        if(rep.trim() != "1");{
            String mot = "";
            System.out.println(joueur.retourneChevalet());
            while(mot.trim().equals("")) {
                System.out.println("Quel est votre mot choisi ?");
                mot = scanner.nextLine(); 
            }
            int posx = choixPos(scanner,"x");
            int posy = choixPos(scanner,"y");
            try {
               joueur.placerMot(mot, posx, posy, false, plateau);
           } catch (HorsPlateauException e) {
               System.out.println("Le mot n'est pas bien placé (depasse du plateau ou pas dans le tableau)");
           }
        }
   }

	private int choixPos(Scanner scanner, String pos) {
		int posy = -1;
		while(posy < 0 && posy > 14) {
		    System.out.println("Quel est la position " + pos + " du mot choisi (de 0 à 14");
		    posy = scanner.nextInt(); 
		}
		return posy;
	}
}
