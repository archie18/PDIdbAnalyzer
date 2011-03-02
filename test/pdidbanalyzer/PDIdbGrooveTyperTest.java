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
import org.openscience.cdk.interfaces.IPDBAtom;
import org.openscience.cdk.interfaces.IPDBMonomer;
import org.openscience.cdk.interfaces.IPDBPolymer;
import org.openscience.cdk.interfaces.IStrand;
import org.openscience.cdk.protein.data.PDBAtom;
import org.openscience.cdk.protein.data.PDBMonomer;
import pdidbanalyzer.cdk.PDBPolymer;
import pdidbanalyzer.cdk.PDBStrand;

/**
 *
 * @author andreas
 */
public class PDIdbGrooveTyperTest {
    
    private IPDBAtom atom;
    private IPDBMonomer residue;
    private IStrand chain;
    private IPDBPolymer pdbPolymer;
    private PDIdbGrooveTyper grooveTyper;

    public PDIdbGrooveTyperTest() {
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
     * Test of setPdbPolymer method, of class PDIdbGrooveTyper.
     */
    @Test
    public void testSetPdbPolymer() {
        System.out.println("setPdbPolymer");
        IPDBPolymer pdbPolymer = new PDBPolymer();
        PDIdbGrooveTyper instance = new PDIdbGrooveTyper();
        IPDBPolymer expResult = pdbPolymer;
        instance.setPdbPolymer(pdbPolymer);
        IPDBPolymer result = instance.getPdbPolymer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPdbPolymer method, of class PDIdbGrooveTyper.
     */
    @Test
    public void testGetPdbPolymer() {
        System.out.println("getPdbPolymer");
        IPDBPolymer pdbPolymer = new PDBPolymer();
        PDIdbGrooveTyper instance = new PDIdbGrooveTyper();
        IPDBPolymer expResult = pdbPolymer;
        instance.setPdbPolymer(pdbPolymer);
        IPDBPolymer result = instance.getPdbPolymer();
        assertEquals(expResult, result);
    }
    
    private void setupPDIdbGrooveTyper(String atomName, String residueName) {
        atom = new PDBAtom("Du");
        atom.setName(atomName);
        atom.setResName(residueName);
        atom.setChainID("A");
        residue = new PDBMonomer();
        residue.setMonomerName(residueName);
        chain = new PDBStrand();
        chain.setStrandName("A");
        chain.setID("A");
        pdbPolymer = new PDBPolymer();
        pdbPolymer.addAtom(atom, residue, chain);

        grooveTyper = new PDIdbGrooveTyper().setPdbPolymer(pdbPolymer);
    }

    /**
     * Test of run method, of class PDIdbGrooveTyper.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        
        // Back bone
        setupPDIdbGrooveTyper("OP1", "");
        grooveTyper.run();
        PDIdbGrooveType expResult = PDIdbGrooveType.B;
        PDIdbGrooveType result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
        
        setupPDIdbGrooveTyper("OP2", "");
        grooveTyper.run();
        expResult = PDIdbGrooveType.B;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
        
        setupPDIdbGrooveTyper("OP3", "");
        grooveTyper.run();
        expResult = PDIdbGrooveType.B;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
        
        setupPDIdbGrooveTyper("P", "");
        grooveTyper.run();
        expResult = PDIdbGrooveType.B;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
        
        setupPDIdbGrooveTyper("O3'", "");
        grooveTyper.run();
        expResult = PDIdbGrooveType.B;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
        
        setupPDIdbGrooveTyper("O5'", "");
        grooveTyper.run();
        expResult = PDIdbGrooveType.B;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
        
        setupPDIdbGrooveTyper("C5'", "");
        grooveTyper.run();
        expResult = PDIdbGrooveType.B;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
        
        setupPDIdbGrooveTyper("C1'", "");
        grooveTyper.run();
        expResult = PDIdbGrooveType.B;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
        
        setupPDIdbGrooveTyper("C2'", "");
        grooveTyper.run();
        expResult = PDIdbGrooveType.B;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
        
        setupPDIdbGrooveTyper("C3'", "");
        grooveTyper.run();
        expResult = PDIdbGrooveType.B;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
        
        setupPDIdbGrooveTyper("C4'", "");
        grooveTyper.run();
        expResult = PDIdbGrooveType.B;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
        
        // Major groove DG
        setupPDIdbGrooveTyper("O6", "DG");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C6", "DG");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C5", "DG");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N7", "DG");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C8", "DG");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Major groove G
        setupPDIdbGrooveTyper("O6", "G");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C6", "G");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C5", "G");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N7", "G");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C8", "G");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Major groove DA
        setupPDIdbGrooveTyper("N6", "DA");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C6", "DA");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C5", "DA");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N7", "DA");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C8", "DA");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Major groove A
        setupPDIdbGrooveTyper("N6", "A");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C6", "A");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C5", "A");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N7", "A");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C8", "A");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Major groove DC
        setupPDIdbGrooveTyper("N4", "DC");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C4", "DC");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C5", "DC");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C6", "DC");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Major groove C
        setupPDIdbGrooveTyper("N4", "C");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C4", "C");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C5", "C");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C6", "C");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Major groove DT
        setupPDIdbGrooveTyper("O4", "DT");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C4", "DT");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C5", "DT");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C6", "DT");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C7", "DT");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Major groove T
        setupPDIdbGrooveTyper("O4", "T");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C4", "T");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C5", "T");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C6", "T");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C7", "T");
        grooveTyper.run();
        expResult = PDIdbGrooveType.W;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Minor groove DG
        setupPDIdbGrooveTyper("N2", "DG");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C2", "DG");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N3", "DG");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C4", "DG");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Minor groove DG
        setupPDIdbGrooveTyper("N2", "G");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C2", "G");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N3", "G");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C4", "G");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Minor groove DA
        setupPDIdbGrooveTyper("N1", "DA");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C2", "DA");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N3", "DA");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C4", "DA");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Minor groove A
        setupPDIdbGrooveTyper("N1", "A");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C2", "A");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N3", "A");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C4", "A");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Minor groove DC
        setupPDIdbGrooveTyper("O2", "DC");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C2", "DC");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Minor groove C
        setupPDIdbGrooveTyper("O2", "C");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C2", "C");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Minor groove DT
        setupPDIdbGrooveTyper("O2", "DT");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C2", "DT");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N3", "DT");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Minor groove T
        setupPDIdbGrooveTyper("O2", "T");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("C2", "T");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N3", "T");
        grooveTyper.run();
        expResult = PDIdbGrooveType.S;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        // Not assigned
        setupPDIdbGrooveTyper("N1", "DG");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N9", "DG");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N1", "G");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N9", "G");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N9", "DA");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N9", "A");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N1", "DC");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N3", "DC");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N1", "C");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N3", "C");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N1", "DT");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);

        setupPDIdbGrooveTyper("N1", "T");
        grooveTyper.run();
        expResult = PDIdbGrooveType.N;
        result = (PDIdbGrooveType) atom.getProperty(PDIdbGrooveType.class);
        assertEquals(expResult, result);
    }

}