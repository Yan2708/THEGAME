package Composantes;

public class Regles {

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
    public static boolean estPosable(int carte, char base, Joueur poseur, Joueur receveur){
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
