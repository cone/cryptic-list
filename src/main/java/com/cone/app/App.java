package com.cone.app;

import com.cone.services.commandline.EntryLister;
import com.cone.services.commandline.EntryReader;
import com.cone.services.commandline.EntryWizard;
import com.cone.services.commandline.Parameters;
import com.cone.services.registry.RegistryWritter;
import com.cone.services.utils.UuidGenerator;

public class App 
{
  public static void main( String[] args )
  {
    String entryFilePath = "data.json";

    try {
        Parameters params = new Parameters(args);

        if(params.include("ls")) {
            EntryLister entryLister = new EntryLister(entryFilePath);
            entryLister.list();
        } else if(params.include("a")) {
            EntryWizard wiz = new EntryWizard();
            RegistryWritter registryWritter = new RegistryWritter(entryFilePath, wiz.getKey(), new UuidGenerator());
            registryWritter.addRegistry(wiz.getCredentials(), wiz.getDescription());
        } else if(params.include("r")) {
            EntryReader reader = new EntryReader(params.getValue("r"), entryFilePath);
            reader.display();
        }
    }
    catch (Exception exp) {
        System.err.println("Something went wrong: " + exp.getMessage());
    }
  }
}
