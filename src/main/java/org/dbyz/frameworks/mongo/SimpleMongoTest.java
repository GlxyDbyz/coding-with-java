package org.dbyz.frameworks.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

/**
 * MongoDB 简单测试
 *
 * @ClassName: SimpleMongoTest
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class SimpleMongoTest {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// 1.连接数据库服务器
		Mongo mongo = new Mongo("localhost", 27017);
		for (String dbName : mongo.getDatabaseNames()) {
			System.out.println(dbName);
		}

		// 2.获取(没有就创建)数据库对象
		DB db = mongo.getDB("testDb");
		for (String collectionName : db.getCollectionNames()) {
			System.out.println(collectionName);
		}

		// 3.获取集合对象(表)
		DBCollection conn = db.getCollection("books");
		// 4.查询数据(获取游标)
		DBCursor cursor = conn.find();
		System.out.println("Data count :" + cursor.count());
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		
		// 关闭
		cursor.close();
		mongo.close();
	}
}
