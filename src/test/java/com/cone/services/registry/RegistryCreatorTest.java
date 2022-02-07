package com.cone.services.registry;

import java.io.File;
import java.util.Scanner;

import com.cone.app.Credentials;
import com.cone.mock.FakeIdGenerator;
import com.cone.services.utils.IdGenerator;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegistryCreatorTest implements WithAssertions {
  @Test
  void testWrite() throws Exception {
    File outputFile = new File("data.json");
    Credentials creds = new Credentials("cone@email.com", "qwerty123", "Description");
    IdGenerator idGen = new FakeIdGenerator();
    RegistryCreator service = new RegistryCreator(outputFile, "qwerty", idGen);
    service.write(creds, "My Credentials");

    File objectFile = new File("12345678");

    Scanner myReader = new Scanner(outputFile);
    String data = "";
    while (myReader.hasNextLine()) {
      data = myReader.nextLine();
    }
    myReader.close();

    Assertions.assertEquals(objectFile.exists(), true);
    Assertions.assertEquals(outputFile.exists(), true);
    Assertions.assertEquals("{\"12345678\":\"My Credentials\"}", data);

    outputFile.delete();
    objectFile.delete();
  }
}
