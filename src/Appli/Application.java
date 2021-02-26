package Appli;

import Composantes.Joueur;
import Composantes.Regles;
import Composantes.Scan;

public class Application {

    /**
     * Affiche les informations des joueurs NORD et SUD ainsi que la main du joueur courant.
     *
     * @param NORD
     *                  le joueur NORD
     *
     * @param SUD
     *                  le joueur SUD
     *
     * @param courant
     *                  le joueur courant
     * */
    private static void showGame(Joueur NORD, Joueur SUD, Joueur courant) {
        System.out.println(NORD);
        System.out.println(SUD);
        System.out.println(courant.afficherJeu());
    }


    public static void main(String[] args) {
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");
        Joueur courant = NORD;
        Joueur passif = SUD;

        while(Regles.partieContinue(courant.clone(), passif.clone(), 0,false)) {

            showGame(NORD, SUD, courant);

            String[] coups = Scan.decomposer(Scan.getUsersLine());

            while(!(Scan.isSyntaxValid(coups)
                    && Regles.areCoupsValid(coups, courant.clone(), passif.clone()))){

                System.out.print("#");
                coups = Scan.decomposer(Scan.getUsersLine());
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

        System.out.println("partie finie, " + passif.nom + " a gagné");
    }
}