package com.secret.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RSABean {

	private PrivateKey privateKey = null ;
	private PublicKey publicKey = null ;

	public RSABean(PrivateKey privateKey,PublicKey publicKey){
		this.privateKey = privateKey ;
		this.publicKey = publicKey ;
	}
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}
	
//	@Override
//	public String toString(){
//		StringBuffer buffer = new StringBuffer() ;
//		buffer.append("[PUBLIC_KEY=").append(RSA.getInstance().getKey(publicKey)) ;
//		buffer.append(",PRIVATE_KEY=").append(RSA.getInstance().getKey(privateKey)).append("]") ;
//		return buffer.toString() ;
//	}
}
