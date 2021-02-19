package Test;

import Composantes.Joueur;
import org.junit.jupiter.api.Test;

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
}