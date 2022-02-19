package com.cone.services.registry;

import java.io.File;
import java.net.URISyntaxException;

import com.cone.app.Credentials;
import com.cone.services.utils.IdGenerator;
import com.cone.services.utils.FileLocator;
import com.cone.services.utils.UuidGenerator;

public class RegistryWritter {
  private String fileName, key;
  private RegistryCreator service;

  public RegistryWritter(String fileName, String key) throws URISyntaxException {
    this.fileName = fileName;
    this.key = key;
    initCreator();
  }

  public void addRegistry(Credentials creds, String description) throws Exception {
    service.write(creds, description);
  }

  private File getFile() throws URISyntaxException {
    String inputFilePath = FileLocator.getPath(fileName);
    return new File(inputFilePath);
  }

  private void initCreator() throws URISyntaxException {
    IdGenerator idGenerator = new UuidGenerator();
    service = new RegistryCreator(getFile(), key, idGenerator);
  }
}
