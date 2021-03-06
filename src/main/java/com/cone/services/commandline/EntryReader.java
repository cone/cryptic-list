package com.cone.services.commandline;

import java.io.File;
import java.net.URISyntaxException;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;

import com.cone.app.Credentials;
import com.cone.services.registry.RegistryReader;
import com.cone.services.utils.FileLocator;

public class EntryReader extends PasswordBasedAction {
  String id;
  String entriesFilePath;
  String encryptedObjectFilePath;
  RegistryReader reader;
  final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

  public EntryReader(String id, String entriesFilePath, String encryptedObjectFilePath) throws URISyntaxException {
    this.id = id;
    this.entriesFilePath = entriesFilePath;
    this.encryptedObjectFilePath = encryptedObjectFilePath;
    reader = new RegistryReader(getEntriesFile(), getEncryptedObjectPath(), getPassword());
  }

  public void display() throws Exception {
    Credentials creds = reader.read(id);
    System.out.println("User: " + creds.getUser());
    copyToClipboard(creds.getPassword());
    System.out.println("The password has been copied to the clipboard.");
  }

  private File getEntriesFile() throws URISyntaxException {
    String inputFilePath = FileLocator.getPath(entriesFilePath);
    return new File(inputFilePath);
  }

  private String getEncryptedObjectPath() throws URISyntaxException {
    return FileLocator.getPath(encryptedObjectFilePath);
  }

  private void copyToClipboard(String data) {
    StringSelection selection = new StringSelection(data);
    clipboard.setContents(selection, selection);
  }
}
