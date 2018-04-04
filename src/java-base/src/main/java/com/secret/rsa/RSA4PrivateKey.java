package com.secret.rsa;

import java.security.PrivateKey;
import java.security.Signature;

public class RSA4PrivateKey {

	public RSA4PrivateKey(PrivateKeyLoader privateKeyLoader){
		this.privateKeyLoader = privateKeyLoader ;
		this.rsaSigntureAlgorithmEnum = RSASigntureAlgorithmEnum.MD5withRSA ;
	}
	
	public RSA4PrivateKey(PrivateKeyLoader privateKeyLoader,RSASigntureAlgorithmEnum rsaSigntureAlgorithmEnum){
		this.privateKeyLoader = privateKeyLoader ;
		this.rsaSigntureAlgorithmEnum = rsaSigntureAlgorithmEnum ;
	}
	
	private PrivateKeyLoader privateKeyLoader = null ;
	private RSASigntureAlgorithmEnum rsaSigntureAlgorithmEnum = null ;
	
	
	public byte[] sign(byte[] values) throws Exception {
		PrivateKey privateKey = privateKeyLoader.getPrivateKey() ;
        Signature signature = Signature.getInstance(rsaSigntureAlgorithmEnum.VALUE);
        signature.initSign(privateKey);
        signature.update(values);
        return signature.sign() ;
	}
	
	
}
