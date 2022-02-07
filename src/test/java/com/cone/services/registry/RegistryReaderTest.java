package com.cone.services.registry;

import java.io.File;
import java.util.Map;

import com.cone.app.Credentials;

import org.assertj.core.api.WithAssertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class RegistryReaderTest implements WithAssertions {
  @Test
  void testListAll() {
    File jsonFile = new File("src/test/resources/data.json");
    RegistryReader service = new RegistryReader(jsonFile, "qwerty");
    Map<String, String> entries = service.listAll();

    Assert.assertEquals("My Credentials", entries.get("12345678"));
  }

  @Test
  void testRead() throws Exception {
    File jsonFile = new File("src/test/resources/data.json");
    RegistryReader service = new RegistryReader(jsonFile, "qwerty");
    Credentials creds = service.read("src/test/resources/12345678");

    Assert.assertEquals("cone@email.com", creds.getUser());
  }
}
