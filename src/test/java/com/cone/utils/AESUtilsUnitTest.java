package com.cone.utils;

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
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.cone.app.Credentials;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AESUtilsUnitTest implements WithAssertions {

    @Test
    void givenString_whenEncrypt_thenSuccess()
        throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
        BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        // given
        String input = "anitalavalatina";
        SecretKey key = AESUtils.generateKey(128);
        IvParameterSpec ivParameterSpec = AESUtils.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";

        // when
        String cipherText = AESUtils.encrypt(algorithm, input, key, ivParameterSpec);
        String plainText = AESUtils.decrypt(algorithm, cipherText, key, ivParameterSpec);

        // then
        Assertions.assertEquals(input, plainText);
    }
 
    @Test
    void givenFile_whenEncrypt_thenSuccess()
        throws NoSuchAlgorithmException, IOException, IllegalBlockSizeException, InvalidKeyException,
        BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        // given
        SecretKey key = AESUtils.generateKey(128);
        String algorithm = "AES/CBC/PKCS5Padding";
        IvParameterSpec ivParameterSpec = AESUtils.generateIv();
        File inputFile = Paths.get("src/test/resources/cone.txt")
            .toFile();
        File encryptedFile = new File("classpath:cone.encrypted");
        File decryptedFile = new File("document.decrypted");

        // when
        AESUtils.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
        AESUtils.decryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);

        // then
        assertThat(inputFile).hasSameTextualContentAs(decryptedFile);
        encryptedFile.delete();
        decryptedFile.delete();
    }

    @Test
    void givenObject_whenEncrypt_thenSuccess()
        throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
        InvalidAlgorithmParameterException, NoSuchPaddingException, IOException, BadPaddingException,
        ClassNotFoundException {
        // given
        Credentials credentials = new Credentials("cone@email.com", "qwerty123");
        SecretKey key = AESUtils.generateKey(128);
        IvParameterSpec ivParameterSpec = AESUtils.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";

        // when
        SealedObject sealedObject = AESUtils.encryptObject(algorithm, credentials, key, ivParameterSpec);
        Credentials object = (Credentials) AESUtils.decryptObject(algorithm, sealedObject, key, ivParameterSpec);

        // then
        assertThat(credentials).isEqualTo(object);
    }

    @Test
    void givenPassword_whenEncrypt_thenSuccess()
        throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException,
        InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        // given
        String plainText = "anitalavalatina";
        String password = "mypass";
        String salt = "12345678";
        IvParameterSpec ivParameterSpec = AESUtils.generateIv();
        SecretKey key = AESUtils.getKeyFromPassword(password, salt);

        // when
        String cipherText = AESUtils.encryptPasswordBased(plainText, key, ivParameterSpec);
        String decryptedCipherText = AESUtils.decryptPasswordBased(cipherText, key, ivParameterSpec);

        // then
        Assertions.assertEquals(plainText, decryptedCipherText);
    }
}