package Composantes;

import java.util.Scanner;

public class Regles {

    /**
     * Joue les coups d'un joueur.
     *
     * @param coups
     *                  les coups du joueurs
     * @param j1
     *                  le joueur qui joue les coups
     * @param j2
     *                  le joueur qui recoit (ou non) les coups
     * */
    public static void jouerCoups(String[] coups,Joueur j1, Joueur j2) {
        for (String coup: coups) {
            if(isCampEnnemie(coup))
                j1.poserCarte(Scan.getCarte(coup), Scan.getBase(coup), j2);
            else j1.poserCarte(Scan.getCarte(coup), Scan.getBase(coup));
        }
    }

    /**
     * Verifie si les coups du joueur sont jouable.
     *
     * @param coups
     *                  les coups du joueurs
     *
     * @param j1
     *                  le joueur qui joue les coups
     *
     * @param j2
     *                  le joueur adverse
     *
     * @return si les coups sont valides ou non
     *
     * */
    public static boolean areCoupsValid(String[] coups, Joueur j1, Joueur j2) {
        // /!\ A FAIRE (SIMULER LES COUPS DU JOUEURS ET RETURN SI UN EST FAUX
        return false;
    }


    /**
     * Vérifie si la carte jouée du joueur est posable sur une base donnée
     * (un joueur peut poser sur sa base et sur la base ennemie).
     *
     * @param carte
     *                  la carte du joueur
     *
     * @param base
     *                  la base du joueur ( 'v' pour descendante et '^' pour ascendante)
     *
     * @param poseur
     *                  le joueur qui pose sa carte
     *
     * @param receveur
     *                  le joueur qui reçoit la carte
     *
     * @return si la carte est posable ou non
     *
     */
    public static boolean estPosable(int carte, char base , Joueur poseur, Joueur receveur){


        if(receveur.equals(poseur)) {
            if( base == 'v' )
                return receveur.descendant > carte || (receveur.descendant + 10 == carte);    //  dizaine au dessus
            else if( base == '^')
                return receveur.ascendant < carte || (receveur.ascendant - 10) == carte;    //  dizaine en dessous
        }
        else {
            if( base == 'v')
                return receveur.descendant > carte;
            else if( base == '^')
                return receveur.ascendant < carte;
        }
        return false;
    }


}
