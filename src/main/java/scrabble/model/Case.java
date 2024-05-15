package scrabble.model;

public class Case {

    private Jeton jeton;
    private boolean occupe;
    private boolean etoile; // True si la case est la case centrale étoile

    public Case(boolean etoile) {
        this.etoile = etoile;
        this.occupe = false;
        this.jeton = null;
    }
    
    public boolean estOccupe() {
        return occupe;
    }

    public void metEnOccupe(boolean occupe) {
        this.occupe = occupe;
    }

    public Jeton retourneJeton() {
        return jeton;
    }

    public void metUnJeton(Jeton jeton) {
        this.jeton = jeton;
    }

    public boolean estEtoile() {
        return etoile;
    }

    @Override
    public String toString() {
        if (jeton == null) {
            return etoile ? "*" : "-";
        } else {
            return jeton.name();
        }
    }
}

