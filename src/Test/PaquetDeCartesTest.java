package Test;

import Composantes.PaquetDeCartes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//  test le constructeur du paquet de cartes
class PaquetDeCartesTest
{
    //  le paquet de carte
    @Test
    public void testinitialisation()
    {
        PaquetDeCartes p1 = new PaquetDeCartes();
        System.out.println(p1.toString());
    }

    // test la pioche aléatoire dans le paquet de carte
    @Test
    public void testPiocheAleatoire()
    {
        PaquetDeCartes p1 = new PaquetDeCartes();
        while(!p1.estVide())
        {
            int carte = p1.piocher();
            assert(carte >=1 && carte < 61);
        }
        assert(p1.estVide());
    }


    //  test si le paquet est vide
    @Test
    public void testEstVide(){
        PaquetDeCartes p1 = new PaquetDeCartes();
        assertFalse(p1.estVide());
        for (int i = 0 ; i<p1.getNbCartes();) {
            p1.piocher();
        }
        assertTrue(p1.estVide());
    }


    //  test si le paquet est vide
    @Test
    public void testGetNbCartes(){
        PaquetDeCartes p1 = new PaquetDeCartes();
        assertEquals(p1.getNbCartes(), 60);
        p1.piocher();
        assertEquals(p1.getNbCartes(), 59);
    }


    //  test le renvoie du dernier index de la liste de carte
    @Test
    public void testGetLastIdx(){
        PaquetDeCartes p1 = new PaquetDeCartes();
        for(int i = 0; i< 60;i++){
            assertEquals(59 - i, p1.getLastIdx());
            p1.piocher();
        }
    }

    //  test la pioche specifique (avec un indice donné)
    @Test
    public void testPiocheSpecifique()
    {
        PaquetDeCartes p1 = new PaquetDeCartes();
        int i=0;
        while(!p1.estVide())
        {
            int carte = p1.piocher(0);
            assert(carte >=1 && carte < 61 && i+1==carte);
            i++;
        }
        assert(p1.estVide());
    }

}