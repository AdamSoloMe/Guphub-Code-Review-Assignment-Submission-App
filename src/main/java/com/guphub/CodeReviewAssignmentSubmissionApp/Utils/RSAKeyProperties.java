package com.guphub.CodeReviewAssignmentSubmissionApp.Utils;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class RSAKeyProperties {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
    private RSAPrivateKey refreshPrivateKey; // Add a field for the refresh private key

    public RSAKeyProperties() {
        generateKeys();
    }

    private void generateKeys() {
        KeyPair pair = KeyGeneratorUtility.generateRsaKey();
        this.publicKey = (RSAPublicKey) pair.getPublic();
        this.privateKey = (RSAPrivateKey) pair.getPrivate();

        // Generate a separate key pair for refresh tokens
        KeyPair refreshPair = generateRefreshRsaKey();
        this.refreshPrivateKey = (RSAPrivateKey) refreshPair.getPrivate();
    }

    private KeyPair generateRefreshRsaKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // You can adjust the key size as needed
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate RSA key pair for refresh tokens", e);
        }
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public RSAPrivateKey getRefreshPrivateKey() {
        return refreshPrivateKey;
    }
}