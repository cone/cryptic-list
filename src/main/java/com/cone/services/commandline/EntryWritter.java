package com.cone.services.commandline;

import java.io.File;
import java.net.URISyntaxException;

import com.cone.app.Credentials;
import com.cone.app.EntryData;
import com.cone.services.registry.RegistryCreator;
import com.cone.services.utils.FileLocator;
import com.cone.services.utils.IdGenerator;

public class EntryWritter {
  EntryData data;
  RegistryCreator service;

  public EntryWritter(EntryData data)
    throws URISyntaxException {
    this.data = data;
    initCreator();
  }

  public void addRegistry(Credentials creds, String description) throws Exception {
    IdGenerator generator = data.getIdGenerator();
    service.write(creds, description, generator.createNewId());
    System.out.println("The entry has been created succesfully.");
  }

  private File getEntriesFile() throws URISyntaxException {
    String inputFilePath = FileLocator.getPath(data.getEntriesFilePath());
    return new File(inputFilePath);
  }

  private String getEncriptedObjectsPath() throws URISyntaxException {
    return FileLocator.getPath(data.getEncriptedObjectsFilePath());
  }

  private void initCreator() throws URISyntaxException {
    service = new RegistryCreator(getEntriesFile(), getEncriptedObjectsPath(), data.getPassword());
  }
}
