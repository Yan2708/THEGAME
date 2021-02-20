package Test;

import Composantes.Joueur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// test la classe joueur
class JoueurTest {
    //  affichage du jeu du joueur
    //  affichage attendu :
    //  cartes NORD { 09 08 44 47 49 43 }
    @Test
    void afficherMain() {
        Joueur NORD = new Joueur("NORD");
        System.out.println(NORD.afficherJeu());
    }

    //  test l'affichage de toString et du constructeur
    //  affichage attendu :
    //  NORD ^[01] v[60] (m6p52)
    //  SUD ^[01] v[60] (m6p52)
    @Test
    void testToString() {
        Joueur NORD = new Joueur("NORD");
        System.out.println(NORD.toString());
        Joueur SUD = new Joueur("SUD");
        System.out.println(SUD.toString());
    }

    //
    @Test
    void testEstDansLeJeu() {
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

}