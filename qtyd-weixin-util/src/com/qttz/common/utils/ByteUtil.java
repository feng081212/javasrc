package com.qttz.common.utils;

public class ByteUtil {
	public static ByteUtil getInstance(){
		return new ByteUtil() ;
	}
	
	/**
	 * java字节码转字符串
	 * 
	 * @param byte
	 * @return
	 */
	public String ByteToHexString(byte b){
		int headByte = ((int) b >>> 4) & 0xf;
        int endByte = ((int) b ) & 0xf;
        String headString = Integer.toHexString(headByte); 
        String endString = Integer.toHexString(endByte);
        return headString +  endString ;
	}
	
	/**
	 * java字节码转字符串
	 * 
	 * @param byte[]
	 * @return
	 */
	public String ByteToHexString(byte[] b){
		if(b == null )
			return "" ;
		StringBuffer buffer = new StringBuffer() ;
		for(int i = 0 ;i < b.length ;i++){
			buffer.append(ByteToHexString(b[i])) ;
		}
		return buffer.toString() ;
	}
	
	/**
     * 字符串转java字节码
     * @param String
     * @return
     */
    public byte[] HexString2Byte(String hexString) {
    	char[] chars = hexString.toCharArray() ;
    	if ((chars.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b = new byte[chars.length/2] ;
        for (int n = 0; n < chars.length - 1; n += 2) {
           int head = Integer.parseInt(String.valueOf(chars[n]) , 16) ;
           int end = Integer.parseInt(String.valueOf(chars[n+1]) , 16) ;
           byte headByte = (byte) (head << 4 & 0xf0 ) ;
           byte endByte = (byte) (end & 0x0f) ;
           byte bb = (byte) (headByte | endByte) ;
           b[n/2] = bb ;
        }
        return b;
    }
	
	/**
     * 字符串转java字节码
     * @param byte[]
     * @return
     */
    public byte[] HexString2Byte(byte[] chars) {
        return HexString2Byte(new String(chars));
    }
	
	 /**
     * 字符串转java字节码
     * @param b
     * @return
     */
    public byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length-1; n += 2) {
            String item = new String(b, n, 2);
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节 
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        b = null;
        return b2;
    }
    
    public static void main(String[] args) {
    	ByteUtil bu = ByteUtil.getInstance() ;
    	String hexString = bu.ByteToHexString("hello world !".getBytes()) ;
    	System.out.println(hexString);
    	byte[] b = bu.HexString2Byte(hexString) ;
    	System.out.println(new String(b));
    	System.out.println(new String(bu.hex2byte(hexString.getBytes())));
	}
}
