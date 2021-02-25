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
        boolean coupAdv = false;
        for (String coup: coups) {
            if(isCampEnnemie(coup)) {
                j1.jouerCarte(Scan.getCarte(coup), Scan.getBase(coup), j2);
                coupAdv = true;
            }
            else j1.jouerCarte(Scan.getCarte(coup), Scan.getBase(coup));
        }
        System.out.println(coups.length + " carte posées, " + regleDePioche(coupAdv, j1) + " cartes piochées");
    }



    /**
     * /!\ A utiliser en partant du principe que la syntaxe est bonne. cf : isSyntaxValid (Scan)
     *
     * Verifie si les coups du joueur sont jouable (la semantique).
     *
     * @param coups
     *                  les coups du joueurs
     *
     * @param j1Bis
     *                  le joueur qui joue les coups
     *
     * @param j2Bis
     *                  le joueur adverse
     *
     * @return si les coups sont valides ou non
     *
     * */
    public static boolean areCoupsValid(String[] coups, Joueur j1Bis, Joueur j2Bis) {
        Joueur receveur;
        int nbCoupAd = 0; // nombres de coups joués chez l'adversaire

        for(String coup : coups) {
           int carte = Scan.getCarte(coup);
           char base = Scan.getBase(coup);

           if(!j1Bis.estDansLeJeu(carte)) //si la carte fait partie du jeu ou non
               return false;

           receveur = isCampEnnemie(coup) ? j2Bis : j1Bis; // le joueur qui reçoit la carte
           if(estPosable(carte, base, j1Bis, receveur)) {
               if(isCampEnnemie(coup)) {
                   if(nbCoupAd >= 1)    // il est possible de jouer qu'une fois chez l'adversaire
                       return false;
                   j1Bis.jouerCarte(carte, base, receveur);
                   nbCoupAd++;

               }
               else j1Bis.jouerCarte(carte, base);
           } else return false;


        }
        return true;
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
        if(!poseur.estDansLeJeu(carte)) //si la carte fait partie du jeu ou non
            return false;
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

    /**
     * Verifie si un coup joué est destiné au joueur adverse (utilisé après estPosable)
     *
     * @return si le coup est pour le joueur adverse ou non
     * */
    public static boolean isCampEnnemie(String coup) {
        return coup.length() == 4; // si un coup constitue une chaine de 4 caracteres alors il doit etre
                                   // destiné à l'adversaire car il comprend une apostrophe. exemple : 34v' (4 char)
    }

    /**
     *  Verifie si le joueur q posé la totalité de ses cartes
     *
     * @return si il n'y a plus de carte à jouer
     * */
    public static boolean partieFinie(Joueur j){
        return (j.jeuEstVide() && j.getNbPioche()==0);
    }



    /**
     * Verifie si il y a au moins 2 combinaisons de cartes possible pour un joueur
     *
     * @param j1Bis
     *              le joueur qui doit jouer
     * @param j2Bis
     *              le 2ème joueur
     * @param nb
     *              le nombre de coups possibles
     *
     * @return si la partie continue ou non
     *
     * */
    public static boolean partieContinue(Joueur j1Bis, Joueur j2Bis, int nb){
        if(nb>=2)
            return true;
        for(int carte : j1Bis.jeu){
            if(estPosable(carte, 'v', j1Bis, j1Bis)) {
                j1Bis.jouerCarte(carte, 'v');
                nb++;
                if (partieContinue(j1Bis, j2Bis, nb, nbCoupAd))
                    return true;
            }
            if(estPosable(carte, '^', j1Bis, j1Bis)) {
                j1Bis.jouerCarte(carte, '^');
                nb++;
                if (partieContinue(j1Bis, j2Bis, nb))
                    return true;
            }
            if(estPosable(carte, 'v', j1Bis, j2Bis)) {
                j1Bis.jouerCarte(carte, 'v', j2Bis);
                nb++;
                if (partieContinue(j1Bis, j2Bis, nb))
                    return true;
            }
            if(estPosable(carte, '^', j1Bis, j2Bis)) {
                j1Bis.jouerCarte(carte, '^', j2Bis);
                nb++;
                if (partieContinue(j1Bis, j2Bis, nb))
                    return true;
            }
        }
        return false;
    }




    /**
     *  fait piocher un joueur les cartes selon si il a joué chez l'adversaire ou non
     *
     * @param jouerAd
     *              true si le joueur joue chez l'adversaire
     * @param j
     *              le joueur qui doit piocher
     *
     * @return le nombre de carte piochees
     */
    public static int regleDePioche(boolean jouerAd, Joueur j) {
        int nbCartePioches = 0;
        if (jouerAd) {                //pioche jusqu'à que sa main soit pleine
            while (!j.jeuEstPlein()) {
                j.piocherCarte();
                nbCartePioches++;
            }
        } else {                  // n'a joué que sur ses bases, pioche 2 cartes
            for (int i = 0; i < 2; i++)
                if (!j.jeuEstPlein()) {
                    j.piocherCarte();
                    nbCartePioches++;
                }
        }
        return nbCartePioches;
    }

}



