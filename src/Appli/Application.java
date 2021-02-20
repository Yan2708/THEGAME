package Appli;

import Composantes.Joueur;
import Composantes.Scan;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");
        System.out.println(NORD.toString() + "\n" + NORD.afficherJeu());
        NORD.poserCarte( 50 , SUD);
        System.out.println(SUD.toString());

        String[] s = Scan.decomposer(Scan.getUsersLine());
        for (String val: s) {

        }

    }
}
