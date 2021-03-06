package com.cone.services.commandline;

import java.io.File;
import java.net.URISyntaxException;

import com.cone.app.Credentials;
import com.cone.app.EntryData;
import com.cone.services.utils.FileLocator;
import com.cone.services.utils.IdGenerator;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EntryWritterTest {
  static EntryWritter subject;
  static IdGenerator fakeGenerator;
  static final String jsonFilePath = "somefile.json";
  static String encryptedFilePath;
  static String createdjsonFilePath;
  static EntryData data;

  @BeforeAll
  static void setup() throws URISyntaxException {
    setupIdGenerator();
    setupFilePaths();
    setupEntryData();
    setupSubject();
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
    Credentials creds = new Credentials("user", "password");

    assertDoesNotThrow(() -> subject.addRegistry(creds, "description"));
  }

  @Test
  void givenJsonFilePath_whenRun_thenSuccess() throws Exception {
    Credentials creds = new Credentials("user", "password");
    subject.addRegistry(creds, "description");

    File encryptedFile = new File(encryptedFilePath);
    File createdjsonFile = new File(createdjsonFilePath);

    assertTrue(encryptedFile.exists());
    assertTrue(createdjsonFile.exists());
  }

  private static void setupIdGenerator() {
    fakeGenerator = mock(IdGenerator.class);
    when(fakeGenerator.createNewId()).thenReturn("12345678");
  }

  private static void setupFilePaths() throws URISyntaxException {
    encryptedFilePath = FileLocator.getPath(fakeGenerator.createNewId());
    createdjsonFilePath = FileLocator.getPath(jsonFilePath);
  }

  private static void setupEntryData() throws URISyntaxException {
    data = new EntryData(
      jsonFilePath,
      "",
      "qwerty",
      fakeGenerator
    );
  }

  private static void setupSubject() throws URISyntaxException {
    subject = new EntryWritter(data);
  }
}

