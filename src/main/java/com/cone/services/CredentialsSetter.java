package com.cone.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;
import com.cone.utils.AESUtils;

public class CredentialsSetter {
  String password;
  String salt;
  IvParameterSpec ivParameterSpec;
  File file;

  public CredentialsSetter(String password, String salt,
    IvParameterSpec ivParameterSpec, File file) {

    this.password = password;
    this.salt = salt;
    this.ivParameterSpec = ivParameterSpec;
    this.file = file;
  }

  public void add(Credentials credentials) throws 
    IOException, NoSuchAlgorithmException, InvalidKeyException,
    NoSuchPaddingException, InvalidAlgorithmParameterException,
    IllegalBlockSizeException, InvalidKeySpecException {

    SecretKey key = AESUtils.getKeyFromPassword(password, salt);
    String algorithm = "AES/CBC/PKCS5Padding";
    Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
    SealedObject sealedObject = new SealedObject(credentials, cipher);

    FileOutputStream fileOut = new FileOutputStream(file);
    CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher);
    ObjectOutputStream out = new ObjectOutputStream(cipherOut);
    out.writeObject(sealedObject);
    out.close();
    fileOut.close();
  }
}
