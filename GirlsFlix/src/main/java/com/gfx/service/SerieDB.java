package com.gfx.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.gfx.Keys;
import com.gfx.domain.series.Serie;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Filters;

public class SerieDB {
	private static MongoClient mongoClient;
	private static MongoDatabase database;
	
	
	public static void connect() {
		if (mongoClient == null) {
		    mongoClient = MongoClients.create("mongodb+srv://" + Keys.mongoUser + ":" + Keys.mongoPwd + "@" + Keys.mongoHost + "/");
		}
		MongoDatabase db = mongoClient.getDatabase(Keys.mongoDb);
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
	
	public static void upsert(String col, Document doc) {
		MongoCollection<Document> collection = database.getCollection(col);
		Document newDocument = new Document();
		newDocument.append("$set", doc);
		UpdateOptions options = new UpdateOptions().upsert(true);
		collection.updateOne(Filters.eq("id", doc.get("id")), newDocument, options);
	}
	
	/**
	 * Update the attribute enjoyersToNotify in the mongo database
	 * 
	 * @param serie
	 */
	public static void updateEnjoyers(Serie serie) {
		MongoCollection<Document> collection = database.getCollection("series");
		Document newDocument = new Document();
		newDocument.append("$set", new Document().append("enjoyersToNotify", serie.getEnjoyersToNotify()));
		UpdateOptions options = new UpdateOptions().upsert(true);
		collection.updateOne(
			Filters.eq("id", serie.getId()), 
			newDocument, 
			options
		);
	}
}
