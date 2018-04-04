package com.secret.rsa;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import com.base64.Base64;

public class PKCS8PrivateKeyLoader extends AbstractPrivateKeyLoader implements PrivateKeyLoader {
	
	public PKCS8PrivateKeyLoader(byte[] keyBytes) throws Exception {
		super(keyBytes);
	}
	
	public PKCS8PrivateKeyLoader(String base64PrivateKeyStr) throws Exception{
		super(base64PrivateKeyStr);
	}
	
	public PKCS8PrivateKeyLoader(InputStream inputStream) throws Exception{
		super(inputStream);
	}

	@Override
	public PrivateKey loadPrivateKey(byte[] keyBytes)  throws Exception{
		try {
        	return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(keyBytes)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public PrivateKey loadPrivateKey(String base64PrivateKeyStr)  throws Exception{
		return loadPrivateKey(Base64.decode(base64PrivateKeyStr));
	}

	@Override
	public PrivateKey loadPrivateKey(InputStream inputStream) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(inputStream));  
        String readLine = "" ;  
        StringBuilder sb = new StringBuilder();  
        while((readLine = br.readLine()) != null){  
            if(readLine.charAt(0) == '-'){ 
                continue;  
            } else {  
                sb.append(readLine);  
                sb.append('\r');  
            }  
        }
        return loadPrivateKey(sb.toString().getBytes()) ;
	}
	
	public static void main(String[] args) throws Exception {
		
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("ifsp.pfx") ;
		System.out.println(new PKCS8PrivateKeyLoader(inputStream).getPrivateKey()) ;
	}

}
