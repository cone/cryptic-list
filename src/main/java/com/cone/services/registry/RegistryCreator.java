package com.cone.services.registry;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;
import com.cone.services.credentials.CredentialsEncrypter;
import com.cone.services.jsonfile.JsonFileCreator;
import com.cone.services.jsonfile.JsonFileReader;
import com.cone.services.utils.IdGenerator;

public class RegistryCreator {
  File file;
  private final String salt = "12345678";
  Map<String, String> entries;
  String password;
  IdGenerator idGenerator;

  public RegistryCreator(File file, String password, IdGenerator idGenerator) {
    this.file = file;
    this.password = password;
    this.idGenerator = idGenerator;
  }

  public void write(Credentials creds, String description) throws Exception {
    try {
      entries = readFile(file);
    } catch(IOException error) {
      entries = new HashMap<>();
    }

    String outputFileName = getUniqueId();
    File outputFile = new File(outputFileName);
    encryptEntry(outputFile, creds);

    entries.put(outputFileName, description);
    writeFile(file, entries);
  }

  private Map<String, String> readFile(File jsonFile) throws IOException{
    JsonFileReader fileReader = new JsonFileReader(jsonFile);
    return fileReader.read();
  }

  private String getUniqueId() {
    return idGenerator.createNewId();
  }

  private IvParameterSpec getIV() {
    byte[] iv = { 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8 };
    return new IvParameterSpec(iv);
  }

  private void encryptEntry(File outputFile, Credentials creds) throws Exception {
    IvParameterSpec iv = getIV();
    CredentialsEncrypter encrypter = new CredentialsEncrypter(password, salt, iv, outputFile);
    encrypter.write(creds);
  }

  private void writeFile(File file, Map<String, String> map) throws Exception {
    JsonFileCreator creator = new JsonFileCreator(file);
    creator.writeMap(map);
  }
}
