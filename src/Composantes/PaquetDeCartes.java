package Composantes;

import java.util.ArrayList;
import java.util.Random;

/**
 * Le paquet de cartes est une composante essentielle au jeu.
 * il signifie à la fois :
 * - l'ensemble complet des cartes nécessaires pour pratiquer le jeu
 * - mais aussi dans notre cas le jeu de société en lui-même, car c'est le matériel exclusif du jeu.
 * Il s'agit ici de faire une représentation d'un réel paquet de cartes.
 * la méthode utilisée est de faire en sorte de rendre piochable n'importe quelle carte
 * d'une ArrayList aléatoire, à la manière d'un paquet de carte que l'on aurait mélanger.
 *
 * @author      Yannick li
 * @author      Stefan Radovanovic
 * @version     1, 2/27/2021
 * */
public class PaquetDeCartes {

    //Taille du paquet de carte pour un joueur
    private static final int TAILLE_PAQUET_CARTE = 60;

    //Tableau de int représentant le paquet de cartes
    private final ArrayList<Integer> paquetDeCartes = new ArrayList<>(TAILLE_PAQUET_CARTE);


    /**
     * constructeur du paquet de cartes
     */
    public PaquetDeCartes() {
        // 2 methodes;
        // methode realiste : initialiser un paquet avec des valeurs randoms entre 1-60 et unique
        // methode simple : pioche une carte au hasard d'un paquet trié (on prend ca)
        for (int i = 0; i < TAILLE_PAQUET_CARTE; i++) {
            paquetDeCartes.add(i, i + 1);
        }
    }


    /**
     * Pioche une carte aleatoirement dans le paquet et la retire du paquet.
     *
     * @return              le numéro de la carte piochée
     * @see                 Random#nextInt()
     * @see                 ArrayList#remove(int)
     */
    public int piocher() {
        assert(!estVide());
        Random rand = new Random();
        int idxAleatoire = rand.nextInt(paquetDeCartes.size());
        int carteAleatoire = paquetDeCartes.get(idxAleatoire);
        paquetDeCartes.remove(idxAleatoire);
        return carteAleatoire;
    }


    /** Pioche une carte specifique dans le paquet de carte.
     *
     * @param carte         la carte choisie
     * @return              la carte piochée
     * @see                 Integer#valueOf(int)
     * @see                 IllegalArgumentException
     **/
    public int piocher(int carte) throws IllegalArgumentException{
        if(estVide() && paquetDeCartes.contains(carte))
            throw new IllegalArgumentException("la carte n'est pas dans le paquet de carte !");

        paquetDeCartes.remove(Integer.valueOf(carte));
        return carte;
    }


    /**
     * Indique si le paquet est vide.
     *
     * @return              la pioche est vide ou non
     */
    public boolean estVide()
    {
        return paquetDeCartes.size() == 0;
    }


    /**
     * Renvoie le dernier indice du paquet de carte.
     *
     * @return              le dernier indice de l'ArrayList
     */
    public int getLastIdx() {
        return paquetDeCartes.size()-1;
    }


    /**
     * Renvoie le nombre de cartes dans le paquet de cartes.
     *
     * @return              le nom de cartes
     */
    public int getNbCartes() { return paquetDeCartes.size(); }

}