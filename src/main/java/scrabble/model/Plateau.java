package scrabble.model;

import scrabble.exceptions.HorsPlateauException;

public class Plateau {

    // Taille du plateau
    private static final int TAILLE = 15;

    // Cases du plateau
    private Case[][] cases;

    // Constructeur
    public Plateau() {
        this.cases = new Case[TAILLE][TAILLE];

        // Initialise chaque case avec une étoile au centre
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                cases[i][j] = new Case(i == TAILLE / 2 && j == TAILLE / 2); // Case centrale étoile
            }
        }
    }

    // Récupère une case en fonction de ses coordonnées
    // Renvoie la case si les coordonnées sont valides, sinon renvoie l'exception métier HorsPlateauException
    public Case recupererJeton(int positionx, int positiony) throws HorsPlateauException {
        try {
            if (positionx < 0 || positionx >= TAILLE || positiony < 0 || positiony >= TAILLE) {
                throw new HorsPlateauException("Indices de la case hors du plateau !");
            }
            return cases[positionx][positiony];
        } catch (IndexOutOfBoundsException e) {
            throw new HorsPlateauException("Les indices fournis sont trop grands ou négatifs !");
        }
    }

    // Place un jeton sur une case
    // Renvoie vrai si la case est libre et que le jeton a été placé, faux sinon
    public boolean placerJeton(Jeton jeton, int positionx, int positiony) throws HorsPlateauException {
        Case case1 = recupererJeton(positionx, positiony);
        if (!case1.isOccupe()) {
            case1.setOccupe(true);
            case1.setJeton(jeton);
            return true;
        }
        return false;
    }

    // Retire le jeton d'une case
    public void retirerJeton(int positionx, int positiony) throws HorsPlateauException {
        Case casee = recupererJeton(positionx, positiony);
        if (casee.isOccupe()) {
            casee.setOccupe(false);
            casee.setJeton(null);
        }
    }

    // Représentation textuelle du plateau
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                builder.append(cases[i][j].toString());
                if (j < TAILLE - 1) {
                    builder.append(" ");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
