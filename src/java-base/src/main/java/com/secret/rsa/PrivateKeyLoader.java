package com.secret.rsa;

import java.io.InputStream;
import java.security.PrivateKey;

public interface PrivateKeyLoader {
	/**
	 * 加载私钥
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	PrivateKey loadPrivateKey(byte[] bytes) throws Exception;
	/**
	 * 加载私钥
	 * @param base64PrivateKeyStr
	 * @return
	 * @throws Exception
	 */
	PrivateKey loadPrivateKey(String base64PrivateKeyStr) throws Exception;
	/**
	 * 加载私钥
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	PrivateKey loadPrivateKey(InputStream inputStream) throws Exception;
	
	/**
	 * 加载私钥
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	PrivateKey getPrivateKey() ;
}
