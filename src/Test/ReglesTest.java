package Test;

import Composantes.Joueur;
import Composantes.Regles;
import Composantes.Input;
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
        Regles.jouerCoups(Input.decomposer("12v 39^ 46v' 59^"), SUD, NORD);
        assertEquals(SUD.getDescendant(), 12);
        assertEquals(SUD.getAscendant(), 59);
        assertEquals(NORD.getDescendant(), 46);
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

        NORD.setAscendant(30);      //  les bases sont mis à 30 pour une meilleur manipulation
        NORD.setDescendant(30);     //
        SUD.setAscendant(30);       //
        SUD.setDescendant(30);      //

        SUD.jeu.set(0, 12);
        SUD.jeu.set(1, 39);
        SUD.jeu.set(2, 46);
        SUD.jeu.set(3, 59);
        SUD.jeu.set(4, 22);
        SUD.jeu.set(5, 14);

        assertTrue(Regles.areCoupsValid(Input.decomposer("12v 39^ 46^"), SUD.clone(), NORD.clone()));
        assertTrue(Regles.areCoupsValid(Input.decomposer("14v 12v"), SUD.clone(), NORD.clone()));
        assertTrue(Regles.areCoupsValid(Input.decomposer("12^' 39^ 46^"), SUD.clone(), NORD.clone()));
        assertTrue(Regles.areCoupsValid(Input.decomposer("12v 39v' 46^"), SUD.clone(), NORD.clone()));

        assertFalse(Regles.areCoupsValid(Input.decomposer("12^ 39v 46^'"), SUD.clone(), NORD.clone()));
        assertFalse(Regles.areCoupsValid(Input.decomposer("14^ 22v'"), SUD.clone(), NORD.clone()));
        assertFalse(Regles.areCoupsValid(Input.decomposer("19^ 39v' 46^"), SUD.clone(), NORD.clone()));
        assertFalse(Regles.areCoupsValid(Input.decomposer("14v' 39v 46^"), SUD.clone(), NORD.clone()));

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
        assertTrue(Regles.partieContinue(NORD.clone(), SUD.clone(), 0,false));
        // avec des bases injouables
        NORD.setAscendant(61);
        NORD.setDescendant(0);
        SUD.setAscendant(61);
        SUD.setDescendant(0);
        assertFalse(Regles.partieContinue(NORD.clone(), SUD.clone(), 0, false));
        // avec un jeu vide
        NORD.jeu.clear();
        assertFalse(Regles.partieContinue(NORD.clone(), SUD.clone(), 0, false));

        SUD.jeu.clear();
        NORD.jeu.add(0,59);
        NORD.jeu.add(1,58);
        NORD.setAscendant(57);
        NORD.setDescendant(2);
        SUD.setAscendant(1);
        SUD.setDescendant(60);
        assertTrue(Regles.partieContinue(NORD.clone(), SUD.clone(), 0,false));
        NORD.setAscendant(58);
        assertFalse(Regles.partieContinue(NORD.clone(), SUD.clone(), 0,false));
        //cas spécifique
        NORD.setAscendant(49);
        NORD.setDescendant(2);
        SUD.setAscendant(53);
        SUD.setDescendant(9);
        SUD.jeu.add(0,34);
        SUD.jeu.add(1,44);
        SUD.jeu.add(2,38);
        SUD.jeu.add(3,6);
        SUD.jeu.add(4,41);
        SUD.jeu.add(5,39);
        assertTrue(Regles.partieContinue(SUD.clone(), NORD.clone(), 0, false));


    }



}