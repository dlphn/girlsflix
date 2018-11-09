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
	
	public SerieFactory() {
		if (Data.getListSeries() == null) {
			initData();
		}
	}
	
	public SerieFactory(String action) {
		if (action == "update") {
			initData();
		}
	}
	
	/**
	 * Fetch data from MongoDB, create the Series/Seasons/Episodes objects and 
	 * save in Data's seriesList or update the object's attribute if it already exists in Data
	 */
	public void initData() {
		SerieDB.connect();
        
        JSONParser parser = new JSONParser();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        List<Serie> serieList = new ArrayList<Serie>();
        if (Data.getListSeries() != null) {
        	serieList = Data.getListSeries();
        } else {
        	Data.setListSeries(serieList);
        }

        List<Document> documents = SerieDB.find("series");
        
        
    	for (Document doc : documents) {
			try {
				JSONObject jsnObj = (JSONObject) parser.parse(doc.toJson());
				Serie serie = null;
				
				int serieId = jsnObj.get("id") != null ? Integer.parseInt(((JSONObject) jsnObj.get("id")).get("$numberLong").toString()) : null;
				
				String title = jsnObj.get("title") != null ? jsnObj.get("title").toString() : "";
	
				JSONArray genres = (JSONArray) jsnObj.get("serieType");
				List<String> serieType = new ArrayList<String>();
				for (int i = 0; i < genres.size(); i++){
                    serieType.add(genres.get(i).toString());
                }
				
				String summary = jsnObj.get("summary") != null ? jsnObj.get("summary").toString() : "";
				LocalDate creationDate = jsnObj.get("date") != null ? LocalDate.parse((CharSequence) jsnObj.get("date"), formatter) : null;
				String image = jsnObj.get("imageLink") != null ? jsnObj.get("imageLink").toString() : "";
				Double rating = jsnObj.get("rating") != null ? Double.parseDouble(jsnObj.get("rating").toString()) : null;
				
				List<Season> seasons = getSeasons(serieId);
				
				@SuppressWarnings("unchecked")
				Map<String, Boolean> enjoyersToNotify = (Map<String, Boolean>) jsnObj.get("enjoyersToNotify");
				
				int newEpisode = jsnObj.get("newEpisodeNb") != null ? Integer.parseInt(((JSONObject) jsnObj.get("newEpisodeNb")).get("$numberLong").toString()) : 0;
				int newSeason = jsnObj.get("newSeasonNb") != null ? Integer.parseInt(((JSONObject) jsnObj.get("newSeasonNb")).get("$numberLong").toString()) : 0;
				LocalDate newDate = jsnObj.get("newDate") != null ? LocalDate.parse((CharSequence) jsnObj.get("newDate"), formatter) : null;
				
//				try {
//    				newEpisode = jsnObj.get("newEpisodeNb") != null ? Integer.parseInt(((JSONObject) jsnObj.get("newEpisodeNb")).get("$numberLong").toString()) : null;
//    				newSeason = jsnObj.get("newSeasonNb") != null ? Integer.parseInt(((JSONObject) jsnObj.get("newSeasonNb")).get("$numberLong").toString()) : null;
//    				newDate = jsnObj.get("newDate") != null ? LocalDate.parse((CharSequence) jsnObj.get("newDate"), formatter) : null;
// 
//    			}
//    			catch(NullPointerException e) {
//    				newEpisode = 0;
//    				newSeason = 0;
//    				newDate = null;
//    			}
				
				if (Data.getById(serieId) == null) {
					System.out.println("la série n'était pas déjà créée");
					// the object was not created so we create it
	    			serie = new Serie(
	    					serieId,
	    					title,
	    					serieType,
	    					summary,
	    					creationDate,
							image,
	    					rating,
	    					seasons,
	    					enjoyersToNotify
    					);
					serie.setNextEpisodeOnAir(newEpisode);
    				serie.setNbSeasonNEOA(newSeason);
    				serie.setDateNextEpisodeOnAir(newDate);
    				System.out.println(serie.info());
    				serieList.add(serie);
				}
				else { 
					// the object was already created so we take back the object in Data thanks to the Id and we update its attributes
					serie = Data.getById(serieId);
					System.out.println("id de la série : " + serieId);
					serie.updateAllAttributes(title, serieType, summary, creationDate, image, rating, seasons, newEpisode, newSeason, newDate);
					//serieList.add(serie);
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
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
}
