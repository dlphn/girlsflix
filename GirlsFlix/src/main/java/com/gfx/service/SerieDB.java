package com.gfx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.Document;
import org.json.simple.JSONObject;

import com.gfx.Keys;
import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Filters;

public class SerieDB {
	private static MongoDatabase database;
	private static MongoClient mongoClient;
	
	
	public static void connect() {
		if (mongoClient == null) {
		    mongoClient = MongoClients.create("mongodb+srv://" + Keys.mongoUser + ":" + Keys.mongoPwd + "@" + Keys.mongoHost + "/");
		}
		MongoDatabase db = mongoClient.getDatabase(Keys.mongoDb);
		database = db;
	}
	
	public static void connect(String dbName) {
		if (mongoClient == null) {
		    mongoClient = MongoClients.create("mongodb+srv://" + Keys.mongoUser + ":" + Keys.mongoPwd + "@" + Keys.mongoHost + "/");
		}
		MongoDatabase db = mongoClient.getDatabase(dbName);
		database = db;
	}
	
	public static List<Document> find(String col) {
		MongoCollection<Document> collection = database.getCollection(col);
		List<Document> result = new ArrayList<Document>();
		collection.find().into(result);
		return result;
	}
	
	public static List<Document> findFiltered(String col, Document filter) {
		MongoCollection<Document> collection = database.getCollection(col);
		List<Document> result = new ArrayList<Document>();
		collection.find(filter).into(result);
		return result;
	}
	
	public static void insertOne(String col, Document doc) {
	    MongoCollection<Document> collection = database.getCollection(col);
	    collection.insertOne(doc);	
	}
	
	public static void insertMany(String col, List<Document> documents) {
		MongoCollection<Document> collection = database.getCollection(col);
	    collection.insertMany(documents);	
	}
	
	@SuppressWarnings("deprecation")
	public static void upsert(String col, Document doc) {
		MongoCollection<Document> collection = database.getCollection(col);
		UpdateOptions options = new UpdateOptions().upsert(true);
		collection.replaceOne(Filters.eq("id", doc.get("id")), doc, options);
		// replaceOne deprecated but updateOne was raising Exception :
		// "java.lang.IllegalArtgumentException : Invalid BSON field name id"
		// and replaceOne was the only working solution
	}
	
	//@SuppressWarnings("unchecked")
	public static void updateEnjoyers(Serie serie) {
		MongoCollection<Document> collection = database.getCollection("series");
		Document newDocument = new Document();
		/*List<JSONObject> enjoyers = new ArrayList<JSONObject>();
		for (Entry<String, Boolean> enjoyer : serie.getEnjoyersToNotify().entrySet()) {
			JSONObject obj = new JSONObject();
			obj.put("user", enjoyer.getKey());
			obj.put("hasBeenNotified", enjoyer.getValue());
			enjoyers.add(obj);
		}*/
		newDocument.append("$set", new Document().append("enjoyersToNotify", serie.getEnjoyersToNotify()));
		UpdateOptions options = new UpdateOptions().upsert(true);
		collection.updateOne(
				Filters.eq("id", serie.getId()), 
				newDocument, 
				options);
	}
}
