/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author archvile18
 */
public class PDIdbAnalyzerTest {

    public PDIdbAnalyzerTest() {
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
    
    List<String> readFile(String fileName) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<String> data = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }
        return data;
    }

    /**
     * Test of main method, of class PDIdbAnalyzer.
     */
//    @Test
//    public void testMain() throws FileNotFoundException, IOException {
//        System.out.println("main");
//        String[] args = new String[2];
//        args[0] = "-f testdata/1bl0_1_1.ent.gz";
//        args[1] = "-m 2";
//        PDIdbAnalyzer.main(args);
//        List<String> expResult = readFile("testdata/1bl0_1_1.dat");
//        List<String> result = readFile("1bl0_1_1.dat");
//        for (int i = 1; i < result.size(); i++) { // Ignore first line which contains a timestamp
//            assertEquals("Line " + (i+1), expResult.get(i), result.get(i));
//        }
//    }

    /**
     * Test effective interactions, w/o HBPLUS, 7.0, fouratoms.pdb
     */
    @Test
    public void testMainEffNoHbplus7Fouratoms() throws FileNotFoundException, IOException {
        System.out.println("testMainEffNoHbplus7Fouratoms");
        String[] args = new String[2];
        args[0] = "-f testdata/fouratoms.pdb";
        args[1] = "-m 2";
        PDIdbAnalyzer.main(args);
        List<String> expResult = readFile("testdata/fouratoms_EffNoHbplus7.dat");
        List<String> result = readFile("fouratoms.dat");
        for (int i = 1; i < result.size(); i++) { // Ignore first line which contains a timestamp 
            assertEquals("Line " + (i+1), expResult.get(i), result.get(i));
        }
    }

    /**
     * Test all interactions, w/o HBPLUS, 7.0, fouratoms.pdb
     */
    @Test
    public void testMainNoEffNoHbplus7Fouratoms() throws FileNotFoundException, IOException {
        System.out.println("testMainNoEffNoHbplus7Fouratoms");
        String[] args = new String[3];
        args[0] = "-f testdata/fouratoms.pdb";
        args[1] = "-m 2";
        args[2] = "--noeff";
        PDIdbAnalyzer.main(args);
        List<String> expResult = readFile("testdata/fouratoms_NoEffNoHbplus7.dat");
        List<String> result = readFile("fouratoms.dat");
        for (int i = 1; i < result.size(); i++) { // Ignore first line which contains a timestamp 
            assertEquals("Line " + (i+1), expResult.get(i), result.get(i));
        }
    }

    /**
     * Test effective interactions, w/o HBPLUS, 7.0, sixatoms.pdb
     */
    @Test
    public void testMainEffNoHbplus7Sixatoms() throws FileNotFoundException, IOException {
        System.out.println("testMainEffNoHbplus7Sixatoms");
        String[] args = new String[2];
        args[0] = "-f testdata/sixatoms.pdb";
        args[1] = "-m 2";
        PDIdbAnalyzer.main(args);
        List<String> expResult = readFile("testdata/sixatoms_EffNoHbplus7.dat");
        List<String> result = readFile("sixatoms.dat");
        for (int i = 1; i < result.size(); i++) { // Ignore first line which contains a timestamp 
            assertEquals("Line " + (i+1), expResult.get(i), result.get(i));
        }
    }

    /**
     * Test all interactions, w/o HBPLUS, 7.0, sixatoms.pdb
     */
    @Test
    public void testMainNoEffNoHbplus7Sixatoms() throws FileNotFoundException, IOException {
        System.out.println("testMainNoEffNoHbplus7Sixatoms");
        String[] args = new String[3];
        args[0] = "-f testdata/sixatoms.pdb";
        args[1] = "-m 2";
        args[2] = "--noeff";
        PDIdbAnalyzer.main(args);
        List<String> expResult = readFile("testdata/sixatoms_NoEffNoHbplus7.dat");
        List<String> result = readFile("sixatoms.dat");
        for (int i = 1; i < result.size(); i++) { // Ignore first line which contains a timestamp 
            assertEquals("Line " + (i+1), expResult.get(i), result.get(i));
        }
    }

    /**
     * Test effective interactions, w/o HBPLUS, 7.0, sixatoms_pantano.pdb
     */
    @Test
    public void testMainEffNoHbplus7PantanoSixatoms() throws FileNotFoundException, IOException {
        System.out.println("testMainEffNoHbplus7PantanoSixatoms");
        String[] args = new String[2];
        args[0] = "-f testdata/sixatoms_pantano.pdb";
        args[1] = "-m 4";
        PDIdbAnalyzer.main(args);
        List<String> expResult = readFile("testdata/sixatoms_pantano_EffNoHbplus7.dat");
        List<String> result = readFile("sixatoms_pantano.dat");
        for (int i = 1; i < result.size(); i++) { // Ignore first line which contains a timestamp 
            assertEquals("Line " + (i+1), expResult.get(i), result.get(i));
        }
    }

    /**
     * Test effective interactions, w/o HBPLUS, 7.0, sixatoms_pantano.pdb
     */
    @Test
    public void testMainNoEffNoHbplus7PantanoSixatoms() throws FileNotFoundException, IOException {
        System.out.println("testMainNoEffNoHbplus7PantanoSixatoms");
        String[] args = new String[3];
        args[0] = "-f testdata/sixatoms_pantano.pdb";
        args[1] = "-m 4";
        args[2] = "--noeff";
        PDIdbAnalyzer.main(args);
        List<String> expResult = readFile("testdata/sixatoms_pantano_NoEffNoHbplus7.dat");
        List<String> result = readFile("sixatoms_pantano.dat");
        for (int i = 1; i < result.size(); i++) { // Ignore first line which contains a timestamp 
            assertEquals("Line " + (i+1), expResult.get(i), result.get(i));
        }
    }

}