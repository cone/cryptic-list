package com.cone.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;
import com.cone.utils.AESUtils;

public class CredentialsGetter {
  String password;
  String salt;
  IvParameterSpec ivParameterSpec;
  File file;

  public CredentialsGetter(String password, String salt,
    IvParameterSpec ivParameterSpec, File file) {

    this.password = password;
    this.salt = salt;
    this.ivParameterSpec = ivParameterSpec;
    this.file = file;
  }

  public Credentials get(String id) throws 
    IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException,
    InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException,
    BadPaddingException, IllegalBlockSizeException {

    String algorithm = "AES/CBC/PKCS5Padding";
    SecretKey key = AESUtils.getKeyFromPassword(password, salt);

    FileInputStream fileIn = new FileInputStream(file);
    Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
    ObjectInputStream in = new ObjectInputStream(cipherIn);

    SealedObject sealed = (SealedObject) in.readObject();

    Credentials credentials = (Credentials) sealed.getObject(cipher);

    in.close();

    fileIn.close();
    return credentials;
  }  
}
