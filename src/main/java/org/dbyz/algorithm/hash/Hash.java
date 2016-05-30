package org.dbyz.algorithm.hash;

import java.io.UnsupportedEncodingException;

/**
 * 常用的hash策略和常用的hash算法
 */
public class Hash {
	/** 加法hash */
	public int addHash(String str) {
		int hash = 0;
		if (str != null && str.length() > 0) {
			byte[] bytes;
			try {
				bytes = str.getBytes("UTF-8");
				for (byte b : bytes) {
					hash += Math.abs(b);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return hash;
	}

	/** 乘法hash */
	public int multiHash(String str) {
		int hash = 0;
		if (str != null && str.length() > 0) {
			byte[] bytes;
			try {
				bytes = str.getBytes("UTF-8");
				for (byte b : bytes) {
					hash *= Math.abs(b);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return hash;
	}
	
	/** 位移hash */
	public int displaceHash(String str) {
		int hash = 0;
		if (str != null && str.length() > 0) {
			byte[] bytes;
			try {
				bytes = str.getBytes("UTF-8");
				for (byte b : bytes) {
					hash = hash<<5 ^ Math.abs(b)<<2;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return hash;
	}
	
	/*RS Hash Function */
	public long RSHash(String str) {
		int b = 378551;
		int a = 63689;
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = hash * a + str.charAt(i);
			a = a * b;
		}
		return hash;
	}

	/*JS Hash Function */
	public long JSHash(String str) {
		long hash = 1315423911;

		for (int i = 0; i < str.length(); i++) {
			hash ^= ((hash << 5) + str.charAt(i) + (hash >> 2));
		}

		return hash;
	}


	/*P. J. Weinberger Hash Function */
	public long PJWHash(String str) {
		long BitsInUnsignedInt = (long) (4 * 8);
		long ThreeQuarters = (long) ((BitsInUnsignedInt * 3) / 4);
		long OneEighth = (long) (BitsInUnsignedInt / 8);
		long HighBits = (long) (0xFFFFFFFF) << (BitsInUnsignedInt - OneEighth);
		long hash = 0;
		long test = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = (hash << OneEighth) + str.charAt(i);

			if ((test = hash & HighBits) != 0) {
				hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
			}
		}

		return hash;
	}


	/*ELF Hash Function */
	public long ELFHash(String str) {
		long hash = 0;
		long x = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = (hash << 4) + str.charAt(i);

			if ((x = hash & 0xF0000000L) != 0) {
				hash ^= (x >> 24);
			}
			hash &= ~x;
		}

		return hash;
	}


	/*BKDR Hash Function */
	public long BKDRHash(String str) {
		long seed = 131; // 31 131 1313 13131 131313 etc..
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = (hash * seed) + str.charAt(i);
		}

		return hash;
	}


	/*SDBM Hash Function */
	public long SDBMHash(String str) {
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
		}

		return hash;
	}


	/*DJB Hash Function */
	public long DJBHash(String str) {
		long hash = 5381;

		for (int i = 0; i < str.length(); i++) {
			hash = ((hash << 5) + hash) + str.charAt(i);
		}

		return hash;
	}


	/*DEK Hash Function */
	public long DEKHash(String str) {
		long hash = str.length();

		for (int i = 0; i < str.length(); i++) {
			hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
		}

		return hash;
	}


	/*BP Hash Function */
	public long BPHash(String str) {
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = hash << 7 ^ str.charAt(i);
		}

		return hash;
	}


	/* FNV Hash Function */
	public long FNVHash(String str) {
		long fnv_prime = 0x811C9DC5;
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash *= fnv_prime;
			hash ^= str.charAt(i);
		}

		return hash;
	}


	/* AP Hash Function */
	public long APHash(String str) {
		long hash = 0xAAAAAAAA;

		for (int i = 0; i < str.length(); i++) {
			if ((i & 1) == 0) {
				hash ^= ((hash << 7) ^ str.charAt(i) * (hash >> 3));
			} else {
				hash ^= (~((hash << 11) + str.charAt(i) ^ (hash >> 5)));
			}
		}

		return hash;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		
	}
}