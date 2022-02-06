package com.sadoon.cbotbdd.util;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;

import static com.sadoon.cbotbdd.util.TestListener.LOGGER;

public class MockPasswordGenerator {

    private final char[] pwdArray = System.getenv("KEYSTORE_PASS").toCharArray();
    private final Path keystorePath = Paths.get("./keystore/cryptobot_ks.pfx");

    public String encryptCardPassword(String cardName, String password, String username) {
        String encrypted = null;
        try {
            SecretKey key = generateKey(256);
            SecretKey iv = new SecretKeySpec(getIv(), "AES");
            String signature = signature(cardName, password, username);

            encrypted = encrypt(
                    "AES/GCM/NoPadding",
                    password,
                    key,
                    iv.getEncoded()
            );

            addToKeyStore(key, String.format("%skey", signature));
            addToKeyStore(iv, String.format("%siv", signature));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(String.format("Error while encrypting card password: %1s", e));
        }
        return encrypted;
    }

    private byte[] getIv() {
        byte[] iv = new byte[12];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }

    private String signature(String update, String initKey, String macUpdate) {
        String signature = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((update.getBytes()));
            signature = new String(Base64.getEncoder().encode(
                    getPrimedHmac(initKey, macUpdate).doFinal(md.digest())
            ));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            LOGGER.error("Error while creating signature: ", e);
        }
        return signature;
    }

    private Mac getPrimedHmac(String initKey, String macUpdate) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmac = Mac.getInstance("HmacSHA512");
        hmac.init(new SecretKeySpec(Base64.getDecoder().decode(
                initKey.replaceAll("[=]", "").trim()), "HmacSHA512"));
        hmac.update(macUpdate.getBytes());
        return hmac;
    }

    private SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(n);
        return keyGen.generateKey();
    }

    private String encrypt(String algorithm, String input, SecretKey key, byte[] iv) {

        Cipher cipher;
        byte[] cipherText = new byte[0];
        try {
            cipher = Cipher.getInstance(algorithm);
            GCMParameterSpec params = new GCMParameterSpec(128, iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, params);
            cipherText = cipher.doFinal(input.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            LOGGER.error(String.format("Error while trying to encrypt key: %1s", e));
        }
        return Base64.getEncoder().encodeToString(cipherText);

    }

    private void addToKeyStore(SecretKey key, String alias) {
        try (InputStream inputStream = Files.newInputStream(keystorePath)) {

            KeyStore keyStore = getKeystore(inputStream);
            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(key);
            KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(pwdArray);
            keyStore.setEntry(alias, secretKeyEntry, protectionParameter);
            keyStore.store(new FileOutputStream(keystorePath.toString()), pwdArray);

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException |
                CertificateException e) {
            LOGGER.error(String.format("Error while accessing key store: %1s", e));
        }
    }

    private KeyStore getKeystore(InputStream inputStream) throws
            CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(inputStream, pwdArray);
        return keyStore;
    }

}