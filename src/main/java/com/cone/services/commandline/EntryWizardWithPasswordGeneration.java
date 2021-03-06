package com.cone.services.commandline;

import com.cone.app.Credentials;
import com.cone.services.commandline.interfaces.Wizard;
import com.cone.utils.PasswordGenerator;

public class EntryWizardWithPasswordGeneration extends PasswordBasedAction implements Wizard {
  Credentials creds;
  String desc;

  public EntryWizardWithPasswordGeneration() {
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
    String password = PasswordGenerator.getPassword(PasswordGenerator.DEFAULT_PASSWORD_LENGTH);
    desc = console.readLine("%s", "short description: ");
    creds = new Credentials(user, password);
  }
}
