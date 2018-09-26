package com.mert.light.tools.encryption;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {
    //Encrypt
    private byte[] privateModulusBytes;
    private byte[] privateExponentBytes;
    private BigInteger privateModulusBigInteger;
    private BigInteger privateExponentBigInteger;
    private RSAPublicKeySpec publicKeySpec;
    private PublicKey publicKey;

    //Decrypt
    private byte[] publicodulusBytes;
    private byte[] publicExponentBytes;
    private BigInteger publicModulusBigInteger;
    private BigInteger publicExponentBigInteger;
    private RSAPrivateKeySpec privateKeySpec;
    private PrivateKey privateKey;


    //Both
    private KeyFactory keyFactory;
    private Cipher cipher;


    public RSA() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        //Both
        keyFactory = KeyFactory.getInstance("RSA");
        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        //Private
        this.privateModulusBytes = Base64.decode("AJmvQm09j0UQEvHgZVt6KFjF8SI6Tj1M4xCpU1/Tof14UrUQycR238bFbbIVYuoyXGCsKi/kEqCJyZ0GrDeShXLaVoD5ErPZ7Yw6KMXsoprYlRo77h7zqb6Bep+UUhRtPR/PIhUoaLzDX3UaqKuEiaLlQcILqhqePIFtfrRZafT/zLIDGtyMOGK9E0API7ADddLPBd3jVqDUBA4mSXFqBOVIi+nHxvvEDi5IEdisEVYvKYv7xeAklsPRKmeQ0PsbO16z2fq4iXhus6Wn9Kcf/I4sz/AsF0puTlDczVScPdzRtVRM9lPg68MWFM10WaFmmJ4alNGmn0pDlWD9jsXLk2k=", Base64.DEFAULT);
        this.privateExponentBytes = Base64.decode("AQAB", Base64.DEFAULT);

        this.privateModulusBigInteger = new BigInteger(1, this.privateModulusBytes);
        this.privateExponentBigInteger = new BigInteger(1, this.privateExponentBytes);

        //Public
        this.publicodulusBytes = Base64.decode("AJmvQm09j0UQEvHgZVt6KFjF8SI6Tj1M4xCpU1/Tof14UrUQycR238bFbbIVYuoyXGCsKi/kEqCJyZ0GrDeShXLaVoD5ErPZ7Yw6KMXsoprYlRo77h7zqb6Bep+UUhRtPR/PIhUoaLzDX3UaqKuEiaLlQcILqhqePIFtfrRZafT/zLIDGtyMOGK9E0API7ADddLPBd3jVqDUBA4mSXFqBOVIi+nHxvvEDi5IEdisEVYvKYv7xeAklsPRKmeQ0PsbO16z2fq4iXhus6Wn9Kcf/I4sz/AsF0puTlDczVScPdzRtVRM9lPg68MWFM10WaFmmJ4alNGmn0pDlWD9jsXLk2k=", Base64.DEFAULT);
        this.publicExponentBytes = Base64.decode("AmWHRajWGR+dDASR0BFhm7DKh9wc6DvQtqvNTws/1XIQ6B3w541rA/CEO2i1+Cz8380Pg1utJq+/YyF9gghY6GebPbuknQVi/PLTO/LqvjtuZ9BTcEwgc4YVYXOwq/zgHSTfxTCyIW9yh6L4ymPwuYeVtO71oiChlOseNLXIPzfPyw51pbn4TBlAEkDY+ppNy2F+x6YBEe37V315BrZ3u6Xq0co/Ut9ldZyuIPnsGk80WbCrDqtz2Klx1NgRcLCe+sRYR/5naRNNI+Ut+ilDR4RlGYlGkPluP/o6QSD2EtatJCmpddXt9I2tmneEauRo+AF/SYHjoPUJQm1IKFz24Q==", Base64.DEFAULT);

        this.publicModulusBigInteger = new BigInteger(1, this.publicodulusBytes);
        this.publicExponentBigInteger = new BigInteger(1, this.publicExponentBytes);

        //Private Key
        this.privateKeySpec = new RSAPrivateKeySpec(publicModulusBigInteger, publicExponentBigInteger);
        this.privateKey = keyFactory.generatePrivate(privateKeySpec);

        //Public Key
        this.publicKeySpec = new RSAPublicKeySpec(privateModulusBigInteger, privateExponentBigInteger);
        this.publicKey = keyFactory.generatePublic(publicKeySpec);

    }

    public String encrypt(String plainText) throws InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] plainBytes = plainText.getBytes("UTF-8");
        byte[] cipherBytes = cipher.doFinal(plainBytes);

        return Base64.encodeToString(cipherBytes, Base64.DEFAULT);
    }

    public String decrypt(String cipherText) throws InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] cipherBytes = Base64.decode(cipherText, Base64.DEFAULT);
        byte[] plainBytes = cipher.doFinal(cipherBytes);

        return new String(plainBytes, "UTF-8");
    }
}
