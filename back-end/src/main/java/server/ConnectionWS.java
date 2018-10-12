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

public class ConnectionWS {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	
	public void connect(String type, String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = null;
		
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line;
			List<String> lines = new ArrayList<String>();
			while ((line = bufReader.readLine()) != null) {
				lines.add(line);
            }
            /*JSONArray jsonArray = new JSONArray(str);
            for (int i = 0; i < jsonArray.length(); i++){
                if (jsonArray.get(i) instanceof JSONObject){
                    JSONObject jsnObj = (JSONObject)jsonArray.get(i);
                    System.out.println(jsnObj);
                }
            }*/
			write(type, lines);
		} finally {
	    	response.close();
		}
	}
	
	private void write(String type, List<String> lines) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String time = sdf.format(timestamp);
		Path file = Paths.get("src/main/resources/" + type + "_" + time + ".json");
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
