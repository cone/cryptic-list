package com.cone.services.commandline;

import com.cone.app.Credentials;
import com.cone.services.commandline.interfaces.Wizard;

public class EntryWizard extends PasswordBasedAction implements Wizard {
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
    desc = console.readLine("%s", "short description: ");
    creds = new Credentials(user, password);
  }
}
