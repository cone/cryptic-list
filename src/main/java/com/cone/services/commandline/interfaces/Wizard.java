package com.cone.services.commandline.interfaces;

import com.cone.app.Credentials;

public interface Wizard {
  String getKey();
  String getDescription();
  Credentials getCredentials();
}
