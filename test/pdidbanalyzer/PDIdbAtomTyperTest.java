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
 * @author archvile18
 */
public class PDIdbAtomTyperTest {
    
    private IPDBAtom atom;
    private IPDBMonomer residue;
    private IStrand chain;
    private IPDBPolymer pdbPolymer;
    private PDIdbAtomTyper atomTyper;

    public PDIdbAtomTyperTest() {
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
     * Test of setPdbPolymer method, of class PDIdbAtomTyper.
     */
    @Test
    public void testSetPdbPolymer() {
        System.out.println("setPdbPolymer");
        IPDBPolymer pdbPolymer = new PDBPolymer();
        PDIdbAtomTyper instance = new PDIdbAtomTyper();
        IPDBPolymer expResult = pdbPolymer;
        instance.setPdbPolymer(pdbPolymer);
        IPDBPolymer result = instance.getPdbPolymer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPdbPolymer method, of class PDIdbAtomTyper.
     */
    @Test
    public void testGetPdbPolymer() {
        System.out.println("getPdbPolymer");
        IPDBPolymer pdbPolymer = new PDBPolymer();
        PDIdbAtomTyper instance = new PDIdbAtomTyper();
        IPDBPolymer expResult = pdbPolymer;
        instance.setPdbPolymer(pdbPolymer);
        IPDBPolymer result = instance.getPdbPolymer();
        assertEquals(expResult, result);
    }
    
    private void setupPDIdbAtomTyperFirstResidue(String atomSymbol, String atomName, String residueName, String chainName) {
        atom = new PDBAtom(atomSymbol);
        atom.setName(atomName);
        atom.setResName(residueName);
        atom.setChainID(chainName);
        residue = new PDBMonomer();
        residue.setMonomerName(residueName);
        chain = new PDBStrand();
        chain.setStrandName(chainName);
        chain.setID(chainName);
        pdbPolymer = new PDBPolymer();
        pdbPolymer.addAtom(atom, residue, chain);

        IPDBAtom atom = new PDBAtom("P");
        atom.setName("P");
        atom.setResName("DG");
        atom.setChainID(chainName);
        residue = new PDBMonomer();
        residue.setMonomerName("DG-2");
        pdbPolymer.addAtom(atom, residue, chain);

        atomTyper = new PDIdbAtomTyper().setPdbPolymer(pdbPolymer);
    }
    
    private void setupPDIdbAtomTyperLastResidue(String atomSymbol, String atomName, String residueName, String chainName) {
        atom = new PDBAtom("P");
        atom.setName("P");
        atom.setResName("DG");
        atom.setChainID(chainName);
        residue = new PDBMonomer();
        residue.setMonomerName("DG-1");
        chain = new PDBStrand();
        chain.setStrandName(chainName);
        chain.setID(chainName);
        pdbPolymer = new PDBPolymer();
        pdbPolymer.addAtom(atom, residue, chain);

        atom = new PDBAtom(atomSymbol);
        atom.setName(atomName);
        atom.setResName(residueName);
        atom.setChainID(chainName);
        residue = new PDBMonomer();
        residue.setMonomerName(residueName);
        pdbPolymer.addAtom(atom, residue, chain);
        
        atomTyper = new PDIdbAtomTyper().setPdbPolymer(pdbPolymer);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for DNA atom types
     */
    @Test
    public void testRunWater() {
        System.out.println("run water");
        setupPDIdbAtomTyperFirstResidue("O", "HOH", "HOH", "A");
        atomTyper.run();
        IAtomType expResult = WaterAtomType.WATER_O;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for guanine atom types.
     */
    @Test
    public void testRunGuanine() {
        System.out.println("run guanine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP1", "DG", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbDNAAtomType.OP1OP2OP3;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP2", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP3", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 2
        setupPDIdbAtomTyperFirstResidue("P", "P", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.P;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O3'", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O5'", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C5'", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C3'", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C4'", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 6
        setupPDIdbAtomTyperLastResidue("O", "O3'", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 7
        setupPDIdbAtomTyperFirstResidue("C", "C2'", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C2Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "C1'", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C1Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 9
        setupPDIdbAtomTyperFirstResidue("O", "O4'", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Nucleobase atom types
        // Type 10
        setupPDIdbAtomTyperFirstResidue("N", "N9", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TertN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 11
        setupPDIdbAtomTyperFirstResidue("C", "C8", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C8;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N7", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N3", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 13
        setupPDIdbAtomTyperFirstResidue("C", "C5", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PuC5;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 14
        setupPDIdbAtomTyperFirstResidue("C", "C4", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PuC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 17
        setupPDIdbAtomTyperFirstResidue("N", "N2", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PrmN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 18
        setupPDIdbAtomTyperFirstResidue("C", "C2", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.GC2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 19
        setupPDIdbAtomTyperFirstResidue("C", "C6", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.GC6TC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("O", "O6", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.CarbonylO;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 24
        setupPDIdbAtomTyperFirstResidue("N", "N1", "DG", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.AmideN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // All types with hydrated ribose (e.g. G instead of DG)
        // Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP1", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP2", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP3", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 2
        setupPDIdbAtomTyperFirstResidue("P", "P", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.P;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O3'", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O5'", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C5'", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C3'", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C4'", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 6
        setupPDIdbAtomTyperLastResidue("O", "O3'", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 7
        setupPDIdbAtomTyperFirstResidue("C", "C2'", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C2Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "C1'", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C1Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 9
        setupPDIdbAtomTyperFirstResidue("O", "O4'", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Nucleobase atom types
        // Type 10
        setupPDIdbAtomTyperFirstResidue("N", "N9", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TertN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 11
        setupPDIdbAtomTyperFirstResidue("C", "C8", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C8;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N7", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N3", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 13
        setupPDIdbAtomTyperFirstResidue("C", "C5", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PuC5;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 14
        setupPDIdbAtomTyperFirstResidue("C", "C4", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PuC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 17
        setupPDIdbAtomTyperFirstResidue("N", "N2", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PrmN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 18
        setupPDIdbAtomTyperFirstResidue("C", "C2", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.GC2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 19
        setupPDIdbAtomTyperFirstResidue("C", "C6", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.GC6TC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("O", "O6", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.CarbonylO;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 24
        setupPDIdbAtomTyperFirstResidue("N", "N1", "G", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.AmideN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for adenine atom types.
     */
    @Test
    public void testRunAdenine() {
        System.out.println("run adenine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP1", "DA", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbDNAAtomType.OP1OP2OP3;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP2", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP3", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 2
        setupPDIdbAtomTyperFirstResidue("P", "P", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.P;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O3'", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O5'", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C5'", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C3'", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C4'", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 6
        setupPDIdbAtomTyperLastResidue("O", "O3'", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 7
        setupPDIdbAtomTyperFirstResidue("C", "C2'", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C2Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "C1'", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C1Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 9
        setupPDIdbAtomTyperFirstResidue("O", "O4'", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Nucleobase atom types
        // Type 10
        setupPDIdbAtomTyperFirstResidue("N", "N9", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TertN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 11
        setupPDIdbAtomTyperFirstResidue("C", "C8", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C8;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N7", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N3", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N1", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 13
        setupPDIdbAtomTyperFirstResidue("C", "C5", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PuC5;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 14
        setupPDIdbAtomTyperFirstResidue("C", "C4", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PuC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 15
        setupPDIdbAtomTyperFirstResidue("C", "C2", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.AC2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 16
        setupPDIdbAtomTyperFirstResidue("C", "C6", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.AC6CC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 17
        setupPDIdbAtomTyperFirstResidue("N", "N6", "DA", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PrmN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // All types with hydrated ribose (e.g. G instead of DG)
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP1", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP2", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP3", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 2
        setupPDIdbAtomTyperFirstResidue("P", "P", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.P;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O3'", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O5'", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C5'", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C3'", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C4'", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 6
        setupPDIdbAtomTyperLastResidue("O", "O3'", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 7
        setupPDIdbAtomTyperFirstResidue("C", "C2'", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C2Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "C1'", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C1Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 9
        setupPDIdbAtomTyperFirstResidue("O", "O4'", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Nucleobase atom types
        // Type 10
        setupPDIdbAtomTyperFirstResidue("N", "N9", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TertN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 11
        setupPDIdbAtomTyperFirstResidue("C", "C8", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C8;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N7", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N3", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N1", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 13
        setupPDIdbAtomTyperFirstResidue("C", "C5", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PuC5;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 14
        setupPDIdbAtomTyperFirstResidue("C", "C4", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PuC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 15
        setupPDIdbAtomTyperFirstResidue("C", "C2", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.AC2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 16
        setupPDIdbAtomTyperFirstResidue("C", "C6", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.AC6CC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 17
        setupPDIdbAtomTyperFirstResidue("N", "N6", "A", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PrmN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for cytosine atom types.
     */
    @Test
    public void testRunCytosine() {
        System.out.println("run cytosine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP1", "DC", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbDNAAtomType.OP1OP2OP3;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP2", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP3", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 2
        setupPDIdbAtomTyperFirstResidue("P", "P", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.P;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O3'", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O5'", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C5'", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C3'", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C4'", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 6
        setupPDIdbAtomTyperLastResidue("O", "O3'", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 7
        setupPDIdbAtomTyperFirstResidue("C", "C2'", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C2Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "C1'", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C1Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 9
        setupPDIdbAtomTyperFirstResidue("O", "O4'", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Nucleobase atom types
        // Type 10
        setupPDIdbAtomTyperFirstResidue("N", "N1", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TertN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N3", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 16
        setupPDIdbAtomTyperFirstResidue("C", "C4", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.AC6CC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 17
        setupPDIdbAtomTyperFirstResidue("N", "N4", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PrmN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("O", "O2", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.CarbonylO;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 21
        setupPDIdbAtomTyperFirstResidue("C", "C2", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PyC2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 22
        setupPDIdbAtomTyperFirstResidue("C", "C5", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.CC5;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 23
        setupPDIdbAtomTyperFirstResidue("C", "C6", "DC", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PyC6;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // All types with hydrated ribose (e.g. G instead of DG)
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP1", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP2", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP3", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 2
        setupPDIdbAtomTyperFirstResidue("P", "P", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.P;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O3'", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O5'", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C5'", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C3'", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C4'", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 6
        setupPDIdbAtomTyperLastResidue("O", "O3'", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 7
        setupPDIdbAtomTyperFirstResidue("C", "C2'", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C2Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "C1'", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C1Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 9
        setupPDIdbAtomTyperFirstResidue("O", "O4'", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Nucleobase atom types
        // Type 10
        setupPDIdbAtomTyperFirstResidue("N", "N1", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TertN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("N", "N3", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.Imine;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 16
        setupPDIdbAtomTyperFirstResidue("C", "C4", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.AC6CC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 17
        setupPDIdbAtomTyperFirstResidue("N", "N4", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PrmN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("O", "O2", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.CarbonylO;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 21
        setupPDIdbAtomTyperFirstResidue("C", "C2", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PyC2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 22
        setupPDIdbAtomTyperFirstResidue("C", "C5", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.CC5;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 23
        setupPDIdbAtomTyperFirstResidue("C", "C6", "C", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PyC6;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for thymine atom types.
     */
    @Test
    public void testRunThymine() {
        System.out.println("run thymine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP1", "DT", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbDNAAtomType.OP1OP2OP3;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP2", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP3", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 2
        setupPDIdbAtomTyperFirstResidue("P", "P", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.P;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O3'", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O5'", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C5'", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C3'", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C4'", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 6
        setupPDIdbAtomTyperLastResidue("O", "O3'", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 7
        setupPDIdbAtomTyperFirstResidue("C", "C2'", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C2Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "C1'", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C1Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 9
        setupPDIdbAtomTyperFirstResidue("O", "O4'", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Nucleobase atom types
        // Type 10
        setupPDIdbAtomTyperFirstResidue("N", "N1", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TertN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 19
        setupPDIdbAtomTyperFirstResidue("C", "C4", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.GC6TC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("O", "O2", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.CarbonylO;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("O", "O4", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.CarbonylO;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 21
        setupPDIdbAtomTyperFirstResidue("C", "C2", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PyC2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 23
        setupPDIdbAtomTyperFirstResidue("C", "C6", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PyC6;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 24
        setupPDIdbAtomTyperFirstResidue("N", "N3", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.AmideN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 25
        setupPDIdbAtomTyperFirstResidue("C", "C5", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TC5;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 26
        setupPDIdbAtomTyperFirstResidue("C", "C7", "DT", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TC7;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // All types with hydrated ribose (e.g. G instead of DG)
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP1", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP2", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 1
        setupPDIdbAtomTyperFirstResidue("O", "OP3", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.OP1OP2OP3;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 2
        setupPDIdbAtomTyperFirstResidue("P", "P", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.P;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O3'", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperFirstResidue("O", "O5'", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3PrO5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C5'", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C5Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C3'", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("C", "C4'", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C3PrC4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 6
        setupPDIdbAtomTyperLastResidue("O", "O3'", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O3Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 7
        setupPDIdbAtomTyperFirstResidue("C", "C2'", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C2Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "C1'", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.C1Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 9
        setupPDIdbAtomTyperFirstResidue("O", "O4'", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.O4Pr;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Nucleobase atom types
        // Type 10
        setupPDIdbAtomTyperFirstResidue("N", "N1", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TertN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 19
        setupPDIdbAtomTyperFirstResidue("C", "C4", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.GC6TC4;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("O", "O2", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.CarbonylO;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("O", "O4", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.CarbonylO;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 21
        setupPDIdbAtomTyperFirstResidue("C", "C2", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PyC2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 23
        setupPDIdbAtomTyperFirstResidue("C", "C6", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.PyC6;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 24
        setupPDIdbAtomTyperFirstResidue("N", "N3", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.AmideN;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 25
        setupPDIdbAtomTyperFirstResidue("C", "C5", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TC5;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 26
        setupPDIdbAtomTyperFirstResidue("C", "C7", "T", "A");
        atomTyper.run();
        expResult = PDIdbDNAAtomType.TC7;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for glycine atom types.
     */
    @Test
    public void testRunGlycine() {
        System.out.println("run glycine");
        
        //Back bone atoms types
        // Type 2
        setupPDIdbAtomTyperFirstResidue("C", "CA", "GLY", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.GLY_CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "GLY", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "GLY", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "GLY", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "GLY", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "GLY", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for alanine atom types.
     */
    @Test
    public void testRunAlanine() {
        System.out.println("run alanine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "ALA", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "ALA", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "ALA", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "ALA", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "ALA", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "ALA", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 6
        setupPDIdbAtomTyperFirstResidue("C", "CB", "ALA", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.METHYL;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for valine atom types.
     */
    @Test
    public void testRunValine() {
        System.out.println("run valine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "VAL", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "VAL", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "VAL", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "VAL", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "VAL", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "VAL", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 6
        setupPDIdbAtomTyperFirstResidue("C", "CG1", "VAL", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.METHYL;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 6
        setupPDIdbAtomTyperFirstResidue("C", "CG2", "VAL", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.METHYL;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 7
        setupPDIdbAtomTyperFirstResidue("C", "CB", "VAL", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for leucine atom types.
     */
    @Test
    public void testRunLeucine() {
        System.out.println("run leucine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "LEU", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "LEU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "LEU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "LEU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "LEU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "LEU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 6
        setupPDIdbAtomTyperFirstResidue("C", "CD1", "LEU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.METHYL;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 6
        setupPDIdbAtomTyperFirstResidue("C", "CD2", "LEU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.METHYL;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 7
        setupPDIdbAtomTyperFirstResidue("C", "CG", "LEU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "LEU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for isoleucine atom types.
     */
    @Test
    public void testRunIsoleucine() {
        System.out.println("run isoleucine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "ILE", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "ILE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "ILE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "ILE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "ILE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "ILE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 6
        setupPDIdbAtomTyperFirstResidue("C", "CD1", "ILE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.METHYL;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 6
        setupPDIdbAtomTyperFirstResidue("C", "CG2", "ILE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.METHYL;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 7
        setupPDIdbAtomTyperFirstResidue("C", "CB", "ILE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CG1", "ILE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for methionine atom types.
     */
    @Test
    public void testRunMethionine() {
        System.out.println("run methionine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "MET", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "MET", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "MET", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "MET", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "MET", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "MET", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "MET", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 9
        setupPDIdbAtomTyperFirstResidue("S", "SD", "MET", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.MET_S;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 29
        setupPDIdbAtomTyperFirstResidue("C", "CG", "MET", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.MET_CYS_CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 30
        setupPDIdbAtomTyperFirstResidue("C", "CE", "MET", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.MET_METHYL;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for proline atom types.
     */
    @Test
    public void testRunProline() {
        System.out.println("run proline");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "PRO", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "PRO", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "PRO", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 10
        setupPDIdbAtomTyperLastResidue("N", "N", "PRO", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.PRO_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "PRO", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "PRO", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "PRO", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CG", "PRO", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 32
        setupPDIdbAtomTyperFirstResidue("C", "CD", "PRO", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.PRO_CD;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for phenylalanine atom types.
     */
    @Test
    public void testRunPhenylalanine() {
        System.out.println("run phenylalanine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "PHE", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 11
        setupPDIdbAtomTyperFirstResidue("C", "CG", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH0_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CD1", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CD2", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CE1", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CE2", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CZ", "PHE", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for tryptophane atom types.
     */
    @Test
    public void testRunTryptophane() {
        System.out.println("run tryptophane");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "TRP", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 11
        setupPDIdbAtomTyperFirstResidue("C", "CD2", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH0_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CE3", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CZ2", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CZ3", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CH2", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 13
        setupPDIdbAtomTyperFirstResidue("C", "CG", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.TRP_CG;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 14
        setupPDIdbAtomTyperFirstResidue("C", "CE2", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.TRP_CE2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 24
        setupPDIdbAtomTyperFirstResidue("C", "CD1", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.TRP_HIS_CH;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 39
        setupPDIdbAtomTyperFirstResidue("N", "NE1", "TRP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.TRP_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for serine atom types.
     */
    @Test
    public void testRunSerine() {
        System.out.println("run serine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "SER", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "SER", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "SER", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "SER", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "SER", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "SER", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 15
        setupPDIdbAtomTyperFirstResidue("C", "CB", "SER", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.SER_CB;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 16
        setupPDIdbAtomTyperFirstResidue("O", "OG", "SER", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.SER_THR_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for threonine atom types.
     */
    @Test
    public void testRunThreonine() {
        System.out.println("run threonine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "THR", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "THR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "THR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "THR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "THR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "THR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 6
        setupPDIdbAtomTyperFirstResidue("C", "CG2", "THR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.METHYL;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 16
        setupPDIdbAtomTyperFirstResidue("O", "OG1", "THR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.SER_THR_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 17
        setupPDIdbAtomTyperFirstResidue("C", "CB", "THR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.THR_CB;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for asparagine atom types.
     */
    @Test
    public void testRunAsparagine() {
        System.out.println("run asparagine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "ASN", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "ASN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "ASN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "ASN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "ASN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "ASN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "ASN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 18
        setupPDIdbAtomTyperFirstResidue("N", "ND2", "ASN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASN_GLN_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 33
        setupPDIdbAtomTyperFirstResidue("C", "CG", "ASN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASN_GLN_C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 34
        setupPDIdbAtomTyperFirstResidue("O", "OD1", "ASN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASN_GLN_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for glutamine atom types.
     */
    @Test
    public void testRunGlutamine() {
        System.out.println("run glutamine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "GLN", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "GLN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "GLN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "GLN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "GLN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "GLN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "GLN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CG", "GLN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Type 18
        setupPDIdbAtomTyperFirstResidue("N", "NE2", "GLN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASN_GLN_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 33
        setupPDIdbAtomTyperFirstResidue("C", "CD", "GLN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASN_GLN_C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 34
        setupPDIdbAtomTyperFirstResidue("O", "OE1", "GLN", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASN_GLN_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for lysine atom types.
     */
    @Test
    public void testRunLysine() {
        System.out.println("run lysine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "LYS", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "LYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "LYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "LYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "LYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "LYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "LYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CG", "LYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CD", "LYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "NZ", "LYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 35
        setupPDIdbAtomTyperFirstResidue("C", "CE", "LYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_CE;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for tyrosine atom types.
     */
    @Test
    public void testRunTyrosine() {
        System.out.println("run tyrosine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "TYR", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 11
        setupPDIdbAtomTyperFirstResidue("C", "CG", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH0_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CD1", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CD2", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CE1", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 12
        setupPDIdbAtomTyperFirstResidue("C", "CE2", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH_SP2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 31
        setupPDIdbAtomTyperFirstResidue("C", "CZ", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.TYR_CZ;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 40
        setupPDIdbAtomTyperFirstResidue("O", "OH", "TYR", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.TYR_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for cysteine atom types.
     */
    @Test
    public void testRunCysteine() {
        System.out.println("run cysteine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "CYS", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "CYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "CYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "CYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "CYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "CYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 19
        setupPDIdbAtomTyperFirstResidue("S", "SG", "CYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CYS_S;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 29
        setupPDIdbAtomTyperFirstResidue("C", "CB", "CYS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.MET_CYS_CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for glutamate atom types.
     */
    @Test
    public void testRunGlutamate() {
        System.out.println("run glutamate");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "GLU", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "GLU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "GLU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "GLU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "GLU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "GLU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "GLU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CG", "GLU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Type 27
        setupPDIdbAtomTyperFirstResidue("C", "CD", "GLU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperFirstResidue("O", "OE1", "GLU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperFirstResidue("O", "OE2", "GLU", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for aspartate atom types.
     */
    @Test
    public void testRunAspartate() {
        System.out.println("run aspartate");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "ASP", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "ASP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "ASP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "ASP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "ASP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "ASP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "ASP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Type 27
        setupPDIdbAtomTyperFirstResidue("C", "CG", "ASP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperFirstResidue("O", "OD1", "ASP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperFirstResidue("O", "OD2", "ASP", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class PDIdbAtomTyper for arginine atom types.
     */
    @Test
    public void testRunArginine() {
        System.out.println("run arginine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "ARG", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "ARG", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "ARG", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "ARG", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "ARG", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "ARG", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "ARG", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CG", "ARG", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 21
        setupPDIdbAtomTyperFirstResidue("C", "CZ", "ARG", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ARG_CZ;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Type 22
        setupPDIdbAtomTyperFirstResidue("N", "NH1", "ARG", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ARG_NH;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Type 22
        setupPDIdbAtomTyperFirstResidue("N", "NH2", "ARG", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ARG_NH;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Type 36
        setupPDIdbAtomTyperFirstResidue("N", "NE", "ARG", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ARG_NE;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }
    /**
     * Test of run method, of class PDIdbAtomTyper for histidine atom types.
     */
    @Test
    public void testRunHistidine() {
        System.out.println("run histidine");
        
        //Back bone atoms types
        // Type 1
        setupPDIdbAtomTyperFirstResidue("C", "CA", "HIS", "A");
        atomTyper.run();
        IAtomType expResult = PDIdbProteinAtomType.CA;
        IAtomType result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 3
        setupPDIdbAtomTyperLastResidue("N", "N", "HIS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 4
        setupPDIdbAtomTyperFirstResidue("C", "C", "HIS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.C;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 5
        setupPDIdbAtomTyperFirstResidue("O", "O", "HIS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 20
        setupPDIdbAtomTyperFirstResidue("N", "N", "HIS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.LYS_N;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 28
        setupPDIdbAtomTyperLastResidue("O", "OXT", "HIS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.ASP_GLU_O;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Side chain atom types
        // Type 8
        setupPDIdbAtomTyperFirstResidue("C", "CB", "HIS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.CH2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 23
        setupPDIdbAtomTyperFirstResidue("C", "CG", "HIS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.HIS_CG;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 24
        setupPDIdbAtomTyperFirstResidue("C", "CD2", "HIS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.TRP_HIS_CH;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Type 25
        setupPDIdbAtomTyperFirstResidue("N", "NE2", "HIS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.HIS_NE2;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);

        // Type 26
        setupPDIdbAtomTyperFirstResidue("C", "CE1", "HIS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.HIS_CE1;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
        
        // Type 38
        setupPDIdbAtomTyperFirstResidue("N", "ND1", "HIS", "A");
        atomTyper.run();
        expResult = PDIdbProteinAtomType.HIS_ND1;
        result = (IAtomType) atom.getProperty(IAtomType.class);
        assertEquals(expResult, result);
    }
}