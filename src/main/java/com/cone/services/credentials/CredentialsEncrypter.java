package com.cone.services.credentials;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SealedObject;
import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;

public class CredentialsEncrypter extends CredentialsCrypto {
  public CredentialsEncrypter(String password, String salt,
    IvParameterSpec ivParameterSpec, File file) {

    this.password = password;
    this.salt = salt;
    this.ivParameterSpec = ivParameterSpec;
    this.file = file;
  }

  public void write(Credentials credentials) throws Exception {
    Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);

    SealedObject sealedObject = new SealedObject(credentials, cipher);

    FileOutputStream fileOut = new FileOutputStream(file);
    CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher);
    ObjectOutputStream out = new ObjectOutputStream(cipherOut);
  
    out.writeObject(sealedObject);
    out.close();
    fileOut.close();
  }
}
