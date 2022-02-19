package com.cone.services.registry;

import java.io.File;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;
import com.cone.services.credentials.CredentialsDecrypter;

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
    File objectFile = new File(key);
    IvParameterSpec iv = getIV();
    CredentialsDecrypter decrypter = new CredentialsDecrypter(password, SALT, iv, objectFile);
    return decrypter.read();
  }
}
