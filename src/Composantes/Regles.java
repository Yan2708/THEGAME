package Composantes;
/**
 * Les règles sont un ensemble d'instructions établi pour conditionner le bon déroulement d'un jeu.
 * Ils établissent les facultés et les contraintes qui sont présentées
 * à chaque joueur et doivent être clairement énoncées à chacun d'eux avant le début de la partie.
 * Il s'agit ici de reguler la sémantique des coups de l'uttilisateur.
 *
 * @author      Yannick li
 * @author      Stefan Radovanovic
 * @version     1, 2/27/2021
 * */
public class Regles {


    /**
     * Joue les coups d'un joueur et pioche selon le coup.
     *
     * @param coups         les coups du joueurs, sous la forme d'un tableau de String
     * @param j1            le joueur qui joue les coups.
     * @param j2            le joueur qui recoit (ou non) les coups.
     * */
    public static void jouerCoups(String[] coups,Joueur j1, Joueur j2) {
        boolean coupAdv = false;
        for (String coup: coups) {
            if(isCampEnnemie(coup)) {
                j1.jouerCarte(Input.getCarte(coup), Input.getBase(coup), j2);
                coupAdv = true;
            }
            else j1.jouerCarte(Input.getCarte(coup), Input.getBase(coup));
        }
        regleDePioche(coupAdv, j1); // pioche les cartes selon les coups jouées
    }


    /**
     *  Fait piocher au joueur les cartes selon si il a joué chez l'adversaire ou non.
     *  - 2 cartes si aucun coup n'a été jouée chez l'adversaire
     *  - rempli la main si un coup a été jouée chez l'adversaire
     *
     * @param jouerAd       true si le joueur joue chez l'adversaire
     * @param j             le joueur qui doit piocher
     */
    private static void regleDePioche(boolean jouerAd, Joueur j) {
        if (jouerAd) {                //pioche jusqu'à que sa main soit pleine
            while (!j.jeuEstPlein() && !j.piocheEstVide())
                j.piocherCarte();
        }
        else                 // n'a joué que sur ses bases, pioche 2 cartes
            for (int i = 0; i < 2; i++)
                if (!j.jeuEstPlein() && !j.piocheEstVide())
                    j.piocherCarte();
    }


    /**
     * Vérifie si la carte jouée du joueur est posable sur une base donnée.
     * Conformemant aux regles du jeu, un joueur peux poser sur :
     * - sa base ascendante, si la carte est supérieure ou est egale à la dizaine du dessous.
     * - sa base descendante, si la carte est inférieur ou est egale à la dizaine du dessus.
     * - la base ascendante adverse, si la carte est inférieur.
     * - la base descendante adverse, si la carte est supérieur.
     *
     * @param carte         la carte du joueur
     * @param base          la base choisie ( 'v' pour descendante et '^' pour ascendante)
     * @param poseur        le joueur qui pose sa carte
     * @param receveur      le joueur qui reçoit la carte
     * @return              si la carte est posable ou non
     */
    private static boolean estPosable(int carte, char base , Joueur poseur, Joueur receveur){
        if(!poseur.estDansLeJeu(carte)) //  si la carte fait partie du jeu ou non
            return false;
        if(receveur.equals(poseur)) {   //  si le joueur pose dans son camp
            if( base == 'v' )
                return receveur.getDescendant() > carte || (receveur.getDescendant() + 10 == carte);    //  dizaine au dessus
            else if( base == '^')
                return receveur.getAscendant() < carte || (receveur.getAscendant() - 10) == carte;    //  dizaine en dessous
        }
        else {  // camp adverse
            if( base == 'v')
                return receveur.getDescendant() < carte;
            else if( base == '^')
                return receveur.getAscendant() > carte;
        }
        return false;
    }


