package data;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoConnection {

	public DB getConnection(String dbName) throws UnknownHostException {

		if (dbName == null)
			dbName = "db";

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		DB db = mongoClient.getDB(dbName);
		
		return db;
	}
}
