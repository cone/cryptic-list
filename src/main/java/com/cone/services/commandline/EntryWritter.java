package com.cone.services.commandline;

import java.io.File;
import java.net.URISyntaxException;

import com.cone.app.Credentials;
import com.cone.services.registry.RegistryCreator;
import com.cone.services.utils.FileLocator;
import com.cone.services.utils.IdGenerator;

public class EntryWritter {
  String entriesFile, key;
  RegistryCreator service;
  IdGenerator idGenerator;

  public EntryWritter(String entriesFile, String key, IdGenerator idGenerator)
    throws URISyntaxException {
    this.entriesFile = entriesFile;
    this.key = key;
    this.idGenerator = idGenerator;
    initCreator();
  }

  public void addRegistry(Credentials creds, String description) throws Exception {
    service.write(creds, description, idGenerator.createNewId());
  }

  private File getEntriesFile() throws URISyntaxException {
    String inputFilePath = FileLocator.getPath(entriesFile);
    return new File(inputFilePath);
  }

  private void initCreator() throws URISyntaxException {
    service = new RegistryCreator(getEntriesFile(), key);
  }
}
