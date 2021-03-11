package Composantes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Le joueur est une représentation de l'utilisateur.
 * Il possède un nom ("NORD" ou "SUD").
 * Un jeu de cartes.
 * Une pile ascendante et descendante (la base).
 * Un paquet de cartes, la pioche.
 * Il s'agit ici de manœuvrer le joueur en fonction de l'utilisateur et de ce qu'il décide.
 *
 * @author      Yannick li
 * @author      Stefan Radovanovic
 * @version     1, 2/27/2021
 * */
public class Joueur {

    public static final int NB_CARTES_MAX = 6;  // nombre de cartes maximum dans le jeu


    public final String nom;  // nom du joueur

    public ArrayList<Integer> jeu;  // jeu du joueur

    private int ascendant, descendant;   // base du joueur

    private final PaquetDeCartes pioche;  // pioche du joueur


    /**
     * Constructeur du joueur
     *
     * @param n             le nom du joueur, sous une chaîne de caractères.
     */
    public Joueur(String n) {
        nom = n;
        pioche = new PaquetDeCartes();
        // la pile ascendante est initialisée à 1 et la descendante à 60
        ascendant = pioche.piocher(1);
        descendant = pioche.piocher(60);
        jeu = new ArrayList<>();
        for(int i=0; i < NB_CARTES_MAX ; i++) // pioche le jeu du joueur
            piocherCarte();

    }


    /**
     * Constructeur d'un clone de joueur.
     * Ce constructeur permet de recopier les données d'un joueur
     * afin de les manipuler sans incidence avec le vrai Joueur (l'utilisateur).
     *
     * @param j             le joueur à cloner.
     */
    private Joueur(Joueur j) {   // clone utilisé que pour la vérification des coups
        jeu = new ArrayList<>();

        jeu.addAll(j.jeu);    //  duplication en profondeur d'une arrayList
        ascendant = j.ascendant;
        descendant = j.descendant;
        pioche = new PaquetDeCartes();  //   pas besoin de paquet de carte
        nom = "CLONE";     //   pas besoin de nom
    }


    /**
     * crée un clone du joueur
     *
     * @return              le clone du joueur
     */
    public Joueur clone() {
        return new Joueur(this);
    }


    /**
     * getter de la pile ascendante
     *
     * @return la valeur de la pile
     * */
    public int getAscendant() {
        return ascendant;
    }


    /**
     * setter de la pile ascendante
     *
     * @param ascendant         la nouvelle valeur de la pile
     * */
    public void setAscendant(int ascendant) {
        this.ascendant = ascendant;
    }


    /**
     * getter de la pile descendante
     *
     * @return la valeur de la pile
     * */
    public int getDescendant() {
        return descendant;
    }


    /**
     * setter de la pile descendante
     *
     * @param descendant        la nouvelle valeur de la pile
     * */
    public void setDescendant(int descendant) {
        this.descendant = descendant;
    }


    /**
     * Renvoie le nombre de cartes dans la pioche
     *
     * @return              le nombre de cartes dans la pioche
     */
    public int getNbPioche() {
        return pioche.getNbCartes();
    }


    /**
     * Verifie si la pioche du joueur est vide
     *
     * @return              si la pioche est vide ou non
     */
    public boolean piocheEstVide() {
        return pioche.estVide();
    }


    /**
     *  Pioche une carte dans la pioche et la rajoute dans le jeu du joueur.
     */
    public void piocherCarte(){
        if(!jeuEstPlein() && !piocheEstVide()){
            jeu.add(pioche.piocher());
        }
        Collections.sort(jeu);
    }


    /**
     * Vérifie si le jeu du joueur est vide
     *
     * @return              si le jeu est vide ou non
     */
    public boolean jeuEstVide() {
        return jeu.isEmpty();
    }


    /**
     * Vérifie si le jeu du joueur est plein.
     *
     * @return              si le jeu est plein ou non.
     */
    public boolean jeuEstPlein() {
        return jeu.size() == NB_CARTES_MAX;
    }


    /**
     * Vérifie si la carte jouée du joueur est dans sa main.
     *
     * @param carte         la carte du joueur.
     * @return              la carte est présente ou non.
     */
    public boolean estDansLeJeu(int carte) {
        return jeu.contains(carte);
    }


    /**
     * Pose une carte donnée dans une base donnée.
     *
     * @param carte         la carte donné.
     * @param base          la base dans la quelle il faut jouer.
     * */
    public void poserCarte(int carte, char base) {
        if (base == '^')
            ascendant = carte;
        else if (base == 'v')
            descendant = carte;
    }


    /**
     * pose une carte donnée sur sa base.
     *
     * @param carte         la carte à poser.
     * @param base          la base où l'on va poser la carte.
     */
    public void jouerCarte(int carte, char base) {
        assert(estDansLeJeu(carte));
        this.jeu.remove((Integer) carte);
        this.poserCarte(carte, base);
    }


    /**
     * pose une carte donnée sur la base du joueur adverse
     *
     * @param carte         la carte à poser
     * @param base          la base où l'on va poser la carte
     * @param j             le joueur à qui on pose une carte dans sa base
     */
    public void jouerCarte(int carte, char base, Joueur j) {
        assert(estDansLeJeu(carte));
        this.jeu.remove(((Integer) carte));
        j.poserCarte(carte, base);
    }


    /**
     * Créer une chaîne de caractères comportant l'ensemble du jeu d'un joueur.
     *
     * @return              la chaîne de caractères
     */
    public String afficherJeu(){
        StringBuilder sb = new StringBuilder();
        sb.append("cartes ").append(nom).append(" { ");
        for(Integer carte : jeu){
            sb.append(String.format("%02d", carte)).append(" ");
        }
        sb.append("}");
        return sb.toString();
    }


    /**
     * Créer une chaîne de caractères comportant le nom du joueur, sa base,
     * le nombre de carte de son jeu ainsi que le nombre de carte dans sa pioche.
     *
     * @return              la chaîne de caractères
     */
    @Override
    public String toString() {
        return nom + " ^["+ String.format("%02d", ascendant) + "]"
                + " v[" + String.format("%02d", descendant) + "]"
                + " (m" + jeu.size() + "p" + (pioche.getLastIdx() + 1) + ")";
    }

}
