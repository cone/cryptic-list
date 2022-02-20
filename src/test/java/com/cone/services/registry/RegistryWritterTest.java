package com.cone.services.registry;

import java.io.File;
import java.net.URISyntaxException;

import com.cone.app.Credentials;
import com.cone.mock.FakeIdGenerator;
import com.cone.services.utils.FileLocator;
import com.cone.services.utils.IdGenerator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RegistryWritterTest {
  static RegistryWritter subject;
  static IdGenerator fakeGenerator = new FakeIdGenerator();
  static final String jsonFilePath = "somefile.json";
  static String encryptedFilePath;
  static String createdjsonFilePath;

  @BeforeAll
  static void setup() throws URISyntaxException {
    subject = new RegistryWritter(jsonFilePath, "qwerty");
    subject.setIdGenerator(fakeGenerator);
    encryptedFilePath = FileLocator.getPath(fakeGenerator.createNewId());
    createdjsonFilePath = FileLocator.getPath(jsonFilePath);
  }

  @AfterEach
  void cleanupForEach() throws URISyntaxException {
    File encryptedFile = new File(encryptedFilePath);
    File createdjsonFile = new File(createdjsonFilePath);
    encryptedFile.delete();
    createdjsonFile.delete();
  }

  @Test
  void givenJsonFilePath_whenRun_thenNoErrors() {
    Credentials creds = new Credentials("user", "password", "shortDescription");

    Assertions.assertDoesNotThrow(() -> subject.addRegistry(creds, "description"));
  }

  @Test
  void givenJsonFilePath_whenRun_thenSuccess() throws Exception {
    Credentials creds = new Credentials("user", "password", "shortDescription");
    subject.addRegistry(creds, "description");

    File encryptedFile = new File(encryptedFilePath);
    File createdjsonFile = new File(createdjsonFilePath);

    Assertions.assertTrue(encryptedFile.exists());
    Assertions.assertTrue(createdjsonFile.exists());
  }
}
