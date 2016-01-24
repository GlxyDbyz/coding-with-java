package org.dbyz.frameworks.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * 增删改查操作
 *
 * @ClassName: MongoCRUDTest
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
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

	/**
	 * 初始化资源
	 * 
	 * @Title: init
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@BeforeClass
	public static void init() {
		client = new MongoClient("localhost", 27017);
		dataBase = client.getDatabase("testDb");
		books = dataBase.getCollection("books");
	}

	/**
	 * 释放资源
	 * 
	 * @Title: destory
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@AfterClass
	public static void destory() {
		client.close();
	}

	/**
	 * 添加1
	 * 
	 * @Title: test1Add1
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test1Add1() {
		for (int i = 0; i < 100; i++) {
			Document book = new Document();
			book.append("name", "Java inaction" + i);
			book.append("pricve", "$" + Math.random() * 100 * i);
			books.insertOne(book);
		}

		System.out.println(books.count());
	}

	/**
	 * 添加2
	 * 
	 * @Title: test1Add2
	 * @param
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void test1Add2() {
		List<Document> documents = new ArrayList<Document>();
		for (int i = 1000; i < 1100; i++) {
			Document element = new Document();
			element.append("name", "Java inaction" + i);
			element.append("pricve", "$" + Math.random() * 100 * i);
			documents.add(element);
		}
		books.insertMany(documents);
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
		// 查找全部
		System.out.println("find all ");
		FindIterable<Document> iterable = books.find();
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

		// 查找指定数据
		System.out.println("find with condition");
		Bson filter = new BasicDBObject("name", "Java inaction10");
		cursor = books.find(filter).iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		
		Document element = new Document();
		element.append("name", "Java inaction change");
		element.append("pricve", "$" + Math.random() * 1000);
		// 查找并删除
		// books.findOneAndDelete(filter);
		// 查找并跟新
		// books.findOneAndReplace(filter, element);
		
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
		// 替换更新
		// books.replaceOne(filter, update);
		
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
		// books.deleteMany(filter);
		
		System.out.println(books.find(filter).first());
	}

}
