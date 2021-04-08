package data;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.gson.Gson;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import Interfaces.PeopleRepositoryInterface;
import models.People;

public class PeopleRepository implements PeopleRepositoryInterface {

	private final String collection = "peoples";

	public List<People> getAll(String idDocument) throws UnknownHostException {
		DBCollection collection = getConnection().getCollection(this.collection);

		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(idDocument));

		DBObject dbObj = collection.findOne(query);

		List<People> peoples = (List<People>) Arrays.asList(new Gson().fromJson((String) dbObj.get("peoples"), People[].class));

		return peoples;
	}

	public People getById(String idDocument, int index) throws UnknownHostException {
		DBCollection collection = getConnection().getCollection(this.collection);

		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(idDocument));

		DBObject dbObj = collection.findOne(query);

		List<People> peoples = Arrays.asList(new Gson().fromJson((String) dbObj.get("peoples"), People[].class));

		return peoples.get(index);
	}

	public String add(List<People> peoples) throws UnknownHostException {
		DBCollection collection = getConnection().getCollection(this.collection);

		Gson gson = new Gson();
		String peoplesJson = gson.toJson(peoples);

		BasicDBObject doc = new BasicDBObject("peoples", peoplesJson);

		collection.insert(doc);

		ObjectId id = (ObjectId) doc.get("_id");

		return id.toString();
	}

	public void edit(List<People> peoples, String idDocument) throws UnknownHostException {
		DBCollection collection = getConnection().getCollection(this.collection);

		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(idDocument));

		Gson gson = new Gson();
		String peoplesJson = gson.toJson(peoples);

		BasicDBObject doc = new BasicDBObject("peoples", peoplesJson);

		collection.update(query, doc);
	}

	public void delete(People people, String idDocument) throws UnknownHostException {
		DBCollection collection = getConnection().getCollection(this.collection);

		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(idDocument));

		collection.remove(query);
	}

	private DB getConnection() throws UnknownHostException {
		return new MongoConnection().getConnection(null);
	}
}
