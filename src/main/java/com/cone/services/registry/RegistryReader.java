package com.cone.services.registry;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;
import com.cone.services.credentials.CredentialsDecrypter;

public class RegistryReader extends RegistryBase {
  public RegistryReader(File entriesFile, String encryptedObjectFilePath, String password) {
    this.entriesFile = entriesFile;
    this.encryptedObjectFilePath = encryptedObjectFilePath;
    this.password = password;
  }

  public Map<String, String> listAll() {
    loadEntries();
    return entries;
  }

  public Credentials read(String fileName) throws Exception {
    IvParameterSpec iv = getIV();
    CredentialsDecrypter decrypter = new CredentialsDecrypter(password, SALT, iv, getCredentialsFile(fileName));
    return decrypter.read();
  }

  private File getCredentialsFile(String fileName) throws URISyntaxException {
    String fullPath = encryptedObjectFilePath + fileName;
    return new File(fullPath);
  }
}
