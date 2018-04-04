package com.secret.rsa;

import java.io.InputStream;
import java.security.PublicKey;

public interface PublicKeyLoader {
	/**
	 * 加载私钥
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	PublicKey loadPublicKey(byte[] bytes) throws Exception;
	/**
	 * 加载私钥
	 * @param base64PrivateKeyStr
	 * @return
	 * @throws Exception
	 */
	PublicKey loadPublicKey(String base64PrivateKeyStr) throws Exception;
	/**
	 * 加载私钥
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	PublicKey loadPublicKey(InputStream inputStream) throws Exception;
	
	/**
	 * 加载私钥
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	PublicKey getPublicKey() ;
}
