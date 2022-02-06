package com.cone.services;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;

import org.assertj.core.api.WithAssertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CredentialsSetterTest implements WithAssertions {
  @Test
  void testAdd() throws 
    IOException, NoSuchAlgorithmException, InvalidKeyException,
    NoSuchPaddingException, InvalidAlgorithmParameterException,
    IllegalBlockSizeException, InvalidKeySpecException, ClassNotFoundException, BadPaddingException {

    File file = new File("delete.txt");
    Credentials creds = new Credentials("cone@email.com", "qwerty123", "My credentials");
    String password = "mypass";
    String salt = "12345678";
    byte[] iv = { 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8 };
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
    CredentialsSetter service = new CredentialsSetter(password, salt, ivParameterSpec, file);
    service.add(creds);

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
