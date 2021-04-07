package data;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;

import Interfaces.PeopleRepositoryInterface;
import models.People;

public class PeopleRepository implements PeopleRepositoryInterface {

	private final String collection = "peoples";

	public List<People> getAll() {
		try {
			DBCollection coll;
			coll = GetConnection().getCollection(this.collection);

			DBCursor cursor = coll.find();
			try {
				while (cursor.hasNext()) {
					System.out.println(cursor.next());
				}
			} finally {
				cursor.close();
			}

			return new ArrayList<People>();

		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}

	public People getById() {
		DBCollection coll;
		try {
			coll = GetConnection().getCollection(this.collection);

			BasicDBObject query = new BasicDBObject("i", 71);

			DBCursor cursor = coll.find(query);

			try {
				while (cursor.hasNext()) {
					System.out.println(cursor.next());
				}
			} finally {
				cursor.close();
			}
			return new People();

		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}

	}

	public String add(List<People> peoples) {

		try {
			DBCollection coll;
			coll = GetConnection().getCollection(this.collection);

			Gson gson = new Gson();
			String peoplesJson = gson.toJson(peoples);

			BasicDBObject doc = new BasicDBObject("peoples", peoplesJson);

			WriteResult result = coll.insert(doc);

			ObjectId id = (ObjectId) doc.get("_id");

			return id.toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void edit(People people) {
		try {
			DBCollection coll;
			coll = GetConnection().getCollection(this.collection);

			BasicDBObject query = new BasicDBObject();
			query.put("id", people.getId());

			BasicDBObject updateObject = new BasicDBObject("name", "MongoDB").append("type", "database")
					.append("count", 1).append("info", new BasicDBObject("x", 203).append("y", 102));

			coll.update(query, updateObject);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(People people) {
		try {
			DBCollection coll;
			coll = GetConnection().getCollection(this.collection);

			coll.remove(new BasicDBObject());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private DB GetConnection() throws UnknownHostException {
		return new MongoConnection().GetConnection(null);
	}
}
