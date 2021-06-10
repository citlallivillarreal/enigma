package enigma;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;


import static enigma.TestUtils.*;

/** The suite of all JUnit tests for the Rotor class.
 *  @author Citlalli Villarreal
 */

public class RotorTest {

    /** Testing time limit. */
    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    /* ***** TESTING UTILITIES ***** */

    private Rotor rotor;
    private String alpha = UPPER_STRING;
    private Permutation perm;


    @Test
    public void settingtest() {
        perm = new Permutation("(ASFG) (OPI) (WRT) (E) (V)", UPPER);
        rotor = new Rotor("Rotor I", perm);
        assertEquals(0, rotor.setting());
        perm = new Permutation("(QRIPWLEVJOS) (ZXMH) ", UPPER);
        rotor = new Rotor("Rotor II", perm);
        assertEquals(0, rotor.setting());
        perm = new Permutation("(V) (A) (I) (T) (E) ", UPPER);
        rotor = new Rotor("Rotor III", perm);
        assertEquals(0, rotor.setting());
    }
    @Test
    public void setntest() {
        perm = new Permutation("(ASFG) (OPI) (WRT) (E) (V)", UPPER);
        rotor = new Rotor("Rotor I", perm);
        rotor.set(5);
        assertEquals(5, rotor.setting());
        perm = new Permutation("(QRIPWLEVJOS) (ZXMH) ", UPPER);
        rotor = new Rotor("Rotor II", perm);
        rotor.set(22);
        assertEquals(22, rotor.setting());
    }
    @Test
    public void setctest() {
        perm = new Permutation("(ASFG) (OPI) (WRT) (E) (V)", UPPER);
        rotor = new Rotor("Rotor I", perm);
        rotor.set('F');
        assertEquals(5, rotor.setting());
        perm = new Permutation("(QRIPWLEVJOS) (ZXMH) ", UPPER);
        rotor = new Rotor("Rotor II", perm);
        rotor.set('Z');
        assertEquals(25, rotor.setting());
    }
    @Test
    public void convertforwardtest() {
        perm = new Permutation("(ASFG) (OPI) (WRT) (E) (V)", UPPER);
        rotor = new Rotor("Rotor I", perm);
        rotor.set('G');
        assertEquals(25, rotor.convertForward(12));
        assertEquals(15, rotor.convertForward(15));
        perm = new Permutation("(QRIPWLEVJOS) (ZXMH) ", UPPER);
        rotor = new Rotor("Rotor II", perm);
        rotor.set('G');
        assertEquals(24, rotor.convertForward(5));
    }
    @Test
    public void convertbackwardtest() {
        perm = new Permutation("(ASFG) (OPI) (WRT) (E) (V)", UPPER);
        rotor = new Rotor("Rotor I", perm);
        rotor.set('G');
        assertEquals(20, rotor.convertBackward(12));
        assertEquals(15, rotor.convertBackward(15));
        perm = new Permutation("(QRIPWLEVJOS) (ZXMH) ", UPPER);
        rotor = new Rotor("Rotor II", perm);
        rotor.set('G');
        assertEquals(16, rotor.convertBackward(5));
    }




}
