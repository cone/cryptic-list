package com.cone.services.registry;

import java.io.File;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;
import com.cone.services.credentials.CredentialsEncrypter;
import com.cone.services.jsonfile.JsonFileCreator;

public class RegistryCreator extends RegistryBase {
  public RegistryCreator(File entriesFile, String encriptedObjectFilePath, String password) {
    this.entriesFile = entriesFile;
    this.encryptedObjectFilePath = encriptedObjectFilePath;
    this.password = password;
  }

  public void write(Credentials creds, String description, String fileName)
    throws Exception {
    loadEntries();
    File encriptedObjectFile = new File(encryptedObjectFilePath + fileName);
    encryptEntry(encriptedObjectFile, creds);

    entries.put(fileName.toString(), description);
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
