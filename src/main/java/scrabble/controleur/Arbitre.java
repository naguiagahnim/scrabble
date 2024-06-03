package scrabble.controleur;

import java.util.Scanner;

import scrabble.application.ScrabbleApplicationConsole;
import scrabble.joueur.Joueur;
import scrabble.model.Jeton;
import scrabble.model.Plateau;
import scrabble.model.Sac;

public class Arbitre {
    int nbJetonsChevalet = scrabble.model.Constant.retourneNbJetonsChevalet();

    public void echanger(Sac sac, Joueur joueur, Jeton jeton) {
        for (Jeton jetonaechanger : joueur.retourneChevalet().retourneJetons()) {
            if (jetonaechanger == jeton) {
                joueur.retourneChevalet().retourneJetons().remove(jetonaechanger);
                sac.melanger();
                joueur.retourneChevalet().retourneJetons().add(sac.retourneJeton(0));
                break;
            }
        }
    }

    public void remplirChevalet(Sac sac, Joueur joueur) {
        int i = 0;
        while (joueur.retourneChevalet().retourneJetons().size() < nbJetonsChevalet) {
            joueur.retourneChevalet().retourneJetons().add(sac.retourneJeton(i));
            sac.supprimerJeton(i);
        }
    }

    public void JouerUnePartie(Joueur joueur, Sac sac, Plateau plateau) {
        boolean finPartie = false;
        while (!finPartie) {
            finPartie = this.jouerUnTour(joueur, sac, plateau);
        }
    }

    public boolean jouerUnTour(Joueur joueur, Sac sac, Plateau plateau) {
        Scanner scanner = new Scanner(System.in);
        String rep = "";
        ScrabbleApplicationConsole.message("Voici les options possibles pour ce tour :");
        ScrabbleApplicationConsole.message("1 - Jouer un mot");
        ScrabbleApplicationConsole.message("2 - Echanger des lettres");
        ScrabbleApplicationConsole.message("3 - Quitter la partie");
        while (!rep.trim().equals("1") && !rep.trim().equals("2") && !rep.trim().equals("3")) {
            ScrabbleApplicationConsole.message("Quel est votre choix (1,2 ou 3) ?");
            rep = scanner.nextLine();
        }
        if (rep.trim().equals("1")) {
            System.out.println(joueur.retourneChevalet().retourneJetons());
            joueur.placerMot(plateau);
            ScrabbleApplicationConsole.message(plateau.toString());
            this.remplirChevalet(sac, joueur);
        }

        if (rep.trim().equals("2")) {
            System.out.println(joueur.retourneChevalet().retourneJetons());
            String lettres = choixMot(scanner, "Donnez la liste des lettres que vous voulez échanger : ");
            lettres = lettres.toUpperCase();
            Jeton jetonaechanger;
            char[] lettresaechanger = lettres.toCharArray();
            for (char lettre : lettresaechanger) {
                for (Jeton jeton : joueur.retourneChevalet().retourneJetons()) {
                    if (jeton.toString().charAt(0) == lettre) {
                        jetonaechanger = jeton;
                        echanger(sac, joueur, jeton);
                        break;
                    }
                }
            }
        }
        if (rep.trim().equals("3")) {
            return true;
        }
        return false;
    }

    private String choixMot(Scanner scanner, String message) {
        String mot = "";
        while (mot.trim().equals("")) {
            System.out.println(message);
            mot = scanner.nextLine();
        }
        return mot;
    }

    private int choixPos(Scanner scanner, String axe) {
        int pos = -1;
        while (pos < 0 || pos > 14) {
            System.out.println("Quel est la position " + axe + " du mot choisi (de 0 à 14)");
            pos = scanner.nextInt();
        }
        return pos;
    }

    private boolean choixOrientation(Scanner scanner) {
        String mot = "";
        boolean bool;
        while (!mot.trim().equals("h") && !mot.trim().equals("v")) {
            System.out.println("Quel est l'orientation du mot ? Est-elle verticale ou horizontale ? (h ou v)");
            mot = scanner.nextLine();
        }
        bool = mot.trim().equals("h");
        return bool;
    }
}