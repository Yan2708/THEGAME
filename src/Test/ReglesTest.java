package Test;

import Composantes.Joueur;
import Composantes.Regles;
import Composantes.Scan;
import org.junit.jupiter.api.Test;

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

    //  test la detection d'un coup ennemie
    @Test
    public void testIsCampEnnemie() {
        assertTrue(Regles.isCampEnnemie("34v'"));
        assertFalse(Regles.isCampEnnemie("34v"));
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
        assertEquals(SUD.descendant, 12);
        assertEquals(SUD.ascendant, 59);
        assertEquals(NORD.descendant, 46);
    }

    //test la verification semantique des coups
    // la syntaxe est bonne, on test seulement la semantique
    @Test
    public void testAreCoupsValid() {
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");
        SUD.jeu.set(0, 12);
        SUD.jeu.set(1, 39);
        SUD.jeu.set(2, 46);
        SUD.jeu.set(3, 59);
        SUD.jeu.set(4, 22);
        SUD.jeu.set(5, 14);

        assertTrue(Regles.areCoupsValid(Scan.decomposer("12^ 39v 46^"), SUD.clone(), NORD.clone()));
        assertTrue(Regles.areCoupsValid(Scan.decomposer("12^ 39v 46^'"),SUD.clone(), NORD.clone()));
        assertFalse(Regles.areCoupsValid(Scan.decomposer("39^ 12^ 46^"), SUD.clone(), NORD.clone()));
        assertFalse(Regles.areCoupsValid(Scan.decomposer("12^ 39v' 46^'"), SUD.clone(), NORD.clone()));
        assertTrue(Regles.areCoupsValid(Scan.decomposer("12v 22v "), SUD.clone(), NORD.clone()));
        assertTrue(Regles.areCoupsValid(Scan.decomposer("12^ 46^'"), SUD.clone(), NORD.clone()));
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
        assertEquals(NORD.jeu.size(), 2);
        NORD.jeu.clear();
        Regles.regleDePioche(true, NORD);
        assertTrue(NORD.jeuEstPlein());
    }

    //  test de la fonction partieContinue
    @Test
    public void testPartieContinue(){
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");
        assertTrue(Regles.partieContinue(NORD.clone(), SUD.clone(), 0,0));
        for (int i=0; i<Joueur.NB_CARTES_MAX;i++) {
            NORD.jeu.set(i,5);
            SUD.jeu.set(i,5);
        }
        NORD.ascendant=59;
        NORD.descendant=2;
        SUD.ascendant=59;
        SUD.descendant=2;
        assertFalse(Regles.partieContinue(NORD.clone(), SUD.clone(), 0,0));
        NORD.jeu.clear();
        NORD.jeu.add(0,5);
        NORD.jeu.add(1,6);
        SUD.descendant=7;
        assertFalse(Regles.partieContinue(NORD.clone(), SUD.clone(), 0,0));
    }


}