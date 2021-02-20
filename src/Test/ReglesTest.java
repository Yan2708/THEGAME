package Test;

import Composantes.Joueur;
import Composantes.Regles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReglesTest {


    //  Test si la carte donnée est posable sur la base
    @Test
    public void TestEstPosable(){
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
}