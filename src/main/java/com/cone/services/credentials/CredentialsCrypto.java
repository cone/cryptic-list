package com.cone.services.credentials;

import java.io.File;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.cone.utils.AESUtils;

public abstract class CredentialsCrypto {
  public static final String ALGORITHM = "AES/CBC/PKCS5Padding";
  String password;
  String salt;
  IvParameterSpec ivParameterSpec;
  File file;

  public Cipher getCipher(int MODE) throws Exception {
    SecretKey key = AESUtils.getKeyFromPassword(password, salt);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(MODE, key, ivParameterSpec);
    return cipher;
  }
}
