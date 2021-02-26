package Composantes;

import java.util.ArrayList;

/**
 *
 * */
public class Joueur {

    public static final int NB_CARTES_MAX = 6;  // nombre de cartes maximum dans le jeu

    public String nom;  // nom du joueur

    private PaquetDeCartes pioche;  // pioche du joueur

    public ArrayList<Integer> jeu;  // jeu du joueur

    public int ascendant, descendant;   // base du joueur


    /**
     * Constructeur du joueur
     *
     * @param n
     *                  le nom du joueur
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

    /**
     * Constructeur d'un clone de joueur
     *
     * @param j
     *                  le joueur à cloner
     *
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
     * @return le clone du joueur
     *
     */
    public Joueur clone() {
        return new Joueur(this);
    }

    /**
     * Renvoie le nombre de cartes dans la pioche
     *
     * @return le nombre de cartes dans la pioche
     */
    public int getNbPioche() {
        return pioche.getNbCartes();
    }

    /**
     * Verifie si la pioche du joueur est vide
     *
     * @return si la pioche est vide ou non
     */
    public boolean piocheEstVide() {
        return pioche.estVide();
    }


    /**
     * Verifie si le jeu du joueur est vide
     *
     * @return si le jeu est vide ou non
     */
    public boolean jeuEstVide() {
        return jeu.isEmpty();
    }


    /**
     * Verifie si le jeu du joueur est plein
     *
     * @return si le jeu est plein ou non
     */
    public boolean jeuEstPlein() {
        return jeu.size() == NB_CARTES_MAX;
    }


    /**
     * Pose une carte donné dans une base donné
     *
     * @param carte
     *                  la carte donné
     *
     * @param base
     *                  la base dans la quelle il faut jouer
     *
     * */
    public void poserCarte(int carte, char base) {
        if (base == '^')
            ascendant = carte;
        else if (base == 'v')
            descendant = carte;
    }


    /**
     * pose une carte donnée sur sa base
     *
     * @param carte
     *                  la carte à poser
     * @param base
     *                  la base où l'on va poser la carte
     */
    public void jouerCarte(int carte, char base) {
        assert(estDansLeJeu(carte));
        this.jeu.remove((Integer) carte);
        this.poserCarte(carte, base);
    }


    /**
     * pose une carte donnée sur la base du joueur adverse
     *
     * @param carte
     *                  la carte à poser
     * @param base
     *                  la base où l'on va poser la carte
     * @param j
     *                  le joueur à qui on pose une carte dans sa base
     */
    public void jouerCarte(int carte, char base, Joueur j) {
        assert(estDansLeJeu(carte));
        this.jeu.remove(((Integer) carte));
        j.poserCarte(carte, base);
    }


    /**
     *  pioche une carte dans la pioche et la rajoute dans la main du joueur
     */
    public void piocherCarte(){
        if(!jeuEstPlein() && !piocheEstVide()){
            jeu.add(pioche.piocher());
        }
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