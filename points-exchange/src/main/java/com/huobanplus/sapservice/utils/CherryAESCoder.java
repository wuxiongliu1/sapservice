package com.huobanplus.sapservice.utils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * AES对称加密算法
 * @author shenzhigang
 */
public abstract class CherryAESCoder {
	 // 密钥算法
	 public static final String KEY_ALGORITHM = "AES";
	 
	 //加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,在128位--16字节下兼容C#的PKCS7Padding
	 public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	 
	 /**
	  * 生成密钥
	  */
	 public static String initkey() throws Exception{
		 KeyGenerator kg = null;  
		 try {  
			 kg = KeyGenerator.getInstance(KEY_ALGORITHM); //实例化密钥生成器
	     } catch (NoSuchAlgorithmException e) {  
	         e.printStackTrace();  
	         return null;  
	     }  
		  kg.init(128);                                              //初始化密钥生成器:AES要求密钥长度为128,192,256位
		  SecretKey secretKey = kg.generateKey();                    //生成密钥
		  return encodeBase64StringChunked(secretKey.getEncoded());  //获取二进制密钥编码形式
	 }
	 
	 
	 /**
	  * 转换密钥
	  */
	 public static Key toKey(byte[] key) throws Exception{
	  return new SecretKeySpec(key, KEY_ALGORITHM);
	 }
	 
	 
	 /**
	  * 加密数据
	  * @param data 待加密数据
	  * @param key  密钥
	  * @return 加密后的数据
	  * */
	 public static String encrypt(String data, String key) throws Exception{
	  Key k = toKey(Base64.decodeBase64(key));                           //还原密钥
	  //使用PKCS7Padding填充方式,这里就得这么写了(即调用BouncyCastle组件实现)
	  Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
	 // Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);              //实例化Cipher对象，它用于完成实际的加密操作
	  cipher.init(Cipher.ENCRYPT_MODE, k);                               //初始化Cipher对象，设置为加密模式
	  //return encodeBase64StringChunked(cipher.doFinal(data.getBytes(Charset.forName("UTF-8")))); //执行加密操作。加密后的结果通常都会用Base64编码进行传输
	  return encodeBase64StringChunked(cipher.doFinal(data.getBytes(Charset.forName("UTF-8"))));
	 }
	 
	 
	 /**
	  * 解密数据
	  * @param data 待解密数据
	  * @param key  密钥
	  * @return 解密后的数据
	  * */
	 public static String decrypt(String data, String key) throws Exception{
	  Key k = toKey(Base64.decodeBase64(key));
	  Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
	  cipher.init(Cipher.DECRYPT_MODE, k);                          //初始化Cipher对象，设置为解密模式
	  return new String(cipher.doFinal(Base64.decodeBase64(data)),"UTF-8"); //执行解密操作
	 }
	 
	 public static String encodeBase64StringChunked(byte binaryData[])
	 {
	     //return StringUtils.newStringUtf8(Base64.encodeBase64(binaryData, true));
	     return StringUtils.newStringUtf8(Base64.encodeBase64(binaryData));
	 }
	 
	 public static void main(String[] args) throws Exception {
	 String source = "Base64是一种基于64个可打印字符来表示二进制数据的表示方法。由于2的6次方等于64，所以每6个位元为一个单元，对应某个可打印字符。三个字节有24个位元，对应于4个Base64单元，即3个字节需要用4个可打印字符来表示。它可用来作为电子邮件的传输编码。在Base64中的可打印字符包括字母A-Z、a-z、数字0-9 ，这样共有62个字符，此外两个可打印符号在不同的系统中而不同。" + 
	"一些如uuencode的其他编码方法，和之后binhex的版本使用不同的64字符集来代表6个二进制数字，但是它们不叫Base64。" +
	 "Base64常用于在通常处理文本数据的场合，表示、传输、存储一些二进制数据。包括MIME的email，email via MIME, 在XML中存储复杂数据";
//		 String source = "Encodes the given data with base64." +
//		 "This encoding is designed to make binary data survive transport through transport layers that are not 8-bit clean, such as mail bodies." +
//		 "Base64-encoded data takes about 33% more space than the original data.";
//	  System.out.println("原文"+"(" + source.length()+ "):" + source);
//	  
//	  String key = initkey();
//	  System.out.println("密钥：" + key);
//
//	  String encryptData = encrypt(source, key);
//	  System.out.println(  "加密"+"(" + encryptData.length()+ "):" + encryptData);

//	  String decryptData = decrypt(encryptData, key);
//	  System.out.println(  "解密"+"(" + decryptData.length()+ "):" + decryptData);
	  
//	  String source1= "5HQ+IYPd/xFHHPZ4LcizLjvaAp28JpU4TxiGWHzXkRkxE87KQWDxqvyABRanpbA06N6ROEca04+gz738g9bg+0RbMVUlZ0Uwd3aeeKvX8zo=";
//	  System.out.println(source1.length());
	  String decryptData1 = encrypt(source, "KwBHRgqFEygN1VZC2TR7Qw==");
	  
	  System.out.println(decryptData1);
	 // System.out.println(Base64.encodeBase64String(source.getBytes()));
	  
	  
	 // System.out.println(  "解密"+"(" + decryptData1.length()+ "):" + decryptData1);
	 }
 
}

