package com.secret.rsa;

import java.io.InputStream;
import java.security.PrivateKey;


public abstract class AbstractPrivateKeyLoader implements PrivateKeyLoader {

	protected PrivateKey privateKey = null ;
	
	public AbstractPrivateKeyLoader(byte[] keyBytes) throws Exception{
		privateKey = loadPrivateKey(keyBytes) ;
	}
	
	public AbstractPrivateKeyLoader(String base64PrivateKeyStr) throws Exception{
		privateKey = loadPrivateKey(base64PrivateKeyStr) ;
	}
	
	public AbstractPrivateKeyLoader(InputStream inputStream) throws Exception{
		privateKey = loadPrivateKey(inputStream) ;
	}
	
	@Override
	public PrivateKey getPrivateKey() {
		return privateKey ;
	}
}
