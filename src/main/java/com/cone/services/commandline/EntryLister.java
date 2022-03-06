package com.cone.services.commandline;

import java.util.HashMap;
import java.util.Map;

import com.cone.services.registry.RegistriesLoader;
import com.cone.services.registry.RegistriesTable;

public class EntryLister {
  Map<String, String> entries;

  public EntryLister(String entriesFilePath) {
    try {
      RegistriesLoader entryLoader = new RegistriesLoader(entriesFilePath);
      entries = entryLoader.getEntries();
    } catch(Exception e) {
      entries = new HashMap<String, String>();
    }
  }

  public void list() {
    RegistriesTable table = new RegistriesTable(entries);
    String rend = table.render();
    System.out.println(rend);
  }
}
