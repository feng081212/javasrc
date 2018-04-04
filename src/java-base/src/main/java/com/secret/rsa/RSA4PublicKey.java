package com.secret.rsa;

import java.security.PublicKey;
import java.security.Signature;

public class RSA4PublicKey {

	public RSA4PublicKey(PublicKeyLoader privateKeyLoader){
		this.privateKeyLoader = privateKeyLoader ;
		this.rsaSigntureAlgorithmEnum = RSASigntureAlgorithmEnum.MD5withRSA ;
	}
	
	public RSA4PublicKey(PublicKeyLoader privateKeyLoader,RSASigntureAlgorithmEnum rsaSigntureAlgorithmEnum){
		this.privateKeyLoader = privateKeyLoader ;
		this.rsaSigntureAlgorithmEnum = rsaSigntureAlgorithmEnum ;
	}
	
	private PublicKeyLoader privateKeyLoader = null ;
	private RSASigntureAlgorithmEnum rsaSigntureAlgorithmEnum = null ;
	
	
	/**
     * 验证已签名的字符串
     * @param values 需要签名的字符串
     * @param result 客户签名结果
     * @return 验签结果
	 * @throws Exception 
     */
	public boolean verify(byte[] values, byte[] result) throws Exception {
		PublicKey publicKey = privateKeyLoader.getPublicKey() ;
        Signature signature = Signature.getInstance(rsaSigntureAlgorithmEnum.VALUE);
        signature.initVerify(publicKey);
        signature.update(values);
        return signature.verify(result) ;
	}
}
