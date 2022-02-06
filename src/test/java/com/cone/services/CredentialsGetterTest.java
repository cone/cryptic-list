package com.cone.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;

import org.assertj.core.api.WithAssertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CredentialsGetterTest implements WithAssertions {
  @Test
  void testGet() throws InvalidKeyException, ClassNotFoundException,
    NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
    InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException,
    IOException {

    File objectFile = Paths.get("src/test/resources/object.txt").toFile();
    String password = "mypass";
    String salt = "12345678";
    byte[] iv = { 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8 };
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
    CredentialsGetter service = new CredentialsGetter(password, salt, ivParameterSpec, objectFile);
    Credentials creds = service.get("test");

    Assert.assertEquals("cone@email.com", creds.getUser());
  }
}
