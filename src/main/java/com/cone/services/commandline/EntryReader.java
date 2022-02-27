package com.cone.services.commandline;

import java.io.File;
import java.net.URISyntaxException;

import com.cone.app.Credentials;
import com.cone.services.registry.RegistryReader;
import com.cone.services.utils.FileLocator;

public class EntryReader extends KeyBasedAction {
  String id;
  String entriesFilePath;
  RegistryReader reader;

  public EntryReader(String id, String entriesFilePath) throws URISyntaxException {
    this.id = id;
    this.entriesFilePath = entriesFilePath;
    reader = new RegistryReader(getEntriesFile(), getKey());
  }

  public void display() throws Exception {
    Credentials creds = reader.read(id);
    System.out.println(creds.getUser());
    System.out.println(creds.getPassword());
  }

  private File getEntriesFile() throws URISyntaxException {
    String inputFilePath = FileLocator.getPath(entriesFilePath);
    return new File(inputFilePath);
  }
}
