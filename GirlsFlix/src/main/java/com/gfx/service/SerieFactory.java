package com.gfx.service;

import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.gfx.domain.series.Data;
import com.gfx.domain.series.Episode;
import com.gfx.domain.series.Season;
import com.gfx.domain.series.Serie;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class SerieFactory {
	ArrayList<Serie> seriesList =  new ArrayList<Serie>(); // A changer plus tard en HashMap par exemple
	
	public SerieFactory() {
		if (Data.getListSeries() == null) {
			initSeries();
		}
	}
	
	public void initSeries() {
		SerieDB.connect();
        
        JSONParser parser = new JSONParser();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        List<Document> documents = SerieDB.find("series");
        
    	for (Document doc : documents) {
			try {
				JSONObject jsnObj = (JSONObject) parser.parse(doc.toJson());
				
    			JSONArray genres = (JSONArray) jsnObj.get("serieType");
    			List<String> serieType = new ArrayList<String>();
    			for (int i = 0; i < genres.size(); i++){
                    serieType.add(genres.get(i).toString());
                }
    			
    			int serieId = Integer.parseInt(((JSONObject) jsnObj.get("id")).get("$numberLong").toString());
				List<Season> seasons = getSeasons(serieId);
    			
    			Serie serie = new Serie(
    					serieId, //id
    					jsnObj.get("title") != null ? jsnObj.get("title").toString() : "", //title
    					serieType,//serieGenres
    					jsnObj.get("summary") != null ? jsnObj.get("summary").toString() : "", //summary
    					jsnObj.get("date") != null ? LocalDate.parse((CharSequence) jsnObj.get("date"), formatter) : null, //date
						jsnObj.get("imageLink") != null ? jsnObj.get("imageLink").toString() : "", //image
    					seasons);//image
    			seriesList.add(serie);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		Data.setListSeries(seriesList);
	}
	
	public List<Serie> getSeries() {
        SerieDB.connect();
        
        JSONParser parser = new JSONParser();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        List<Document> documents = SerieDB.find("series");
        
    	for (Document doc : documents) {
			try {
				JSONObject jsnObj = (JSONObject) parser.parse(doc.toJson());
				
    			JSONArray genres = (JSONArray) jsnObj.get("serieType");
    			List<String> serieType = new ArrayList<String>();
    			for (int i = 0; i < genres.size(); i++){
                    serieType.add(genres.get(i).toString());
                }
    			
    			int serieId = Integer.parseInt(((JSONObject) jsnObj.get("id")).get("$numberLong").toString());
				List<Season> seasons = getSeasons(serieId);
    			
    			Serie serie = new Serie(
    					serieId, //id
    					jsnObj.get("title") != null ? jsnObj.get("title").toString() : "", //title
    					serieType,//serieGenres
    					jsnObj.get("summary") != null ? jsnObj.get("summary").toString() : "", //summary
    					jsnObj.get("date") != null ? LocalDate.parse((CharSequence) jsnObj.get("date"), formatter) : null, //date
						jsnObj.get("imageLink") != null ? jsnObj.get("imageLink").toString() : "", //image
    					seasons);//image
    			seriesList.add(serie);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return seriesList;
	}
	
	public List<Season> getSeasons(int serieId) {
		SerieDB.connect();
		
        JSONParser parser = new JSONParser();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Season> seasons = new ArrayList<Season>();
        
		Document filter = new Document("serieId", serieId);
		List<Document> documents = SerieDB.findFiltered("seasons", filter);
		
		for (Document doc : documents) {
			try {
				JSONObject jsnObj = (JSONObject) parser.parse(doc.toJson());
				
				int seasonNb = Integer.parseInt(((JSONObject) jsnObj.get("nb")).get("$numberLong").toString());
				List<Episode> episodes = getEpisodes(serieId, seasonNb);
				
				Season season = new Season(
						Integer.parseInt(((JSONObject) jsnObj.get("id")).get("$numberLong").toString()), //seasonId
						seasonNb,  //seasonNb
						jsnObj.get("name") != null ? jsnObj.get("name").toString() : "", //seasonName
						jsnObj.get("summary") != null ? jsnObj.get("summary").toString() : "", //summary
						jsnObj.get("date") != null ? LocalDate.parse((CharSequence) jsnObj.get("date"), formatter) : null, //relaseDate
						serieId, //serieId
						Integer.parseInt(((JSONObject) jsnObj.get("episodeCount")).get("$numberLong").toString()), //episodeCount
						jsnObj.get("imageLink") != null ? jsnObj.get("imageLink").toString() : "", //image
						episodes); //episodes
				seasons.add(season);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return seasons;
	}
	
	public List<Episode> getEpisodes(int serieId, int seasonNb) {
		SerieDB.connect();
		
		JSONParser parser = new JSONParser();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Episode> episodes = new ArrayList<Episode>();
		
		Document filter = new Document("serieId", serieId).append("seasonNb", seasonNb);
		List<Document> documents = SerieDB.findFiltered("episodes", filter);
		
		for (Document doc : documents) {
			try {
				JSONObject jsnObj = (JSONObject) parser.parse(doc.toJson());
				Episode episode = new Episode(
						Integer.parseInt(((JSONObject) jsnObj.get("id")).get("$numberLong").toString()), //episodeId
						Integer.parseInt(((JSONObject) jsnObj.get("nb")).get("$numberLong").toString()),  //episodeNb
						jsnObj.get("name") != null ? jsnObj.get("name").toString() : "", //episodeName
						jsnObj.get("summary") != null ? jsnObj.get("summary").toString() : "", //summary
						jsnObj.get("date") != null ? LocalDate.parse((CharSequence) jsnObj.get("date"), formatter) : null, //relaseDate
						jsnObj.get("rating") != null ? Double.parseDouble(jsnObj.get("rating").toString()) : 0,  //rating
						seasonNb, //seasonNb
						serieId, //serieId
						jsnObj.get("imageLink") != null ? jsnObj.get("imageLink").toString() : ""); //image
				episodes.add(episode);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return episodes;
	}
	
	public List<Serie> getList(){
		return seriesList;
	}
	
	public Serie getById(int id) {
		Serie searched = null;
		for( Serie s : seriesList) {
			if(s.getId() == id) {
				 searched = s;
			}
		}
		return searched;
		
	}
}
