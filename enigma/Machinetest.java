package enigma;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;


import java.util.ArrayList;

import java.util.Collection;


import static org.junit.Assert.*;

import static enigma.TestUtils.*;

/** The suite of all JUnit tests for the Machine class.
 *  @author Citlalli Villarreal
 */
public class Machinetest {

    /**
     * Testing time limit.
     */

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    /** Permutations for rotor I. */
    private Permutation permI = new Permutation("(AELTPHQXRU) (BKNW) (CMOY) "
            + "(DFG) (IV) (JZ) (S)", UPPER);
    /** Permutation for rotor II. */
    private Permutation permII = new Permutation("(FIXVYOMW) (CDKLHUP) "
            + "(ESZ) (BJ) (GR) (NT) (A) (Q)", UPPER);
    /** Permutation for rotor III. */
    private Permutation permIII = new Permutation("(ABDHPEJT) (CFLVM"
            + "ZOYQIRWUKXSG) (N)", UPPER);
    /** Permutation for rotor IV. */
    private Permutation permIV = new Permutation("(AEPLIYWCOXMRFZBSTGJ"
            + "QNH) (DV) (KU)", UPPER);
    /** Permutation for rotor V. */
    private Permutation permV = new Permutation("(AVOLDRWFIUQ)"
            + "(BZKSMNHYC) (EGTJPX)", UPPER);
    /** Permutation for rotor VI. */
    private Permutation permVI = new Permutation("(AJQDVLEOZWIYTS)"
            + " (CGMNHFUX) (BPRK)", UPPER);
    /** Permutation for rotor VII. */
    private Permutation permVII = new Permutation("(ANOUPFRIMBZTL"
            + "WKSVEGCJYDHXQ)", UPPER);
    /** Permutation for rotor VIII. */
    private Permutation permVIII = new Permutation("(AFLSETWUNDHOZVICQ)"
            + " (BKJ) (GXY) (MPR)", UPPER);
    /** Permutation for rotor Beta. */
    private Permutation permBeta = new Permutation("(ALBEVFCYODJ"
            + "WUGNMQTZSKPR) (HIX)", UPPER);
    /** Permutation for rotor Gamma. */
    private Permutation permGamma = new Permutation("(AFNIRLBSQWVX"
            + "GUZDKMTPCOYJHE)", UPPER);
    /** Permutation for rotor B. */
    private Permutation permBR = new Permutation("(AE) (BN) (CK) (DQ) (FU) "
            + "(GY) (HW) (IJ) (LO) (MP) (RX) (SZ) (TV)", UPPER);
    /** Permutation for rotor C. */
    private Permutation permCR = new Permutation("(AR) (BD) (CO) (EJ) (FN) (GT)"
            + " (HK) (IV) (LM) (PW) (QZ) (SX) (UY)", UPPER);
    /**Rotor I for machine. */
    private Rotor movingrotorI = new MovingRotor("I", permI, "Q");
    /**Rotor II for machine. */
    private Rotor movingrotorII = new MovingRotor("II", permII, "E");
    /**Rotor III for machine. */
    private Rotor movingrotorIII = new MovingRotor("III", permIII, "V");
    /**Rotor IV for machine. */
    private Rotor movingrotorIV = new MovingRotor("IV", permIV, "J");
    /**Rotor V for machine. */
    private Rotor movingrotorV = new MovingRotor("V", permV, "Z");
    /**Rotor VI for machine. */
    private Rotor movingrotorVI = new MovingRotor("VI", permVI, "ZM");
    /**Rotor VII for machine. */
    private Rotor movingrotorVII = new MovingRotor("VII", permVII, "ZM");
    /**Rotor VIII for machine. */
    private Rotor movingrotorVIII = new MovingRotor("VIII", permVIII, "ZM");
    /**Rotor Beta for machine. */
    private Rotor fixedrotorBeta = new FixedRotor("Beta", permBeta);
    /**Rotor Gamma for machine. */
    private Rotor fixedrotorGamma = new FixedRotor("Gamma", permGamma);
    /**Rotor B for machine. */
    private Rotor reflectorBR = new Reflector("B", permBR);
    /**Rotor C for machine. */
    private Rotor reflectorCR = new Reflector("C", permCR);

