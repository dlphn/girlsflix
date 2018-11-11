package com.gfx.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.gfx.domain.series.Data;
import com.gfx.domain.series.Genre;
import com.gfx.domain.series.SeasonPair;
import com.gfx.domain.series.SeasonResult;
import com.gfx.domain.series.Serie;
import com.gfx.Config;
import com.gfx.Keys;

/**
 * API calls to tmdb to get series information
 * Save information in MongoDB
 */
@Service
public class SerieService {
	
	public SerieService() {
		if (Genre.getGenres() == null) {
			initGenres();
		}
	}
	
	/**
	 * Initiate/update the series genres.
	 */
	public void initGenres() {
		ConnectionWS connection = new ConnectionWS();
		try {
			String url = Config.apiUrl + "genre/tv/list?api_key=" + Keys.apiKey + "&language=" + Config.lang;
			String response = connection.connect(url);
			List<JSONObject> genres = buildGenresList(response);
			Genre.setGenres(genres);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Genres all set!");
		}
	}
	
	private List<JSONObject> buildGenresList(String genres) {
		JSONParser parser = new JSONParser();
		List<JSONObject> documents = new ArrayList<JSONObject>();
		try {
			JSONObject o = (JSONObject) parser.parse(genres);
            JSONArray results = (JSONArray) o.get("genres");
            for (int i = 0; i < results.size(); i++){
                if (results.get(i) instanceof JSONObject){
                    JSONObject jsnObj = (JSONObject)results.get(i);
                    documents.add(jsnObj);
                }
            }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documents;
	}

	/**
	 * Initiate/update the database collections: series, seasons and episodes.
	 */
	public void init() {
		ConnectionWS connection = new ConnectionWS();
		try {
			// GET popular series
			String url = Config.apiFull + "popular?api_key=" + Keys.apiKey + "&language=" + Config.lang + "&page=1";
			String response = connection.connect(url);
			
			// Series and Seasons
			List<Integer> seriesIds = buildSeriesIdsList(response);
			SeasonResult seriesAndSeasons = getSeriesSeasonsDetails(seriesIds);
			List<Document> seriesDocs = seriesAndSeasons.getSeriesDocs();
			List<Document> seasonsDocs = seriesAndSeasons.getSeasonsDocs();
			List<SeasonPair> seasonsIds = seriesAndSeasons.getSeasons();
			add("series", seriesDocs);
			System.out.println("Series saved");
			add("seasons", seasonsDocs);
			System.out.println("Seasons saved");
			
			//Episodes
			List<Document> episodes = getSeasonsDetails(seasonsIds);
			add("episodes", episodes);
			System.out.println("Episodes saved");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Database all set!");
		}
	}
	
	/**
	 * Add the documents to the MongoDB collection.
	 * 
	 * @param collection	MongoDB collection where the documents are to be added
	 * @param documents
	 */
	private void add(String collection, List<Document> documents) {
	    SerieDB.connect();
		for (Document doc : documents) {
			SerieDB.upsert(collection, doc);
		}
	}
	
	/**
	 * Build the list of Series Ids from the response received from the API.
	 * 
	 * @param series	the String result from the API
	 * @return 			the ids of the series retrieved
	 */
	private List<Integer> buildSeriesIdsList(String series) {
		JSONParser parser = new JSONParser();
		List<Integer> seriesIds = new ArrayList<Integer>();
		try {
			JSONObject o = (JSONObject) parser.parse(series);
            JSONArray results = (JSONArray) o.get("results");
            for (int i = 0; i < results.size(); i++){
                if (results.get(i) instanceof JSONObject){
                    JSONObject jsnObj = (JSONObject)results.get(i);
                    seriesIds.add((int) (long) (Long) jsnObj.get("id"));
                }
            }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seriesIds;
	}
	
	/**
	 * Fetch the series' details to get info and seasons of the series retrieved.
	 * 
	 * @param seriesList	Series ids
	 * @return 				the Serie and Season Documents to be added and the seasons/series ids
	 */
	private SeasonResult getSeriesSeasonsDetails(List<Integer> seriesList) {
		ConnectionWS connection = new ConnectionWS();
		List<Document> series = new ArrayList<Document>();
		List<Document> seasons = new ArrayList<Document>();
		List<SeasonPair> seasonsSeries = new ArrayList<SeasonPair>();
		for (int id : seriesList) {
			try {
				String response = connection.connect(Config.apiFull + id + "?api_key=" + Keys.apiKey + "&language=" + Config.lang);
				SeasonResult res = buildSeriesSeasonsList(response, id);
				series.addAll(res.getSeriesDocs());
				seasons.addAll(res.getSeasonsDocs());
				seasonsSeries.addAll(res.getSeasons());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SeasonResult result = new SeasonResult(series, seasons, seasonsSeries);
		return result;
	}
	
	/**
	 * Build the list of Series & Seasons Documents from the response received from the API.
	 * 
	 * @param seasons
	 * @param serieId
	 * @return the Series and Seasons from the specified series to be added and the seasons/series ids
	 */
	private SeasonResult buildSeriesSeasonsList(String seriesInfo, int serieId) {
		JSONParser parser = new JSONParser();
        JSONObject details;
		List<Document> seriesDocs = new ArrayList<Document>();
		List<Document> seasonsDocs = new ArrayList<Document>();
		List<SeasonPair> seasonsIds = new ArrayList<SeasonPair>();
		try {
			details = (JSONObject) parser.parse(seriesInfo);
			JSONObject nextEpisode = (JSONObject) details.get("next_episode_to_air");
			JSONArray genres = (JSONArray) details.get("genres");
			List<String> genresNames = new ArrayList<String>();
			for (int i = 0; i < genres.size(); i++) {
				JSONObject genre = (JSONObject) genres.get(i);
				genresNames.add((String) genre.get("name"));
			}
			Document serieDoc = new Document("id", details.get("id"))
            		.append("title", details.get("name"))
            		.append("summary", details.get("overview"))
            		.append("serieType", genresNames)
            		.append("rating", details.get("vote_average"))
            		.append("date", details.get("first_air_date"))
            		.append("imageLink", details.get("poster_path"));
			if (nextEpisode != null) {
				serieDoc
					.append("newDate", nextEpisode.get("air_date"))
	        		.append("newSeasonNb", nextEpisode.get("season_number"))
	        		.append("newEpisodeNb", nextEpisode.get("episode_number"));
			}
			seriesDocs.add(serieDoc);
            JSONArray results = (JSONArray) details.get("seasons");
            for (int i = 0; i < results.size(); i++){
                if (results.get(i) instanceof JSONObject){
                    JSONObject jsnObj = (JSONObject)results.get(i);
                    SeasonPair seasonSerie = new SeasonPair(serieId, (int) (long) (Long) jsnObj.get("season_number"));
                    seasonsIds.add(seasonSerie);
                    Document seasonDoc = new Document("id", jsnObj.get("id"))
                    		.append("name", jsnObj.get("name"))
                    		.append("summary", jsnObj.get("overview"))
                    		.append("nb", jsnObj.get("season_number"))
                    		.append("episodeCount", jsnObj.get("episode_count"))
                    		.append("imageLink", jsnObj.get("poster_path"))
                    		.append("date", jsnObj.get("air_date"))
                    		.append("serieId", serieId);
                    seasonsDocs.add(seasonDoc);
                }
            }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SeasonResult result = new SeasonResult(seriesDocs, seasonsDocs, seasonsIds);
		return result;
	}
	
	/**
	 * Fetch the seasons' details to get each added seasons' episodes.
	 * 
	 * @param seasonsList	Seasons ids
	 * @return 				the Documents to be added
	 */
	private List<Document> getSeasonsDetails(List<SeasonPair> seasonsList) {
		ConnectionWS connection = new ConnectionWS();
		List<Document> seasons = new ArrayList<Document>();
		for (SeasonPair season : seasonsList) {
			try {
				String response = connection.connect(Config.apiFull + season.getTvId() + "/season/" + season.getSeasonNumber() + "?api_key=" + Keys.apiKey + "&language=" + Config.lang);
				List<Document> res = buildEpisodesList(response, season);
				seasons.addAll(res);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return seasons;
	}
	
	/**
	 * Build the list of Episodes Documents from the response received from the API.
	 * 
	 * @param episodes
	 * @param season
	 * @return the Episodes from the specified seasons to be added
	 */
	private List<Document> buildEpisodesList(String episodes, SeasonPair season) {
		JSONParser parser = new JSONParser();
        JSONObject details;
		List<Document> documents = new ArrayList<Document>();
		try {
			details = (JSONObject) parser.parse(episodes);
            JSONArray results = (JSONArray) details.get("episodes");
            for (int i = 0; i < results.size(); i++){
                if (results.get(i) instanceof JSONObject){
                    JSONObject jsnObj = (JSONObject)results.get(i);
                    Document doc = new Document("id", jsnObj.get("id"))
                    		.append("name", jsnObj.get("name"))
                    		.append("summary", jsnObj.get("overview"))
                    		.append("nb", jsnObj.get("episode_number"))
                    		.append("seasonNb", jsnObj.get("season_number"))
                    		.append("rating", jsnObj.get("vote_average"))
                    		.append("date", jsnObj.get("air_date"))
                    		.append("imageLink", jsnObj.get("still_path"))
                    		.append("serieId", season.getTvId());
                    documents.add(doc);
                }
            }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documents;
	}
	
	
	//TODO Move to another file
	/**
	 * this method is called by the Scheduler on a regular basis.
	 * for each Serie, launch a Thread if it has a date for the next episode on air not null
	 */
	
	public synchronized static void launchGlobalNotificationProcess() {
		List<Serie> listSerie = Data.getListSeries();
		for (Serie s : listSerie) {
			if (s.getDateNextEpisodeOnAir() != null) {
				Thread thread = new Thread(new ThrowNotificationProcess(s));
				thread.start();
			}
		}
	}
	
	
}
