package Test;

import Composantes.Scan;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ScanTest {

    //  Test du décomposeur de ligne
    @Test
    public void testDecomposer() {
        System.out.println(Arrays.toString(Scan.decomposer("12v 39^ 46v’ 59^")));
    }

    //  Test si les entrées sont valides ou non
    @Test
    public void testInputChecker() {
        assertFalse(Scan.inputChecker("jqhlsdgf"));
        assertFalse(Scan.inputChecker(""));
        assertFalse(Scan.inputChecker("1"));
        assertFalse(Scan.inputChecker("21"));
        assertFalse(Scan.inputChecker("ds5"));
        assertTrue(Scan.inputChecker("56v'"));
        assertTrue(Scan.inputChecker("21v"));
        assertTrue(Scan.inputChecker("21^"));
        assertFalse(Scan.inputChecker("21b"));
        assertTrue(Scan.inputChecker("21^'"));
        assertFalse(Scan.inputChecker("21v*"));
        assertFalse(Scan.inputChecker("21b'"));
    }

    //  test les fonctions decomposer et inputChecker
    @Test
    public void testInputEtDecomposer() {
        for (String val : Scan.decomposer("12v 39^ 46v' 59^")) {
            assertTrue(Scan.inputChecker(val));
        }

    }

    //  test la recuperation de la carte d'un coup
    @Test
    public void testGetCarte() {
        assertEquals(Scan.getCarte("55v"), 55);
        assertEquals(Scan.getCarte("09^'"), 9);
        assertEquals(Scan.getCarte("95v'"), 95);
        assertEquals(Scan.getCarte("12^"), 12);
    }

    //  test la recuperation de base d'un coup
    @Test
    void testGetBase() {
        assertEquals(Scan.getBase("55v'"), 'v');
        assertEquals(Scan.getBase("55^'"), '^');
        assertEquals(Scan.getBase("55^"), '^');
        assertEquals(Scan.getBase("55v"), 'v');
    }
}
