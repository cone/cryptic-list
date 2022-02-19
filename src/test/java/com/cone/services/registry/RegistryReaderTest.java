package com.cone.services.registry;

import java.io.File;
import java.util.Map;

import com.cone.app.Credentials;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RegistryReaderTest {
  static RegistryReader service;

  @BeforeAll
  static void setup() {
    File jsonFile = new File("src/test/resources/data.json");
    service = new RegistryReader(jsonFile, "qwerty");
  }

  @Test
  void whenAllEntriesAreListed_thenSuccess() {
    Map<String, String> entries = service.listAll();

    Assertions.assertEquals("My Credentials", entries.get("12345678"));
  }

  @Test
  void givenAFilePath_whenReadingAnEntry_thenSuccess() throws Exception {
    Credentials creds = service.read("src/test/resources/12345678");

    Assertions.assertEquals("cone@email.com", creds.getUser());
  }
}
