package Test;

import Composantes.Joueur;
import Composantes.Regles;
import Composantes.Scan;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ReglesTest {

    //  Test si la carte donnée est posable sur la base
    @Test
    public void testEstPosable(){
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");
        //  test de coups normaux
        assertTrue(Regles.estPosable(30,'v',NORD,NORD));
        assertTrue(Regles.estPosable(30,'^',NORD,NORD));
        assertTrue(Regles.estPosable(30,'^',NORD,SUD));
        assertTrue(Regles.estPosable(30,'v',SUD,NORD));
        assertFalse(Regles.estPosable(60,'v',SUD,NORD));
        assertFalse(Regles.estPosable(1,'^',SUD,NORD));
        //  test de l'exception
        NORD.descendant=30;
        NORD.ascendant=30;
        assertFalse(Regles.estPosable(20,'^',SUD,NORD));
        assertFalse(Regles.estPosable(40,'v',SUD,NORD));
        assertTrue(Regles.estPosable(20,'^',NORD,NORD));
        assertTrue(Regles.estPosable(40,'v',NORD,NORD));
    }

    //test la possibilité de poser une carte sur une base adverse ou non
    @Test
    public void testJouerCoups() {
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");
        SUD.jeu.set(0, 12);
        SUD.jeu.set(1, 39);
        SUD.jeu.set(2, 46);
        SUD.jeu.set(3, 59);
        Regles.jouerCoups(Scan.decomposer("12v 39^ 46v' 59^"), SUD, NORD);
        assertTrue(SUD.descendant == 12);
        assertTrue(SUD.ascendant == 59);
        assertTrue(NORD.descendant == 46);
    }

    //test la verification des coups
    @Test
    public void testAreCoupsValid() {
        // /!\ A FAIRE
    }

    //test la detection de fin de partie pour un joueur
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



    //  test des règles de pioche
    @Test
    public void testRegleDePioche() {
        Joueur NORD = new Joueur("NORD");
        NORD.jeu.clear();
        Regles.regleDePioche(false, NORD);
        assertTrue(NORD.jeu.size() == 2);
        NORD.jeu.clear();
        Regles.regleDePioche(true, NORD);
        assertTrue(NORD.jeuEstPlein());
    }
}