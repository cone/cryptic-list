package com.cone.services.registry;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;

import com.cone.services.jsonfile.JsonFileReader;

public abstract class RegistryBase {
  File entriesFile;
  String encryptedObjectFilePath;
  public final String SALT = "12345678";
  Map<String, String> entries;
  String password;

  protected IvParameterSpec getIV() {
    byte[] iv = { 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8 };
    return new IvParameterSpec(iv);
  }

  protected void loadEntries() {
    try {
      entries = readFile(entriesFile);
    } catch(IOException error) {
      entries = new HashMap<>();
    }
  }

  protected Map<String, String> readFile(File jsonFile) throws IOException{
    JsonFileReader fileReader = new JsonFileReader(jsonFile);
    return fileReader.read();
  }
}
