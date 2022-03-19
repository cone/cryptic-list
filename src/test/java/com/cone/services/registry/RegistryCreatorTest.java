package com.cone.services.registry;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Scanner;

import com.cone.app.Credentials;
import com.cone.services.utils.FileLocator;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistryCreatorTest implements WithAssertions {
  File outputFile;
  File objectFile;
  RegistryCreator service;
  final String fileId = "12345678";

  @BeforeEach
  void setup() throws URISyntaxException {
    outputFile = new File("data.json");
    objectFile = new File(FileLocator.getPath(fileId));
    service = new RegistryCreator(outputFile, "qwerty");
  }

  @AfterEach
  void cleanup() {
    outputFile.delete();
    objectFile.delete();
  }

  @Test
  void givenSomeCredentialsAndDescription_whenWritting_thenSuccess() throws Exception {
    Credentials creds = new Credentials("cone@email.com", "qwerty123");
    service.write(creds, "My Credentials", fileId);

    Scanner myReader = new Scanner(outputFile);
    String data = "";
    while (myReader.hasNextLine()) {
      data = myReader.nextLine();
    }
    myReader.close();

    Assertions.assertEquals(objectFile.exists(), true);
    Assertions.assertEquals(outputFile.exists(), true);
    Assertions.assertEquals("{\"12345678\":\"My Credentials\"}", data);
  }
}
