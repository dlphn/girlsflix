package com.gfx.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.gfx.helper.Keys;
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
		Keys keys = new Keys();
		if (mongoClient == null) {
		    mongoClient = MongoClients.create("mongodb+srv://" + keys.getMongoUser() + ":" + keys.getMongoPwd() + "@" + keys.getMongoHost() + "/");
		}
		MongoDatabase db = mongoClient.getDatabase(keys.getMongoDb());
		database = db;
	}
	
	public static void connect(String dbName) {
		Keys keys = new Keys();
		if (mongoClient == null) {
		    mongoClient = MongoClients.create("mongodb+srv://" + keys.getMongoUser() + ":" + keys.getMongoPwd() + "@" + keys.getMongoHost() + "/");
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
	
	public static void insertOne(String col, Document doc) {
	    MongoCollection<Document> collection = database.getCollection(col);
	    collection.insertOne(doc);	
	}
	
	public static void insertMany(String col, List<Document> documents) {
		MongoCollection<Document> collection = database.getCollection(col);
	    collection.insertMany(documents);	
	}
	
	public static void upsert(String col, Document doc) {
		MongoCollection<Document> collection = database.getCollection(col);
		UpdateOptions options = new UpdateOptions().upsert(true);
		collection.replaceOne(Filters.eq("id", doc.get("id")), doc, options);
		/*collection.updateOne(
                eq("_id", new ObjectId("57506d62f57802807471dd41")),
                combine(set("stars", 1), set("contact.phone", "228-555-9999"), currentDate("lastModified")));*/
	}
}
