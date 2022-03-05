package com.cone.services.commandline;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Parameters {
  private CommandLine cmd;
  private String[] args;
  private Options options;

  public Parameters(String[] args) throws ParseException {
    this.args = args;
    initParser();
  }

  public Boolean include(String arg) {
    return cmd.hasOption(arg);
  }

  public String getValue(String arg) {
    return cmd.getOptionValue(arg);
  }

  public void printHelp() {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("CrypticList", options);
  }

  private void initParser() throws ParseException {
    CommandLineParser parser = new DefaultParser();
    cmd = parser.parse(generateOptions(), args);
  }

  private Options generateOptions() {
    options = new Options();
    options.addOption("ls", false, "display entry list");
    options.addOption("r", true, "read entry values");
    options.addOption("a", false, "add new entry");
    options.addOption("h", false, "show help");

    return options;
  }
}
