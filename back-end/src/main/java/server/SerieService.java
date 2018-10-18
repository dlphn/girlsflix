package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SerieService {
	public void init() {
		Keys apiKey = new Keys();
		Config config = new Config();
		
		ConnectionWS connection = new ConnectionWS();
		try {
			String url = config.getApiUrlFull() + "popular?api_key=" + apiKey.getApiKey() + "&language=" + config.getLang() + "&page=1";
			String response = connection.connect(url);
			// List<String> lines = buildSeriesListJson(response);
			// write("series", lines);
			SeriesResult result = buildSeriesList(response);
			List<Document> documents = result.getDocuments();
			List<Integer> seriesIds = result.getIds();
			add("series", documents);
			System.out.println("Series saved");
			SeasonResult seasons = getSeriesDetails(seriesIds);
			List<Document> seasonsDocs = seasons.getDocuments();
			List<SeasonPair> seasonsIds = seasons.getSeason();
			add("seasons", seasonsDocs);
			System.out.println("Seasons saved");
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
	
	private void add(String collection, List<Document> documents) {
		SerieDB db = new SerieDB();
	    db.connect();
		for (Document doc : documents) {
			db.upsert(collection, doc);
		}
	}
	
	private SeriesResult buildSeriesList(String series) {
		JSONParser parser = new JSONParser();
        JSONObject o;
		List<Document> documents = new ArrayList<Document>();
		List<Integer> seriesIds = new ArrayList<Integer>();
		try {
			o = (JSONObject) parser.parse(series);
            JSONArray results = (JSONArray) o.get("results");
            for (int i = 0; i < results.size(); i++){
                if (results.get(i) instanceof JSONObject){
                    JSONObject jsnObj = (JSONObject)results.get(i);
                    seriesIds.add((int) (long) (Long) jsnObj.get("id"));
                    Document doc = new Document("id", jsnObj.get("id"))
                    		.append("title", jsnObj.get("name"))
                    		.append("summary", jsnObj.get("overview"))
                    		.append("serieType", jsnObj.get("genre_ids"))
                    		.append("rating", jsnObj.get("vote_average"))
                    		.append("date", jsnObj.get("first_air_date"))
                    		.append("imageLink", jsnObj.get("poster_path"));
                    documents.add(doc);
                }
            }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SeriesResult result = new SeriesResult(documents, seriesIds);
		return result;
	}
	
	private SeasonResult getSeriesDetails(List<Integer> seriesList) {
		Keys apiKey = new Keys();
		Config config = new Config();
		ConnectionWS connection = new ConnectionWS();
		List<Document> seasons = new ArrayList<Document>();
		List<SeasonPair> seasonSerie = new ArrayList<SeasonPair>();
		for (int id : seriesList) {
			try {
				String response = connection.connect(config.getApiUrlFull() + id + "?api_key=" + apiKey.getApiKey() + "&language=" + config.getLang());
				SeasonResult res = buildSeasonsList(response, id);
				seasons.addAll(res.getDocuments());
				seasonSerie.addAll(res.getSeason());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SeasonResult result = new SeasonResult(seasons, seasonSerie);
		return result;
	}
	
	private SeasonResult buildSeasonsList(String seasons, int serieId) {
		JSONParser parser = new JSONParser();
        JSONObject details;
		List<Document> documents = new ArrayList<Document>();
		List<SeasonPair> seasonsIds = new ArrayList<SeasonPair>();
		try {
			details = (JSONObject) parser.parse(seasons);
            JSONArray results = (JSONArray) details.get("seasons");
            for (int i = 0; i < results.size(); i++){
                if (results.get(i) instanceof JSONObject){
                    JSONObject jsnObj = (JSONObject)results.get(i);
                    SeasonPair seasonSerie = new SeasonPair(serieId, (int) (long) (Long) jsnObj.get("season_number"));
                    seasonsIds.add(seasonSerie);
                    // seasonsIds.add(String.valueOf(jsnObj.get("episode_count")));
                    Document doc = new Document("id", jsnObj.get("id"))
                    		.append("name", jsnObj.get("name"))
                    		.append("summary", jsnObj.get("overview"))
                    		.append("nb", jsnObj.get("season_number"))
                    		.append("episodeCount", jsnObj.get("episode_count"))
                    		//.append("rating", jsnObj.get("vote_average"))
                    		.append("date", jsnObj.get("air_date"))
                    		.append("serieId", serieId);
                    documents.add(doc);
                }
            }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SeasonResult result = new SeasonResult(documents, seasonsIds);
		return result;
	}
	
	private List<Document> getSeasonsDetails(List<SeasonPair> seasonsList) {
		Keys apiKey = new Keys();
		Config config = new Config();
		ConnectionWS connection = new ConnectionWS();
		List<Document> seasons = new ArrayList<Document>();
		for (SeasonPair season : seasonsList) {
			try {
				String response = connection.connect(config.getApiUrlFull() + season.getTvId() + "/season/" + season.getSeasonNumber() + "?api_key=" + apiKey.getApiKey() + "&language=" + config.getLang());
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
	
	

	/** 
     * Writes the given string in a JSON file.
     * 
     * @param type	the type of information we are fetching (series, series details, etc.)
     * @param lines	the content to be written in the file
     */
	/*private void write(String type, List<String> lines) {

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String time = sdf.format(timestamp);
		Path file = Paths.get("src/main/resources/" + type + "_" + time + ".json");
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("File created!");
		}
	}
	
	private List<String> buildSeriesListJson(String series) {
		JSONParser parser = new JSONParser();
        JSONObject o;
		List<String> lines = new ArrayList<String>();
		try {
			o = (JSONObject) parser.parse(series);
            JSONArray results = (JSONArray) o.get("results");
            JSONArray newResults = new JSONArray();
            for (int i = 0; i < results.size(); i++){
                if (results.get(i) instanceof JSONObject){
                    JSONObject jsnObj = (JSONObject)results.get(i);
                    JSONObject obj = new JSONObject();
                    obj.put("id", jsnObj.get("id"));
                    obj.put("title", jsnObj.get("name"));
                    obj.put("summary", jsnObj.get("overview"));
                    obj.put("serieType", jsnObj.get("genre_ids"));
                    obj.put("rating", jsnObj.get("vote_average"));
                    obj.put("date", jsnObj.get("first_air_date"));
                    obj.put("imageLink", jsnObj.get("poster_path"));
                    obj.put("seasons", getSeriesDetails((Long)jsnObj.get("id")));
                    newResults.add(obj);
                }
            }
            lines.add(newResults.toJSONString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(lines);
	}
	*/
	
}
