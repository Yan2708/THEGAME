package Test;

import Composantes.Joueur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test la classe joueur
class JoueurTest {


    /**
     * Test si une carte est dans le jeu.
     * */
    @Test
    public void testEstDansLeJeu() {
        Joueur NORD = new Joueur("NORD");
        NORD.jeu.set(0, 21);
        NORD.jeu.set(1, 45);
        NORD.jeu.set(2, 52);
        NORD.jeu.set(3, 13);
        NORD.jeu.set(4, 9);
        NORD.jeu.set(5, 12);
        assertTrue(NORD.estDansLeJeu(21));
        assertTrue(NORD.estDansLeJeu(45));
        assertTrue(NORD.estDansLeJeu(52));
        assertTrue(NORD.estDansLeJeu(13));
        assertTrue(NORD.estDansLeJeu(9));
        assertTrue(NORD.estDansLeJeu(12));
        assertFalse(NORD.estDansLeJeu(1));
        assertFalse(NORD.estDansLeJeu(60));
        assertFalse(NORD.estDansLeJeu(19));
    }

    /**
     * Test de la pioche de carte.
     * */
    @Test
    public void testPiocherCarte(){
        Joueur NORD = new Joueur("NORD");
        NORD.piocherCarte();
        assertEquals(NORD.jeu.size(), 6);
        NORD.jeu.clear();
        NORD.piocherCarte();
        assertEquals(NORD.jeu.size(), 1);
        NORD.piocherCarte();
        assertEquals(NORD.jeu.size(), 2);
    }

    /**
     * Test du getter de nombre de cartes.
     * */
    @Test
    public void testGetNbPioche(){
        Joueur NORD = new Joueur("NORD");
        assertEquals(NORD.getNbPioche(), 52);
        NORD.jeu.clear();
        while(!NORD.jeuEstPlein())
            NORD.piocherCarte();
        assertEquals(NORD.getNbPioche(), 46);
        while(NORD.getNbPioche()!=0){
            NORD.jeu.clear();
            NORD.piocherCarte();
        }
        assertEquals(NORD.getNbPioche(), 0);
    }

    /**
     * T du clone du joueur.
     * */
    @Test
    public void testClone() {
        Joueur NORD = new Joueur("NORD");
        Joueur DOPPELGANGER = NORD.clone();
        assertEquals(NORD.jeu, DOPPELGANGER.jeu);
        assertEquals(NORD.getDescendant(), DOPPELGANGER.getDescendant());
        assertEquals(NORD.getAscendant(), DOPPELGANGER.getAscendant());
        DOPPELGANGER.jeu.set(0,44);
        DOPPELGANGER.jouerCarte(44,'^');
        assertNotEquals(NORD.jeu, DOPPELGANGER.jeu);
    }

    /**
     * Test si le jeu est vide.
     * */
    @Test
    public void testJeuEstVide() {
        Joueur NORD = new Joueur("NORD");
        assertFalse(NORD.jeuEstVide());
        NORD.jeu.clear();
        assertTrue(NORD.jeuEstVide());
    }

    /**
     * Test si le jeu est plein.
     * */
    @Test
    public void testJeuEstPlein() {
        Joueur NORD = new Joueur("NORD");
        assertTrue(NORD.jeuEstPlein());
        NORD.jeu.clear();
        assertFalse(NORD.jeuEstPlein());
    }

    /**
     * Test l'action de jouer une carte.
     * */
    @Test
    public void testJouerCarte() {
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");
        NORD.jeu.set(0, 23);
        NORD.jeu.set(1,34);
        NORD.jouerCarte(23, 'v');
        assertEquals(NORD.getDescendant(), 23);
        NORD.jouerCarte(34, '^', SUD);
        assertEquals(SUD.getAscendant(), 34);
    }
}