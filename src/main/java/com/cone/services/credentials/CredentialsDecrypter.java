package com.cone.services.credentials;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SealedObject;
import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;

public class CredentialsDecrypter extends CredentialsCrypto {
  public CredentialsDecrypter(String password, String salt,
    IvParameterSpec ivParameterSpec, File file) {

    this.password = password;
    this.salt = salt;
    this.ivParameterSpec = ivParameterSpec;
    this.file = file;
  }

  public Credentials read() throws Exception {
    Cipher cipher = getCipher(Cipher.DECRYPT_MODE);

    FileInputStream fileIn = new FileInputStream(file);
    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
    ObjectInputStream in = new ObjectInputStream(cipherIn);

    SealedObject sealed = (SealedObject) in.readObject();
    Credentials credentials = (Credentials) sealed.getObject(cipher);

    in.close();
    fileIn.close();
    return credentials;
  }  
}
