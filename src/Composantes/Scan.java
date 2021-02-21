package Composantes;

import java.util.Scanner;

public class Scan {

    /**
     * récupère l'entrée de l'uttilisateur, son coup.
     *
     * @return le coup du joueur
     *
     * */
    public static String getUsersLine() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        String s;
        System.out.print("> ");
        s = sc.nextLine();
        return s;

    }

    /**
     * Décompose les différents coups d'un joueur.
     *
     * @param UsersLine
     *                  la ligne à décomposer
     *
     * @return un tableau de String contenant tous les coups du joueur
     */
    public static String[] decomposer(String UsersLine) {
        return UsersLine.split("\\s+");
    }

    /**
     * Vérifie le format des coups de l'utilisateur (non la cohérence).
     *
     * @param s
     *              le coup à vérifier
     *
     * @return le coup est jouable ou non.
     * */
    public static boolean inputChecker(String s) {
        if(!(s.length()==4 || s.length()==3))
            return false;
        if(!(Character.isDigit(s.charAt(0)) && Character.isDigit(s.charAt(1))))
            return false;
        if(!(s.charAt(2)=='^' || s.charAt(2) == 'v'))
            return false;
        if(s.length()==4)
            return s.charAt(3) == '\'';
        return true;
    }

    /**
     *  retourne la carte qui est joué par ce coup
     *  (A utiliser qu'après avoir vérifié que le coup est jouable)
     *
     * @param coup
     *              le coup à jouer
     *
     * @return valeur de la carte
     */
    public static int getCarte(String coup) {
        return Integer.parseInt("" + coup.charAt(0)) * 10 + Integer.parseInt("" + coup.charAt(1));
    }

    /**
     *  retourne dans quelle base le coup va être joué
     *  (A utiliser qu'après avoir vérifié que le coup est jouable)
     *
     * @param coup
     *              le coup à jouer
     *
     * @return le caractère représentant la base
     */
    public static char getBase(String coup) {
        return coup.charAt(2);
    }

}
