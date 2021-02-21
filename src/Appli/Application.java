package Appli;

import Composantes.Joueur;
import Composantes.Scan;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");
        Joueur jouant = NORD;
        Joueur passif = SUD;
        boolean estFini = true; // /!\ faire une fonction qui detect la fin de la partie
        while(estFini){
            NORD.toString();
            SUD.toString();
            String[] coups = Scan.decomposer(Scan.getUsersLine());
            /*while(!Regles.isCoupValid(coups, j1, j2));
                System.out.println("#> ");
            Regles.jouerCoups(NORD, SUD);*/
        }

    }
}
