package com.cone.services.commandline;

import java.io.Console;

public abstract class PasswordBasedAction {
  Console console;
  String password;

  public PasswordBasedAction() {
    this.console = System.console();
    askForPassword();
  }

  public String getPassword() {
    return password;
  }

  private void askForPassword() {
    password = new String(console.readPassword("key: "));
  }
}
