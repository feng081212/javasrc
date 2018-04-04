package com.secret.rsa;

/**
 * 签名算法
 */
public enum RSASigntureAlgorithmEnum {
	
	/**
     * SHA1WithRSA 签名算法
     */
	SHA1WithRSA("SHA1WithRSA"),
	/**
     * MD5withRSA 签名算法
     */
	MD5withRSA("MD5withRSA");
	
	public String VALUE = "" ;
	
	RSASigntureAlgorithmEnum(String value){
		this.VALUE = value ;
	}
}