    /** TEST for insertRotors. */
    @Test
    public void testinsertrotors() {
        Permutation perm1 = new Permutation("(AELTPHQXRU) (BKNW) (CM"
                + "OY) (DFG) (IV) (JZ) (S)", UPPER);
        Permutation perm2 = new Permutation("(FIXVYOMW) (CDKLHUP) (ESZ)"
                + " (BJ) (GR) (NT) (A) (Q)", UPPER);
        Permutation perm3 = new Permutation("(ABDHPEJT) (CFLVMZOY"
                + "QIRWUKXSG) (N)", UPPER);
        Permutation perm4 = new Permutation("(AEPLIYWCOXMRFZBST"
                + "GJQNH) (DV) (KU)", UPPER);
        Permutation permB = new Permutation("(ALBEVFCYODJWUGN"
                + "MQTZSKPR) (HIX)", UPPER);
        Permutation permG = new Permutation("(AFNIRLBSQWVXGUZ"
                + "DKMTPCOYJHE)", UPPER);
        Permutation permBR2 = new Permutation("(AE) (BN) (CK) "
                + "(DQ) (FU) (GY) (HW)"
                + " (IJ) (LO) (MP) (RX) (SZ) (TV)", UPPER);
        Collection<Rotor> allrotors = new ArrayList<>();
        Rotor movingrotor1 = new MovingRotor("I", perm1, "Q");
        Rotor movingrotor2 = new MovingRotor("II", perm2, "E");
        Rotor movingrotor3 = new MovingRotor("III", perm3, "V");
        Rotor movingrotor4 = new MovingRotor("IV", perm4, "J");
        Rotor fixedrotorB = new FixedRotor("Beta", permB);
        Rotor fixedrotorG = new FixedRotor("Gamma", permG);
        Rotor reflectorB = new Reflector("B", permBR2);
        allrotors.add(movingrotor1);
        allrotors.add(movingrotor2);
        allrotors.add(movingrotor3);
        allrotors.add(movingrotor4);
        allrotors.add(fixedrotorB);
        allrotors.add(fixedrotorG);
        allrotors.add(reflectorB);

        Machine machine1 = new Machine(UPPER, 4, 2, allrotors);

        String[] rotornames = new String[]{"Rotor B", "Rotor Beta",
                                           "Rotor V", "Rotor III"};
        machine1.insertRotors(rotornames);

        ArrayList<Rotor> insertRotorstest = new ArrayList<Rotor>();
        insertRotorstest.add(reflectorB);
        insertRotorstest.add(fixedrotorB);
        insertRotorstest.add(movingrotor3);

        assertEquals(insertRotorstest, machine1.insertrotors());

    }

    /** TEST for setRotors. */
    @Test
    public void testsetrotors() {
        Permutation perm1 = new Permutation("(AELTPHQXRU) (BKNW) (CMOY) "
                + "(DFG) (IV) (JZ) (S)", UPPER);
        Permutation perm2 = new Permutation("(FIXVYOMW) (CDKLHUP) (ESZ)"
                + " (BJ) (GR) (NT) (A) (Q)", UPPER);
        Permutation perm3 = new Permutation("(ABDHPEJT) (CFLVMZ"
                + "OYQIRWUKXSG) (N)", UPPER);
        Permutation perm4 = new Permutation("(AEPLIYWCOXMR"
                + "FZBSTGJQNH) (DV) (KU)", UPPER);
        Permutation permB = new Permutation("(ALBEVFCYODJWUG"
                + "NMQTZSKPR) (HIX)", UPPER);
        Permutation permG = new Permutation("(AFNIRLBSQWVXGU"
                + "ZDKMTPCOYJHE)", UPPER);
        Permutation permBRR = new Permutation("(AE) (BN) (CK) (DQ) (FU) (GY) "
                + "(HW) (IJ) (LO) (MP) (RX) (SZ) (TV)", UPPER);
        Collection<Rotor> allrotors = new ArrayList<>();
        Rotor movingrotor1 = new MovingRotor("I", perm1, "Q");
        Rotor movingrotor2 = new MovingRotor("II", perm2, "E");
        Rotor movingrotor3 = new MovingRotor("III", perm3, "V");
        Rotor movingrotor4 = new MovingRotor("IV", perm4, "J");
        Rotor fixedrotorB = new FixedRotor("Beta", permB);
        Rotor fixedrotorG = new FixedRotor("Gamma", permG);
        Rotor reflectorB = new Reflector("B", permBRR);
        allrotors.add(movingrotor1);
        allrotors.add(movingrotor2);
        allrotors.add(movingrotor3);
        allrotors.add(movingrotor4);
        allrotors.add(fixedrotorB);
        allrotors.add(fixedrotorG);
        allrotors.add(reflectorB);

        Machine machine1 = new Machine(UPPER, 4, 2, allrotors);

        String[] rotornames = new String[]{"Rotor B", "Rotor Beta",
                                           "Rotor V", "Rotor III"};
        machine1.insertRotors(rotornames);
        machine1.setRotors("KL");

        assertEquals(0, machine1.insertrotors().get(0).setting());
        assertEquals(10, machine1.insertrotors().get(1).setting());
        assertEquals(11, machine1.insertrotors().get(2).setting());
    }

