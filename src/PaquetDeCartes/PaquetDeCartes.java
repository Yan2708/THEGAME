package PaquetDeCartes;

import java.util.ArrayList;
import java.util.Random;

public class PaquetDeCartes
{
    /** Taille du paquet de carte pour un joueur */
    private static final int TAILLE_PAQUET_CARTE = 60;
    /** Tableau de int représentant le paquet de cartes */
    public ArrayList<Integer> paquetDeCartes = new ArrayList<>(TAILLE_PAQUET_CARTE);

    /**
     * constructeur du paquet de cartes
     */
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

    /** pioche une carte aleatoirement dans le paquet
     *
     * @return le numéro de la carte piochée
     */
    public int piocher()
    {
        assert(!estVide());
        Random rand = new Random();
        int idxAleatoire = rand.nextInt(paquetDeCartes.size());
        int carteAleatoire = paquetDeCartes.get(idxAleatoire); //taille = 50 -> 0-49
        paquetDeCartes.remove(idxAleatoire);
        return carteAleatoire;
    }


     /** pioche la carte à l'indice donné dans le paquet
      *
      * @param idx
      *              l'indice de la carte voulue
      * @return le numéro de la carte piochée
      * */
    public int piocher(int idx){
        assert(!estVide() && (idx >= 0 && idx <= paquetDeCartes.size()));
        int carte = paquetDeCartes.get(idx);
        paquetDeCartes.remove(idx);
        return carte;
    }

    /**
     * indique si le paquet est vide
     */
    public boolean estVide()
    {
        return paquetDeCartes.size() == 0;
    }

    /**
     * retourne la liste des cartes restantes dans le paquet
     */
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
