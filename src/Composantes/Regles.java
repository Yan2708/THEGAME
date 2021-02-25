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
        Joueur j1Bis = j1.clone();  //  on cree deux clones pour simuler les coups du joueur
        Joueur j2Bis = j2.clone();  //
        Joueur receveur;
        int nbCoupAd = 0; // nombres de coups joués chez l'adversaire

        for(String coup : coups) {
           int carte = Scan.getCarte(coup);
           char base = Scan.getBase(coup);

           if(!j1.estDansLeJeu(carte)) //si la carte fait partie du jeu ou non
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
     *
     *          /!\ A FAIRE
     * */
    public static boolean partieperdue(Joueur j1, Joueur j2){
        // plutot complexe
        return false;
    }


    /**
     *
     *          /!\ A FAIRE
     * */
    public static boolean cartePosable(int carte, char base, Joueur j1, Joueur j2){
        return estPosable(carte, 'v', j1, j1) || (estPosable(carte, '^', j1, j1))
                || estPosable(carte, 'v', j1, j2) || estPosable(carte, '^', j1, j2);
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
    public static void regleDePioche(boolean jouerAd, Joueur j){
        if(jouerAd){                //pioche jusqu'à que sa main soit pleine
            while(!j.jeuEstPlein()){
                j.piocherCarte();
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



