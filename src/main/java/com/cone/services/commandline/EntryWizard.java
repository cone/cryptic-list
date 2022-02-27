package com.cone.services.commandline;

import com.cone.app.Credentials;

public class EntryWizard extends KeyBasedAction {
  Credentials creds;
  String desc;

  public EntryWizard() {
    askForCredentials();
  }

  public Credentials getCredentials() {
    return creds;
  }

  public String getDescription() {
    return desc;
  }

  private void askForCredentials() {
    String user = console.readLine("%s", "user (email or name): ");
    String password = new String(console.readPassword("user password: "));
    // TODO: The description shouldn't be part of the credentials
    desc = console.readLine("%s", "short description: ");
    creds = new Credentials(user, password, desc);
  }
}
