/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pdidbanalyzer;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.sun.xml.internal.stream.buffer.stax.StreamReaderBufferCreator;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openscience.cdk.interfaces.IAtom;

/**
 *
 * @author pipe
 */
public class HBPlus {

    private String programOut = "";
    private String programError = "";
    
    public static int count = 1;

    /**
     * Runs the external program HBPlus
     *
     * @param file a PDB file
     */
    public void run(File file) {

        String[] args = new String[2];
        args[0] = "./hbplus";
        //args[1]="-P";
        args[1] = file.getAbsolutePath();

        try {

            
            ProcessBuilder pb = new ProcessBuilder(args);
            Process p = pb.start();
            p.waitFor();
            
            BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            
            while ((line = stdout.readLine()) != null) {
                programOut+=line+"\n";                
            }
            
            while ((line = stderr.readLine()) != null) {
                programError+=line+"\n";                
            }
            
            //<editor-fold defaultstate="collapsed" desc="Saving Output and Error in file -> Out_HBPlus.out">
            FileWriter fw = new FileWriter("Out_HBPlus.out", true); 
            fw.write("-----------------"+"HBPLus Output nº"+count+"-----------------"+"\n");
            count ++;
            fw.write("Exit code: " + p.exitValue()+"\n");
            fw.write("Output of running "+Arrays.toString(args)+" is:"+"\n");
            fw.write(line+"\n");
            fw.write(""+"\n");
            fw.write("Stderr of running "+ Arrays.toString(args)+" is:"+"\n");
            fw.write(line+"\n");
            fw.write("\n");
            fw.write("---------------------------------------------------------\n");
            fw.close();
            //</editor-fold>

        } catch (InterruptedException ex) {
            //log.error(ex.toString(), ex);
        } catch (IOException ex) {
            System.out.print(ex.toString() + "\n");            
        }

    }
    
    private HBPlusRecord parseLine(String line) {
        // Parse line
        HBPlusRecord hbprecord = new HBPlusRecord();
        hbprecord.setDonorResname(line.substring(6,9).trim());
        hbprecord.setDonorResId(line.substring(1, 5).replaceFirst("^0+(?!$)", ""));
        hbprecord.setDonorChainId(line.substring(0,1));
        hbprecord.setDonorAtomname(line.substring(9,13).trim());
        hbprecord.setDonorElement(hbprecord.getDonorAtomname().substring(0, 1));
        
        hbprecord.setAcceptorResname(line.substring(20,23).trim());
        hbprecord.setAcceptorResId(line.substring(15, 19).replaceFirst("^0+(?!$)", ""));
        hbprecord.setAcceptorChainId(line.substring(14,15));
        hbprecord.setAcceptorAtomname(line.substring(23,27).trim());
        hbprecord.setAcceptorElement(hbprecord.getAcceptorAtomname().substring(0, 1));
        
        
        try
        {
            
        
            hbprecord.setDistDA(Float.parseFloat(line.substring(27, 32))) ;
            hbprecord.setDistHA(Float.parseFloat(line.substring(52, 57)));
            
            hbprecord.setAngle_D_H_A(Float.parseFloat(line.substring(46, 51)));
            hbprecord.setAngle_H_A_AA(Float.parseFloat(line.substring(58, 63)));
            hbprecord.setAngle_D_A_AA(Float.parseFloat(line.substring(64, 69)));
        }
        catch (NumberFormatException e)
        {
            System.out.println(e.getMessage());
        }
        // Fill HBPlusRecord object
        return hbprecord;
    }
    
    public List<HBPlusRecord> parse(String filename) {
        
        
        List<HBPlusRecord> hb2Contents = new ArrayList<HBPlusRecord>();
        String newfilename = filename.substring(0,filename.lastIndexOf("."))+".hb2";        
        
        try {            
            
            FileInputStream fstream = new FileInputStream(newfilename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                if(Character.isDigit(line.charAt(1))&&Character.isDigit(line.charAt(2)))
                {
                    hb2Contents.add(parseLine(line));
                }
            }
            in.close();
            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return hb2Contents;
    }
    
    /**
     * Returns true if HBPLUS detected an H-bond involving atom1 and atom2,
     * otherwise returns false.
     * @param atom1 first atom
     * @param atom2 second atom
     * @return true if HBPLUS detected an H-bond involving the two atoms,
     *         otherwise false
     */
    public boolean isHBond(IAtom atom1, IAtom atom2) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
