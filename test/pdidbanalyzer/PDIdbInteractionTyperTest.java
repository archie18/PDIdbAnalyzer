/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IPDBAtom;
import org.openscience.cdk.protein.data.PDBAtom;

/**
 *
 * @author archvile18
 */
public class PDIdbInteractionTyperTest {

    public PDIdbInteractionTyperTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    IPDBAtom setupAtom(IAtomType atomType) {
        IPDBAtom atom = new PDBAtom("Du");
        atom.setProperty(IAtomType.class, atomType);
        return atom;
    }
    
    IPDBAtom setupAtom(IAtomType atomType, String symbol, String name) {
        IPDBAtom atom = new PDBAtom(symbol);
        atom.setName(name);
        atom.setProperty(IAtomType.class, atomType);
        return atom;
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PSC: NA-ND (type 1).
     */
    @Test
    public void testGetInteractionType1() {
        System.out.println("getInteractionType1");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.TRP_N);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PSC_NA_ND;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_NA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_NA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NE);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_NA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NH);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_NA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_NE2);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_NA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PSC: NA-OD (type 2).
     */
    @Test
    public void testGetInteractionType2() {
        System.out.println("getInteractionType2");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PSC_NA_OD;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_NA_OD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PSC: OA-ND (type 3).
     */
    @Test
    public void testGetInteractionType3() {
        System.out.println("getInteractionType3");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.TRP_N);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PSC_OA_ND;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NE);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NH);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_NE2);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PSC: OA-OD (type 4).
     */
    @Test
    public void testGetInteractionType4() {
        System.out.println("getInteractionType4");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PSC_OA_OD;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_OA_OD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PSC: ND-OA (type 5).
     */
    @Test
    public void testGetInteractionType5() {
        System.out.println("getInteractionType5");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.PrmN);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PSC_ND_OA;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PrmN);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_ND_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PrmN);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_ND_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PrmN);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_ND_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AmideN);
        atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_ND_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AmideN);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_ND_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AmideN);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_ND_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AmideN);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_ND_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PBB: NA-ND (type 6).
     */
    @Test
    public void testGetInteractionType6() {
        System.out.println("getInteractionType6");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.N);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PBB_NA_ND;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_NA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_N, "N", "N"); // N-terminus
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_NA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PBB: ND-OA (type 7).
     */
    @Test
    public void testGetInteractionType7() {
        System.out.println("getInteractionType7");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.PrmN);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.O);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PBB_ND_OA;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PrmN);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O, "O", "OXT");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_ND_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AmideN);
        atom2 = setupAtom(PDIdbProteinAtomType.O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_ND_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AmideN);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O, "O", "OXT");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_ND_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PBB: OA-ND (type 8).
     */
    @Test
    public void testGetInteractionType8() {
        System.out.println("getInteractionType8");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.N);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PBB_OA_ND;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_N, "N", "N"); // N-terminus
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBB-PSC: OA-ND (type 9).
     */
    @Test
    public void testGetInteractionType9() {
        System.out.println("getInteractionType9");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.TRP_N);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_NE2);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NE);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NH);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_NE2);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NE);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NH);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_NE2);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NE);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NH);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_NE2);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBB-PSC: OA-OD (type 10).
     */
    @Test
    public void testGetInteractionType10() {
        System.out.println("getInteractionType10");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBB_PSC_OA_OD;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_OD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_OD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_OD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_OD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_OD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_OD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_OD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBB-PBB: OA-ND (type 11).
     */
    @Test
    public void testGetInteractionType11() {
        System.out.println("getInteractionType11");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.N);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBB_PBB_OA_ND;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_N, "N", "N"); // N-terminus
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_N, "N", "N"); // N-terminus
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_N);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_N, "N", "N"); // N-terminus
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PBB_OA_ND;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBB-PSC: OA-SD (type 12).
     */
    @Test
    public void testGetInteractionType12() {
        System.out.println("getInteractionType12");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.CYS_S);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBB_PSC_OA_SD;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3PrO5Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.CYS_S);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_SD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O3Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.CYS_S);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_SD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.O4Pr);
        atom2 = setupAtom(PDIdbProteinAtomType.CYS_S);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBB_PSC_OA_SD;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PSC: NA-SD (type 13).
     */
    @Test
    public void testGetInteractionType13() {
        System.out.println("getInteractionType13");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.Imine);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.CYS_S);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PSC_NA_SD;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PSC: OA-SD (type 14).
     */
    @Test
    public void testGetInteractionType14() {
        System.out.println("getInteractionType14");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.CarbonylO);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.CYS_S);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PSC_OA_SD;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PSC: ND-SA (type 15).
     */
    @Test
    public void testGetInteractionType15() {
        System.out.println("getInteractionType15");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.PrmN);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.CYS_S);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PSC_ND_SA;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PrmN);
        atom2 = setupAtom(PDIdbProteinAtomType.MET_S);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_ND_SA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AmideN);
        atom2 = setupAtom(PDIdbProteinAtomType.CYS_S);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_ND_SA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AmideN);
        atom2 = setupAtom(PDIdbProteinAtomType.MET_S);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_ND_SA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PSC: CD-OA (type 16).
     */
    @Test
    public void testGetInteractionType16() {
        System.out.println("getInteractionType16");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.C8);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2);
        atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5);
        atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
        
        atom1 = setupAtom(PDIdbDNAAtomType.PyC6);
        atom2 = setupAtom(PDIdbProteinAtomType.SER_THR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6);
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6);
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PSC_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type DBE-PBB: CD-OA (type 17).
     */
    @Test
    public void testGetInteractionType17() {
        System.out.println("getInteractionType17");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.C8);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.O);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.DBE_PBB_CD_OA;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O, "O", "OXT");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2);
        atom2 = setupAtom(PDIdbProteinAtomType.O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O, "O", "OXT");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5);
        atom2 = setupAtom(PDIdbProteinAtomType.O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O, "O", "OXT");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6);
        atom2 = setupAtom(PDIdbProteinAtomType.O);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6);
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_O, "O", "OXT");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.DBE_PBB_CD_OA;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type Ionic bond: (-)...(+) (type 18).
     */
    @Test
    public void testGetInteractionType18() {
        System.out.println("getInteractionType18");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.LYS_N);
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.ION;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NE);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.ION;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_NH);
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.ION;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.OP1OP2OP3);
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_N, "N", "N"); // N-terminus
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.ION;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionType method, of class PDIdbInteractionTyper for
     * type C-C: hydrophobic (type 19).
     */
    @Test
    public void testGetInteractionType19() {
        System.out.println("getInteractionType19");

        IAtom atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        IAtom atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        PDIdbInteractionTyper instance = new PDIdbInteractionTyper();
        PDIdbInteractionType expResult = PDIdbInteractionType.HPH;
        PDIdbInteractionType result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C5Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C3PrC4Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C2Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C1Pr, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.C8, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PuC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.AC6CC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.GC6TC4, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC2, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.CC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.PyC6, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC5, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);


        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.GLY_CA, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH0_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.CH_SP2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_CE2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.SER_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.THR_CB, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CG, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TRP_HIS_CH, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.HIS_CE1, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASP_GLU_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_CYS_CH2, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.MET_METHYL, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.TYR_CZ, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.PRO_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ASN_GLN_C, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.LYS_CE, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);

        atom1 = setupAtom(PDIdbDNAAtomType.TC7, "C", "fubar");
        atom2 = setupAtom(PDIdbProteinAtomType.ARG_CD, "C", "fubar");
        instance = new PDIdbInteractionTyper();
        expResult = PDIdbInteractionType.HPH;
        result = instance.getInteractionType(atom1, atom2);
        assertEquals(expResult, result);
    }
}