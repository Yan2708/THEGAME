package Composantes;

import java.util.ArrayList;

public class Joueur {

    // nombre de cartes maximum dans le jeu
    public static final int NB_CARTES_MAX = 6;
    // nom du joueur
    private String nom;
    // pioche du joueur
    private PaquetDeCartes pioche;
    // jeu du joueur
    public ArrayList<Integer> jeu;
    // base du joueur
    public int ascendant, descendant;


    /**
     * Constructeur du joueur
     *
     * @param n
     *              le nom du joueur
     */
    public Joueur(String n) {
        nom = n;
        pioche = new PaquetDeCartes();
        // la pile ascendante est initialisée à 0 et la descendante à 60
        ascendant = pioche.piocher(0);
        descendant = pioche.piocher(pioche.getLastIdx());

        jeu = new ArrayList<>();
        for(int i=0; i < NB_CARTES_MAX ; i++) // pioche le jeu du joueur
            jeu.add(pioche.piocher());
    }




    public void poserCarte(int carte, Joueur base) {
        // retirer carte de la main
        // verifier quelle est dans la main
        // l'appliquer sur la pile
        // verifier si on peut la poser
        // verifier la pile
             base.ascendant = carte;
    }

    /**
     * Vérifie si la carte jouée du joueur est dans sa main.
     *
     * @param carte
     *                  la carte du joueur
     *
     * @return la carte est présente ou non
     *
     */
    public boolean estDansLeJeu(int carte) {
        return jeu.contains(carte);
    }

    /**
     * Créer une chaine de caractères comportant l'ensemble du jeu d'un joueur.
     *
     * @return la chaine de caractères
     *
     */
    public String afficherJeu(){
        StringBuilder sb = new StringBuilder();
        sb.append("cartes " + nom + " { ");
        for(Integer carte : jeu){
            sb.append(String.format("%02d",carte) + " ");
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Créer une chaine de ractères comportant le nom du joueur, sa base,
     * le nombre de carte de son jeu ainsi que le nombre de carte dans sa pioche.
     *
     * @return la chaine de caractères
     *
     */
    public String toString() {
        return nom + " ^["+ String.format("%02d", ascendant) + "]"
                + " v[" + String.format("%02d", descendant) + "]"
                + " (m" + jeu.size() + "p" + (pioche.getLastIdx() + 1) + ")";
    }


}
