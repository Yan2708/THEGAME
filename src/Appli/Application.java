package Appli;

import Composantes.Joueur;
import Composantes.Regles;
import Composantes.Input;

import java.util.Scanner;

/**
 * L'application agence chacune de nos composantes
 * et permet de jouer au jeu The Game - le Duel.
 *
 * @author Yannick li
 * @author Stefan Radovanovic
 * @version 1, 2/27/2021
 * */
public class Application {


    /**
     * Récupère l'entrée de l'uttilisateur, son coup.
     * Cette méthode utilise la classe Scanner qui couplé à un flux comme system.in
     * permet d'extraire les informations données qui sont ensuite retourné dans un String.
     * "> " est affiché pour correspondre aux normes d'affichages.
     *
     * @return              un String contenant les coups du joueur.
     * @see                 Scanner
     * */
    private static String getUsersLine(Scanner sc) {
        System.out.print("> ");
        return sc.nextLine();
    }


    /**
     * Affiche les informations des joueurs NORD et SUD ainsi que la main du joueur courant.
     *
     * @param NORD          le joueur NORD
     * @param SUD           le joueur SUD
     * @param courant       le joueur courant
     * */
    private static void showGame(Joueur NORD, Joueur SUD, Joueur courant) {
        System.out.println(NORD);
        System.out.println(SUD);
        System.out.println(courant.afficherJeu());
    }


    public static void main(String[] args) {
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");

        Joueur courant = NORD;      //  Références des objets joueurs
        Joueur passif = SUD;        //

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        while(Regles.partieContinue(courant.clone(), passif.clone(), 0,false)) {

            showGame(NORD, SUD, courant);

            String[] coups = Input.decomposer(getUsersLine(sc));

            while(!(Input.isSyntaxValid(coups) &&
                    Regles.areCoupsValid(coups, courant.clone(), passif.clone()))){

                System.out.print("#");
                coups = Input.decomposer(getUsersLine(sc));
            }

            int nbCarteAvantCoup = courant.jeu.size();  //  save
            Regles.jouerCoups(coups, courant, passif);

            //  calcul permettant d'obtenir le nombre de carte piochée.
            int nbCartePiochee = courant.jeu.size() - (nbCarteAvantCoup - coups.length);

            System.out.println(coups.length + " cartes posées, " + nbCartePiochee + " cartes piochées");

            //  permutation
            Joueur tmp = passif;
            passif = courant;
            courant = tmp;

            if(Regles.partieFinie(passif))
                break;  //  la partie se finit avant de laisser le prochain joueur jouer

        }
        showGame(NORD, SUD, courant);

        System.out.println("partie finie, " + passif.nom + " a gagné");
        sc.close();
    }
}