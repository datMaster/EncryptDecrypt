package com.company;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import java.security.InvalidKeyException;
import java.security.Key;

public class Main {

    private static String algorithm = "DESede";
    private static Key key = null;
    private static Cipher cipher = null;

    public static void main(String[] args) {
        String encrypData = "HP Pavilion DV7 (CPU : Core 2 Duo(2 x 2.4Gz), RAM : 4Gb, HDD 750Gb + 250Gb )nnnndnfndfng dfn gfdn n n dnfng dfn gdf gnd fng dfn gdfn gn dfng dfn gndf gHP Pavilion DV7 (CPU : Core 2 Duo(2 x 2.4Gz), RAM : 4Gb, HDD 750Gb + 250Gb )nnnndnfndfng dfn gfdn n n dnfng dfn gdf gnd fng dfn gdfn gn dfng dfn gndf g";
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        byte[] encriptBytes = encrypt(encrypData);
        String keyString = (key.toString()).substring(key.toString().lastIndexOf('.') + 10, key.toString().length() - 1);
        System.out.println(
                         "\nInput string : " + encrypData
                        + "\nKey : " + keyString
                        + "\nEncrypted string : " + encriptBytes.toString()
                        + "\nDecrypted String : " + decrypt(encriptBytes)
                        + "\nAlgorithm : " + key.getAlgorithm());
    }

    private static void init() throws Exception {
        key = KeyGenerator.getInstance(algorithm).generateKey();
        cipher = Cipher.getInstance(algorithm);
    }

    private static byte[] encrypt(String input) {

        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        byte[] inputBytes = input.getBytes();
        byte[] result = new byte[0];
        try {
            result = cipher.doFinal(inputBytes);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String decrypt(byte[] encryptionBytes) {

        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] recoveredBytes = new byte[0];
        try {
            recoveredBytes = cipher.doFinal(encryptionBytes);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(recoveredBytes);
    }
}
