package com.cone.services.jsonfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonFileReaderTest implements WithAssertions {
  @Test
  void testRead() throws IOException {
    File jsonFile = Paths.get("src/test/resources/test.json").toFile();
    JsonFileReader service = new JsonFileReader(jsonFile);
    Map<String, String> content = service.read();

    Assertions.assertEquals("world", content.get("hello"));
  }
}
