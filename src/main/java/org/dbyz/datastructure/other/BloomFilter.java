package org.dbyz.datastructure.other;

import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 布隆过滤器
 * Created by lemon on 2014/6/28.
 */
public class BloomFilter {
    private BitSet bits;
    private int size;
    private AtomicInteger realSize = new AtomicInteger(0);
    private int addedElements;
    private int hashFunctionNumber;

    /**
     * 构造一个布隆过滤器，过滤器的容量为maximumNum * hashNum 个bit.
     *
     * @param maximumNum    当前过滤器预先开辟的最大包含记录,通常要比预计存入的记录多一倍.
     * @param expectedNum   当前过滤器预计所要包含的记录.
     * @param hashFunNum    哈希函数的个数，等同每条记录要占用的bit数.
     */
    public BloomFilter(int maximumNum, int expectedNum, int hashFunNum) {
        if (hashFunNum > 8){
        	hashFunNum = 8;
        }
        this.hashFunctionNumber = hashFunNum;
        this.size = maximumNum * hashFunNum;
        this.addedElements = expectedNum;
        this.bits = new BitSet(size);
    }

    /**
     * 写入Bloom过滤器
     *
     * @param str 缓存字符串
     */
    public void put(String str) {
        realSize.incrementAndGet();
        byte[] bytes = str.getBytes();
        int[] positions = createHashes(bytes, hashFunctionNumber);
        for (int position1 : positions) {
            int position = Math.abs(position1 % size);
            bits.set(position, true);
        }
    }

    /**
     * Bloom过滤器是否包含对应的字符串
     *
     * @param   str    字符串对象
     * @return  true   表示包含（不一定正确）
     */
    public boolean contains(String str) {
        byte[] bytes = str.getBytes();
        int[] positions = createHashes(bytes, hashFunctionNumber);
        for (int i : positions) {
            int position = Math.abs(i % size);
            if (!bits.get(position)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 得到当前过滤器的错误率.
     *
     * @return 错误率
     */
    public Double getFalsePositiveProbability() {
        return Math.pow((1 - Math.exp(-hashFunctionNumber * (double) addedElements / size)),
                hashFunctionNumber);
    }

    public int getSize(){
        return size;
    }

    public int getRealSize(){
        return realSize.get();
    }

    private int[] createHashes(byte[] bytes, int hashFunctionNumber) {
        int[] result = new int[hashFunctionNumber];
        for (int i = 0; i < hashFunctionNumber; i++) {
            result[i] = HashFunctions.hash(bytes, i);
        }
        return result;
    }

    /**
     * 八种哈希算法计算哈希值
     */
    static class HashFunctions {
        static int hash(byte[] bytes, int index) {
            switch (index) {
                case 0:
                    return RSHash(bytes);
                case 1:
                    return JSHash(bytes);
                case 2:
                    return ELFHash(bytes);
                case 3:
                    return BKDRHash(bytes);
                case 4:
                    return APHash(bytes);
                case 5:
                    return DJBHash(bytes);
                case 6:
                    return SDBMHash(bytes);
                case 7:
                    return PJWHash(bytes);
            }
            throw new IllegalArgumentException("Invalid index: " + index);
        }

        static int RSHash(byte[] bytes) {
            int hash = 0;
            int magic = 63689;
            for (byte b : bytes) {
                hash = hash * magic + b;
                magic = magic * 378551;
            }
            return hash;
        }

        static int JSHash(byte[] bytes) {
            int hash = 1315423911;
            for (byte b : bytes) {
                hash ^= ((hash << 5) + b + (hash >> 2));
            }
            return hash;
        }

        static int ELFHash(byte[] bytes) {
            int hash = 0;
            int x;
            for (byte b : bytes) {
                hash = (hash << 4) + b;
                if ((x = hash & 0xF0000000) != 0) {
                    hash ^= (x >> 24);
                    hash &= ~x;
                }
            }
            return hash;
        }

        static int BKDRHash(byte[] bytes) {
            int seed = 131;
            int hash = 0;
            for (byte b : bytes) {
                hash = (hash * seed) + b;
            }
            return hash;
        }

        static int APHash(byte[] bytes) {
            int hash = 0;
            int len = bytes.length;
            for (int i = 0; i < len; i++) {
                if ((i & 1) == 0) {
                    hash ^= ((hash << 7) ^ bytes[i] ^ (hash >> 3));
                } else {
                    hash ^= (~((hash << 11) ^ bytes[i] ^ (hash >> 5)));
                }
            }
            return hash;
        }

        static int DJBHash(byte[] bytes) {
            int hash = 5381;
            for (byte b : bytes) {
                hash = ((hash << 5) + hash) + b;
            }
            return hash;
        }

        static int SDBMHash(byte[] bytes) {
            int hash = 0;
            for (byte b : bytes) {
                hash = b + (hash << 6) + (hash << 16) - hash;
            }
            return hash;
        }

        static int PJWHash(byte[] bytes) {
            long bitsInUnsignedInt = (4 << 3);
            long threeQuarters = ((bitsInUnsignedInt * 3) >> 2);
            long oneEighth = (bitsInUnsignedInt >> 3);
            long highBits = (long) (0xFFFFFFFF) << (bitsInUnsignedInt - oneEighth);
            int hash = 0;
            long test;
            for (byte b : bytes) {
                hash = (hash << oneEighth) + b;
                if ((test = hash & highBits) != 0) {
                    hash = (int) ((hash ^ (test >> threeQuarters)) & (~highBits));
                }
            }
            return hash;
        }
    }
}
