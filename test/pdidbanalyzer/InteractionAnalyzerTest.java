/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import javax.vecmath.Point3d;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openscience.cdk.Atom;
import static org.junit.Assert.*;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IPDBPolymer;
import pdidbanalyzer.IAtomType;
import pdidbanalyzer.cdk.PDBPolymer;

/**
 *
 * @author archvile18
 */
public class InteractionAnalyzerTest {

    public InteractionAnalyzerTest() {
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

    /**
     * Test of setDistanceCutoff method, of class InteractionAnalyzer.
     */
    @Test
    public void testSetDistanceCutoff() {
        System.out.println("setDistanceCutoff");
        double distanceCutoff = 5.0;
        InteractionAnalyzer instance = new InteractionAnalyzer();
        double expResult = 5.0;
        instance.setDistanceCutoff(distanceCutoff);
        double result = instance.getDistanceCutoff();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDistanceCutoff method, of class InteractionAnalyzer.
     */
    @Test
    public void testGetDistanceCutoff() {
        System.out.println("getDistanceCutoff");
        double distanceCutoff = 5.0;
        InteractionAnalyzer instance = new InteractionAnalyzer();
        double expResult = 5.0;
        instance.setDistanceCutoff(distanceCutoff);
        double result = instance.getDistanceCutoff();
        assertEquals(expResult, result, 0.0);

        // Test default value
        instance = new InteractionAnalyzer();
        expResult = 7.0;
        result = instance.getDistanceCutoff();
        assertEquals(expResult, result, 0.0);
    
    }

    /**
     * Test of setOmega method, of class InteractionAnalyzer.
     */
    @Test
    public void testSetOmega() {
        System.out.println("setOmega");
        double omega = Math.PI / 3.0;
        InteractionAnalyzer instance = new InteractionAnalyzer();
        double expResult = Math.PI / 3.0;
        instance.setOmega(omega);
        double result = instance.getOmega();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getOmega method, of class InteractionAnalyzer.
     */
    @Test
    public void testGetOmega() {
        System.out.println("getOmega");
        double omega = Math.PI / 3.0;
        InteractionAnalyzer instance = new InteractionAnalyzer();
        double expResult = Math.PI / 3.0;
        instance.setOmega(omega);
        double result = instance.getOmega();
        assertEquals(expResult, result, 0.0);

        // Test default omega
        instance = new InteractionAnalyzer();
        expResult = Math.PI / 2.0;
        result = instance.getOmega();
        assertEquals(expResult, result, 0.0);
    
    }

    /**
     * Test of setPdbPolymer method, of class InteractionAnalyzer.
     */
    @Test
    public void testSetPdbPolymer() {
        System.out.println("setPdbPolymer");
        IPDBPolymer pdbPolymer = new PDBPolymer();
        InteractionAnalyzer instance = new InteractionAnalyzer();
        IPDBPolymer expResult = pdbPolymer;
        instance.setPdbPolymer(pdbPolymer);
        IPDBPolymer result = instance.getPdbPolymer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPdbPolymer method, of class InteractionAnalyzer.
     */
    @Test
    public void testGetPdbPolymer() {
        System.out.println("getPdbPolymer");
        IPDBPolymer pdbPolymer = new PDBPolymer();
        InteractionAnalyzer instance = new InteractionAnalyzer();
        IPDBPolymer expResult = pdbPolymer;
        instance.setPdbPolymer(pdbPolymer);
        IPDBPolymer result = instance.getPdbPolymer();
        assertEquals(expResult, result);

        // Test default value
        instance = new InteractionAnalyzer();
        result = instance.getPdbPolymer();
        assertNull(result);
    }

    /**
     * Test of run method, of class InteractionAnalyzer.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        IAtom atom1 = new Atom("C", new Point3d(0.0, 0.0, 0.0));
        IAtom atom2 = new Atom("C", new Point3d(1.0, 0.0, 0.0));
        IAtom atom3 = new Atom("C", new Point3d(0.0, 2.0, 0.0));
        atom1.setProperty(IAtomType.class, PDIdbProteinAtomType.ARG_CD);
        atom2.setProperty(IAtomType.class, PDIdbProteinAtomType.ARG_CD);
        atom3.setProperty(IAtomType.class, PDIdbDNAAtomType.AC2);
        IPDBPolymer polymer = new PDBPolymer();
        polymer.addAtom(atom1);
        polymer.addAtom(atom2);
        polymer.addAtom(atom3);
        InteractionAnalyzer instance = new InteractionAnalyzer().setPdbPolymer(polymer);
        instance.run();

        atom1 = new Atom("C", new Point3d(0.0, 0.0, 0.0));
        atom2 = new Atom("C", new Point3d(0.1, 0.5, 0.0));
        atom3 = new Atom("C", new Point3d(0.0, 1.0, 0.0));
        atom1.setProperty(IAtomType.class, PDIdbProteinAtomType.ARG_CD);
        atom2.setProperty(IAtomType.class, PDIdbProteinAtomType.ARG_CD);
        atom3.setProperty(IAtomType.class, PDIdbDNAAtomType.AC2);
        polymer = new PDBPolymer();
        polymer.addAtom(atom1);
        polymer.addAtom(atom2);
        polymer.addAtom(atom3);
        instance = new InteractionAnalyzer().setPdbPolymer(polymer);
        instance.run();
    
    }

    /**
     * Test of getAngle method, of class InteractionAnalyzer.
     */
    @Test
    public void testGetAngle() {
        System.out.println("getAngle");
        IAtom atom1 = new Atom("C", new Point3d(0.0, 0.0, 0.0));
        IAtom atom2 = new Atom("C", new Point3d(1.0, 0.0, 0.0));
        IAtom atom3 = new Atom("C", new Point3d(0.0, 1.0, 0.0));
        double expResult = Math.PI / 2.0;
        double result = InteractionAnalyzer.getAngle(atom1, atom2, atom3);
        assertEquals(expResult, result, 1E-10);
    }

}