package org.dbyz.frameworks.commons.codec;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.jce.provider.JDKMessageDigest.SHA224;
import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * java的数据指纹算法 java实现(commons-codec简化)和 bouncycastle 实现
 *
 * @ClassName: TestDigest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class DigestTest {
	private static String str = "123456";// 待加密字符串

	/**
	 * MD2算法
	 * 
	 * @throws Exception
	 * @since Ver 1.0
	 */
	@Test
	public void testMd2() throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD2");

		byte[] code = md.digest(str.getBytes());
		char[] chars = Hex.encodeHex(code, true);
		System.out.println(new String(chars));

		System.out.println(DigestUtils.md2Hex(str));
	}

	/**
	 * MD4算法 算法 java没有实现（借助第三方（bouncycastle：BC）实现 ）
	 * 
	 * @throws Exception
	 * @since Ver 1.0
	 */
	@Test
	public void testMd4() throws Exception {
		// MessageDigest md = MessageDigest.getInstance("MD4");// java没有实现
		MD4Digest md = new MD4Digest();

		md.update(str.getBytes(), 0, str.getBytes().length);// 设置数据
		byte[] bytes = new byte[md.getDigestSize()];
		md.doFinal(bytes, 0);
		System.out.println(Hex.encodeHexString(bytes));

		// System.out.println(DigestUtils.md4Hex(s));
		// cc其实是对java的实现进行了封装简化了编码所以也没有md4算法
	}

	/**
	 * md5算法
	 * 
	 * @throws Exception
	 * @since Ver 1.0
	 */
	@Test
	public void testmd5() throws Exception {
		MessageDigest md = MessageDigest.getInstance("md5");

		byte[] code = md.digest(str.getBytes());
		char[] chars = Hex.encodeHex(code, true);
		System.out.println(new String(chars));

		System.out.println(DigestUtils.md5Hex(str));

	}

	/**
	 * SHA-1算法
	 * 
	 * @throws Exception
	 * @since Ver 1.0
	 */
	@Test
	public void testSHA1() throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-1");

		byte[] code = md.digest(str.getBytes());
		char[] chars = Hex.encodeHex(code, true);
		System.out.println(new String(chars));

		System.out.println(DigestUtils.sha1Hex(str));
	}

	/**
	 * SHA-224 算法 java没有实现（借助第三方（bouncycastle）实现 ）
	 * 
	 * @throws Exception
	 * @since Ver 1.0
	 */
	@Test
	public void testSHA224() throws Exception {
		MessageDigest md;// = MessageDigest.getInstance("SHA-224");// java 没有实现
		md = new SHA224();// bouncycastle 实现了 sha224算法

		byte[] code = md.digest(str.getBytes());
		code = md.digest(code);
		System.out.println(Hex.encodeHex(code, true));

		// System.out.println(DigestUtils.sha224Hex(s));
		// cc其实是对java的实现进行了封装简化了编码所以也没有sha-224算法
	}

	/**
	 * SHA-256算法
	 * 
	 * @throws Exception
	 * @since Ver 1.0
	 */
	@Test
	public void testSHA256() throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		byte[] code = md.digest(str.getBytes());
		char[] chars = Hex.encodeHex(code, true);
		System.out.println(new String(chars));

		System.out.println(DigestUtils.sha256Hex(str));
	}

	/**
	 * SHA-384算法
	 * 
	 * @throws Exception
	 * @since Ver 1.0
	 */
	@Test
	public void testSHA384() throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-384");

		byte[] code = md.digest(str.getBytes());
		char[] chars = Hex.encodeHex(code, true);
		System.out.println(new String(chars));

		System.out.println(DigestUtils.sha384Hex(str));
	}

	/**
	 * SHA-512算法
	 * 
	 * @throws Exception
	 * @since Ver 1.0
	 */
	@Test
	public void testSHA512() throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-512");

		byte[] code = md.digest(str.getBytes());
		char[] chars = Hex.encodeHex(code, true);
		System.out.println(new String(chars));

		System.out.println(DigestUtils.sha512Hex(str));
	}

	/**
	 * BASE64 编码
	 * 
	 * @throws Exception
	 * @since Ver 1.0
	 */
	@Test
	public void testBASE64ENCODE() throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		BASE64Encoder encoder = new BASE64Encoder();

		byte[] code = md.digest(str.getBytes());
		String s = encoder.encode(code);

		System.out.print("base64:" + s);
	}

	/**
	 * BASE64 编码&解码
	 * 
	 * @throws Exception
	 * @since Ver 1.0
	 */
	@Test
	public void testBASE64DECODE() throws Exception {
		BASE64Encoder encoder = new BASE64Encoder();
		String base64 = encoder.encode("hellohello".getBytes());

		System.out.println(base64);

		BASE64Decoder decoder = new BASE64Decoder();
		System.out.println(new String(decoder.decodeBuffer(base64)));
	}
}
