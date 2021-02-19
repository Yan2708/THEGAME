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
    private ArrayList<Integer> jeu;
    // base du joueur
    private int ascendant, descendant;


    /**
     * constructeur du joueur
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

    /** créer une chaine de caractères comportant l'ensemble de le jeu d'un joueur
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

    /** créer une chaine de ractères comportant le nom du joueur, sa base,
     * le nombre de carte de son jeu ainsi que le nombre de carte dans sa pioche
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
