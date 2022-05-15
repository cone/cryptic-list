package com.cone.services.registry;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegistriesTableTest {
  @Test
  void testRender() {
    Map<String, String> entries = new HashMap<String, String>() {{
      put("key1", "value1");
      put("key2", "value2");
    }};
    RegistriesTable table = new RegistriesTable(entries);
    String result = table.render();

    Assertions.assertTrue(result.contains("key1"));
    Assertions.assertTrue(result.contains("value1"));
    Assertions.assertTrue(result.contains("key2"));
    Assertions.assertTrue(result.contains("value2"));
  }
}
