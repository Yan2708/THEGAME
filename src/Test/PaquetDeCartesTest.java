package Test;

import PaquetDeCartes.PaquetDeCartes;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

//  test le constructeur du paquet de cartes
class PaquetDeCartesTest
{
    //  le paquet de carte
    @Test
    public void Testinitialisation()
    {
        PaquetDeCartes p1 = new PaquetDeCartes();
        System.out.println(p1.toString());
    }

    // test la pioche aléatoire dans le paquet de carte
    @Test
    public void TestPiocheAleatoire()
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
    public void TestPiocheSpecifique()
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