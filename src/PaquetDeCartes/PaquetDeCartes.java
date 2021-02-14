package PaquetDeCartes;

import java.util.ArrayList;
import java.util.Random;

public class PaquetDeCartes
{
    private static final int TAILLE_PAQUET_CARTE = 60; //Taille du paquet de carte pour un joueur
    public ArrayList<Integer> paquetDeCartes = new ArrayList<Integer>(TAILLE_PAQUET_CARTE); //Tableau de int représentant
                                                                                             //le paquet de carte


    public PaquetDeCartes()
    {
        // 2 methodes;
        // methode realiste : initialiser un paquet avec des valeurs randoms entre 1-60 et unique
        // methode simple : pioche une carte au hasard d'un paquet trié (on prend ca)
        for (int i = 0; i < TAILLE_PAQUET_CARTE; i++)
        {
            paquetDeCartes.add(i, i + 1);
        }
    }

    public int piocher()
    {
        assert(!estVide());
        Random rand = new Random();
        int idxAleatoire = rand.nextInt(paquetDeCartes.size());
        int carteAleatoire = paquetDeCartes.get(idxAleatoire); //taille = 50 -> 0-49
        paquetDeCartes.remove(idxAleatoire);
        return carteAleatoire;
    }

    public int piocher(int idx){
        assert(!estVide() && (idx >= 0 && idx <= paquetDeCartes.size()));
        int carte = paquetDeCartes.get(idx);
        paquetDeCartes.remove(idx);
        return carte;
    }

    public boolean estVide()
    {
        return paquetDeCartes.size() == 0;
    }


    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < TAILLE_PAQUET_CARTE; i++)
        {
            if(i != 0)
                sb.append(", ");
            sb.append(paquetDeCartes.get(i));
        }

        return sb.toString();
    }
}
