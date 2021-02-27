package Test;

import Composantes.Joueur;
import Composantes.Regles;
import Composantes.Scan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReglesTest {

    /**
     * test la possibilité de poser une carte sur une base adverse ou non
     */
    @Test
    public void testJouerCoups() {
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");
        SUD.jeu.set(0, 12);
        SUD.jeu.set(1, 39);
        SUD.jeu.set(2, 46);
        SUD.jeu.set(3, 59);
        Regles.jouerCoups(Scan.decomposer("12v 39^ 46v' 59^"), SUD, NORD);
        assertEquals(SUD.descendant, 12);
        assertEquals(SUD.ascendant, 59);
        assertEquals(NORD.descendant, 46);
    }

    /**
     * test la verification semantique des coups
     * la syntaxe est bonne, on test seulement la semantique
     *
     * */
    @Test
    public void testAreCoupsValid() {
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");

        NORD.ascendant=30;      //  les bases sont mis à 30 pour une meilleur manipulation
        NORD.descendant=30;     //
        SUD.ascendant=30;       //
        SUD.descendant=30;      //

        SUD.jeu.set(0, 12);
        SUD.jeu.set(1, 39);
        SUD.jeu.set(2, 46);
        SUD.jeu.set(3, 59);
        SUD.jeu.set(4, 22);
        SUD.jeu.set(5, 14);

        assertTrue(Regles.areCoupsValid(Scan.decomposer("12v 39^ 46^"), SUD.clone(), NORD.clone()));
        assertTrue(Regles.areCoupsValid(Scan.decomposer("14v 12v"), SUD.clone(), NORD.clone()));
        assertTrue(Regles.areCoupsValid(Scan.decomposer("12^' 39^ 46^"), SUD.clone(), NORD.clone()));
        assertTrue(Regles.areCoupsValid(Scan.decomposer("12v 39v' 46^"), SUD.clone(), NORD.clone()));

        assertFalse(Regles.areCoupsValid(Scan.decomposer("12^ 39v 46^'"), SUD.clone(), NORD.clone()));
        assertFalse(Regles.areCoupsValid(Scan.decomposer("14^ 22v'"), SUD.clone(), NORD.clone()));
        assertFalse(Regles.areCoupsValid(Scan.decomposer("19^ 39v' 46^"), SUD.clone(), NORD.clone()));
        assertFalse(Regles.areCoupsValid(Scan.decomposer("14v' 39v 46^"), SUD.clone(), NORD.clone()));

    }

    /**
     * test la detection de fin de partie pour un joueur
     * */
    @Test
    public void testPartieFinie() {
        Joueur NORD = new Joueur("NORD");
        assertFalse(Regles.partieFinie(NORD));
        NORD.jeu.clear();
        for (int i = 0; i < NORD.getNbPioche();) {
            NORD.piocherCarte();
            NORD.jeu.clear();
        }
        assertTrue(Regles.partieFinie(NORD));
    }



    /**
     * test de la fonction partieContinue
     * */
    @Test
    public void testPartieContinue(){
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");
        assertTrue(Regles.partieContinue(NORD, SUD, 0,false));

        NORD.jeu.clear();
        SUD.jeu.clear();
        NORD.jeu.add(0,59);
        NORD.jeu.add(1,58);

        NORD.ascendant=57;
        NORD.descendant=2;
        SUD.ascendant=1;
        SUD.descendant=60;
        assertTrue(Regles.partieContinue(NORD, SUD, 0,false));
        NORD.ascendant=58;
        assertFalse(Regles.partieContinue(NORD, SUD, 0,false));
    }

}