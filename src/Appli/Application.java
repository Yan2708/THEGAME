package Appli;

import Composantes.Joueur;
import Composantes.Regles;
import Composantes.Input;

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

        while(Regles.partieContinue(courant, passif, 0,false)) {

            showGame(NORD, SUD, courant);

            String[] coups = Input.decomposer(Input.getUsersLine());

            while(!(Input.isSyntaxValid(coups)
                    && Regles.areCoupsValid(coups, courant.clone(), passif.clone()))){

                System.out.print("#");
                coups = Input.decomposer(Input.getUsersLine());
            }

            Regles.jouerCoups(coups, courant, passif);

            //  permutation
            if(courant.equals(NORD)) {
                courant = SUD;
                passif = NORD;
            } else {
                courant = NORD;
                passif = SUD;
            }

            if(Regles.partieFinie(passif)){
                break;
            }
        }
        showGame(NORD, SUD, courant);

        System.out.println("partie finie, " + passif.getNom() + " a gagné");
    }
}