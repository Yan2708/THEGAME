package Test;

import Composantes.Input;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputTest {

    /**
     * Test du décomposeur de ligne.
     * */
    @Test
    public void testDecomposer() {
        String [] s = Input.decomposer("12v 39^ 46v’ 59^");
        assertEquals(s[0], "12v");
        assertEquals(s[1], "39^");
        assertEquals(s[2], "46v’");
        assertEquals(s[3], "59^");
        assertEquals(s.length, 4);
    }

    /**
     * Test la syntaxe d'une entrée de joueur.
     * */
    @Test
    public void testIsSyntaxValid() {
        assertTrue(Input.isSyntaxValid(Input.decomposer("12v 15^")));
        assertTrue(Input.isSyntaxValid(Input.decomposer("12v 15v'")));
        assertTrue(Input.isSyntaxValid(Input.decomposer("12v' 15v' 81v 35v")));
        assertFalse(Input.isSyntaxValid(Input.decomposer("une erreur de saisie")));
        assertFalse(Input.isSyntaxValid(Input.decomposer("uneLonguePhrase")));
        assertFalse(Input.isSyntaxValid(Input.decomposer("12v")));
        assertFalse(Input.isSyntaxValid(Input.decomposer("deux elements")));
        assertFalse(Input.isSyntaxValid(Input.decomposer("12 12v")));
        assertFalse(Input.isSyntaxValid(Input.decomposer("12' 32v")));
        assertFalse(Input.isSyntaxValid(Input.decomposer("")));   // une saisie vide
    }

    /**
     * test la recuperation de la carte d'un coup
     * */
    @Test
    public void testGetCarte() {
        assertEquals(Input.getCarte("55v"), 55);
        assertEquals(Input.getCarte("09^'"), 9);
        assertEquals(Input.getCarte("95v'"), 95);
        assertEquals(Input.getCarte("12^"), 12);
    }

    /**
     * test la recuperation de base d'un coup
     * */
    @Test
    void testGetBase() {
        assertEquals(Input.getBase("55v'"), 'v');
        assertEquals(Input.getBase("55^'"), '^');
        assertEquals(Input.getBase("55^"), '^');
        assertEquals(Input.getBase("55v"), 'v');
    }
}
