package com.cone.services.registry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import com.cone.app.Credentials;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RegistryReaderTest {
  static RegistryReader service;

  @BeforeAll
  static void setup() throws IOException {
    File jsonFile = new File("src/test/resources/data.json");
    service = new RegistryReader(jsonFile, "qwerty");
    copyTestFiles();
  }

  static void copyTestFiles() throws IOException {
    Path copiedPath = Paths.get("target/12345678");
    Path originalPath = Paths.get("src/test/resources/12345678");
    Files.copy(originalPath, copiedPath, StandardCopyOption.REPLACE_EXISTING);
  }

  @Test
  void whenAllEntriesAreListed_thenSuccess() {
    Map<String, String> entries = service.listAll();

    Assertions.assertEquals("My Credentials", entries.get("12345678"));
  }

  @Test
  void givenAFilePath_whenReadingAnEntry_thenSuccess() throws Exception {
    Credentials creds = service.read("12345678");

    Assertions.assertEquals("cone@email.com", creds.getUser());
  }
}
