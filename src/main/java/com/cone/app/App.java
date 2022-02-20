package com.cone.app;

import java.io.Console;
import java.util.Map;

import com.cone.services.commandline.Parameters;
import com.cone.services.registry.RegistriesTable;
import com.cone.services.registry.RegistryWritter;
import com.cone.services.utils.UuidGenerator;
import com.cone.services.registry.RegistriesLoader;

public class App 
{
  public static void main( String[] args )
  {
    try {
        Parameters params = new Parameters(args);
        RegistriesLoader entryLoader = new RegistriesLoader("data.json");
        Map<String, String> entries = entryLoader.getEntries();
        RegistriesTable table = new RegistriesTable(entries);

        if(params.include("ls")) {
            String rend = table.render();
            System.out.println(rend);
        } else if(params.include("a")) {
            Console console = System.console();
            String key = new String(console.readPassword("key: "));
            String user = console.readLine("%s", "user (email or name): ");
            String password = new String(console.readPassword("user password: "));
            String desc = console.readLine("%s", "short description: ");
            RegistryWritter registryWritter = new RegistryWritter("data.json", key, new UuidGenerator());
            Credentials creds = new Credentials(user, password, desc);
            registryWritter.addRegistry(creds, desc);
        } else if(params.include("r")) {
            Console console = System.console();
            String password = new String(console.readPassword("key: "));
            String id = params.getValue("r");
            System.out.println(password);
            System.out.println(id);
        }
    }
    catch (Exception exp) {
        System.err.println("Something went wrong: " + exp.getMessage());
    }
  }
}