    /** TEST for convert. */
    @Test
    public void testconvert() {
        Collection<Rotor> allrotors = new ArrayList<>();
        allrotors.add(movingrotorI); allrotors.add(movingrotorII);
        allrotors.add(movingrotorIII); allrotors.add(movingrotorIV);
        allrotors.add(movingrotorV); allrotors.add(movingrotorVI);
        allrotors.add(movingrotorVII); allrotors.add(movingrotorVIII);
        allrotors.add(fixedrotorBeta); allrotors.add(fixedrotorGamma);
        allrotors.add(reflectorBR); allrotors.add(reflectorCR);
        Machine machine1 = new Machine(UPPER, 5, 3, allrotors);
        String[] rotornames = new String[]{"Rotor B", "Rotor Beta",
                                           "Rotor III", "Rotor IV",
                                           "Rotor I"};
        machine1.insertRotors(rotornames); machine1.setRotors("AXLE");
        Permutation plugb = new Permutation("(YF) (ZH)", UPPER);
        machine1.setPlugboard(plugb);
        assertEquals(UPPER.toInt('Z'), machine1.convert(UPPER.toInt('Y')));
        machine1.setRotors("AXLE");
        Permutation plugb2 = new Permutation("(HQ) (EX) (IP) (TR) (BY)", UPPER);
        machine1.setPlugboard(plugb2);
        assertEquals(UPPER.toInt(UPPER.toChar(16)),
                machine1.convert(UPPER.toInt(UPPER.toChar(5))));
        assertEquals(UPPER.toInt('V'),
                machine1.convert(UPPER.toInt('R')));
        assertEquals(UPPER.toInt(UPPER.toChar(15)),
                machine1.convert(UPPER.toInt(UPPER.toChar(14))));
        assertEquals(UPPER.toInt(UPPER.toChar(16)),
                machine1.convert(UPPER.toInt(UPPER.toChar(12))));
        assertEquals(UPPER.toInt('S'), machine1.convert(UPPER.toInt('H')));
        assertEquals(UPPER.toInt(UPPER.toChar(14)),
                machine1.convert(UPPER.toInt(UPPER.toChar(8))));
        assertEquals(UPPER.toInt('K'), machine1.convert(UPPER.toInt('S')));
        assertEquals(UPPER.toInt('O'), machine1.convert(UPPER.toInt('S')));
        assertEquals(UPPER.toInt(UPPER.toChar(8)),
                machine1.convert(UPPER.toInt(UPPER.toChar(7))));
        assertEquals(UPPER.toInt(UPPER.toChar(11)),
                machine1.convert(UPPER.toInt(UPPER.toChar(14))));
        assertEquals(UPPER.toInt('P'), machine1.convert(UPPER.toInt('U')));
        assertEquals(UPPER.toInt('U'), machine1.convert(UPPER.toInt('L')));
        assertEquals(UPPER.toInt(UPPER.toChar(1)),
                machine1.convert(UPPER.toInt(UPPER.toChar(3))));
        assertEquals(UPPER.toInt(UPPER.toChar(10)),
                machine1.convert(UPPER.toInt(UPPER.toChar(4))));
        assertEquals(UPPER.toInt('J'), machine1.convert(UPPER.toInt('R')));
        assertEquals(UPPER.toInt('Z'), machine1.convert(UPPER.toInt('H')));
        assertEquals(UPPER.toInt(UPPER.toChar(15)),
                machine1.convert(UPPER.toInt(UPPER.toChar(8))));
        assertEquals(UPPER.toInt(UPPER.toChar(8)),
                machine1.convert(UPPER.toInt(UPPER.toChar(0))));
        assertEquals(UPPER.toInt('S'), machine1.convert(UPPER.toInt('W')));
        assertEquals(UPPER.toInt(UPPER.toChar(5)),
                machine1.convert(UPPER.toInt(UPPER.toChar(0))));
        assertEquals(UPPER.toInt('X'), machine1.convert(UPPER.toInt('T')));
        assertEquals(UPPER.toInt(UPPER.toChar(3)),
                machine1.convert(UPPER.toInt(UPPER.toChar(7))));
        assertEquals(UPPER.toInt('W'), machine1.convert(UPPER.toInt('A')));
        assertEquals(UPPER.toInt('B'), machine1.convert(UPPER.toInt('T')));
        assertEquals(UPPER.toInt(UPPER.toChar(7)),
                machine1.convert(UPPER.toInt(UPPER.toChar(14))));
    }
    /** TEST for convert. */
    @Test
    public void testconvertmsg() {
        Collection<Rotor> allrotors = new ArrayList<>();
        allrotors.add(movingrotorI);
        allrotors.add(movingrotorII);
        allrotors.add(movingrotorIII);
        allrotors.add(movingrotorIV);
        allrotors.add(movingrotorV);
        allrotors.add(movingrotorVI);
        allrotors.add(movingrotorVII);
        allrotors.add(movingrotorVIII);
        allrotors.add(fixedrotorBeta);
        allrotors.add(fixedrotorGamma);
        allrotors.add(reflectorBR);
        allrotors.add(reflectorCR);
        Machine machine1 = new Machine(UPPER, 5, 3, allrotors);
        String [] rotornames = new String [] {"Rotor B", "Rotor Beta",
                                              "Rotor III", "Rotor IV",
                                              "Rotor I"};
        machine1.insertRotors(rotornames);
        Permutation plugb2 = new Permutation("(HQ) (EX) (IP) (TR) (BY)", UPPER);
        machine1.setRotors("AXLE");
        machine1.setPlugboard(plugb2);
        assertEquals("QVPQS OKOIL PUBKJ ZPISF XDW",
                machine1.convert("FROM HIS SHOULDER HIAWATHA"));
        assertEquals("BHCNS CXNUO AATZX SRCFY DGU",
                machine1.convert("TOOK THE CAMERA OF ROSEWOOD"));
        assertEquals("FLPNX GXIXT YJUJR CAUGE UNCFM KUF",
                machine1.convert("MADE OF SLIDING FOLDING ROSEWOOD"));
        assertEquals("WJFGK CIIRG XODJG VCGPQ OH",
                machine1.convert("NEATLY PUT IT ALL TOGETHER"));
        assertEquals("ALWEB UHTZM OXIIV XUEFP RPR",
                machine1.convert("IN ITS CASE IT LAY COMPACTLY"));
        assertEquals("KCGVP FPYKI KITLB URVGT SFU",
                machine1.convert("FOLDED INTO NEARLY NOTHING"));
        assertEquals("SMBNK FRIIM PDOFJ VTTUG RZM",
                machine1.convert("BUT HE OPENED OUT THE HINGES"));
        assertEquals("UVCYL FDZPG IBXRE WXUEB ZQJO",
                machine1.convert("PUSHED AND PULLED THE JOINTS"));
        assertEquals("YMHIP GRRE",
                machine1.convert("AND HINGES"));
        Machine machine3 = new Machine(UPPER, 5, 3, allrotors);
        String [] rotornames3 = new String [] {"Rotor B", "Rotor Beta",
                                               "Rotor I", "Rotor II",
                                               "Rotor III"};
        machine3.insertRotors(rotornames3);
        Permutation plugb4 = new Permutation("(TD) (KC) (JZ)", UPPER);
        machine3.setRotors("AAAA"); machine3.setPlugboard(plugb4);
        assertEquals("HGJNB OKDWA LBFKU CMUTJ ZUIO",
                machine3.convert("I WAS SCARED OF CODING IN JAVA"));
        assertEquals("XTYQF BDZRG BYFZC ASYRU",
                machine3.convert("I WAS SCARED OF USING GIT"));
        assertEquals("UAAFW OAGFK OCJGM UMOPC HTAVR SA",
                machine3.convert("AND STARTING ALL THESE PROJECTS"));
        assertEquals("HXHFR UXOFC BLRYS DXFCZ XGVFA NA",
                machine3.convert("COMPILER KEEPS GETTING MAD AT ME"));
        assertEquals("CNBZH SNQMC MNIRW MTTTQ BRNKR XDRPN",
                machine3.convert("NOW MY PROJECT ONLY RUNS IN MY DREAMS"));
        assertEquals("AJIRV IFOVC TKGNU CKUMB ITFEN V",
                machine3.convert("OH OH ALL THESE MERGE CONFLICTS"));
    }
}
