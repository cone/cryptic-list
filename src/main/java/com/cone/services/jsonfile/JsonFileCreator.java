package com.cone.services.jsonfile;

import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileCreator {
  final ObjectMapper mapper = new ObjectMapper();
  File outputFile;

  public JsonFileCreator(File outputFile) {
    this.outputFile = outputFile;
  }

  public void writeMap(Map<String, String> values) throws
    JsonProcessingException, FileNotFoundException, IOException {
    final String jsonResult = mapper.writeValueAsString(values);
    byte[] strToBytes = jsonResult.getBytes();

    FileOutputStream fos = new FileOutputStream(outputFile);
    fos.write(strToBytes);
    fos.close();
  }
}
