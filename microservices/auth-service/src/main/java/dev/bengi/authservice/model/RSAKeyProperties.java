package dev.bengi.authservice.model;

import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;

public record RSAKeyProperties(RSAPublicKey publicKey, PrivateKey privateKey) {

}