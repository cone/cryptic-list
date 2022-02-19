package com.cone.services.utils;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileLocatorTest {
  @Test
  void givenPath_whenRun_thenNoErrors() {
    Assertions.assertDoesNotThrow(() -> FileLocator.getPath("dummy.json"));
  }

  @Test
  void givenPath_whenRun_theReturnFinalPath() throws URISyntaxException {
    String path = FileLocator.getPath("dummy.json");
    Assertions.assertTrue(path.contains("dummy.json"));
  }
}
