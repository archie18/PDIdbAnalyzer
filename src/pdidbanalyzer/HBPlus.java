/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pdidbanalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author pipe
 */
public class HBPlus {

    private String program_out = "output.out";
    private String program_error = "error.out";

    /**
     * Runs the external program HBPlus
     *
     * @param file a PDB file
     */
    public void run(File file) {

        String[] args = new String[2];
        args[0] = "../hbplus";
        //args[1]="-X";
        args[1] = file.getAbsolutePath();

        try {

            ProcessBuilder pb = new ProcessBuilder(args);

            File f = new File("Data_HBPlus");
            f.mkdir();
            pb.directory(f);

            Process p = pb.start();
            p.waitFor();

            BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            System.out.println("Exit code: " + p.exitValue());
            System.out.printf("Output of running %s is:", Arrays.toString(args));
            while ((line = stdout.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("");
            System.out.printf("Stderr of running %s is:", Arrays.toString(args));
            while ((line = stderr.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("");


        } catch (InterruptedException ex) {
            //log.error(ex.toString(), ex);
        } catch (IOException ex) {
            System.out.print(ex.toString() + "\n");
        }


    }
}
