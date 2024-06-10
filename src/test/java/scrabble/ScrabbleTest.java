package scrabble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrabble.controleur.Arbitre;
import scrabble.exceptions.HorsPlateauException;
import scrabble.joueur.Joueur;
import scrabble.model.Case;
import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Plateau;
import scrabble.model.Sac;

import static org.junit.jupiter.api.Assertions.*;

public class ScrabbleTest {

    private Sac sac;
    private Chevalet chevalet1;
    private Chevalet chevalet2;
    private Joueur j1;
    private Joueur j2;
    private Arbitre arbitre;
    private Plateau plateau;

    @BeforeEach
    public void setUp() {
        sac = new Sac();
        chevalet1 = new Chevalet();
        chevalet2 = new Chevalet();
        j1 = new Joueur(chevalet1);
        j2 = new Joueur(chevalet2);
        arbitre = new Arbitre();
        sac.remplirJeuFrançais();
        plateau = new Plateau();
    }

    @Test
    public void testSacRemplissageEtMelange() {
        System.out.println("Avant de mélanger");
        System.out.println(sac.retourneJetons());
        sac.melanger();
        System.out.println("Après mélanger");
        System.out.println(sac.retourneJetons());
        assertNotNull(sac.retourneJetons());
        assertTrue(sac.retourneJetons().size() > 0);
    }

    @Test
    public void testRemplirChevaletEtEchanger() {
    	int nbJeton = sac.retourneJetons().size();
        arbitre.remplirChevalet(sac, j1);
        assertFalse(j1.retourneChevalet().retourneJetons().isEmpty());
        assertTrue(nbJeton > sac.retourneJetons().size());

        arbitre.remplirChevalet(sac, j2);
        assertFalse(j2.retourneChevalet().retourneJetons().isEmpty());

        System.out.println(j2.retourneChevalet().retourneJetons());
        arbitre.echanger(sac, j2, Jeton.E);
        System.out.println(j2.retourneChevalet().retourneJetons());
        assertTrue(j2.retourneChevalet().retourneJetons().contains(Jeton.E));
    }

    @Test
    public void testPlateauInitial() {
        assertNotNull(plateau);
        System.out.println(plateau.toString());
    }

}