    /**
     * Verifie si les coups du joueur sont jouable (la sémantique).
     * /!\ A utiliser en partant du principe que la syntaxe est bonne.
     * /!\ la methode simule et applique les coups à des clones.
     * cad qu'il faut mettre en parametre des clones.
     *
     * @param coups         les coups du joueurs
     * @param j1Bis         le joueur qui joue les coups
     * @param j2Bis         le joueur adverse
     * @return              si les coups sont valides ou non
     * @see                 Joueur#clone()
     * @see                 Input#isSyntaxValid(String[])
     * */
    public static boolean areCoupsValid(String[] coups, Joueur j1Bis, Joueur j2Bis) {

        boolean coupAdv = false; // vérifie si un coups a été joué chez l'adversaire

        for(String coup : coups) {
           int carte = Input.getCarte(coup);
           char base = Input.getBase(coup);

           if(!j1Bis.estDansLeJeu(carte))
               return false;

           Joueur receveur = isCampEnnemie(coup) ? j2Bis : j1Bis; // le joueur qui reçoit la carte

           if(estPosable(carte, base, j1Bis, receveur)) {
               if(isCampEnnemie(coup)) {
                   if(coupAdv)    // il est possible de jouer qu'une fois chez l'adversaire
                       return false;
                   j1Bis.jouerCarte(carte, base, receveur);
                   coupAdv = true;

               }
               else j1Bis.jouerCarte(carte, base);
           } else return false;
        }
        return true;
    }


    /**
     * Methode récursive qui verifie si il y a au moins 2 combinaisons de cartes possible pour un joueur.
     * L'algorithme simule une partie avec les combinaisons courantes (base, carte en main etc).
     * /!\ la methode simule et applique les coups à des clones.
     *
     * @param j1            le joueur qui doit jouer
     * @param j2            le 2ème joueur
     * @param nb            le nombre de coups possibles (deux)
     * @param coupAdv       si l'on a déjà joué dans chez l'adversaire ou non
     * @return              si la partie continue ou non
     * @see                 Joueur#clone()
     * */
    public static boolean partieContinue(Joueur j1, Joueur j2, int nb, boolean coupAdv){
        if(nb>=2)  //  si il y a au moins 2 coups à jouer
            return true;
        for(int carte : j1.jeu){
            // A chaque carte jouée
            Joueur j1Bis = j1.clone();
            Joueur j2Bis = j2.clone();
            //  la vérification commence en jouant les coups possible chez le joueur courant
            if(estPosable(carte, 'v', j1Bis, j1Bis)) {
                    j1Bis.jouerCarte(carte, 'v');
                    nb++;
                if (partieContinue(j1Bis, j2Bis, nb, coupAdv))
                    return true;
            }
            if(estPosable(carte, '^', j1Bis, j1Bis)) {
                j1Bis.jouerCarte(carte, '^');
                nb++;
                if (partieContinue(j1Bis, j2Bis, nb, coupAdv))
                    return true;
            }
            //  verification chez le joueur adverse
            if(!coupAdv){ //  si l'on a pas déjà joué chez l'adversaire
                if(estPosable(carte, 'v', j1Bis, j2Bis)) {
                    j1Bis.jouerCarte(carte, 'v', j2Bis);
                    nb++;
                    coupAdv=true;
                    if (partieContinue(j1Bis, j2Bis, nb, coupAdv))
                        return true;
                    else
                        coupAdv=false;
                        nb--;
                }
                if(estPosable(carte, '^', j1Bis, j2Bis)) {
                    j1Bis.jouerCarte(carte, '^', j2Bis);
                    nb++;
                    coupAdv=true;
                    if (partieContinue(j1Bis, j2Bis, nb, coupAdv))
                        return true;
                    else
                        coupAdv=false;
                        nb--;
                }
            }
        }
        return false;
    }

    /**
     * Verifie si un coup joué est destiné au joueur adverse (à utiliser après estPosable)
     *
     * @return              si le coup est pour le joueur adverse ou non
     * */
    private static boolean isCampEnnemie(String coup) {
        return coup.length() == 4; // si un coup constitue une chaine de 4 caracteres alors il doit etre
                                   // destiné à l'adversaire car il comprend une apostrophe. exemple : 34v' (4 char)
    }


    /**
     *  Verifie si le joueur a posé la totalité de ses cartes.
     *
     * @return              si il n'y a plus de carte à jouer
     * */
    public static boolean partieFinie(Joueur j){
        return (j.jeuEstVide() && j.getNbPioche()==0);
    }

}