package server;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConnectionWS {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	
    /** 
     * Connects to an external API, fetches the result and writes a json file out of the response.
     * 
     * @param type	the type of information we are fetching (series, series details, etc.)
     * @param url	the API url
     */
	public void connect(String type, String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = null;
		
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line;
			String str = "";
			List<String> lines = new ArrayList<String>();
			while ((line = bufReader.readLine()) != null) {
				// lines.add(line);
				str += line;
            }
            JSONParser parser = new JSONParser();
            JSONObject o;
			try {
				o = (JSONObject) parser.parse(str);
	            JSONArray results = (JSONArray) o.get("results");
	            JSONArray newResults = new JSONArray();
	            for (int i = 0; i < results.size(); i++){
	                if (results.get(i) instanceof JSONObject){
	                    JSONObject jsnObj = (JSONObject)results.get(i);
	                    JSONObject obj = new JSONObject();
	                    obj.put("title", jsnObj.get("name"));
	                    obj.put("summary", jsnObj.get("overview"));
	                    obj.put("serieType", jsnObj.get("genre_ids"));
	                    obj.put("rating", jsnObj.get("vote_average"));
	                    obj.put("date", jsnObj.get("first_air_date"));
	                    obj.put("imageLink", jsnObj.get("poster_path"));
	                    newResults.add(obj);
	                }
	            }
                lines.add(newResults.toJSONString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			write(type, lines);
		} finally {
	    	response.close();
		}
	}
	
	/** 
     * Writes the given string in a JSON file.
     * 
     * @param type	the type of information we are fetching (series, series details, etc.)
     * @param lines	the content to be written in the file
     */
	private void write(String type, List<String> lines) {
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
	
}
