package scrabble.model;

public class Plateau {
	//TODO A MODIFIER

    private static final int TAILLE = 15;
    private Case[][] cases;

    public Plateau() {
        this.cases = new Case[TAILLE][TAILLE];
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                cases[i][j] = new Case(i == TAILLE / 2 && j == TAILLE / 2); // Case centrale étoile
            }
        }
    }

    public Case getCase(int x, int y) {
        if (x < 0 || x >= TAILLE || y < 0 || y >= TAILLE) {
            throw new IllegalArgumentException("Coordonnées de la case invalides");
        }
        return cases[x][y];
    }

    public boolean placerJeton(Jeton jeton, int x, int y) {
        Case casee = getCase(x, y);
        if (!casee.isOccupe()) {
            casee.setOccupe(true);
            casee.setJeton(jeton);
            return true;
        }
        return false;
    }

    public void retirerJeton(int x, int y) {
        Case casee = getCase(x, y);
        if (casee.isOccupe()) {
            casee.setOccupe(false);
            casee.setJeton(null);
        }
    }

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