package com.cone.services.credentials;

import java.io.File;
import java.nio.file.Paths;

import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;

import org.assertj.core.api.WithAssertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CredentialsDecryperTest implements WithAssertions {
  @Test
  void testGet() throws Exception {

    File objectFile = Paths.get("src/test/resources/object.txt").toFile();
    String password = "mypass";
    String salt = "12345678";
    byte[] iv = { 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8 };
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
    CredentialsDecrypter service = new CredentialsDecrypter(password, salt, ivParameterSpec, objectFile);
    Credentials creds = service.read();

    Assert.assertEquals("cone@email.com", creds.getUser());
  }
}
