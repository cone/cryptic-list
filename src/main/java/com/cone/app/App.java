package com.cone.app;

import com.cone.services.commandline.EntryLister;
import com.cone.services.commandline.EntryReader;
import com.cone.services.commandline.EntryWizard;
import com.cone.services.commandline.EntryWizardWithPasswordGeneration;
import com.cone.services.commandline.EntryWritter;
import com.cone.services.commandline.Parameters;
import com.cone.services.commandline.interfaces.Wizard;
import com.cone.services.utils.UuidGenerator;

public class App 
{
  static final String ENTRY_FILE_PATH = "data.json";

  public static void main( String[] args )
  {
    try {
        Parameters params = new Parameters(args);

        if(params.include("ls")) {
            EntryLister entryLister = new EntryLister(ENTRY_FILE_PATH);
            entryLister.list();
        } else if(params.include("a")) {
            write(new EntryWizard());
        } else if(params.include("ap")) {
            write(new EntryWizardWithPasswordGeneration());
        } else if(params.include("r")) {
            EntryReader reader = new EntryReader(params.getValue("r"), ENTRY_FILE_PATH);
            reader.display();
        } else {
            params.printHelp();
        }
    }
    catch (Exception exp) {
        System.err.println("Something went wrong: " + exp.getMessage());
    }
  }

  private static void write(Wizard wiz) throws Exception {
    EntryWritter entryWritter = new EntryWritter(ENTRY_FILE_PATH, wiz.getPassword(), new UuidGenerator());
    entryWritter.addRegistry(wiz.getCredentials(), wiz.getDescription());
  }
}
