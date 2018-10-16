package server;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
			List<String> lines = buildSeriesListJson(response);
			write("series", lines);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
     * Writes the given string in a JSON file.
     * 
     * @param type	the type of information we are fetching (series, series details, etc.)
     * @param lines	the content to be written in the file
     */
	private void write(String type, List<String> lines) {

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
	
	public JSONArray getSeriesDetails(Long id) {
		Keys apiKey = new Keys();
		Config config = new Config();
		ConnectionWS connection = new ConnectionWS();
		JSONParser parser = new JSONParser();
		JSONObject details = new JSONObject();
		try {
			String response = connection.connect(config.getApiUrlFull() + id + "?api_key=" + apiKey.getApiKey() + "&language=" + config.getLang());
			try {
				details = (JSONObject) parser.parse(response);
				return (JSONArray) details.get("seasons");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JSONArray();
	}
}
