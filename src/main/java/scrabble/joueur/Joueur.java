package scrabble.joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import scrabble.exceptions.HorsPlateauException;
import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Plateau;

public class Joueur {
    private Chevalet chevalet;

    public Chevalet retourneChevalet() {
        return this.chevalet;
    }

    public Joueur(Chevalet chevalet) {
        this.chevalet = chevalet;
    }

    public void placerLettre(Jeton jeton, int positionx, int positiony, Plateau plateau) throws HorsPlateauException {
        for (Jeton jetonAechanger : this.retourneChevalet().retourneJetons()) {
            if (jetonAechanger == jeton) {
                this.retourneChevalet().retourneJetons().remove(jetonAechanger);
                if (jetonAechanger == Jeton.JOKER) {
                    jeton = estUnJoker(jetonAechanger);
                }
                plateau.placerJeton(jeton, positionx, positiony);
                break;
            }
        }
    }

    public void placerMot(String mot, int positionx, int positiony, boolean horizontal, Plateau plateau) {
        mot = mot.toUpperCase();
        char[] lettres = mot.toCharArray();
        boolean premierMot = plateau.estVide();
        List<Jeton> lettresUtilisees = new ArrayList<>();
        int centre = plateau.retourneTaille() / 2;

        try {
            if (premierMot) {
                boolean passeParCentre = false;
                for (int i = 0; i < lettres.length; i++) {
                    int x = horizontal ? positionx + i : positionx;
                    int y = horizontal ? positiony : positiony + i;
                    if (x == centre && y == centre) {
                        passeParCentre = true;
                        break;
                    }
                }
                if (!passeParCentre) {
                    throw new HorsPlateauException("Le premier mot doit passer par le centre du plateau !");
                }
            } else {
                boolean appuieSurLettreExistante = false;
                for (int i = 0; i < lettres.length; i++) {
                    int x = horizontal ? positionx + i : positionx;
                    int y = horizontal ? positiony : positiony + i;
                    if (plateau.recupererJeton(x, y) != null) {
                        appuieSurLettreExistante = true;
                        break;
                    }
                }
                if (!appuieSurLettreExistante) {
                    throw new HorsPlateauException("Les mots suivants doivent s'appuyer sur une lettre existante !");
                }
            }

            for (int i = 0; i < lettres.length; i++) {
                int x = horizontal ? positionx + i : positionx;
                int y = horizontal ? positiony : positiony + i;
                if (x >= plateau.retourneTaille() || y >= plateau.retourneTaille()) {
                    throw new HorsPlateauException("Le mot sort du plateau !");
                }

                Jeton jeton = this.retourneChevalet().retourneJetons().get(i);
                if (jeton == null) {
                    throw new HorsPlateauException("Le joueur n'a pas tous les jetons nécessaires pour former ce mot !");
                }

                if (plateau.recupererJeton(x, y) != null) {
                    throw new HorsPlateauException("Il est impossible de placer une lettre sur une case occupée !");
                }

                lettresUtilisees.add(jeton);
                this.placerLettre(jeton, x, y, plateau);
            }

        } catch (HorsPlateauException e) {
            System.out.println(e.getMessage());
            this.retourneChevalet().retourneJetons().addAll(lettresUtilisees);
            System.out.println("Souhaitez-vous rejouer (r) ou passer votre tour (p) ?");
            Scanner scanner = new Scanner(System.in);
            String choix = scanner.nextLine().toLowerCase();
            

            }
        }

    public Jeton estUnJoker(Jeton jeton) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quelles lettres voulez-vous pour le joker ?");
        String lettre = scanner.nextLine().toUpperCase();

        while (lettre.length() != 1 || !Character.isLetter(lettre.charAt(0))) {
            System.out.println("Entrée invalide. Veuillez entrer une seule lettre.");
            lettre = scanner.nextLine().toUpperCase();
        }

        Jeton nouveauJeton = Jeton.valueOf(lettre);

        this.retourneChevalet().retourneJetons().remove(jeton);
        this.retourneChevalet().retourneJetons().add(nouveauJeton);

        return nouveauJeton;
    }
}
