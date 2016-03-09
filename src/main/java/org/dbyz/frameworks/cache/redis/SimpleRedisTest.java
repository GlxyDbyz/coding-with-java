package org.dbyz.frameworks.cache.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class SimpleRedisTest {
	/**
	 * jedis 实例
	 */
	private static Jedis jedis;

	/**
	 * 初始化jedis
	 * 
	 * @Title: setup
	 * @param     
	 * @return: void
	 * @since V1.0
	 */
	@BeforeClass
	public static void setup() {
		// 连接redis服务器，localhost:6379
		jedis = new Jedis("localhost", 6379);
		// 权限认证
		// jedis.auth("admin");
	}

	/**
	 * jedis存储字符串
	 * 
	 * @Title: test1String
	 * @param     
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test1String() {
		// 简单存储字符串
		jedis.set("name", "zlf");// key是name value是zlf
		System.out.println(jedis.get("name"));
		
		// 用append可以连接字符串
		jedis.append("name", " is my lover"); // 拼接
		System.out.println(jedis.get("name"));

		jedis.del("name"); // 删除某个键
		System.out.println(jedis.get("name"));
		
		// 同时存储多个键值对
		jedis.mset("name", "zlf", "age", "24", "qq", "123456");
		jedis.incr("age"); // 对存储的值进行加1操作
		System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
	}

	/**
	 * jedis操作Map
	 * 
	 * @Title: test2Map
	 * @param     
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test2Map() {
		// 存储map
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "zlf");
		map.put("age", "24");
		map.put("qq", "123456");
		// hmset() 放置数据
		jedis.hmset("lover", map);
		// hmget() 得到数据
		// 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
		List<String> rsmap = jedis.hmget("lover", "name", "age", "qq");
		System.out.println(rsmap);

		// 删除map中的某个键值
		jedis.hdel("lover", "age");
		System.out.println(jedis.hmget("lover", "age")); // 因为删除了，所以返回的是null
		System.out.println(jedis.hlen("lover")); // 返回key为lover的键中存放的值的个数2
		System.out.println(jedis.exists("lover"));// 是否存在key为lover的记录 返回true
		System.out.println(jedis.hkeys("lover"));// 返回map对象中的所有key
		System.out.println(jedis.hvals("lover"));// 返回map对象中的所有value
		// 得到指定key的迭代器
		Iterator<String> iter = jedis.hkeys("lover").iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + ":" + jedis.hmget("lover", key));
		}
	}

	/**
	 * jedis操作List
	 * 
	 * @Title: test3List
	 * @param     
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test3List() {
		// 开始前，先移除所有的内容
		jedis.del("java framework");
		System.out.println(jedis.lrange("java framework", 0, -1));
		// 先向key java framework中存放三条数据(放到头部)
		jedis.lpush("java framework", "spring");
		jedis.lpush("java framework", "struts");
		jedis.lpush("java framework", "hibernate");
		// 再取出所有数据jedis.lrange是按范围取出，
		// 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
		System.out.println(jedis.lrange("java framework", 0, -1));

		jedis.del("java framework");
		// 先向key java framework中存放三条数据(放到尾部)
		jedis.rpush("java framework", "spring");
		jedis.rpush("java framework", "struts");
		jedis.rpush("java framework", "hibernate");
		System.out.println(jedis.lrange("java framework", 0, -1));
	}

	/**
	 * jedis操作Set
	 * 
	 * @Title: test4Set
	 * @param     
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test4Set() {
		// 添加
		jedis.sadd("users", "fz");
		jedis.sadd("users", "zlf");
		jedis.sadd("users", "lm");
		jedis.sadd("users", "khy");
		jedis.sadd("users", "who");
		// 移除
		jedis.srem("users", "who");
		System.out.println(jedis.smembers("users"));// 获取所有加入的value
		System.out.println(jedis.sismember("users", "who"));// 判断 who 是否是users集合的元素
		System.out.println(jedis.srandmember("users"));// 随机取出一个值
		System.out.println(jedis.scard("users"));// 返回集合的元素个数
	}

	/**
	 * 排序
	 * 
	 * @Title: test5
	 * @param @throws InterruptedException    
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test5() throws InterruptedException {
		// jedis 排序
		jedis.del("a");// 先清除数据，再加入数据进行测试
		jedis.rpush("a", "1");
		jedis.lpush("a", "6");
		jedis.lpush("a", "3");
		jedis.lpush("a", "9");
		System.out.println(jedis.lrange("a", 0, -1));//  [9, 3, 6, 1] // 从头到尾部
		System.out.println(jedis.sort("a")); 		 //  [1, 3, 6, 9] // 排序后结果
		System.out.println(jedis.lrange("a", 0, -1));//  [9, 3, 6, 1] // 随机
	}

	/**
	 * 测试工具类
	 * 
	 * @Title: test6RedisPool
	 * @param     
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test6RedisPool() {
		RedisUtil.getJedis().set("newname", "中文测试");
		System.out.println(RedisUtil.getJedis().get("newname"));
	}
}