package com.cone.services.registry;

import com.cone.app.Credentials;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class RegistryWritterTest {
  @Test
  @Disabled
  void givenJsonFilePath_whenRun_thenSuccess() throws Exception {
    String jsonFilePath = "somefile.json";
    RegistryWritter subject = new RegistryWritter(jsonFilePath, "qwerty");
    Credentials creds = new Credentials("user", "password", "shortDescription");

    Assertions.assertDoesNotThrow(() -> subject.addRegistry(creds, "description"));
  }
}
