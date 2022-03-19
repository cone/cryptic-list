package com.cone.services.credentials;

import java.io.File;
import java.util.Scanner;

import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;

import org.assertj.core.api.WithAssertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CredentialsEncrypterTest implements WithAssertions {
  @Test
  void testAdd() throws Exception {

    File file = new File("custom_iv_and_salt");
    Credentials creds = new Credentials("cone@email.com", "qwerty123");
    String password = "mypass";
    String salt = "12345678";
    byte[] iv = { 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8 };
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
    CredentialsEncrypter service = new CredentialsEncrypter(password, salt, ivParameterSpec, file);
    service.write(creds);

    Scanner myReader = new Scanner(file);
    String data = "";
    while (myReader.hasNextLine()) {
      data = myReader.nextLine();
    }
    myReader.close();

    Assert.assertEquals("", data);

    file.delete();
  }
}
