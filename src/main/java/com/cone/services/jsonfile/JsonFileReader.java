package com.cone.services.jsonfile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

public class JsonFileReader {
  final ObjectMapper mapper = new ObjectMapper();
  File jsonFile;

  public JsonFileReader(File jsonFile) {
    this.jsonFile = jsonFile;
  }

  public HashMap<String, String> read() throws IOException {
    InputStream inputStream = new FileInputStream(jsonFile);
    String jsonInput = readFromInputStream(inputStream);
    TypeReference<HashMap<String, String>> typeRef 
      = new TypeReference<HashMap<String, String>>() {};
    return mapper.readValue(jsonInput, typeRef);
  }

  private String readFromInputStream(InputStream inputStream)
  throws IOException {
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br
      = new BufferedReader(new InputStreamReader(inputStream))) {
        String line;
        while ((line = br.readLine()) != null) {
            resultStringBuilder.append(line);
        }
    }
    return resultStringBuilder.toString();
  }
}
