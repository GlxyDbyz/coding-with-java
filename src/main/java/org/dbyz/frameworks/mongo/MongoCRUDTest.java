package org.dbyz.frameworks.mongo;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 增删改查操作
 *
 * @ClassName: MongoCRUDTest
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class MongoCRUDTest {
	/**
	 * 连接的客户端
	 */
	private static MongoClient client;
	/**
	 * 数据库
	 */
	private static MongoDatabase dataBase;
	/**
	 * 集合(表)
	 */
	private static MongoCollection<Document> books;

	@BeforeClass
	public static void init() {
		client = new MongoClient("localhost", 27017);
		dataBase = client.getDatabase("testDb");
		books = dataBase.getCollection("books");
	}

	@AfterClass
	public static void destory() {
		client.close();
	}

	/**
	 * 增加
	 */
	@Test
	public void test1Add() {
		for (int i = 0; i < 100; i++) {
			Document book = new Document();
			book.append("name", "Java inaction" + i);
			book.append("pricve", "$" + Math.random() * 100 * i);
			books.insertOne(book);
		}

		System.out.println(books.count());
	}

	/**
	 * 查找
	 * 
	 * @Title: test2Find
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test2Find() {
		Bson filter = new BasicDBObject("name", "Java inaction10");
		System.out.println(books.find(filter).first());
	}

	/**
	 * 修改
	 * 
	 * @Title: test3Update
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test3Update() {
		Bson filter = new BasicDBObject("name", "Java inaction50");
		System.out.println(books.find(filter).first());

		Document update = new Document();
		update.append("name", "Java inaction50");
		update.append("pricve", "$" + 1000);
		// 查找并替换
		books.findOneAndReplace(filter, update);

		System.out.println(books.find(filter).first());
	}

	/**
	 * 删除
	 * 
	 * @Title: test4Del
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test4Del() {
		Bson filter = new BasicDBObject("name", "Java inaction50");
		System.out.println(books.find(filter).first());

		books.deleteOne(filter);

		System.out.println(books.find(filter).first());
	}

}
