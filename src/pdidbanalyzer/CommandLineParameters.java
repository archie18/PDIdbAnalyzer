/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import java.io.PrintWriter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * Provides the necessary functionality with regard to the command line parsing.
 * @author archvile18
 */
public class CommandLineParameters {

  /** The command line options */ 
  private Options options;
  
  /** Object to store the command line */
  private CommandLine commandLine;
  
  /** The system specific new line separator */
  private final String nl = System.getProperty("line.separator");
  
  /** The header for printing at the console */
  private final String header = "---" + nl +
          "PDIdbAnalyzer v" + PDIdbAnalyzer.version + nl +
          "by Andreas Schueller <aschueller@bio.puc.cl>" + nl +
          "Copyright (c) 2011 Andreas Schueller, Santiago, Chile" + nl +
          "---" + nl;
  
  /** The footer for printing at the console */
  private final String footer = "";
          
  
  /**
   * Creates a new instance of CommandLineParameters.
   */
  public CommandLineParameters() {
    // options holds all options that are available
    options = new Options();
    // Add the individual options
    options.addOption( OptionBuilder.isRequired().hasArg().withArgName("pdbFile").withDescription("name of the input PDB file or directory name").withLongOpt("file").create('f') );
    options.addOption( OptionBuilder.hasArg().withArgName("mode").withDescription("1: Original PDIdb mode (default), 2: Extended mode, 3: Extended mode with HBPLUS, 4: Extended mode with PantanoOutputFilter").withLongOpt("mode").create('m') );
    options.addOption( OptionBuilder.withDescription("disable effective interactions").withLongOpt("noeff").create('n') );
    options.addOption( OptionBuilder.hasArg().withArgName("cutoff").withDescription("distance cutoff in angstrom (default: 7.0)").withLongOpt("distance").create('d') );
    options.addOption( OptionBuilder.withDescription("displays this help text").withLongOpt("help").create('h') );
    options.addOption( OptionBuilder.withDescription("displays version information and exits").withLongOpt("version").create('v') );
  }
  
  /**
   * Parses the command line arguments.
   * @param args the command line arguments that should be parsed
   */
  void parse( String[] args ) {
    CommandLineParser parser = new PosixParser(); //create the parser
    
    // parse the command line parameters
    try {
      commandLine = parser.parse( options, args );
    } catch( ParseException exception ) {
      System.err.println("Wrong or missing commandline parameters: " + exception.getMessage());
      printHelp();
      System.exit(1);
    }
  }

  
  /**
   * Prints a formatted help text.
   */
  void printHelp() {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp(new PrintWriter(System.out, true),    // PrintWriter
                          70,                                 // width
                          "java -jar PDIdbAnalyzer.jar",     // cmdLineSyntax
                          header,                             // header
                          options,                            // options
                          4,                                  // leftPad
                          2,                                  // descPad
                          footer,                             // footer
                          true                                // autoUsage
            );
                          
    System.exit(0);
  }
  
  /**
   * Prints a formatted version information.
   */
  public void printVersion( ) {
    System.out.println(header);
    System.out.println(footer);
    System.exit(0);
  }
  
  /**
   * Lists the values of the command line options.
   * @return formatted string representation of the command line options
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append( "Input PDB file: " + this.getPdbFileOption() + nl);
    return stringBuilder.toString();
  }
  
  /**
   * Returns whether the version option has been provided at the command line.
   * @return true if the version option has been provided at the command line, false otherwise.
   */
  public boolean getVersionOption() {
    return commandLine.hasOption('v');
  }
  
  /**
   * Returns whether the help option has been provided at the command line.
   * @return true if the help option has been provided at the command line, false otherwise.
   */
  public boolean getHelpOption() {
    return commandLine.hasOption('h');
  }
  
  /**
   * Returns the name of the input PDB file or directory name.
   * @return the name of the input PDB file or directory name
   */
  public String getPdbFileOption() {
    return commandLine.getOptionValue("f").trim();
  }

  /**
   * Returns the operation mode 1: Original PDIdb mode (default), 2: Extended mode.
   * @return the operation mode
   */
  public String getModeOption() {
      if (commandLine.getOptionValue("m") != null)
          return commandLine.getOptionValue("m").trim();
      else
          return "1"; // default value
  }
  
  /**
   * Returns whether the noeff (no effective interactions) option has been provided at the command line.
   * @return true if the noeff option has been provided at the command line, false otherwise.
   */
  public boolean getNoeffOption() {
    return commandLine.hasOption('n');
  }

  /**
   * Returns the distance cutoff in angstrom. The condition test is distance
   * <= cutoff. The default is 7.0 angstrom.
   * @return the distance cutoff
   */
  public double getDistanceOption() {
      if (commandLine.getOptionValue("d") != null)
          return Double.valueOf(commandLine.getOptionValue("d"));
      else
          return 7.0; // default value
  }
  
}
