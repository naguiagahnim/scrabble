package scrabble.model;

import scrabble.exceptions.HorsPlateauException;

public class Plateau {

    // Taille du plateau
    private static final int TAILLE = 3;

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
    public Case getCase(int x, int y) throws HorsPlateauException {
        try {
            if (x < 0 || x >= TAILLE || y < 0 || y >= TAILLE) {
                throw new HorsPlateauException("Indices de la case hors du plateau !");
            }
            return cases[x][y];
        } catch (IndexOutOfBoundsException e) {
            throw new HorsPlateauException("Les indices fournis sont trop grands ou négatifs !");
        }
    }

    // Place un jeton sur une case
    // Renvoie vrai si la case est libre et que le jeton a été placé, faux sinon
    public boolean placerJeton(Jeton jeton, int x, int y) throws HorsPlateauException {
        Case casee = getCase(x, y);
        if (!casee.isOccupe()) {
            casee.setOccupe(true);
            casee.setJeton(jeton);
            return true;
        }
        return false;
    }

    // Retire le jeton d'une case
    public void retirerJeton(int x, int y) throws HorsPlateauException {
        Case casee = getCase(x, y);
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
