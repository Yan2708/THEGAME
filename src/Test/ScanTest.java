package Test;

import Composantes.Scan;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ScanTest {

    //  Test du décomposeur de ligne
    @Test
    void decomposer() {
            System.out.println(Arrays.toString(Scan.decomposer("12v 39^ 46v’ 59^")));
    }

    //  Test si les entrées sont valides ou non
    @Test
    void inputChecker() {
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
}