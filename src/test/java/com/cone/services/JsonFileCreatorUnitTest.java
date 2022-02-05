package com.cone.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonFileCreatorUnitTest implements WithAssertions{
  final ObjectMapper mapper = new ObjectMapper();

  @Test
  void whenMapJsonWrite_thenCorrect() throws
    JsonProcessingException, FileNotFoundException, IOException {

    File outputFile = new File("document.json");
    JsonFileCreator service = new JsonFileCreator(outputFile);

    Map<String, String> map = new HashMap<>();
    map.put("key", "value");

    service.writeMap(map);

    Scanner myReader = new Scanner(outputFile);
    String data = "";
    while (myReader.hasNextLine()) {
      data = myReader.nextLine();
    }
    myReader.close();

    Assertions.assertEquals("{\"key\":\"value\"}", data);

    outputFile.delete();
  }
}
