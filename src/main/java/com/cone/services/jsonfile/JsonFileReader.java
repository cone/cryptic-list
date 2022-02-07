package com.cone.services.jsonfile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

public class JsonFileReader {
  File jsonFile;

  public JsonFileReader(File jsonFile) {
    this.jsonFile = jsonFile;
  }

  public String read() throws IOException {
    InputStream inputStream = new FileInputStream(jsonFile);
    return readFromInputStream(inputStream);
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
