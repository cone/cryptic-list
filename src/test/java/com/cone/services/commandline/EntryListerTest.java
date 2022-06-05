package com.cone.services.commandline;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntryListerTest {
  static EntryLister service;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeAll
  static void setup() throws IOException {
    service = new EntryLister("../src/test/resources/data.json");
  }

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  void whenListingEntries_thenSuccess() {
    service.list();
    String output = outContent.toString();
    Assertions.assertEquals(true, output.contains("hardcoded_iv_and_salt"));
    Assertions.assertEquals(true, output.contains("My Credentials"));
  }
}
