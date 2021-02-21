package Test;

import Composantes.PaquetDeCartes;
import org.junit.jupiter.api.Test;

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