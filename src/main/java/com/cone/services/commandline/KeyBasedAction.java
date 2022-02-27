package com.cone.services.commandline;

import java.io.Console;

public abstract class KeyBasedAction {
  Console console;
  String key;

  public KeyBasedAction() {
    this.console = System.console();
    askForKey();
  }

  public String getKey() {
    return key;
  }

  private void askForKey() {
    key = new String(console.readPassword("key: "));
  }
}
