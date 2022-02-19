package com.cone.services.registry;

import java.io.File;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;
import com.cone.services.credentials.CredentialsEncrypter;
import com.cone.services.jsonfile.JsonFileCreator;
import com.cone.services.utils.FileLocator;
import com.cone.services.utils.IdGenerator;

public class RegistryCreator extends RegistryBase {
  IdGenerator idGenerator;

  public RegistryCreator(File entriesFile, String password, IdGenerator idGenerator) {
    this.entriesFile = entriesFile;
    this.password = password;
    this.idGenerator = idGenerator;
  }

  public void write(Credentials creds, String description) throws Exception {
    loadEntries();

    String outputFileName = getUniqueId();
    String outputFilePath = FileLocator.getPath(outputFileName);
    File outputFile = new File(outputFilePath);
    encryptEntry(outputFile, creds);

    entries.put(outputFileName, description);
    writeFile(entriesFile, entries);
  }

  private String getUniqueId() {
    return idGenerator.createNewId();
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
