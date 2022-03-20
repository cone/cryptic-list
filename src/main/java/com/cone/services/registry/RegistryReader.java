package com.cone.services.registry;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;
import com.cone.services.credentials.CredentialsDecrypter;
import com.cone.services.utils.FileLocator;

public class RegistryReader extends RegistryBase {
  public RegistryReader(File entriesFile, String password) {
    this.entriesFile = entriesFile;
    this.password = password;
  }

  public Map<String, String> listAll() {
    loadEntries();
    return entries;
  }

  public Credentials read(String key) throws Exception {
    IvParameterSpec iv = getIV();
    CredentialsDecrypter decrypter = new CredentialsDecrypter(password, SALT, iv, getCredentialsFile(key));
    return decrypter.read();
  }

  private File getCredentialsFile(String path) throws URISyntaxException {
    String objectFile = FileLocator.getPath(path);
    return new File(objectFile);
  }
}
