package com.cone.services.registry;

import java.io.File;
import java.util.Map;

import com.cone.services.jsonfile.JsonFileReader;
import com.cone.services.utils.FileLocator;

public class RegistriesLoader {
  private String fileName;
  private JsonFileReader service;

  public RegistriesLoader(String fileName) throws Exception {
    this.fileName = fileName;
    loadJsonFile();
  }

  public Map<String, String> getEntries() throws Exception {
    return service.read();
  }

  private void loadJsonFile() throws Exception {
    String inputFilePath = FileLocator.getPath(fileName);
    File jsonFile = new File(inputFilePath);
    service = new JsonFileReader(jsonFile);
  }
}
