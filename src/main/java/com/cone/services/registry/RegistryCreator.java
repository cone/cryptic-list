package com.cone.services.registry;

import java.io.File;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;
import com.cone.services.credentials.CredentialsEncrypter;
import com.cone.services.jsonfile.JsonFileCreator;
import com.cone.services.utils.FileLocator;

public class RegistryCreator extends RegistryBase {
  public RegistryCreator(File entriesFile, String password) {
    this.entriesFile = entriesFile;
    this.password = password;
  }

  public void write(Credentials creds, String description, String fileId)
    throws Exception {
    loadEntries();

    String outputFilePath = FileLocator.getPath(fileId);
    File outputFile = new File(outputFilePath);
    encryptEntry(outputFile, creds);

    entries.put(fileId, description);
    writeFile(entriesFile, entries);
  }

  private void encryptEntry(File outputFile, Credentials creds) throws Exception {
    IvParameterSpec iv = getIV();
    CredentialsEncrypter encrypter = new CredentialsEncrypter(password, SALT, iv, outputFile);
    encrypter.write(creds);
  }

  private void writeFile(File file, Map<String, String> map) throws Exception {
    JsonFileCreator creator = new JsonFileCreator(file);
    creator.writeMap(map);
  }
}
