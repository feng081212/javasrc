package com.number;

public class BinaryHex {

	public final static char[] HEX_ARRAY = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'} ;
	
	public static void main(String[] args) {
		System.out.println(hex2byte('B','B')) ;
		byte[] bytes = hex2byte("BBBBBBBB") ;
		for (int i = 0; i < bytes.length ; i++) {
			System.out.println("22="+bytes[i]) ;
		}
		System.out.println(byte2hex((byte)-69)) ;
		System.out.println(byte2hex(new byte[]{-69,-69,1,1,1})) ;
	}
	
	/**
	 * 16进制转换成二进制
	 * @param hex 16进制字母
	 * @return
	 */
	public static final byte hex2byte(char hex) {
		return Byte.parseByte(hex+"", 16) ;
	}
	
	/**
	 * 16进制转换成二进制
	 * @param hex1 16进制字母
	 * @param hex2 16进制字母
	 * @return
	 */
	public static final byte hex2byte(char hex1,char hex2) {
		return (byte) ((hex2byte(hex1) << 4) | hex2byte(hex2)) ;
	}
	
	/**
	 * 16进制转换成二进制
	 * @param hex 字符串
	 * @return
	 */
    public static final byte[] hex2byte(String hex) {
    	char[] chars = hex.toCharArray() ;
    	if ((chars.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
    	byte[] result = new byte[chars.length / 2];
    	for (int i = 0; i < chars.length ; i = i + 2) {
    		result[i/2] = hex2byte(chars[i], chars[i+1]) ;
		}
    	return result;
    }
    
    /**
	 * 二进制转换成16进制
	 * @param byte 二进制
	 * @return
	 */
	public static final char[] byte2hexChar(byte b) {
		char[] chars = new char[2] ;
		chars[0] = HEX_ARRAY[(b >> 4) & 0x0f] ;
		chars[1] = HEX_ARRAY[b & 0x0f] ;
		return chars ;
	}
	
	/**
	 * 二进制转换成16进制
	 * @param byte 二进制
	 * @return
	 */
	public static final String byte2hex(byte b) {
		char[] chars = byte2hexChar(b) ;
		return chars[0]  + "" + chars[1] ;
	}
    
    /**
	 * 二进制数组转成16进制字符串
	 * @param bytes byte数组
	 * @return 16进制字符串
	 */
	public static final String byte2hex(byte[] bytes) {
		StringBuffer buffer = new StringBuffer() ;
		for (byte b : bytes) {
			buffer.append(byte2hex(b)) ;
		}
		return buffer.toString() ;
	}
}
