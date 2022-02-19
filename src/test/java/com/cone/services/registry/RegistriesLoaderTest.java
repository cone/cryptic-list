package com.cone.services.registry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegistriesLoaderTest {
  @Test
  void givenInvalidJsonFilePath_whenRun_thenThrowsError() throws Exception {
    String jsonFilePath = "nofile";
    RegistriesLoader subject = new RegistriesLoader(jsonFilePath);

    Assertions.assertThrows(Exception.class, () -> subject.getEntries() );
  }
}
