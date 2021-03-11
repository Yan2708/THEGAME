package Composantes;


/**
 * Les inputs sont les entrées données à notre programme, celles-ci sont indépendantes du programme.
 * Par conséquent, ces données ne peuvent être traitées par de simples assertions et necessite
 * un traitement particulier quant à leurs traitements.
 * Ici, il s'agit de pouvoir récupérer les inputs de l'utilisateur et pouvoir les manipuler
 * sans affecter son expérience (avec des crashs ou assertions levées par exemple).
 *
 * @author      Yannick li
 * @author      Stefan Radovanovic
 * @version     1, 2/27/2021
 * */
public class Input {


    /**
     * Décompose les différents coups d'un joueur.
     * Cette méthode utilise la méthode Split de la classe String qui permet de séparer
     * une chaîne de caractères en fragment stocké par la suite dans un tableau de String,
     * ici on sépare avec un regex nous permettant d'obtenir séparément chaque coup de l'utilisateur ("\\s+").
     *
     * @param UsersLine     le String à décomposer (l'entrée de l'uttilisateur).
     * @return              un tableau de String contenant tous les coups du joueur
     * @see                 String#split(String)
     */
    public static String[] decomposer(String UsersLine) {
        return UsersLine.split("\\s+");
    }


    /**
     * Vérifie le format d'un coup de l'utilisateur (non la sémantique).
     * Un coup doit avoir pour ses deux premiers caractère des entiers.
     * le troisième caractère peut être "^" ou un "v" pour les bases ascendantes et descendantes
     * enfin, si l'utilisateur joue son coup chez l'adversaire
     * le quatrième caractère doit être "\'" (une apostrophe)
     *
     * @param s             la chaine de caractères à vérifier.
     * @return              le coup est jouable ou non.
     * @see                 Character#isDigit(char)
     * */
    private static boolean inputChecker(String s) {
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
     * Verifie si la syntaxe des coups est correcte.
     * Cette méthode utilise les méthodes précédente pour vérifier l'intégralité des coups de l'utilisateur.
     * Elle verifie egalement s'il y a bien au moins deux coups.
     *
     * @param coups         les coups du utilisateur, stockés dans un tableau de String.
     * @return              les coups ont une syntaxe correcte ou non.
     * */
    public static boolean isSyntaxValid(String[] coups){
        if(coups.length < 2)
            return false;
        for (String coup: coups) {
            if(!Input.inputChecker(coup))
                return false;
        }
        return true;
    }


    /**
     * Retourne la carte qui est joué dans la chaine de caractères.
     * Cette methode est à utiliser qu'après avoir vérifié que le coup est jouable.
     * De plus, elle utilise la méthode substring pour faire une sous chaîne de caractère
     * contenant les caractères allant jusqu'à un certain indice.
     *
     * @param coup          le coup à jouer, sous la forme de String.
     * @return              la valeur de la carte.
     * @see                 String#substring(int)
     * @see                 Integer#parseInt(String)
     */
    public static int getCarte(String coup) {
        return Integer.parseInt(coup.substring(0, 2), 10); //   substring creer une sous chaine de caracteres
                                                                //   parseInt renvoie un entier en base 10 (radix)
    }


    /**
     * Retourne dans quelle base le coup va être joué.
     * Cette méthode est à utiliser qu'après avoir vérifié que le coup est jouable
     *
     * @param coup          le coup à jouer, sous la forme de String.
     * @return              le caractère représentant la base ("^" ou "v").
     */
    public static char getBase(String coup) {
        return coup.charAt(2);
    }

}