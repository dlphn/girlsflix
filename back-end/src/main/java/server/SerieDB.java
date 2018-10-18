package server;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Filters;

public class SerieDB {
	private MongoDatabase database;
	
	public void connect() {
		Keys keys = new Keys();
	    MongoClient mongoClient = MongoClients.create("mongodb+srv://" + keys.getMongoUser() + ":" + keys.getMongoPwd() + "@" + keys.getMongoHost() + "/");
	    MongoDatabase database = mongoClient.getDatabase(keys.getMongoDb());
	    this.database = database;
	}
	
	public void insertOne(String col, Document doc) {
	    MongoCollection<Document> collection = this.database.getCollection(col);
	    collection.insertOne(doc);	
	}
	
	public void insertMany(String col, List<Document> documents) {
		MongoCollection<Document> collection = this.database.getCollection(col);
	    collection.insertMany(documents);	
	}
	
	public void upsert(String col, Document doc) {
		MongoCollection<Document> collection = this.database.getCollection(col);
		UpdateOptions options = new UpdateOptions().upsert(true);
		collection.replaceOne(Filters.eq("id", doc.get("id")), doc, options);
		/*collection.updateOne(
                eq("_id", new ObjectId("57506d62f57802807471dd41")),
                combine(set("stars", 1), set("contact.phone", "228-555-9999"), currentDate("lastModified")));*/
	}
}
